package com.ultrapower.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ultrapower.common.oec.OecUtil;
import com.ultrapower.common.util.UrlUtil;

public class testSensitiveWord {
	private static final Log log = LogFactory.getLog(testSensitiveWord.class);

	//private static UrlUtil urlUtil = new UrlUtil();

	public static void main(String[] args) {
		
		File file = new File("E:\\同济\\语料\\1.txt");
		File out = new File("E:\\同济\\语料\\结果.txt");
		BufferedReader reader = null;
		
		OutputStream outs = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis,"UTF-8"); 
			reader = new BufferedReader(isr);
			outs = new FileOutputStream(out);
			String tempString = null;
			//int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while((tempString = reader.readLine()) != null) {
				
				if(tempString.length()>0){
					JSONObject json=getValue(tempString.trim());
					
					String flag =json.getString("status");
					String sensitive=json.getString("sensitive");
					String emotion0=json.getString("emotion");
					String emotion1=emotion0.replaceAll("\"", "");
					String emotion2=emotion1.replace("{", "");
					String emotion=emotion2.replace("}", "");
					String deptname=json.getString("deptname");
					String ss=UrlUtil.getURLDecoderString(tempString).replace(",","，")+","+sensitive+","+flag+","+deptname+","+emotion;
					outs.write(ss.getBytes());
					outs.write("\n".getBytes());
					
				
					//line++;
				}
				
			}
			reader.close();
			outs.close();
			OecUtil.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e1) {
				}
			}
		}
	}

	public static JSONObject getValue(String content){
		String output=null;
		try {
			content = UrlUtil.getURLEncoderString(content);
			URL url = new URL("http://localhost:8080/TongjiUniversity/monitor/info?content="
					+ content);
			// 打开RestFul链接
			URLConnection urlConn = url.openConnection();  
			HttpURLConnection conn = (HttpURLConnection) urlConn;
			//设置允许输出
			conn.setDoOutput(true);
			//设置不使用缓存
			conn.setUseCaches(false);
			// Get提交模式
			conn.setRequestMethod("GET");// POST GET PUT DELETE
			conn.connect();
			// json格式
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("请求URL失败: " + conn.getResponseCode());
			}
			// 得到远程返回的输入流
			BufferedReader responseBuffer = new BufferedReader(
			new InputStreamReader(conn.getInputStream(), "UTF-8"));
			// 输出远程的处理结果
			while((output = responseBuffer.readLine())!= null) {
				JSONObject json = JSONObject.fromObject(output);
				return  json;						
			}
			// 关闭连接
			conn.disconnect();
		} catch (Exception e) {
			log.error("URL格式或路径错误" + e );
		}
		return null;
	}
	
}
