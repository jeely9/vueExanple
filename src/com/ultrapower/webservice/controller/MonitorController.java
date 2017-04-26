package com.ultrapower.webservice.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ultrapower.common.oec.OecUtil;

/**
 * 敏感关键字监测服务器端
 * 
 * @author wsh
 * 
 */
@Controller
@RequestMapping(value = "monitor")
public class MonitorController {
	private static final Log log = LogFactory.getLog(MonitorController.class);
	@RequestMapping(value="info",method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String monitorbefore(
			/*@PathVariable("content") String tcontent,*/
			HttpServletRequest request, HttpServletResponse response) {
		//获取URL中的参数
		String tcontent = request.getParameter("content");
		
		//tcontent = new UrlUtil().getURLDecoderString(tcontent);
		//创建一个JSONObject对象
		
		try {		
			
			//调用oec
			OecUtil oec=new OecUtil();
			JSONObject json=oec.keyUnitEmotion(tcontent);
			log.info(json.toString());
		    return json.toString();		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
}
