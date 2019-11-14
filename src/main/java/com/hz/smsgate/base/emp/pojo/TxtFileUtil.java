package com.hz.smsgate.base.emp.pojo;


/**
 * 读写文件的工具类
 *
 * @author Administrator
 */
public class TxtFileUtil {


	/**
	 * 记录短信发送网关的返回状态
	 *
	 * @param taskid  任务id ，传入0时为发送请求
	 * @param content
	 * @return
	 */
//	public boolean writeSendResult(long taskid, String result) {
//		try {
//			//日历对象
//			Calendar c = Calendar.getInstance();
//			int year = c.get(Calendar.YEAR);
//			int month = c.get(Calendar.MONTH) + 1;
//			int day = c.get(Calendar.DAY_OF_MONTH);
//
//			String dirPath = year + "/" + month + "/" + day + "/";
//			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//			String fileName = "smshttp";
//
//			String filep = getWebRoot() + StaticValue.FILEDIRNAME + "manualsmstxt/" + dirPath + "smsresult/";
//			String filePath = getWebRoot() + StaticValue.FILEDIRNAME + "manualsmstxt/" + dirPath + "smsresult/" + fileName + ".txt";
//
//			String time = sd.format(new Date());
//			String line = StaticValue.systemProperty.getProperty(StaticValue.LINE_SEPARATOR);
//			//默认记录接收请求
//			String content = "reciveTime=" + time + line + "taskid=" + taskid + line + "resultCode=" + line + result + line;
//			//传入0时为发送请求
//			if (taskid - 0 == 0) {
//				content = "sendTime=" + time + line + "requestUrl=" + line + result + line + line;
//			}
//			File filee = new File(filep);
//			if (!filee.exists()) {
//				filee.mkdirs();
//			}
//			writeToTxtFile(filePath, content);
//		} catch (Exception e) {
//			EmpExecutionContext.error(e, "记录网关发送日志异常！");
//			return false;
//		}
//		return true;
//	}


}
