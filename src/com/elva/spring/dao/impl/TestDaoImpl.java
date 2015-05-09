package com.elva.spring.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.elva.spring.dao.TestDao;

public class TestDaoImpl implements TestDao{
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate j){
		this.jdbcTemplate = j;
	}
	public List<String> findAllNames() {
		return jdbcTemplate.query("select * from test", new ResultSetExtractor<List<String>>(){
			public List<String> extractData(ResultSet rs) throws SQLException,DataAccessException {
				List<String> list = new ArrayList<String>();
				while(rs.next()){
					list.add(rs.getString("name"));
				}
				return list;
			}
		});
	}
	public int insert(String name) {
		return insert(Arrays.asList(name));
	}
	public int insert(List<String> list) {
		for(String name:list){
			this.jdbcTemplate.execute("insert into test(name) values('"+name+"')");
		}
		return list.size();
	}

}
