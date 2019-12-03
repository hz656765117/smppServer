package com.hz.smsgate.base.constants;


import com.hz.smsgate.base.emp.pojo.WGParams;
import com.hz.smsgate.base.smpp.pojo.SessionKey;
import com.hz.smsgate.base.utils.FileUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huangzhuo
 * @date 2019/9/2 15:51
 */
public class StaticValue {

	//资源文件地址
	public static String RESOURCE_HOME = "";


	//SP账号资源文件地址
	public static String SP_RESOURCE_HOME = "";

	//服务端 端口
	public static int SERVER_PORT = 2776;

	//心跳频率
	public static int ENQUIRE_LINK_TIME = 20000;


	/**
	 * opt通道
	 */
	public static List<String> CHANNEL_OPT_LIST = new ArrayList<>();
	/**
	 * 营销通道
	 */
	public static List<String> CHANNEL_YX_LIST = new ArrayList<>();
	/**
	 * 通知通道
	 */
	public static List<String> CHANNEL_TZ_LIST = new ArrayList<>();



	public static String CHANNEL_1 = "555";

	public static String CHANNEL_CM_1 = "882";
	public static String CHANNEL_CM_2 = "883";
	public static String CHANNEL_CM_3 = "884";
	public static String CHANNEL_CM_4 = "885";
	public static String CHANNEL_CM_5 = "886";
	public static String CHANNEL_CM_6 = "887";
	public static String CHANNEL_CM_7 = "8881";
	public static String CHANNEL_CM_8 = "8882";
	public static String CHANNEL_CM_9 = "8883";
	public static String CHANNEL_CM_10 = "8884";
	public static String CHANNEL_CM_11 = "8885";

	public static String SYSTEMID_CM_1 = "HaloPushingLtd";
	public static String SYSTEMID_CM_2 = "HaloMakreting";

	public static String SYSTEMID_MK_1 = "HP01";
	public static String SYSTEMID_MK_2 = "HP02";
	public static String SYSTEMID_MK_3 = "HP03";
	public static String SYSTEMID_MK_4 = "HP04";
	public static String SYSTEMID_MK_5 = "HP0101";


	public static String SYSTEMID_ALEX = "Alex";


	public static String CHANNEL_MK_1 = "778";
	public static String CHANNEL_MK_2 = "779";
	public static String CHANNEL_MK_3 = "771";
	public static String CHANNEL_MK_4 = "772";
	public static String CHANNEL_MK_5 = "773";

	public static String CHANNEL_MK_6 = "7779";
	public static String CHANNEL_MK_7 = "7771";
	public static String CHANNEL_MK_8 = "7772";
	public static String CHANNEL_MK_9 = "7773";

	public static String CHANNEL_MK_10 = "7774";
	public static String CHANNEL_MK_11 = "7775";

	public static String CHANNEL_MK_12 = "7776";
	public static String CHANNEL_MK_13 = "7777";
	public static String CHANNEL_MK_14 = "7778";


	public static String CHANNEL_YN_1 = "668";




	public static List<String> CHANNEL_MK_LIST = new ArrayList<>();

	//马来西亚区号
	public static String AREA_CODE_MALAYSIA = "60";

	//越南区号
	public static String AREA_CODE_VIETNAM = "84";


	//越南区号
	public static String AREA_CODE_PHILIPPINES = "63";

	public static Map<String, SessionKey> CHANNL_REL = null;

	public static Map<SessionKey,  String[]> CHANNL_SP_REL = null;


	public static String WEB_GATE = "";

	public static String SP_ID = "";

	public static String SP_PWD = "";

	public static String TYPE = "";


