package com.ultrapower.common.util;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 获取系统property属性服务类
 * @author Administrator
 *
 * 使用单例模式
 */
public class PropertyUtil {
	private Log log = LogFactory.getLog(this.getClass());//记录日志	
	private static PropertyUtil instance;
	private Properties properties;
	private PropertyUtil() {
		properties = new Properties();
		try {
			//配置文件所在的位置，一定要在根目录下面
			properties.load(new InputStreamReader(
					PropertyUtil.class.getClassLoader().getResourceAsStream("oec.properties"), "UTF-8"));
		} 
		catch (Exception e){
			log.error("装载oec.properties配置文件出现异常", e);
		}
	}
	
	public static PropertyUtil getInstance(){
		if(instance == null){
			instance = new PropertyUtil();
		}
		return instance;
	}
	
	/**
	 * 获取属性
	 * @param key
	 * @return
	 */
	public String getProperty(String key){
		return this.properties.getProperty(key);
	}
	
}
