package com.hz.smsgate.business.controller;

import com.google.gson.Gson;
import com.hz.smsgate.base.constants.SmppServerConstants;
import com.hz.smsgate.base.constants.StaticValue;
import com.hz.smsgate.base.utils.FileUtils;
import com.hz.smsgate.base.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TestController {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
	@Autowired
	public   RedisUtil redisUtil ;

	@RequestMapping(value = "/push")
	public void demoTest(){
		redisUtil.set("1","value22222");
		redisUtil.lPush("duilie","111");
		redisUtil.lPush("duilie","222");
		redisUtil.lPush("duilie","333");
	}




	@RequestMapping(value = "/pop")
	public void pop(){
		Object duilie = redisUtil.rPop("duilie");
		Object duilie1 = redisUtil.rPop("duilie");
		Object duilie2 = redisUtil.rPop("duilie");


	}

	@RequestMapping(value = "/mapAdd")
	public void mapAdd(){

		redisUtil.hmSet("map","key1","value1");
		redisUtil.hmSet("map","key2","value2");
		redisUtil.hmSet("map","key3","value3");
		redisUtil.hmSet("map","key4","value4");
		redisUtil.hmSet("map","key5","value5");
	}


	@RequestMapping(value = "/mapGet")
	public void mapGet(){
		Object map = redisUtil.hmGetAll(SmppServerConstants.CM_MSGID_CACHE);
		Map<Object,Object> mm = (Map<Object,Object>)map;
		System.out.println(map);
	}

	@RequestMapping(value = "/mapGet1")
	public void mapGet1(){
		Object map = redisUtil.hmGetAll("webMsgIdCache");
		Map<Object,Object> mm = (Map<Object,Object>)map;
		System.out.println(map);
	}

	@RequestMapping(value = "/mapDel")
	public void mapDel(){
		Object map = redisUtil.hmRemove("map","key3");
		System.out.println(map);
	}

	@RequestMapping("test1")
	public String getSomething1() throws Exception {
		LOGGER.debug("aaaaaaaaaaadebugdebugdebugtest1111123434");
		LOGGER.info("aaaaaaaaaaaaaaaaaaainfoinfoinfotest1111123434");
		LOGGER.error("aaaaaaaaaaaaaaaaerrorerrorerrortest1111123434");
		LOGGER.warn("aaaaaaaaaaaaaawarnwarnwarntest1warnwarn111123434");
//		SmppSession session0 = ClientInit.session0;
//		if (session0 == null) {
//			session0 = ClientInit.clientBootstrap.bind(ClientInit.config0, ClientInit.sessionHandler);
//		}
//
//		String text160 = "\u20AC Lorem [ipsum] dolor sit amet, consectetur adipiscing elit. Proin feugiat, leo id commodo tincidunt, nibh diam ornare est, vitae accumsan risus lacus sed sem metus.";
//		byte[] textBytes = CharsetUtil.encode(text160, CharsetUtil.CHARSET_GSM);
//		SubmitSm submit0 = new SubmitSm();
//
//		submit0.setSourceAddress(new Address((byte) 0x03, (byte) 0x00, "40404"));
//		submit0.setDestAddress(new Address((byte) 0x01, (byte) 0x01, "44555519205"));
//		try{
//			submit0.setShortMessage(textBytes);
//			SubmitSmResp submitResp = session0.submit(submit0, 10000);
//		}catch (Exception e){
//
//		}


		return "test111";
	}




	@CrossOrigin
	@RequestMapping("getResource")
	public String getResource() {
		LOGGER.info("获取资源列表");
		List<String> strings = FileUtils.readFileByLines(StaticValue.RESOURCE_HOME);
		Gson gson = new Gson();
		String listJson = gson.toJson(strings);
		return listJson;
	}

	@CrossOrigin
	@RequestMapping("getResourceById/{id}")
	public String getResourceById(@PathVariable(name = "id") String id) {
		LOGGER.info("根据id获取资源，id={}", id);
		List<String> strings = FileUtils.readFileByLines(StaticValue.RESOURCE_HOME);
		String s = strings.get(Integer.parseInt(id));
		return s;
	}









	@RequestMapping("/login")
	public String welcome() {


		return "index";

	}


}
