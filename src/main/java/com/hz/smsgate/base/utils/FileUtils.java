package com.hz.smsgate.base.utils;

import com.hz.smsgate.base.emp.pojo.WGParams;
import com.hz.smsgate.base.smpp.config.SmppSessionConfiguration;
import com.hz.smsgate.base.smpp.pojo.Address;
import com.hz.smsgate.base.smpp.pojo.SmppBindType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: huangzhuo
 * @Date: 2019/9/11 17:42
 * @Description:
 */
public class FileUtils {
	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);


	public static List<String> readFileByLines(String fileName) {
		List<String> list = new LinkedList<>();
		BufferedReader br = null;

		try {
			File file = new File(fileName);
			br = new BufferedReader(new FileReader(file));
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = br.readLine()) != null) {
				if (StringUtils.isNotBlank(tempString)) {
					//将读取到的数据添加到stringBuffer
					list.add(tempString.trim());
				}
			}
		} catch (Exception e) {
			//异常信息打印
			logger.error("以行为单位读取文件异常！", e);
		} finally {
			try {
				if (br != null) {
					//关闭流
					br.close();
				}
			} catch (IOException ioe) {
				//异常信息打印
				logger.error("关闭文本流异常！", ioe);
			}
		}
		//返回读到的数据
		return list;
	}

	public static void main(String[] args) {
		getConfigs("C:/Users/Administrator.SC-201812271516/Desktop/resource.txt");
	}


	public static Map<String, String[]> getSpConfigs(String fileName) {
		List<String> strings = readFileByLines(fileName);
		Map<String, String[]> configMap = new LinkedHashMap<>(strings.size());
		for (int i = 0; i < strings.size(); i++) {
			String str = strings.get(i);
			if (StringUtils.isBlank(str)) {
				continue;
			}
			String[] split = str.split("\\|");

			try {
				if (split != null && split.length > 0) {
					String channel = split[0].trim();
					configMap.put(channel, split);
				}
			} catch (Exception e) {
				logger.error("sp账号解析异常！,过滤该配置", e);
				continue;
			}

		}
		return configMap;
	}


	public static Map<String, SmppSessionConfiguration> getConfigs(String fileName) {
		List<String> strings = readFileByLines(fileName);
		Map<String, SmppSessionConfiguration> configMap = new LinkedHashMap<>(strings.size());
		for (int i = 0; i < strings.size(); i++) {
			String str = strings.get(i);
			if (StringUtils.isBlank(str)) {
				continue;
			}
			String[] split = str.split("\\|");
			try {
				if (split != null && split.length > 0) {
					String channels = split[4].trim();
					String[] split1 = channels.split(",");

					if (split1 != null && split1.length > 0) {
						for (int j = 0; j < split1.length; j++) {
							SmppSessionConfiguration config0 = new SmppSessionConfiguration();
							config0.setWindowSize(1);
							config0.setConnectTimeout(10000);
							config0.setRequestExpiryTimeout(30000);
							config0.setWindowMonitorInterval(15000);
							config0.setCountersEnabled(true);
							config0.getLoggingOptions().setLogBytes(true);
							config0.setType(SmppBindType.TRANSCEIVER);
							config0.setName("Tester.Session." + i);
							config0.setHost(split[0].trim());
							config0.setPort(Integer.parseInt(split[1].trim()));
							config0.setSystemId(split[2].trim());
							config0.setPassword(split[3].trim());

							String channel = split1[j];
							config0.setAddressRange(new Address((byte) 0, (byte) 0, channel));
							configMap.put(config0.getAddressRange().getAddress(), config0);
						}
					}

				}
			} catch (Exception e) {
				logger.error("客户端配置对象组装异常！,过滤该配置", e);
				continue;
			}

		}
		return configMap;
	}


	public static boolean writerTxt(List<String> txtList, String fileName) {
		boolean flag = false;
		if (txtList == null || txtList.size() < 1) {
			return flag;
		}
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {
			fos = new FileOutputStream(fileName);
			//一行一行读取文件，解决读取中文字符时出现乱码
			osw = new OutputStreamWriter(fos, "UTF-8");
			bw = new BufferedWriter(osw);
			for (int i = 0; i < txtList.size(); i++) {
				String tempStr = txtList.get(i);
				bw.write(tempStr + "\r\n");
			}
			flag = true;
		} catch (Exception e) {
			logger.error("文件写入异常", e);
		} finally {
			// 注意关闭的先后顺序，先打开的后关闭，后打开的先关闭
			try {
				bw.close();
				osw.close();
				fos.close();
			} catch (Exception e) {
				logger.error("io流关闭异常", e);
			}

		}
		return flag;
	}


//	public static void main(String[] args) {
//		String fileName = "C:\\Users\\Administrator.SC-201812271516\\Desktop\\resource.txt";
//
//		List<String> strings = readFileByLines(fileName);
//
//		String s = strings.get(2);
//		strings.set(2, s + "test");
//
//		writerTxt(strings, "C:\\Users\\Administrator.SC-201812271516\\Desktop\\resource.txt");
//
//	}
}
