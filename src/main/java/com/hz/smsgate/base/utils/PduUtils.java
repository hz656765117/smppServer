package com.hz.smsgate.base.utils;

import com.cloudhopper.commons.charset.CharsetUtil;
import com.hz.smsgate.base.constants.StaticValue;
import com.hz.smsgate.base.smpp.config.SmppSessionConfiguration;
import com.hz.smsgate.base.smpp.pdu.DeliverSm;
import com.hz.smsgate.base.smpp.pdu.SubmitSm;
import com.hz.smsgate.base.smpp.pojo.Address;
import com.hz.smsgate.base.smpp.pojo.SessionKey;
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

	//获取原通道
	public static String getRealChannel(String systemId, String gwChannel) {
		if (StringUtils.isBlank(gwChannel)) {
			return gwChannel;
		}
		SessionKey sessionKey = new SessionKey(systemId, gwChannel);
		for (Map.Entry<String, SessionKey> entry : StaticValue.CHANNL_REL.entrySet()) {
			if (sessionKey.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		return gwChannel;
	}

	public static SmppSession getServerSmppSession(DeliverSm deliverSm) {
		SmppSession smppSession = null;
		//根据通道获取session
		String channel = deliverSm.getDestAddress().getAddress();
		String systemId = deliverSm.getSystemId();


		String[] strings = StaticValue.CHANNL_SP_REL.get(new SessionKey(systemId, channel));
		if (strings == null) {
			String realChannel = getRealChannel(systemId, channel);
			strings = StaticValue.CHANNL_SP_REL.get(new SessionKey(systemId, realChannel));
		}
		LOGGER.info("systemid({}),senderid({})获取ServerSmppSession,获取到的对象为{}", systemId, channel, strings != null ? strings.toString() : null);
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
		}

		return smppSession;
	}

}
