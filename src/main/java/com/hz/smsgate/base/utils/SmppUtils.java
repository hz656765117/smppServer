package com.hz.smsgate.base.utils;

import com.hz.smsgate.base.je.BDBStoredMapFactoryImpl;
import com.hz.smsgate.base.smpp.config.SmppServerConfiguration;
import com.hz.smsgate.base.smpp.pdu.SubmitSmResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class SmppUtils {

	private static final Logger logger = LoggerFactory.getLogger(SmppUtils.class);

	/**
	 * 获取服务端配置实例
	 *
	 * @param port 服务端监听端口
	 * @return
	 */
	public static SmppServerConfiguration getServerConfig(int port) {
		// create a server configuration
		SmppServerConfiguration configuration = new SmppServerConfiguration();
		configuration.setPort(port);
		configuration.setMaxConnectionSize(10);
		configuration.setNonBlockingSocketsEnabled(true);
		configuration.setDefaultRequestExpiryTimeout(30000);
		configuration.setDefaultWindowMonitorInterval(15000);
		configuration.setDefaultWindowSize(50);
		configuration.setDefaultWindowWaitTimeout(configuration.getDefaultRequestExpiryTimeout());
		configuration.setDefaultSessionCountersEnabled(true);
		configuration.setJmxEnabled(true);
		return configuration;
	}

	public static ScheduledThreadPoolExecutor getThreadPool(String name) {
		// to enable automatic expiration of requests, a second scheduled executor
		// is required which is what a monitor task will be executed with - this
		// is probably a thread pool that can be shared with between all client bootstraps

		ScheduledThreadPoolExecutor monitorExecutor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1, new ThreadFactory() {
			private AtomicInteger sequence = new AtomicInteger(0);

			@Override
			public Thread newThread(Runnable r) {
				Thread t = new Thread(r);
				t.setName(name + "-" + sequence.getAndIncrement());
				return t;
			}
		});
		return monitorExecutor;
	}


	public static SubmitSmResp getSubmitResp(String jeName, String msgid) {
		SubmitSmResp submitSmResp = null;
		BlockingQueue<Object> submitRespQueue = null;
		try {
			submitRespQueue = BDBStoredMapFactoryImpl.INS.getQueue(jeName, jeName);
			if (submitRespQueue != null) {
				Object obj = submitRespQueue.poll();
				if (obj != null) {
					submitSmResp = (SubmitSmResp) obj;
					String msgId16 = new BigInteger(msgid, 10).toString(16);
					submitSmResp.setMessageId(msgId16);
					submitSmResp.calculateAndSetCommandLength();
					return submitSmResp;
				}
			}
		} catch (Exception e) {
			logger.error("获取短信下行响应对象异常 {}", e);
		}
		return submitSmResp;
	}


	public static String getMsgId() {
		String str = new MsgId().toString().substring(3);
		if (str.startsWith("0")) {
			str = "1" + str.substring(1);
		}
		return str;
	}

}
