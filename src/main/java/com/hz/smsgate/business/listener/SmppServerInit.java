package com.hz.smsgate.business.listener;

import com.hz.smsgate.base.constants.StaticValue;
import com.hz.smsgate.base.constants.SystemGlobals;
import com.hz.smsgate.base.smpp.config.SmppServerConfiguration;
import com.hz.smsgate.base.smpp.pojo.SessionKey;
import com.hz.smsgate.base.utils.*;
import com.hz.smsgate.business.pojo.Channel;
import com.hz.smsgate.business.pojo.OperatorVo;
import com.hz.smsgate.business.pojo.SmppUserVo;
import com.hz.smsgate.business.service.SmppService;
import com.hz.smsgate.business.smpp.handler.CmSmppServerHandler;
import com.hz.smsgate.business.smpp.impl.DefaultSmppServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.*;
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


	@Autowired
	public RedisUtil redisUtil;

	public static SmppServerInit smppServerInit;


	@Autowired
	private SmppService smppService;


	public static Map<String, SessionKey> CHANNL_REL = new LinkedHashMap<>();

	public static List<SessionKey> CHANNEL_MK_LIST = new ArrayList<>();


	/**
	 * opt通道
	 */
	public static List<SessionKey> CHANNEL_OPT_LIST = new ArrayList<>();
	/**
	 * 营销通道
	 */
	public static List<SessionKey> CHANNEL_YX_LIST = new ArrayList<>();
	/**
	 * 通知通道
	 */
	public static List<SessionKey> CHANNEL_TZ_LIST = new ArrayList<>();


	@PostConstruct
	public void postConstruct() throws Exception {
		smppServerInit = this;
		smppServerInit.redisUtil = this.redisUtil;

		initSystemGlobals();
		initSmppServer();
		initConfigs();

		initChannels();

		//启动相关线程
		initMutiThread();

	}



	public void initChannels() {
		CHANNL_REL.clear();
		Map<String, SessionKey> map = new LinkedHashMap<>();

		List<OperatorVo> allOperator = smppService.getAllOperator();
		for (OperatorVo operatorVo : allOperator) {
			SessionKey sessionKey = new SessionKey();
			sessionKey.setSenderId(operatorVo.getSenderid());
			sessionKey.setSystemId(operatorVo.getSystemid());
			map.put(operatorVo.getChannel(), sessionKey);
		}
		CHANNL_REL = map;
	}



	public void initMkList() {
		CHANNEL_MK_LIST.clear();
		List<OperatorVo> allOperator = smppService.getAllOperator();
		for (OperatorVo operatorVo : allOperator) {
			if ("HP01".equals(operatorVo.getSystemid()) || "HP02".equals(operatorVo.getSystemid()) || "HP03".equals(operatorVo.getSystemid()) || "HP04".equals(operatorVo.getSystemid())) {
				SessionKey sessionKey = new SessionKey(operatorVo.getSystemid(), operatorVo.getChannel());
				SessionKey sessionKey1 = SmppServerInit.CHANNL_REL.get(operatorVo.getChannel());
				CHANNEL_MK_LIST.add(sessionKey);
				CHANNEL_MK_LIST.add(sessionKey1);
			}
		}
	}

	public void initYxj() {
		CHANNEL_OPT_LIST.clear();
		CHANNEL_TZ_LIST.clear();
		CHANNEL_YX_LIST.clear();
		List<OperatorVo> allOperator = smppService.getAllOperator();
		for (OperatorVo operatorVo : allOperator) {
			SessionKey sessionKey = new SessionKey(operatorVo.getSystemid(), operatorVo.getChannel());
			if (operatorVo.getType() != null && 0 == operatorVo.getType()) {
				CHANNEL_OPT_LIST.add(sessionKey);
				CHANNEL_OPT_LIST.add(SmppServerInit.CHANNL_REL.get(operatorVo.getChannel()));
			} else if (operatorVo.getType() != null && 1 == operatorVo.getType()) {
				CHANNEL_TZ_LIST.add(sessionKey);
				CHANNEL_TZ_LIST.add(SmppServerInit.CHANNL_REL.get(operatorVo.getChannel()));
			} else {
				CHANNEL_YX_LIST.add(sessionKey);
				CHANNEL_YX_LIST.add(SmppServerInit.CHANNL_REL.get(operatorVo.getChannel()));
			}
		}


	}




	public static void initSmppServer() {
		try {
			int serverPort = StaticValue.SERVER_PORT;
			ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
			ScheduledThreadPoolExecutor monitorExecutor = SmppUtils.getThreadPool("SmppServerSessionWindowMonitorPool");
			final SmppServerConfiguration configuration = SmppUtils.getServerConfig(serverPort);
			// create a server, start it up
			DefaultSmppServer smppServer = new DefaultSmppServer(configuration, new CmSmppServerHandler(), executor, monitorExecutor);
			logger.info("Starting SMPP server...  port is {}", serverPort);
			smppServer.start();
			logger.info("SMPP server started");
		} catch (Exception e) {
			logger.error("smpp server 启动异常", e);
		}

	}


	public void initConfigs() {
		StaticValue.SMPP_USER.clear();
		StaticValue.SMPP_USER = smppService.getAllSmppUser();
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

		ConfigLoadThread configLoadThread = new ConfigLoadThread();

		//redis状态报告处理线程
		ThreadPoolHelper.executeTask(rptRedisConsumer);

		ThreadPoolHelper.executeTask(configLoadThread);

	}


}
