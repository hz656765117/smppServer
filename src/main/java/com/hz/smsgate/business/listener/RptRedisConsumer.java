package com.hz.smsgate.business.listener;

import com.hz.smsgate.base.constants.SmppServerConstants;
import com.hz.smsgate.base.constants.StaticValue;
import com.hz.smsgate.base.smpp.constants.SmppConstants;
import com.hz.smsgate.base.smpp.pdu.DeliverSm;
import com.hz.smsgate.base.smpp.pdu.Pdu;
import com.hz.smsgate.base.smpp.pojo.Address;
import com.hz.smsgate.base.smpp.pojo.SmppSession;
import com.hz.smsgate.base.smpp.pojo.Tlv;
import com.hz.smsgate.base.smpp.transcoder.DefaultPduTranscoder;
import com.hz.smsgate.base.smpp.transcoder.DefaultPduTranscoderContext;
import com.hz.smsgate.base.smpp.transcoder.PduTranscoder;
import com.hz.smsgate.base.smpp.transcoder.PduTranscoderContext;
import com.hz.smsgate.base.smpp.utils.BufferHelper;
import com.hz.smsgate.base.smpp.utils.DeliveryReceipt;
import com.hz.smsgate.base.utils.PduUtils;
import com.hz.smsgate.base.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.jboss.netty.buffer.ChannelBuffer;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @author huangzhuo
 * @date 2019/7/2 15:53
 */
@Component
public class RptRedisConsumer implements Runnable {
	private static Logger LOGGER = LoggerFactory.getLogger(RptRedisConsumer.class);


	private static PduTranscoderContext context = new DefaultPduTranscoderContext();
	private static PduTranscoder transcoder = new DefaultPduTranscoder(context);

	@Autowired
	public RedisUtil redisUtil;

	public static RptRedisConsumer rptRedisConsumer;

	@PostConstruct
	public void init() {
		rptRedisConsumer = this;
		rptRedisConsumer.redisUtil = this.redisUtil;
	}

	//key为 msgid + 后缀     value 为 运营商的真实msgid
//	public static final Map<String, String> CACHE_MAP = new LinkedHashMap<>();

	@Override
	public void run() {
		DeliverSm deliverSm;

		while (true) {
			try {
				if (rptRedisConsumer.redisUtil != null) {
					Object obj = rptRedisConsumer.redisUtil.rPop(SmppServerConstants.CM_DELIVER_SM);
					if (obj != null) {
						deliverSm = (DeliverSm) obj;
						LOGGER.info("{}-读取到状态报告信息{}", Thread.currentThread().getName(), deliverSm.toString());
						sendDeliverSm(deliverSm);
					} else {
						Thread.sleep(1000);
					}
				} else {
					Thread.sleep(1000);
				}
			} catch (Exception e) {
				LOGGER.error("{}-处理短信状态报告转发异常", Thread.currentThread().getName(), e);
			}

		}
	}

