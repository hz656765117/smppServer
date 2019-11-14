package com.hz.smsgate.base.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public class HttpClientUtils {

	private static Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);

	/**
	 * get方式发起HttpClient请求
	 */
	public static String doGet(String url, Map<String, String> param) {
		String uuidStr = UUID.randomUUID().toString();
		if (!StringUtils.isBlank(url)) {
			LOGGER.info("{}  此次请求，url为{},参数为{}", uuidStr, url, param != null ? param.toString() : null);
		}

		// 创建Httpclient对象
		CloseableHttpClient httpclient = null;

		// 返回结果
		String resultString = "";

		// 执行url之后的响应
		CloseableHttpResponse response = null;

		try {
			httpclient = HttpClients.createDefault();
			// 创建uri
			URIBuilder builder = new URIBuilder(url);

			// 将参数封装到uri里面
			if (param != null) {
				for (String key : param.keySet()) {
					builder.addParameter(key, param.get(key));
				}
			}

			URI uri = builder.build();

			// 创建http GET请求
			HttpGet httpGet = new HttpGet(uri);

			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectTimeout(5000).setConnectionRequestTimeout(5000)
					.setSocketTimeout(5000).build();
			httpGet.setConfig(requestConfig);

			// 执行请求
			response = httpclient.execute(httpGet);

			// 校验获取的response为空的情况
			if (response == null) {
				return resultString;
			}
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
			}

		} catch (IOException e) {
			LOGGER.info("httpClient 获取响应结果失败", e);
		} catch (URISyntaxException e) {
			LOGGER.info("httpClient 参数拼接URI失败", e);
		} catch (Exception e) {
			LOGGER.info("httpClient 其他异常失败", e);
		} finally {
			try {
				if (null != response) {
					response.close();
				}
				if (null != httpclient) {
					httpclient.close();
				}
			} catch (IOException e) {
				LOGGER.error("httpClient httpClient或者响应关系失败", e);
			}
		}
		LOGGER.info("{} 此次请求，响应结果为{}", uuidStr, resultString);
		return resultString;
	}

	public static String doGet(String url) {
		return doGet(url, null);
	}

	/**
	 * post方式发起HttpClient请求
	 */
	public static String doPost(String url, Map<String, String> param, HashMap<String, String> headerMap) {
		String uuidStr = UUID.randomUUID().toString();
		if (!StringUtils.isBlank(url)) {
			LOGGER.info("{} 此次请求，url为{},参数为{}", uuidStr, url, param != null ? param.toString() : null);
		}
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();

		CloseableHttpResponse response = null;

		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			if (headerMap != null && headerMap.size() > 0) {
				for (String key : headerMap.keySet()) {
					Header header = new BasicHeader(key, headerMap.get(key));
					httpPost.setHeader(header);
				}
			}

			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectTimeout(5000).setConnectionRequestTimeout(5000)
					.setSocketTimeout(5000).build();
			httpPost.setConfig(requestConfig);

			// 创建参数列表
			if (param != null) {
				List<NameValuePair> paramList = new ArrayList<NameValuePair>();
				for (String key : param.keySet()) {
					paramList.add(new BasicNameValuePair(key, param.get(key)));
				}
				// 模拟表单
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);

				httpPost.setEntity(entity);
			}
			// 执行http请求
			response = httpClient.execute(httpPost);
			// 校验获取的response为空的情况
			if (response == null) {
				return resultString;
			}

			resultString = EntityUtils.toString(response.getEntity(), "utf-8");

		} catch (UnsupportedEncodingException e) {
			LOGGER.error("httpClient 生成模拟表单对象失败，UnsupportedEncoding异常", e);
		} catch (ParseException e) {
			LOGGER.error("httpClient 获取响应结果失败,Parse转换异常", e);
		} catch (IOException e) {
			LOGGER.error("httpClient 获取响应结果失败，IO流异常", e);
		} catch (Exception e) {
			LOGGER.error("httpClient 请求失败", e);
		} finally {
			try {
				if (null != response) {
					response.close();
				}
				if (null != httpClient) {
					httpClient.close();
				}
			} catch (IOException e) {
				LOGGER.error("httpClient 资源关闭异常，IO流异常", e);
			}
		}
		LOGGER.info("{} 此次请求，响应结果为{}", uuidStr, resultString);
		return resultString;
	}

	public static String doPost(String url) {
		return doPost(url, null, null);
	}

	/**
	 * 以JSON方式发起HttpClient请求
	 */
	public static String doPostJson(String url, String json) {
		String uuidStr = UUID.randomUUID().toString();
		if (!StringUtils.isBlank(url)) {
			LOGGER.info("{} 此次请求，url为{},参数为{}", uuidStr, url, json);
		}
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();

		CloseableHttpResponse response = null;

		String resultString = "";

		try {

			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);

			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectTimeout(5000).setConnectionRequestTimeout(5000)
					.setSocketTimeout(5000).build();
			httpPost.setConfig(requestConfig);


			// 创建请求内容
			StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);

			httpPost.setEntity(entity);
			// 执行http请求
			response = httpClient.execute(httpPost);

			// 校验获取的response为空的情况
			if (response == null) {

				return resultString;
			}

			resultString = EntityUtils.toString(response.getEntity(), "utf-8");

		} catch (Exception e) {
			LOGGER.error("httpClient 请求失败", e);
		} finally {
			try {
				if (null != response) {
					response.close();
				}
				if (null != httpClient) {
					httpClient.close();
				}

			} catch (IOException e) {
				LOGGER.error("httpClient 资源关闭异常，IO流异常", e);

			}
		}
		LOGGER.info("{} 此次请求，响应结果为{}", uuidStr, resultString);
		return resultString;
	}


	public static void main(String[] args) {
		String ss = doGet("http://localhost:8080/benxibank_war_exploded/smsSameBatchSend?username=GGAANN&password=GGAANN&message=%b2%e2%ca%d4%d2%bb%cf%c2&epid=123&linkid=2222333332&subcode=1234&phone=15111163924,15111163919,15915845213");
		System.out.println(ss);
	}

}
