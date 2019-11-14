package com.hz.smsgate.base.emp.pojo;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class SmsUtil {
	private static Logger LOGGER = LoggerFactory.getLogger(WGParams.class);

	public String httpUrl;

	/**
	 * 执行请求
	 *
	 * @param obj
	 * @param httpUrl
	 * @return
	 * @throws Exception
	 */
	public String execute(Object obj, String httpUrl) throws Exception {

		this.httpUrl = httpUrl;
		String result = "";
		Class cls = obj.getClass();
		Field[] fields = cls.getDeclaredFields();
		StringBuffer sb = new StringBuffer();

		String fieldName = null;
		String fieldNameUpper = null;
		Method getMethod = null;
		String value = null;
		//组装请求参数
		for (int i = 0; i < fields.length; i++) {
			fieldName = fields[i].getName();
			if (fieldName.equals("LOGGER")) {
				continue;
			}
			fieldNameUpper = Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
			getMethod = cls.getMethod("get" + fieldNameUpper);
			value = (String) getMethod.invoke(obj);
			if (value != null) {
				sb.append("&").append(fieldName).append("=").append(value);
			}
		}
		//设置参数到httppost中
		String param = sb.toString().substring(1);
		LOGGER.info("请求网关地址为{}，参数为{}",httpUrl,param);
		StringEntity paramEntity = new StringEntity(param);
		//记录当前发送请求到日志记录，与发送号码文件同级目录
//		new TxtFileUtil().writeSendResult(0l,httpUrl+"?"+param);
		HttpPost httppost = new HttpPost(httpUrl);

		httppost.setEntity(paramEntity);
		HttpClient httpclient = new DefaultHttpClient();
		//设置连接超时时间
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
		//设置请求超时时间
		httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 30000);
		//执行
		HttpEntity entity = httpclient.execute(httppost).getEntity();
		//获取返回结果
		if (entity != null && entity.getContentLength() != -1) {
			result = EntityUtils.toString(entity);
			//EmpExecutionContext.error(result);
		}
		LOGGER.info("请求网关地址为{}，参数为{},请求结果为{}",httpUrl,param,result);
		//关闭连接
		httpclient.getConnectionManager().shutdown();
		return result;

	}


}

