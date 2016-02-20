package com.epam.spring.theater.db;

import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcEventDao {

	private JdbcTemplate jt;
	
	public JdbcEventDao(JdbcTemplate jdbcTemplate) {
		jt = jdbcTemplate;
		jt.execute(SQL.AUDITORIUM_CREATE_TABLE);
		jt.execute(SQL.AUDITORIUM_INSERT_DATA);
	}
}
