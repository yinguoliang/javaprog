package com.elva.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.elva.spring.dao.TestDao;
import com.elva.spring.service.BusinessService;

public class BusinessServiceImpl implements BusinessService{
	@Autowired
	TestDao testDao;
	public void setTestDao(TestDao testDao){
		this.testDao = testDao;
	}
	public List<String> findAllNames() {
		return testDao.findAllNames();
	}
	public int insert(List<String> list) {
		return testDao.insert(list);
	}
	public List<String> insertAndQuery(List<String> list) {
		this.insert(list);
		List<String> ret = findAllNames();
		this.insert(list);
		return ret;
	}
}
