package com.ultrapower.db.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ultrapower.db.dao.TestMapper;
import com.ultrapower.db.service.ItestService;
@Service("itestService")
public class TestServiceImpl implements ItestService{
	
	@Autowired
	@Qualifier("testMappper")
	private TestMapper testMappper;
	
	@Override
	public void deleteTest() {
		// TODO Auto-generated method stub
		testMappper.deleteTest();
	}

}