	//获取原通道
	public String getRealChannel(String gwChannel) {
		if (StringUtils.isBlank(gwChannel)) {
			return gwChannel;
		}
		for (Map.Entry<String, String> entry : StaticValue.CHANNL_REL.entrySet()) {
			if (gwChannel.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		return gwChannel;
	}

	/**
	 * @param deliverSm
	 * @return
	 */
	public DeliverSm reWriteDeliverSm(DeliverSm deliverSm) {
		//替换真实通道
		Address destAddress = deliverSm.getDestAddress();
		String realChannel = getRealChannel(destAddress.getAddress());
		destAddress.setAddress(realChannel);
		deliverSm.setDestAddress(destAddress);


		//补齐号码
		Address sourceAddress = deliverSm.getSourceAddress();
		String address1 = sourceAddress.getAddress();
		if (!address1.startsWith("0")) {
			address1 = "00" + address1;
			sourceAddress.setAddress(address1);
			deliverSm.setSourceAddress(sourceAddress);

		}


		deliverSm.calculateAndSetCommandLength();
		return deliverSm;
	}


	public void sendDeliverSm(DeliverSm deliverSm) {
		Map<String, String> removeMap = new LinkedHashMap<>();

		SmppSession smppSession = PduUtils.getServerSmppSession(deliverSm);
		//如果获取不到通道，暂时先丢弃，之后要缓存处理，另起线程重发状态报告
		if (smppSession == null) {
			return;
		}

//		deliverSm = reWriteDeliverSm(deliverSm);


		String str = new String(deliverSm.getShortMessage());
		DeliveryReceipt deliveryReceipt = null;
		String messageId = "";
		try {
			deliveryReceipt = DeliveryReceipt.parseShortMessage(str, DateTimeZone.UTC);
			messageId = deliveryReceipt.getMessageId();
		} catch (Exception e) {
			LOGGER.error("{}-处理短信状态报告内容解析异常", Thread.currentThread().getName(), e);
			return;
		}

		//这个通道的运营商会返回两个状态报告 忽略掉accepted  只处理Delivered
		if (StaticValue.CHANNEL_MK_LIST.contains(deliverSm.getDestAddress().getAddress())) {
			String mbl = deliverSm.getSourceAddress().getAddress();
			String areaCode = PduUtils.getAreaCode(mbl);
			//马来西亚和越南 只有accepted
			if (StaticValue.AREA_CODE_MALAYSIA.equals(areaCode) || StaticValue.AREA_CODE_VIETNAM.equals(areaCode) || StaticValue.AREA_CODE_PHILIPPINES.equals(areaCode)) {
				if (deliveryReceipt.getState() == SmppConstants.STATE_ACCEPTED) {
					deliveryReceipt.setState(SmppConstants.STATE_DELIVERED);
				}
			} else {
				if (deliveryReceipt.getState() == SmppConstants.STATE_ACCEPTED) {
					LOGGER.info("渠道为：{}的状态报告，状态为：{}的丢弃,状态报告信息为：{}", deliverSm.getDestAddress().getAddress(), deliveryReceipt.getState(), deliveryReceipt.toString());
					return;
				}
			}

		}

		//key为 msgid + 后缀     value 为 运营商的真实msgid
		Map<String, String> msgidCache = (Map<String, String>) rptRedisConsumer.redisUtil.hmGetAll(SmppServerConstants.CM_MSGID_CACHE);

		for (Map.Entry<String, String> entry : msgidCache.entrySet()) {
			//生成的Msgid
			String msgId = entry.getKey();

			//资源处下行响应时更新的MsgId
			String address = entry.getValue();

			try {

				if (address.equals(messageId)) {
					LOGGER.info("状态报告响应msgid为{}，缓存中key为{}，value为{}", messageId, entry.getKey(), entry.getValue());

					String[] split = msgId.split("-");
					if (split != null && split.length > 3) {
						//替换sequenceNumber
						deliverSm.setSequenceNumber(Integer.valueOf(split[3]));
					}

//					String msgId16 = new BigInteger(split[0], 10).toString(16);
					String realMsgid = split[0];
					//替换messageId
					deliveryReceipt.setMessageId(realMsgid);

					byte[] bytes = deliveryReceipt.toShortMessage().getBytes();
					deliverSm.setShortMessage(bytes);


					Tlv tlv = getTlv(realMsgid);
					deliverSm.setOptionalParameter(tlv);

					deliverSm.calculateAndSetCommandLength();

//					deliverSm = reWrite(deliverSm);


					removeMap.put(entry.getKey(), entry.getValue());
					smppSession.sendRequestPdu(deliverSm, 10000, true);
				}
			} catch (Exception e) {
				LOGGER.error("{}-处理长短信状态报告转发异常", Thread.currentThread().getName(), e);
			}

		}


		if (removeMap != null && removeMap.size() > 0) {
			for (Map.Entry<String, String> entry : removeMap.entrySet()) {
				rptRedisConsumer.redisUtil.hmRemove(SmppServerConstants.CM_MSGID_CACHE, entry.getKey());
			}
		} else {
			try {

				byte[] bytes = deliveryReceipt.toShortMessage().getBytes();
				deliverSm.setShortMessage(bytes);

//				Tlv tlv = getTlv(deliveryReceipt.getMessageId());
//				deliverSm.setOptionalParameter(tlv);


				deliverSm.calculateAndSetCommandLength();

				smppSession.sendRequestPdu(deliverSm, 10000, true);
			} catch (Exception e) {
				LOGGER.error("{}-处理短短信状态报告转发异常", Thread.currentThread().getName(), e);
			}
		}


	}

	/**
	 * 手动生成tlv   cm - 状态报告使用
	 * @param msgId
	 * @return
	 */
	public static Tlv getTlv(String msgId) {
		Tlv tlv = new Tlv((short) 30, msgId.getBytes(), "receipted_message_id");
		return tlv;
	}

	public static DeliverSm reWrite(DeliverSm deliverSm) {

		DeliveryReceipt dr;
		try {

			byte[] shortMessage = deliverSm.getShortMessage();
			dr = DeliveryReceipt.parseShortMessage(new String(shortMessage), DateTimeZone.UTC, false);
			String messageId = dr.getMessageId();

			ArrayList<Tlv> optionalParameters = deliverSm.getOptionalParameters();
			Tlv tlv = optionalParameters.get(0);
			short tag = tlv.getTag();
			byte[] value = messageId.getBytes();
			String tagName = tlv.getTagName();

			Tlv newTlv = new Tlv(tag, value, tagName);

			deliverSm.setOptionalParameter(newTlv);
			deliverSm.calculateAndSetCommandLength();


			ChannelBuffer encode = transcoder.encode(deliverSm);
			String hexString = BufferHelper.createHexString(encode);
			ChannelBuffer cb = BufferHelper.createBuffer(hexString);
			Pdu ss = transcoder.decode(cb);
			deliverSm = (DeliverSm) ss;


		} catch (Exception e) {
			LOGGER.error("{}-重组tlv异常", Thread.currentThread().getName(), e);
		}
		return deliverSm;

	}


}
