package com.ultrapower.webservice.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ultrapower.common.util.UrlUtil;

/**
 * 敏感关键字监测客户端
 * 
 * @author wsh
 * 
 */
public class Main {
	private static final Log log = LogFactory.getLog(Main.class);// 记录日志
	//private static UrlUtil urlUtil = new UrlUtil();

	public static void main(String[] args) {
		test("帮室友征婚 绩点满五 德语大牛 优质暖男 还不快出手[来][来][来]");
	}
		
	public static void test(String scontent){
		try {
			//通过一个表示URL地址的字符串构建一个URL对象
			scontent = UrlUtil.getURLEncoderString(scontent);

			URL url=new URL("http://localhost:8080/TongjiUniversity/monitor/info?content="+scontent);
			
			//打开RestFul链接  
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();  		               
			// Get提交模式  
			conn.setRequestMethod("GET");//POST GET PUT DELETE  
			//json格式
			if (conn.getResponseCode() != 200) {
                 throw new RuntimeException("请求URL失败: "
                               + conn.getResponseCode());
            }
			//得到远程返回的输入流
			BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(conn.getInputStream() ,"UTF-8"));
			//输出远程的处理结果
			String output;
            while ((output = responseBuffer.readLine()) != null) {
            	JSONObject obj = JSONObject.fromObject(output);
				System.out.println(obj);
				String flag =obj.getString("status");
				String sensitive=obj.getString("sensitive");
				String emotion=obj.getString("emotion");
				//return sensitive+"|"+flag;
				System.out.println(sensitive+"|"+flag+"|"+emotion);
            }
            //关闭连接
            conn.disconnect();
		} catch (Exception e) {
			log.error("URL格式或路径错误"+e+scontent);
		
		}		
	}
	
}
