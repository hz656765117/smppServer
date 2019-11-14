package com.hz.smsgate.base.smpp.utils;

import java.util.UUID;

/**
 * @Auther: huangzhuo
 * @Date: 2019/9/4 19:32
 * @Description:
 */
public class PrimaryGenerater {


	private static final String SERIAL_NUMBER = "XXXX"; // 流水号格式
	private static PrimaryGenerater primaryGenerater = null;

	private PrimaryGenerater() {
	}

	/**
	 * 取得PrimaryGenerater的单例实现
	 *
	 * @return
	 */
	public static PrimaryGenerater getInstance() {
		if (primaryGenerater == null) {
			synchronized (PrimaryGenerater.class) {
				if (primaryGenerater == null) {
					primaryGenerater = new PrimaryGenerater();
				}
			}
		}
		return primaryGenerater;
	}

	/**
	 * 生成下一个编号
	 */
	public synchronized String generaterNextNumber(String sno) {
		String id = null;
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString().replaceAll("-", "");
		String uuidStr = str.substring(str.length() - 19 + sno.length(), str.length());
		id = uuidStr + sno;
		return id;
	}

	public static void main(String[] args) {
		String s = PrimaryGenerater.getInstance().generaterNextNumber("53-12345");
		System.out.println(s);

	}
}
