
package com.hz.smsgate.base.constants;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author huangzhuo
 * @date 2019/6/26 15:51
 */
public class SystemGlobals {

	private static Logger LOGGER = LoggerFactory.getLogger(SystemGlobals.class);

	public static final String SYSTEM_GLOBALS_NAME = "SystemGlobals";

	private static Properties defaults = new Properties();

	private SystemGlobals() {
	}

	public static void setProperties(Properties properties) {
		defaults = properties;
	}

	public static Properties getProperties() {
		return defaults;
	}


	public static String getValue(String key) {
		return defaults.getProperty(key);
	}

	public static String getValue(String key, String defaultValue) {
		String value = defaults.getProperty(key);
		if (value == null || "".equals(value)) {
			return defaultValue;
		}
		return value;
	}

	/**
	 * 读取配置文件中的信息
	 *
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static Integer getIntValue(String key, int defaultValue) {
		if (defaults.getProperty(key) == null) {
			return defaultValue;
		}
		try {
			return Integer.parseInt(defaults.getProperty(key));
		} catch (Exception e) {
			LOGGER.error("读取配置文件信息异常", e);
		}
		return defaultValue;
	}


	public static Integer getIntValue(String key) {
		try {
			return Integer.parseInt(defaults.getProperty(key));
		} catch (Exception e) {
			LOGGER.error("读取配置文件信息异常", e);
		}
		return null;
	}

}
