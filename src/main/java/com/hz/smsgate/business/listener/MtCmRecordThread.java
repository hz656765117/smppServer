package com.hz.smsgate.business.listener;

import com.hz.smsgate.base.constants.SmppServerConstants;
import com.hz.smsgate.base.emp.pojo.WGParams;
import com.hz.smsgate.base.smpp.pdu.SubmitSm;
import com.hz.smsgate.base.smpp.pdu.SubmitSmResp;
import com.hz.smsgate.base.smpp.pojo.SessionKey;
import com.hz.smsgate.base.utils.RedisUtil;
import com.hz.smsgate.business.pojo.MsgVo;
import com.hz.smsgate.business.service.SmppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


/**
 * cm redis短信下行记录线程
 *
 * @author huangzhuo
 * @date 2019/10/17 15:38
 */
@Component
public class MtCmRecordThread implements Runnable {
	private static Logger LOGGER = LoggerFactory.getLogger(MtCmRecordThread.class);

	@Autowired
	public RedisUtil redisUtil;

	public static MtCmRecordThread mtCmRecordThread;

	@Autowired
	public SmppService smppService;

	@PostConstruct
	public void init() {
		mtCmRecordThread = this;
		mtCmRecordThread.redisUtil = this.redisUtil;
		mtCmRecordThread.smppService = this.smppService;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			LOGGER.error("{}-线程启动异常", Thread.currentThread().getName(), e);
		}
		SubmitSm submitSm;
		LOGGER.info("{}-记录短信（redis）-cm下行线程开始工作......", Thread.currentThread().getName());
		SubmitSmResp submitResp;
		Object obj;
		while (true) {
			try {
				//redis对象为空休眠一秒
				if (mtCmRecordThread.redisUtil == null) {
					Thread.sleep(1000);
					continue;
				}
				obj = mtCmRecordThread.redisUtil.rPop(SmppServerConstants.BACK_SUBMIT_SM);
				//下行对象为空，从其他队列中获取，获取不到休眠半秒
				if (obj == null) {
					//通道没消息 则休眠1s
					Thread.sleep(1000);
					continue;
				}

				submitSm = (SubmitSm) obj;

				sendToWg(submitSm);

				addRecord(submitSm);

			} catch (Exception e) {
				LOGGER.error("{}- 记录短信下行异常", Thread.currentThread().getName(), e);
				try {
					Thread.sleep(10000);
				} catch (Exception e1) {

				}
			}


		}

	}

	private void addRecord(SubmitSm submitSm) {
		try {
			boolean result = mtCmRecordThread.smppService.insertMtTask(submitSm);
			if (result) {
				return;
			}
			boolean flag = false;
			int i = 0;
			while (!flag && i <= 3) {
				flag = mtCmRecordThread.smppService.insertMtTask(submitSm);
				i++;
			}
		} catch (Exception e) {
			LOGGER.error("{}- 记录短信下行异常", Thread.currentThread().getName(), e);
		}

	}


	private void sendToWg(SubmitSm submitSm) {
		SessionKey sessionKey = new SessionKey();
		Object obj = mtCmRecordThread.redisUtil.hmGet(SmppServerConstants.CM_MSGID_CACHE, submitSm.getTempMsgId());
		if (obj != null) {
			MsgVo msgVo = (MsgVo) obj;
			sessionKey.setSystemId(msgVo.getSmppUser());
			sessionKey.setSenderId(msgVo.getSmppPwd());
		}

		WGParams wgParams = SmppServerInit.CHANNL_SP_REL.get(sessionKey);
		if (wgParams != null) {
			wgParams.setDas(submitSm.getDestAddress().getAddress());
			String sm = new String(submitSm.getShortMessage());
			wgParams.setSm(sm);
			mtCmRecordThread.redisUtil.lPush(SmppServerConstants.SYNC_SUBMIT, wgParams);
		} else {
			LOGGER.error("{}- {} -{}-{}短信记录异常，未能获取到sp账号", Thread.currentThread().getName(), submitSm.getSystemId(), submitSm.getSourceAddress().getAddress(), submitSm.getDestAddress().getAddress());
		}

	}

}
