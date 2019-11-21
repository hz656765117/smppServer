package com.hz.smsgate.base.utils;

import com.cloudhopper.commons.charset.CharsetUtil;
import com.hz.smsgate.base.constants.StaticValue;
import com.hz.smsgate.base.smpp.config.SmppSessionConfiguration;
import com.hz.smsgate.base.smpp.pdu.DeliverSm;
import com.hz.smsgate.base.smpp.pdu.SubmitSm;
import com.hz.smsgate.base.smpp.pojo.Address;
import com.hz.smsgate.base.smpp.pojo.SmppSession;
import com.hz.smsgate.business.listener.RptRedisConsumer;
import com.hz.smsgate.business.smpp.impl.DefaultSmppServer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @Auther: huangzhuo
 * @Date: 2019/9/6 15:10
 * @Description:
 */
@Component
public class PduUtils {
	private static Logger LOGGER = LoggerFactory.getLogger(PduUtils.class);


	@Autowired
	public RedisUtil redisUtil;

	public static PduUtils pduUtils;

	@PostConstruct
	public void init() {
		pduUtils = this;
		pduUtils.redisUtil = this.redisUtil;
	}


	/**
	 * 通道555，778的短信去掉前面两个00
	 *
	 * @param sm
	 * @return
	 */
	public static SubmitSm removeZero(SubmitSm sm) {
		if (sm.getSourceAddress().getAddress().equals(StaticValue.CHANNL_REL.get(StaticValue.CHANNEL_1)) || sm.getSourceAddress().getAddress().equals(StaticValue.CHANNEL_1) || StaticValue.CHANNEL_MK_LIST.contains(sm.getSourceAddress().getAddress())) {
			Address destAddress = sm.getDestAddress();
			if (destAddress.getAddress().startsWith("00")) {
				String address = destAddress.getAddress().substring(2);
				destAddress.setAddress(address);
				sm.setDestAddress(destAddress);
				sm.calculateAndSetCommandLength();
			}
		}
		return sm;
	}


	/**
	 * 获取区号
	 *
	 * @param mbl
	 * @return
	 */
	public static String getAreaCode(String mbl) {
		String areaCode = "";
		if (StringUtils.isBlank(mbl)) {
			return areaCode;
		}

		if (mbl.startsWith("00")) {
			areaCode = mbl.substring(2, 4);
		} else {
			areaCode = mbl.substring(0, 2);
		}

		return areaCode;
	}


	/**
	 * 根据通道获取用户名
	 *
	 * @param sendId 通道id
	 * @return
	 */
	public static String getSystemIdBySendId(String sendId) {

		SmppSessionConfiguration smppSessionConfiguration = (SmppSessionConfiguration) pduUtils.redisUtil.hmGet("configMap", sendId);

		if (smppSessionConfiguration == null) {
			String key = getKey(sendId);
			smppSessionConfiguration = (SmppSessionConfiguration) pduUtils.redisUtil.hmGet("configMap", key);
		}
		if (smppSessionConfiguration == null) {
			return null;
		}
		String systemId = smppSessionConfiguration.getSystemId();
		return systemId;
	}


	/**
	 * 短信内容GSM编码  cm运营商的需要编码
	 *
	 * @param sm 下行短信对象
	 * @return
	 */
	public static SubmitSm encodeGsm(SubmitSm sm) {
		String sendId = sm.getSourceAddress().getAddress();
		String systemId = getSystemIdBySendId(sendId);

		//cm资源需要GSM格式编码
		if (StaticValue.SYSTEMID_CM.equals(systemId)) {
			onlyEncodeGsm(sm);
		}
		return sm;
	}

	/**
	 * 短信内容Gsm编码
	 *
	 * @param sm 下行短信对象
	 * @return
	 */
	public static SubmitSm onlyEncodeGsm(SubmitSm sm) {
		byte[] shortMessage = sm.getShortMessage();
		String content = new String(shortMessage);
		LOGGER.info("短短信的内容为{},下行号码为{}，通道为{}", content, sm.getDestAddress().getAddress(), sm.getSourceAddress().getAddress());
		try {
			byte[] textBytes = CharsetUtil.encode(content, CharsetUtil.CHARSET_GSM);
			sm.setShortMessage(textBytes);
		} catch (Exception e) {
			LOGGER.error("短信内容编码异常", e);
		}
		LOGGER.info("短短信编码后的内容为{},下行号码为{}，通道为{}", new String(content.getBytes()), sm.getDestAddress().getAddress(), sm.getSourceAddress().getAddress());

		sm.calculateAndSetCommandLength();
		return sm;
	}


	/**
	 * 重写下行对象
	 *
	 * @param sm
	 * @return
	 */
	public static SubmitSm rewriteSubmitSm(SubmitSm sm) {
		//短信下行内容编码
		sm = PduUtils.encodeGsm(sm);
		//通道555的短信去掉前面两个00
		sm = PduUtils.removeZero(sm);
		return sm;
	}


	public static String getKey(String sendId) {
		Map<String, String> channlRel = StaticValue.CHANNL_REL;

		for (Map.Entry<String, String> entry : channlRel.entrySet()) {
			if (sendId.equals(entry.getValue())) {
				sendId = entry.getKey();
				break;
			}
		}
		return sendId;
	}


	public static SmppSession getServerSmppSession(DeliverSm deliverSm) {
		SmppSession smppSession = null;
		//根据通道获取session
		String channel = deliverSm.getDestAddress().getAddress();
		String systemId = deliverSm.getSystemId();

		String[] strings = StaticValue.CHANNL_SP_REL.get(channel);
		String pwd = strings[4];


		if (DefaultSmppServer.smppSessionList == null || DefaultSmppServer.smppSessionList.size() < 1) {
			LOGGER.error("{}-处理状态报告异常，未能获取到服务端连接(通道为：{}，systemId为：{})-------", Thread.currentThread().getName(), channel, systemId);
			return smppSession;
		}

		for (SmppSession session : DefaultSmppServer.smppSessionList) {
			if (session.getConfiguration().getSystemId().equals(systemId) && session.getConfiguration().getPassword().equals(pwd)) {
				smppSession = session;
				break;
			}
		}

		if (smppSession == null) {
			LOGGER.error("{}-处理状态报告异常，未能匹配到服务端连接(通道为：{}，systemId为：{},password为：{})-------", Thread.currentThread().getName(), channel, systemId, pwd);
			return smppSession;
		}
		return smppSession;
	}

}
