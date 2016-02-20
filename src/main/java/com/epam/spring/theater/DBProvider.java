package com.epam.spring.theater;

import com.epam.spring.theater.db.JdbcAuditoriumDao;

public class DBProvider {

	public JdbcAuditoriumDao jdbcAuditoriumDao;
	
	public DBProvider(JdbcAuditoriumDao jdbcAuditoriumDao) {
		this.jdbcAuditoriumDao = jdbcAuditoriumDao;
	}
}
