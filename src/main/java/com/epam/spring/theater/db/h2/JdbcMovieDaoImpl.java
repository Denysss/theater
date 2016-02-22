package com.epam.spring.theater.db.h2;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.epam.spring.theater.domain.Movie;

public class JdbcMovieDaoImpl {
	
	private static JdbcTemplate jt;
	
	public JdbcMovieDaoImpl(JdbcTemplate jdbcTemplate) {
		setJdbcTemplate(jdbcTemplate);
	}
	
	public static Movie getById(long id) {
		return jt.queryForObject(SQL.MOVIE_SELECT_BY_ID, new Object[] { id }, new RowMapper<Movie>() {
			public Movie mapRow(ResultSet rs,
					int rowNum) throws SQLException {
					long id = rs.getLong("Id");
					String name = rs.getString("Name");
					Movie movie = new Movie(id, name);
					return movie;
					 }
					 });

	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		jt = jdbcTemplate;
		jt.execute(SQL.MOVIE_CREATE_TABLE);
		jt.execute(SQL.MOVIE_INSERT_DATA);
	}
}
