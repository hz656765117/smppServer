package com.hz.smsgate.base.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author huangzhuo
 * @date 2019/6/26 15:51
 */
public class PropertiesLoader {
	private static Logger LOGGER = LoggerFactory.getLogger(PropertiesLoader.class);


	/**
	 * 读取配置文件信息
	 *
	 * @param propertiesName
	 * @return
	 */
	public Properties getProperties(String propertiesName) {
		InputStream is = getClass().getResourceAsStream("/" + propertiesName + ".properties");
		Properties dbProps = new Properties();
		try {
			dbProps.load(is);
			return dbProps;
		} catch (IOException ex) {
			LOGGER.error("读取配置文件信息异常", ex);
			return null;
		}
	}
}
