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
import com.hz.smsgate.business.pojo.SmppUserVo;
import com.hz.smsgate.business.smpp.impl.DefaultSmppServer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
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

	public static SmppUserVo getSmppUserByUserPwd(String smppUser, String smppPwd) {
		if (StringUtils.isBlank(smppUser) || StringUtils.isBlank(smppPwd)) {
			return null;
		}

		for (SmppUserVo smppUserVo : StaticValue.SMPP_USER) {
			if (smppUser.equals(smppUserVo.getSmppUser()) && smppPwd.equals(smppUserVo.getSmppPwd())) {
				return smppUserVo;
			}

		}
		return null;
	}


	public static SessionKey getRealSystemId(String systemId, String senderId) {
		SessionKey sessionKey = new SessionKey("CM0001", "888");
		if ("HP01".equals(systemId) && "Haaloo".equals(senderId)) {
			return sessionKey;
		} else if ("HP01".equals(systemId) && StaticValue.CHANNEL_MK_10.equals(senderId)) {
			return sessionKey;
		} else if ("HP03".equals(systemId) && "Haaloo".equals(senderId)) {
			return sessionKey;
		} else if ("HP03".equals(systemId) && StaticValue.CHANNEL_MK_11.equals(senderId)) {
			return sessionKey;
		} else if ("SA015a".equals(systemId) && "776".equals(senderId)) {
			return sessionKey;
		} else if ("SA015a".equals(systemId) && "0123456789".equals(senderId)) {
			return sessionKey;
		}
		return null;
	}

	public static SmppSession getServerSmppSession(DeliverSm deliverSm) {
		SmppSession smppSession;
		//根据通道获取session
		String channel = deliverSm.getDestAddress().getAddress();
		String systemId = deliverSm.getSystemId();

		smppSession = getServerSmppSession(systemId, channel);

		return smppSession;
	}

	public static SmppUserVo getSmppUserFather(String curSystemId, String curSenderId) {
		if (StringUtils.isBlank(curSystemId) || StringUtils.isBlank(curSenderId)) {
			return null;
		}
		SmppUserVo smppUserVo = null;

		List<SmppUserVo> list;

		for (SmppUserVo smppUser : StaticValue.SMPP_USER) {
			list = smppUser.getList();
			if (list == null || list.size() <= 0) {
				continue;
			}
			for (SmppUserVo sonSmppUser : list) {
				if (checkSmppUser(sonSmppUser, curSystemId, curSenderId)) {
					return smppUser;
				}
			}
		}
		return smppUserVo;
	}


	public static SmppUserVo getSmppUserSon(String curSystemId, String curSenderId) {
		if (StringUtils.isBlank(curSystemId) || StringUtils.isBlank(curSenderId)) {
			return null;
		}

		for (SmppUserVo smppUser : StaticValue.SMPP_USER) {
			boolean flag = checkSmppUser(smppUser, curSystemId, curSenderId);
			if (flag) {
				return smppUser;
			}
		}

		return null;
	}


	public static SmppUserVo getSmppUserFatherBefore(String curSystemId, String curSenderId) {
		if (StringUtils.isBlank(curSystemId) || StringUtils.isBlank(curSenderId)) {
			return null;
		}
		SmppUserVo smppUserVo;

		smppUserVo = getSmppUserFather(curSystemId, curSenderId);

		//再查子账号
		if (smppUserVo == null) {
			smppUserVo = getSmppUserSon(curSystemId, curSenderId);
		}


		return smppUserVo;
	}


	public static SmppUserVo getSmppUserSonBefore(String curSystemId, String curSenderId) {
		if (StringUtils.isBlank(curSystemId) || StringUtils.isBlank(curSenderId)) {
			return null;
		}
		SmppUserVo smppUserVo;

		smppUserVo = getSmppUserSon(curSystemId, curSenderId);
		if (smppUserVo == null) {
			smppUserVo = getSmppUserFather(curSystemId, curSenderId);
		}

		return smppUserVo;
	}


	public static boolean checkSmppUser(SmppUserVo smppUser, String curSystemId, String curSenderId) {
		boolean flag = false;
		String systemid = smppUser.getSystemid();
		String senderid = smppUser.getSenderid();
		String channel = smppUser.getChannel();
		if (curSystemId.equals(systemid) && curSenderId.equals(senderid)) {
			flag = true;
		}

		if (curSystemId.equals(systemid) && curSenderId.equals(channel)) {
			flag = true;
		}

		return flag;
	}


	public static SmppSession getServerSmppSession(String systemId, String senderId) {
		SmppSession smppSession = null;
		try {
			SmppUserVo smppUserVo = getSmppUserFatherBefore(systemId, senderId);
			LOGGER.info("systemid({}),senderid({})获取ServerSmppSession,获取到的对象为{}", systemId, senderId, smppUserVo != null ? smppUserVo.toString() : null);


			if (DefaultSmppServer.smppSessionList == null || DefaultSmppServer.smppSessionList.size() < 1) {
				LOGGER.error("{}-处理状态报告异常，未能获取到服务端连接(通道为：{}，systemId为：{})-------", Thread.currentThread().getName(), senderId, systemId);
				return smppSession;
			}

			smppSession = getSmppSessionBySmppUser(smppUserVo);
			if (smppSession == null) {
				smppUserVo = getSmppUserSonBefore(systemId, senderId);
				smppSession = getSmppSessionBySmppUser(smppUserVo);
			}

			if (smppSession == null) {
				LOGGER.error("{}-处理状态报告异常，未能匹配到服务端连接(通道为：{}，systemId为：{},password为：{})-------", Thread.currentThread().getName(), senderId, systemId);
			}
		} catch (Exception e) {
			LOGGER.error("{}-处理状态报告异常，未能匹配到服务端连接(通道为：{}，systemId为：{},password为：{})-------", Thread.currentThread().getName(), senderId, systemId);
		}

		return smppSession;
	}


	public static SmppSession getSmppSessionBySmppUser(SmppUserVo smppUserVo) {
		if (smppUserVo == null) {
			return null;
		}
		SmppSession smppSession = null;
		for (SmppSession session : DefaultSmppServer.smppSessionList) {
			if (session.getConfiguration().getSystemId().equals(smppUserVo.getSmppUser()) && session.getConfiguration().getPassword().equals(smppUserVo.getSmppPwd())) {
				smppSession = session;
				break;
			}
		}
		return smppSession;
	}


}
