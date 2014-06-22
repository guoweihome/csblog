package com.csdig.cms.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.io.OutputFormat;

public class SendMessage {
	private String innerUrl = "http://i.sms.sohu.com/WLS/smsaccess/mt";// (内网环境)
	private String timestamp = "";
	private String appid = "100154";
	private String enc;
	private String linkid = "0";
	private String priority = "3";
	private String key = "20131211165424606SMSPLATACCESS327455";

	public static String sendSms(String mobile, String content) throws Exception {
		return (new SendMessage()).sendSmsCore(mobile, content);
	}

	/**
	 * 短信下发接口
	 */
	public String sendSmsCore(String mobile, String content) throws Exception {
		String xmldata = "";
		timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		// System.out.println("content："+content);
		// content = UTF2GBK.GBK2Unicode(content);
		// content = UTF2GBK.Unicode2GBK(content);

		String md5Str = appid + mobile + content + timestamp + key;

		enc = DigestUtils.md5Hex(md5Str.getBytes("UTF-8"));

		// System.out.println("enc:"+enc);
		content = URLEncoder.encode(content, "UTF-8");

		StringBuffer responseUrl = new StringBuffer(innerUrl);

		responseUrl.append("?appid=" + appid);
		responseUrl.append("&destnumber=" + mobile);
		responseUrl.append("&content=" + content);
		responseUrl.append("&timestamp=" + timestamp);
		responseUrl.append("&linkid=" + linkid);
		responseUrl.append("&priority=" + priority);
		responseUrl.append("&enc=" + enc);

		// System.out.println(mobile+"  content: "+content);
		URL url = new URL(responseUrl.toString());

		HttpURLConnection huConn = (HttpURLConnection) url.openConnection();
		huConn.setDoInput(true);
		huConn.setDoOutput(true);
		huConn.setRequestProperty("content-type", "text/xml");
		huConn.setRequestMethod("POST");
		huConn.connect();

		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("GBK");

		huConn.getOutputStream().write(xmldata.getBytes());
		huConn.getOutputStream().flush();
		huConn.getOutputStream().close();
		InputStream responseBody = huConn.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(responseBody, "gbk"));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		reader.close();
		String newStr = buffer.toString();

		String returnString = "无返回状态";

		if ("0".equals(newStr)) {
			returnString = "成功";
		} else if ("1".equals(newStr)) {
			returnString = "ip拒绝";
		} else if ("2".equals(newStr)) {
			returnString = "发送条数超限、异常";
		} else if ("3".equals(newStr)) {
			returnString = "关键字过滤失败";
		} else if ("4".equals(newStr)) {
			returnString = "无效appid";
		} else if ("5".equals(newStr)) {
			returnString = "Columnid未授权";
		} else if ("6".equals(newStr)) {
			returnString = "鉴权失败（根据客户端IP， appid， mobile， columnid， type进行鉴权），enc校验失败";
		} else if ("7".equals(newStr)) {
			returnString = "接入通道已过期";
		} else if ("-1".equals(newStr)) {
			returnString = "发送失败";
		} else if ("-2".equals(newStr)) {
			returnString = "业务处理异常";
		}
		System.out.println("" + returnString);
		// logger.info("手机号码  "+mobile +":"+ returnString);
		return returnString + ",content:" + content;

	}

	public static String sendSmsWithPart(String mobile, String content) {
		try {
			content = content.replaceAll(" ", "_");
			content = content.replaceAll("%", "%25");

			System.out.println("content=" + content);
			if (mobile != null && !mobile.trim().equals("")) {
				if (content != null && !content.trim().equals("")) {
					int length = content.length();
					int quotient = length / 60 + 1;
					String sms = "";
					for (int i = 0; i < quotient; i++) {
						if (i < quotient - 1) {
							sms = content.substring(i * 60, (i + 1) * 60);
						} else {
							sms = content.substring(i * 60);
						}

						if (sms != null && !sms.trim().equals("")) {
							System.out.println("sms=" + sms);
							String xmldata = "";
							StringBuilder builder = new StringBuilder();
							builder.append(" http://10.10.82.62/service/sms.jsp?mobile=");
							builder.append(mobile);
							builder.append("&content=");
							builder.append(sms);
							URL url = new URL(builder.toString());
							HttpURLConnection huConn = (HttpURLConnection) url.openConnection();
							huConn.setDoInput(true);
							huConn.setDoOutput(true);
							huConn.setRequestProperty("content-type", "text/xml");
							huConn.setRequestMethod("POST");
							huConn.connect();

							huConn.getOutputStream().write(xmldata.getBytes());
							huConn.getOutputStream().flush();
							huConn.getOutputStream().close();

							int code = huConn.getResponseCode();
							System.out.println(mobile + ":send sms code=" + code);
							TimeUnit.MILLISECONDS.sleep(500);
						}

					}
				}

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return mobile + ":发送失败;";
		}

		return mobile + ":发送成功;";
	}

	public static void main(String[] args) {
		// SendMessage.sendSms("13811932661", "你的登陆密码是:12b32d，5分钟后密码将失效。");
	}

}