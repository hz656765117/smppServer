package com.hz.smsgate.business.listener;

import com.hz.smsgate.base.constants.SmppServerConstants;
import com.hz.smsgate.base.constants.StaticValue;
import com.hz.smsgate.base.smpp.constants.SmppConstants;
import com.hz.smsgate.base.smpp.pdu.DeliverSm;
import com.hz.smsgate.base.smpp.pojo.SmppSession;
import com.hz.smsgate.base.smpp.pojo.Tlv;
import com.hz.smsgate.base.smpp.transcoder.DefaultPduTranscoder;
import com.hz.smsgate.base.smpp.transcoder.DefaultPduTranscoderContext;
import com.hz.smsgate.base.smpp.transcoder.PduTranscoder;
import com.hz.smsgate.base.smpp.transcoder.PduTranscoderContext;
import com.hz.smsgate.base.smpp.utils.DeliveryReceipt;
import com.hz.smsgate.base.utils.PduUtils;
import com.hz.smsgate.base.utils.RedisUtil;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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




		Object obj = rptRedisConsumer.redisUtil.hmGet(SmppServerConstants.CM_MSGID_CACHE, messageId);
		if (obj != null) {
			String preMsgId = obj.toString();
			LOGGER.info("状态报告响应systemid为{}，缓存中key为{}，value为{}", deliverSm.getSystemId(), messageId, preMsgId);
			try {
				String[] split = preMsgId.split("-");
				if (split != null && split.length > 3) {
					//替换sequenceNumber
					deliverSm.setSequenceNumber(Integer.valueOf(split[3]));
				}

				String realMsgid = split[0];
				//替换messageId
				deliveryReceipt.setMessageId(realMsgid);

				byte[] bytes = deliveryReceipt.toShortMessage().getBytes();
				deliverSm.setShortMessage(bytes);

				Tlv tlv = getTlv(realMsgid);
				deliverSm.setOptionalParameter(tlv);
				deliverSm.calculateAndSetCommandLength();

				removeMap.put(messageId, preMsgId);
				smppSession.sendRequestPdu(deliverSm, 10000, true);
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
				deliverSm.calculateAndSetCommandLength();

				smppSession.sendRequestPdu(deliverSm, 10000, true);
			} catch (Exception e) {
				LOGGER.error("{}-处理短短信状态报告转发异常", Thread.currentThread().getName(), e);
			}
		}


	}

	/**
	 * 手动生成tlv   cm - 状态报告使用
	 *
	 * @param msgId
	 * @return
	 */
	public static Tlv getTlv(String msgId) {
		Tlv tlv = new Tlv((short) 30, msgId.getBytes(), "receipted_message_id");
		return tlv;
	}


}