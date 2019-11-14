package com.hz.smsgate.business.listener;

import com.hz.smsgate.base.constants.StaticValue;
import com.hz.smsgate.base.constants.SystemGlobals;
import com.hz.smsgate.base.smpp.config.SmppServerConfiguration;
import com.hz.smsgate.base.utils.PropertiesLoader;
import com.hz.smsgate.base.utils.SmppUtils;
import com.hz.smsgate.base.utils.ThreadPoolHelper;
import com.hz.smsgate.business.smpp.handler.CmSmppServerHandler;
import com.hz.smsgate.business.smpp.impl.DefaultSmppServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Auther: huangzhuo
 * @Date: 2019/8/16 15:26
 * @Description:
 */


@Configuration
public class SmppServerInit {
	private static final Logger logger = LoggerFactory.getLogger(SmppServerInit.class);

	@PostConstruct
	public void postConstruct() throws Exception {
		initSystemGlobals();

		int serverPort = StaticValue.SERVER_PORT;

		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

		ScheduledThreadPoolExecutor monitorExecutor = SmppUtils.getThreadPool("SmppServerSessionWindowMonitorPool");

		final SmppServerConfiguration configuration = SmppUtils.getServerConfig(serverPort);

		// create a server, start it up
		DefaultSmppServer smppServer = new DefaultSmppServer(configuration, new CmSmppServerHandler(), executor, monitorExecutor);

		logger.info("Starting SMPP server...  port is {}", serverPort);
		smppServer.start();
		logger.info("SMPP server started");


		//启动相关线程
		initMutiThread();

	}

	/**
	 * 初始化读取配置文件信息
	 */
	private void initSystemGlobals() {
		try {
			PropertiesLoader propertiesLoader = new PropertiesLoader();
			Properties properties = propertiesLoader.getProperties(SystemGlobals.SYSTEM_GLOBALS_NAME);
			SystemGlobals.setProperties(properties);
		} catch (Exception e) {
			logger.error("系统启动，初始化读取配置文件信息失败", e);
		}
	}


	private static void initMutiThread() {
		RptRedisConsumer rptRedisConsumer = new RptRedisConsumer();
		
		//redis状态报告处理线程
		ThreadPoolHelper.executeTask(rptRedisConsumer);

	}


}
