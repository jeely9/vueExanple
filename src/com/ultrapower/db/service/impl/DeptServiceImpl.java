package com.ultrapower.db.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ultrapower.db.dao.DeptMapper;
import com.ultrapower.db.service.DeptService;
import com.ultrapower.webservice.bean.Dept;
import com.ultrapower.webservice.bean.DeptDto;

@Service("deptService")
public class DeptServiceImpl implements DeptService {
	@Autowired
	@Qualifier("DeptMapper")
	private DeptMapper deptMapper;

	@Override
	public List<Dept> getAll(DeptDto dto) {
		return deptMapper.selectDept(dto);
	}

	@Override
	public Dept selectById(int id) {
		return deptMapper.selectById(id);
	}

	@Override
	public int remove(int id) {
		return deptMapper.delete(id);
	}

	@Override
	public void update(Dept dept) {
		this.deptMapper.update(dept);
	}

	@Override
	public void save(Dept dept) {
		this.deptMapper.insert(dept);
	}

	@Override
	public List<Dept> selectAll() {
		return deptMapper.select();
	}


	@Override
	public Dept select_Department(String department) {
		return deptMapper.select_Department(department);
	}
}
