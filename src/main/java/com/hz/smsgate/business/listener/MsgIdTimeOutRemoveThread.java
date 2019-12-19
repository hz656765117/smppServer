package com.hz.smsgate.business.listener;

import com.hz.smsgate.base.constants.SmppServerConstants;
import com.hz.smsgate.base.smpp.pojo.SessionKey;
import com.hz.smsgate.base.utils.RedisUtil;
import com.hz.smsgate.business.pojo.MsgVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * msgid超时移除线程
 *
 * @author huangzhuo
 * @date 2019/12/13 10:58
 */
@Component
public class MsgIdTimeOutRemoveThread implements Runnable {
	private static Logger LOGGER = LoggerFactory.getLogger(MsgIdTimeOutRemoveThread.class);


	@Autowired
	public RedisUtil redisUtil;

	public static MsgIdTimeOutRemoveThread msgIdTimeOutRemoveThread;

	@PostConstruct
	public void init() {
		msgIdTimeOutRemoveThread = this;
		msgIdTimeOutRemoveThread.redisUtil = this.redisUtil;
	}


	@Override
	public void run() {
		try {
			Thread.sleep(30000);
		} catch (Exception e) {
			LOGGER.error("{}-线程启动异常", Thread.currentThread().getName(), e);
		}

		SessionKey key;
		LOGGER.info("{}-msgid超时移除线程（redis）开始工作......", Thread.currentThread().getName());
		List<String> keys = new ArrayList<>();
		while (true) {
			try {
				if (msgIdTimeOutRemoveThread.redisUtil != null) {
					Object obj = msgIdTimeOutRemoveThread.redisUtil.hmGetAll(SmppServerConstants.CM_MSGID_CACHE);
					if (obj != null) {
						Map<String, MsgVo> msgMap = (Map<String, MsgVo>) obj;
						for (Map.Entry<String, MsgVo> entry : msgMap.entrySet()) {
							MsgVo msgVo = entry.getValue();
							long sendTime = msgVo.getSendTime();
							long curTime = System.currentTimeMillis();
							long betTime = curTime - sendTime;
							long time = 24 * 60 * 60 * 1000;
							if (betTime > time) {
								keys.add(entry.getKey());
							}
						}
						if (keys != null && keys.size() > 0) {
							msgIdTimeOutRemoveThread.redisUtil.hmRemoves(SmppServerConstants.CM_MSGID_CACHE, keys.toArray());
							keys.clear();
						}

					}
					Thread.sleep(600000);
				} else {
					Thread.sleep(10000);
				}
			} catch (Exception e) {
				LOGGER.error("{}-处理msgid超时移除线程异常", Thread.currentThread().getName(), e);
			}

		}
	}


}
