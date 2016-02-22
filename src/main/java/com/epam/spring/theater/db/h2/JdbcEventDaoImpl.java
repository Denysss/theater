package com.epam.spring.theater.db.h2;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.epam.spring.theater.domain.Auditorium;
import com.epam.spring.theater.domain.Event;
import com.epam.spring.theater.domain.Movie;
import com.epam.spring.theater.enums.Rating;
import com.epam.spring.theater.services.EventService;

public class JdbcEventDaoImpl implements EventService {

	public static final DateTimeZone jodaTzUTC = DateTimeZone.forID("UTC");

	private JdbcTemplate jt;

	public JdbcEventDaoImpl(JdbcTemplate jdbcTemplate) {
		setJdbcTemplate(jdbcTemplate);
	}

	@Override
	public Boolean create(Event event) {
		return jt.update(SQL.EVENT_INSERT_NEW, event.getMovie().getId(), event.getAuditorium().getId(),
				event.getBasePriceForTicket(), event.getRating().toString(),
				new Date(event.getDate().toDateTimeAtStartOfDay(jodaTzUTC).getMillis()),
				new Time(event.getTime().toDateTimeToday().getMillis())) > 0;
	}

	@Override
	public Boolean remove(Event event) {
		return jt.update(SQL.EVENT_DELETE, event.getId()) > 0;
	}

	@Override
	public List<Event> getByName(String name) {
		return jt.query(SQL.EVENT_SELECT_BY_MOVIENAME, new Object[] { name }, getEventRowMapper());
	}

	@Override
	public List<Event> getAll() {
		return jt.query(SQL.EVENT_SELECT_ALL, getEventRowMapper());
	}

	@Override
	public List<Event> getForDateRange(LocalDate from, LocalDate to) {
		return jt.query(SQL.EVENT_SELECT_FOR_DATE_RANGE, new Object[] { from.toDate(), to.toDate() },
				getEventRowMapper());
	}

	@Override
	public List<Event> getForDateRange(String from, String to) {
		return getForDateRange(LocalDate.parse(from), LocalDate.parse(to));
	}

	@Override
	public List<Event> getNextEvents(LocalDate to) {
		return jt.query(SQL.EVENT_SELECT_NEXT_DATE, new Object[] { to.toDate() }, getEventRowMapper());
	}

	@Override
	public List<Event> getNextEvents(String to) {
		return getNextEvents(LocalDate.parse(to));
	}

	@Override
	public Boolean assignAuditorium(Event event, Auditorium auditorium) {
		return jt.update(SQL.EVENT_UPDATE_AUDITORIUM, auditorium.getId(), event.getId()) > 0;
	}

	public static Event getById(long id) {
		// TODO
		return null;
	}

	private RowMapper<Event> getEventRowMapper() {
		return new RowMapper<Event>() {
			public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
				long id = rs.getLong("Id");
				Movie movie = new Movie(rs.getLong("MOVIE.Id"), rs.getString("MOVIE.Name"));
				Auditorium auditorium = new Auditorium(rs.getLong("AUDITORIUM.Id"), rs.getString("AUDITORIUM.Name"), null, "");
				double basePriceForTicket = rs.getDouble("BasePriceForTicket");
				Rating rating = Rating.valueOf(rs.getString("Rating"));
				LocalDate date = new LocalDate(rs.getDate("Date"));
				LocalTime time = new LocalTime(rs.getTime("Time"));
				Event event = new Event(id, movie, auditorium, date, time, basePriceForTicket, rating);
				return event;
			}
		};
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		jt = jdbcTemplate;
		jt.execute(SQL.EVENT_CREATE_TABLE);
		jt.execute(SQL.EVENT_INSERT_DATA);
	}
}
