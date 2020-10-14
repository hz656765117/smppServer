package com.hz.smsgate.business.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


/**
 * 定时加载数据库中的配置到内存中
 *
 * @author huangzhuo
 * @date 2019/9/11 14:27
 */
@Component
public class ConfigLoadThread {
	private static Logger LOGGER = LoggerFactory.getLogger(ConfigLoadThread.class);

	@Autowired
	SmppServerInit smppServerInit;

	public static ConfigLoadThread configLoadThread;

	@PostConstruct
	public void init() {
		configLoadThread = this;
		configLoadThread.smppServerInit = this.smppServerInit;
	}


	@Scheduled(cron = "0 0/2 * * * ?")
	public void dealFailSendMap() {
		try {
			configLoadThread.smppServerInit.initConfigs();
			configLoadThread.smppServerInit.initChannels();
			configLoadThread.smppServerInit.initMkList();
			configLoadThread.smppServerInit.initYxj();
		} catch (Exception e) {
			LOGGER.error("{}-处理定时加载数据库中的配置到内存中异常", Thread.currentThread().getName(), e);
		}

	}

}
