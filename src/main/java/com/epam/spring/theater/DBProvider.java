package com.epam.spring.theater;

import com.epam.spring.theater.db.h2.JdbcMovieDaoImpl;

import com.epam.spring.theater.services.AuditoriumService;
import com.epam.spring.theater.services.EventService;
import com.epam.spring.theater.services.UserService;

public class DBProvider {

	public AuditoriumService jdbcAuditorium;
	public EventService jdbcEvent;
	public JdbcMovieDaoImpl jdbcMovie;
	public UserService jdbcUser;
	
	public DBProvider(AuditoriumService auditorium, EventService event, JdbcMovieDaoImpl movie, UserService user) {
		jdbcAuditorium = auditorium;
		jdbcEvent = event;
		jdbcMovie = movie;
		jdbcUser = user;
	}
}