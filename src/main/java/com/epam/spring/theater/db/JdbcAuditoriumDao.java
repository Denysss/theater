package com.epam.spring.theater.db;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.epam.spring.theater.domain.Auditorium;
import com.epam.spring.theater.domain.Seat;
import com.epam.spring.theater.helper.Converter;

public class JdbcAuditoriumDao {

	private JdbcTemplate jt;
	private final String separator = ", ";
	
	public JdbcAuditoriumDao(JdbcTemplate jdbcTemplate) {
		jt = jdbcTemplate;
		jt.execute(SQL.AUDITORIUM_CREATE_TABLE);
		jt.execute(SQL.AUDITORIUM_INSERT_DATA);
	}
	
	public List<Auditorium> selectAll() {
		
		List<Auditorium> auditoriums = new ArrayList<Auditorium>();
		List<Seat> seats = new ArrayList<Seat>();
		String auditoriumName = "";
		String auditoriumVipSeats = "";
		String currentSeatNumber;
		boolean currentIsVip;

		SqlRowSet srs = jt.queryForRowSet(SQL.AUDITORIUM_SELECT_ALL);
		
		while(srs.next()) {
			if (auditoriumName != srs.getString("name")) {
				
				if (seats.size() > 0) {
					
					auditoriums.add(getAuditorium(auditoriumName, seats, auditoriumVipSeats));
					
					seats = new ArrayList<Seat>();
					auditoriumVipSeats = "";
				}
				
				auditoriumName = srs.getString("name");
			}
			
			currentSeatNumber = srs.getString("seat");
			currentIsVip = srs.getBoolean("isVip");
			
			seats.add(new Seat(currentSeatNumber, currentIsVip));
			
			if (currentIsVip)
				auditoriumVipSeats = auditoriumVipSeats + currentSeatNumber + ",";
		}

		auditoriums.add(getAuditorium(auditoriumName, seats, auditoriumVipSeats));
		
		return auditoriums;
	}
	
	public String getSeatsNumber(String auditoriumName) {
		String seats = "";
		
		SqlRowSet srs = jt.queryForRowSet(SQL.AUDITORIUM_SELECT_SEATS_FOR_ONE_AUDITORIUM, new Object[] { auditoriumName });
		
		while(srs.next()) {
			seats = seats + srs.getString("seat") + separator;
		}
		
		return Converter.removeSeparator(seats, separator);
	}
	
	public String getVipSeats(String auditoriumName) {
		String seats = "";
		
		SqlRowSet srs = jt.queryForRowSet(SQL.AUDITORIUM_SELECT_VIP_SEATS_FOR_ONE_AUDITORIUM, new Object[] { auditoriumName });
		
		while(srs.next()) {
			seats = seats + srs.getString("seat") + separator;
		}
		
		return Converter.removeSeparator(seats, separator);
	}
	
	private Auditorium getAuditorium(String auditoriumName, List<Seat> seats, String auditoriumVipSeats) {
		auditoriumVipSeats = Converter.removeSeparator(auditoriumVipSeats, separator);
		return new Auditorium(auditoriumName, seats, auditoriumVipSeats);
	}	
}