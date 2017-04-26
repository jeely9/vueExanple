package com.ultrapower.db.service;

import java.util.List;

import com.ultrapower.webservice.bean.Dept;
import com.ultrapower.webservice.bean.DeptDto;

public interface DeptService {
	/**
	 * 根据条件查询分页
	 * @param dto
	 * @param map
	 * @return
	 */
	public List<Dept> getAll(DeptDto dto);
	/**
	 * 查询所有
	 * @return
	 */
	public List<Dept> selectAll();
	/**
	 * 根据部门查询邮件信息
	 * @param department
	 * @return
	 */
	public Dept select_Department(String department);
	/**
	 * 查询单个信息
	 * @param id
	 * @return
	 */
	public Dept selectById(int id);
	/**
	 * 新增
	 * @param dept
	 * @return
	 */
	public void save(Dept dept);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int remove(int id);
	/**
	 * 修改
	 * @param dept
	 * @return
	 */
	public void update(Dept dept);
}
