package com.hz.smsgate.business.smpp.handler;

import com.hz.smsgate.base.smpp.config.SmppSessionConfiguration;
import com.hz.smsgate.base.smpp.exception.SmppProcessingException;
import com.hz.smsgate.base.smpp.pdu.BaseBind;
import com.hz.smsgate.base.smpp.pdu.BaseBindResp;
import com.hz.smsgate.base.smpp.pojo.SmppServerHandler;
import com.hz.smsgate.base.smpp.pojo.SmppServerSession;
import com.hz.smsgate.base.smpp.pojo.SmppSessionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Auther: huangzhuo
 * @Date: 2019/8/28 14:07
 * @Description:
 */
public class DefaultSmppServerHandler implements SmppServerHandler {
	private static final Logger logger = LoggerFactory.getLogger(DefaultSmppServerHandler.class);

	@Override
	public void sessionBindRequested(Long sessionId, SmppSessionConfiguration sessionConfiguration, BaseBind bindRequest) throws SmppProcessingException {
		// test name change of sessions
		// this name actually shows up as thread context....
		sessionConfiguration.setName("Application.SMPP." + sessionConfiguration.getSystemId());

		//throw new SmppProcessingException(SmppConstants.STATUS_BINDFAIL, null);
	}

	@Override
	public void sessionCreated(Long sessionId, SmppServerSession session, BaseBindResp preparedBindResponse) throws SmppProcessingException {
		logger.info("Session created: {}", session);
		// need to do something it now (flag we're ready)
		SmppSessionHandler smppSessionHandler = new ServerSmppSessionCmHandler(session);
		session.serverReady(smppSessionHandler);
	}

	@Override
	public void sessionDestroyed(Long sessionId, SmppServerSession session) {
		logger.info("Session destroyed: {}", session);
		// print out final stats
		if (session.hasCounters()) {
			logger.info(" final session rx-submitSM: {}", session.getCounters().getRxSubmitSM());
		}

		// make sure it's really shutdown
		session.destroy();
	}
}
