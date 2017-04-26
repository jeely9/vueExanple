package com.ultrapower.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ultrapower.webservice.bean.Dept;
import com.ultrapower.webservice.bean.DeptDto;

@Component("DeptMapper")
public interface DeptMapper {
	/**
	 * 根据条件查询分页
	 * @param dto
	 * @param map
	 * @return
	 */
	public List<Dept> selectDept(DeptDto dto);
	/**
	 * 查询所有
	 * @return
	 */
	public List<Dept> select();
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
	public Dept selectById(@Param("id")int id);
	/**
	 * 新增
	 * @param dept
	 * @return
	 */
	public void insert(Dept dept);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int delete(@Param("id")int id);
	/**
	 * 修改
	 * @param dept
	 * @return
	 */
	public void update(Dept dept);
}
