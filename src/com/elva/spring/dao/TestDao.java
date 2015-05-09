package com.elva.spring.dao;

import java.util.List;

public interface TestDao {
	public List<String> findAllNames();
	public int insert(String name);
	public int insert(List<String> list);
}
