package com.epam.spring.theater.db.h2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.epam.spring.theater.domain.Auditorium;
import com.epam.spring.theater.domain.Seat;
import com.epam.spring.theater.helper.Converter;
import com.epam.spring.theater.services.AuditoriumService;

public class JdbcAuditoriumDaoImpl implements AuditoriumService {

	private JdbcTemplate jt;
	private final String separator = ", ";
	
	public JdbcAuditoriumDaoImpl(JdbcTemplate jdbcTemplate) {
		setJdbcTemplate(jdbcTemplate);
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		jt = jdbcTemplate;
		jt.execute(SQL.AUDITORIUM_CREATE_TABLE);
		jt.execute(SQL.AUDITORIUM_INSERT_DATA);
	}
	
	public List<Auditorium> getAuditoriums() {
		
		List<Auditorium> auditoriums = new ArrayList<Auditorium>();
		List<Seat> seats = new ArrayList<Seat>();
		String auditoriumName = "";
		String auditoriumVipSeats = "";
		String currentSeatNumber;
		boolean currentIsVip;

		SqlRowSet srs = jt.queryForRowSet(SQL.AUDITORIUM_SELECT_ALL);
		
		while(srs.next()) {
			if (auditoriumName != srs.getString("Name")) {
				
				if (seats.size() > 0) {
					
					auditoriums.add(getAuditorium(auditoriumName, seats, auditoriumVipSeats));
					
					seats = new ArrayList<Seat>();
					auditoriumVipSeats = "";
				}
				
				auditoriumName = srs.getString("Name");
			}
			
			currentSeatNumber = srs.getString("Seat");
			currentIsVip = srs.getBoolean("IsVip");
			
			seats.add(new Seat(currentSeatNumber, currentIsVip));
			
			if (currentIsVip)
				auditoriumVipSeats = auditoriumVipSeats + currentSeatNumber + ",";
		}

		auditoriums.add(getAuditorium(auditoriumName, seats, auditoriumVipSeats));
		
		return auditoriums;
	}
	
	public String getSeatsNumber(Auditorium auditorium) {
		return getSeatsNumber(auditorium.getName());
	}

	public String getSeatsNumber(String auditorium) {
		String seats = "";
		
		SqlRowSet srs = jt.queryForRowSet(SQL.AUDITORIUM_SELECT_SEATS_FOR_ONE_AUDITORIUM, new Object[] { auditorium });
		
		while(srs.next()) {
			seats = seats + srs.getString("Seat") + separator;
		}
		
		return Converter.removeSeparator(seats, separator);
	}

	public String getVipSeats(Auditorium auditorium) {
		return getVipSeats(auditorium.getName());
	}
	
	public String getVipSeats(String auditorium) {
		String seats = "";
		
		SqlRowSet srs = jt.queryForRowSet(SQL.AUDITORIUM_SELECT_VIP_SEATS_FOR_ONE_AUDITORIUM, new Object[] { auditorium });
		
		while(srs.next()) {
			seats = seats + srs.getString("Seat") + separator;
		}
		
		return Converter.removeSeparator(seats, separator);
	}
	
	private Auditorium getAuditorium(String auditoriumName, List<Seat> seats, String auditoriumVipSeats) {
		auditoriumVipSeats = Converter.removeSeparator(auditoriumVipSeats, separator);
		return new Auditorium(auditoriumName, seats, auditoriumVipSeats);
	}
	
	public Auditorium getById(long id) {
		List<Auditorium> auditoriums = getAuditoriums();
		
		for (Auditorium auditorium : auditoriums) {
			if (auditorium.getId() == id)
				return auditorium;
		}
		
		return null;
	}
}