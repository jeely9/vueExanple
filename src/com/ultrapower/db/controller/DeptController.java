package com.ultrapower.db.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ultrapower.db.service.DeptService;
import com.ultrapower.webservice.bean.Dept;

@Controller
public class DeptController {
	@Autowired
	@Qualifier("deptService")
	private DeptService deptService;
	
	/**
	 * 根据部门名称查询邮箱信息
	 * @param request
	 * @param response
	 * @param department
	 * @return
	 */
	@RequestMapping("/select_Department")
	public String select_Depar(HttpServletRequest request,HttpServletResponse response, String department){
		Dept de = deptService.select_Department(department);
		request.setAttribute("dept", de);
		return "index.jsp";
	}
	/**
	 * 根据ID单个查询
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping("/selectById")
	public String selectById(HttpServletRequest request,HttpServletResponse response,int id){
		Dept de = deptService.selectById(id);
		request.setAttribute("dept", de);
		return "index.html";
	}
	/**
	 * 新增
	 * @param request
	 * @param response
	 * @param dept
	 * @return
	 */
	@RequestMapping("/insert")
	public String save(HttpServletRequest request,HttpServletResponse response,Dept dept){
		this.deptService.save(dept);
		return "index.jsp";
	}
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public String remove(HttpServletRequest request,HttpServletResponse response,int id){
		int flag = deptService.remove(id);
		if(flag>0){
			return "index.jsp";
		}
		return null;
	}
	/**
	 * 修改
	 * @param request
	 * @param response
	 * @param dept
	 * @return
	 */
	@RequestMapping("/update")
	public String updates(HttpServletRequest request,HttpServletResponse response,Dept dept){
		this.deptService.update(dept);
		return "index.jsp";
	}
}
