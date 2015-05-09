package com.elva.spring.service;

import java.util.List;

public interface BusinessService {
	public List<String> findAllNames();
	public int insert(List<String> list);
	public List<String> insertAndQuery(List<String> list);
}