	static {

		TYPE = SystemGlobals.getValue("montnets.type","0");

		RESOURCE_HOME = SystemGlobals.getValue("resource.home");

		SP_RESOURCE_HOME = SystemGlobals.getValue("sp.resource.home");

		SERVER_PORT = SystemGlobals.getIntValue("server.port", 2776);

		ENQUIRE_LINK_TIME = SystemGlobals.getIntValue("enquire.link.time", 20000);

		CHANNL_REL = new LinkedHashMap<>();
		CHANNL_REL.put(CHANNEL_CM_1, new SessionKey(SYSTEMID_CM_1, "CMK"));
		CHANNL_REL.put(CHANNEL_CM_2, new SessionKey(SYSTEMID_CM_1, "HAALOO"));
		CHANNL_REL.put(CHANNEL_CM_3, new SessionKey(SYSTEMID_CM_1, "HALLOO"));
		CHANNL_REL.put(CHANNEL_CM_4, new SessionKey(SYSTEMID_CM_1, "FINLNX"));
		CHANNL_REL.put(CHANNEL_CM_5, new SessionKey(SYSTEMID_CM_1, "INLINX"));
		CHANNL_REL.put(CHANNEL_CM_6, new SessionKey(SYSTEMID_CM_1, "CASHTP"));
		CHANNL_REL.put(CHANNEL_CM_7, new SessionKey(SYSTEMID_CM_1, "MONEYB"));
		CHANNL_REL.put(CHANNEL_CM_10, new SessionKey(SYSTEMID_CM_1, "RUPEEP"));

		CHANNL_REL.put(CHANNEL_CM_8, new SessionKey(SYSTEMID_CM_2, "CASHTP"));
		CHANNL_REL.put(CHANNEL_CM_9, new SessionKey(SYSTEMID_CM_2, "MONEYB"));
		CHANNL_REL.put(CHANNEL_CM_11, new SessionKey(SYSTEMID_CM_2, "RUPEEP"));


		CHANNL_REL.put(CHANNEL_1, new SessionKey("INFIPRO", "BYTDNC"));


		CHANNL_REL.put(CHANNEL_MK_2, new SessionKey(SYSTEMID_MK_1, "etracker"));
		CHANNL_REL.put(CHANNEL_MK_6, new SessionKey(SYSTEMID_MK_1, "Tantan"));
		CHANNL_REL.put(CHANNEL_MK_10, new SessionKey(SYSTEMID_MK_1, "Haaloo"));

		CHANNL_REL.put(CHANNEL_MK_3, new SessionKey(SYSTEMID_MK_2, "etracker"));
		CHANNL_REL.put(CHANNEL_MK_7, new SessionKey(SYSTEMID_MK_2, "Tantan"));

		CHANNL_REL.put(CHANNEL_MK_4, new SessionKey(SYSTEMID_MK_3, "etracker"));
		CHANNL_REL.put(CHANNEL_MK_8, new SessionKey(SYSTEMID_MK_3, "Tantan"));
		CHANNL_REL.put(CHANNEL_MK_11, new SessionKey(SYSTEMID_MK_3, "Haaloo"));

		CHANNL_REL.put(CHANNEL_MK_5, new SessionKey(SYSTEMID_MK_4, "etracker"));
		CHANNL_REL.put(CHANNEL_MK_9, new SessionKey(SYSTEMID_MK_4, "Tantan"));

		CHANNL_REL.put(CHANNEL_MK_12, new SessionKey(SYSTEMID_MK_5, "etracker"));
		CHANNL_REL.put(CHANNEL_MK_13, new SessionKey(SYSTEMID_MK_5, "Tantan"));
		CHANNL_REL.put(CHANNEL_MK_14, new SessionKey(SYSTEMID_MK_5, "Haaloo"));

		CHANNL_REL.put(CHANNEL_YN_1, new SessionKey(SYSTEMID_ALEX, "Alex"));




		CHANNEL_MK_LIST.add(CHANNEL_MK_1);

		CHANNEL_MK_LIST.add(CHANNEL_MK_2);
		CHANNEL_MK_LIST.add(CHANNL_REL.get(CHANNEL_MK_2).getSenderId());

		CHANNEL_MK_LIST.add(CHANNEL_MK_3);
		CHANNEL_MK_LIST.add(CHANNL_REL.get(CHANNEL_MK_3).getSenderId());

		CHANNEL_MK_LIST.add(CHANNEL_MK_4);
		CHANNEL_MK_LIST.add(CHANNL_REL.get(CHANNEL_MK_4).getSenderId());

		CHANNEL_MK_LIST.add(CHANNEL_MK_5);
		CHANNEL_MK_LIST.add(CHANNL_REL.get(CHANNEL_MK_5).getSenderId());

		CHANNEL_MK_LIST.add(CHANNEL_MK_6);
		CHANNEL_MK_LIST.add(CHANNL_REL.get(CHANNEL_MK_6).getSenderId());

		CHANNEL_MK_LIST.add(CHANNEL_MK_7);
		CHANNEL_MK_LIST.add(CHANNL_REL.get(CHANNEL_MK_7).getSenderId());

		CHANNEL_MK_LIST.add(CHANNEL_MK_8);
		CHANNEL_MK_LIST.add(CHANNL_REL.get(CHANNEL_MK_8).getSenderId());

		CHANNEL_MK_LIST.add(CHANNEL_MK_9);
		CHANNEL_MK_LIST.add(CHANNL_REL.get(CHANNEL_MK_9).getSenderId());

		CHANNEL_MK_LIST.add(CHANNEL_MK_10);
		CHANNEL_MK_LIST.add(CHANNL_REL.get(CHANNEL_MK_10).getSenderId());

		CHANNEL_MK_LIST.add(CHANNEL_MK_11);
		CHANNEL_MK_LIST.add(CHANNL_REL.get(CHANNEL_MK_11).getSenderId());



		CHANNEL_OPT_LIST.add(CHANNEL_CM_6);
		CHANNEL_OPT_LIST.add(CHANNL_REL.get(CHANNEL_CM_6).getSenderId());

		CHANNEL_OPT_LIST.add(CHANNEL_MK_2);
		CHANNEL_OPT_LIST.add(CHANNL_REL.get(CHANNEL_MK_2).getSenderId());
		CHANNEL_OPT_LIST.add(CHANNEL_MK_6);
		CHANNEL_OPT_LIST.add(CHANNL_REL.get(CHANNEL_MK_6).getSenderId());
		CHANNEL_OPT_LIST.add(CHANNEL_MK_10);
		CHANNEL_OPT_LIST.add(CHANNL_REL.get(CHANNEL_MK_10).getSenderId());

		CHANNEL_YX_LIST.add(CHANNEL_YN_1);
		CHANNEL_YX_LIST.add(CHANNL_REL.get(CHANNEL_YN_1).getSenderId());

		CHANNEL_YX_LIST.add(CHANNEL_MK_4);
		CHANNEL_YX_LIST.add(CHANNL_REL.get(CHANNEL_MK_4).getSenderId());

		CHANNEL_YX_LIST.add(CHANNEL_MK_8);
		CHANNEL_YX_LIST.add(CHANNL_REL.get(CHANNEL_MK_8).getSenderId());

		CHANNEL_YX_LIST.add(CHANNEL_MK_11);
		CHANNEL_YX_LIST.add(CHANNL_REL.get(CHANNEL_MK_11).getSenderId());

		CHANNL_SP_REL = FileUtils.getSpConfigs(StaticValue.SP_RESOURCE_HOME);
	}
}
