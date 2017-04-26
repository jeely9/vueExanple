package com.ultrapower.db.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ultrapower.db.service.ItestService;

@Controller
public class TestController {
	@Autowired
	@Qualifier("itestService")
	private ItestService itestService;
	
	@SuppressWarnings("finally")
	@RequestMapping("/del")
	public String delete(HttpServletRequest request,HttpServletResponse response){
		try {		
			System.out.println("11111111");
			itestService.deleteTest();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {			
			return "success";
		}
	}
	
}
