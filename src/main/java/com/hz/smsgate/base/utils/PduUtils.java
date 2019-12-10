package com.hz.smsgate.base.utils;

import com.hz.smsgate.base.smpp.pojo.SmppSession;
import com.hz.smsgate.business.listener.SmppServerInit;
import com.hz.smsgate.business.pojo.SmppUserVo;
import com.hz.smsgate.business.smpp.impl.DefaultSmppServer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

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



	public static SmppUserVo getSmppUserByUserPwd(String smppUser, String smppPwd) {
		if (StringUtils.isBlank(smppUser) || StringUtils.isBlank(smppPwd)) {
			return null;
		}
		List<SmppUserVo> smppUser1 = SmppServerInit.SMPP_USER;
		for (SmppUserVo smppUserVo : smppUser1) {
			if (smppUser.equals(smppUserVo.getSmppUser()) && smppPwd.equals(smppUserVo.getSmppPwd())) {
				return smppUserVo;
			}

		}
		return null;
	}














	public static SmppSession getServerSmppSession(String smppUser, String smppPwd) {
		SmppSession smppSession = null;
		try {
			SmppUserVo smppUserVo = getSmppUserByUserPwd(smppUser, smppPwd);

			if (DefaultSmppServer.smppSessionList == null || DefaultSmppServer.smppSessionList.size() < 1) {
				LOGGER.error("{}-处理状态报告异常，未能获取到服务端连接(通道为：{}，systemId为：({}),smppUser(),smppPwd({}))-------", Thread.currentThread().getName(), smppUserVo.getSenderid(), smppUserVo.getSystemid(), smppUserVo.getSmppUser(), smppUserVo.getSmppPwd());
				return smppSession;
			}

			smppSession = getSmppSessionBySmppUser(smppUserVo);


			if (smppSession == null) {
				LOGGER.error("{}-处理状态报告异常，未能获取到服务端连接(通道为：{}，systemId为：({}),smppUser(),smppPwd({}))-------", Thread.currentThread().getName(), smppUserVo.getSenderid(), smppUserVo.getSystemid(), smppUserVo.getSmppUser(), smppUserVo.getSmppPwd());
			}
		} catch (Exception e) {
			LOGGER.error("{}-处理状态报告异常，未能匹配到服务端连接(smppUser(),smppPwd({}))-------", Thread.currentThread().getName(), smppUser, smppPwd);
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
