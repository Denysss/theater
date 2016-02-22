package com.epam.spring.theater.db.h2;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.epam.spring.theater.domain.Event;
import com.epam.spring.theater.domain.Ticket;
import com.epam.spring.theater.domain.User;
import com.epam.spring.theater.services.UserService;

public class JdbcUserDaoImpl implements UserService {

	public static final DateTimeZone jodaTzUTC = DateTimeZone.forID("UTC");

	private JdbcTemplate jt;

	public JdbcUserDaoImpl(JdbcTemplate jdbcTemplate) {
		setJdbcTemplate(jdbcTemplate);
	}

	@Override
	public Boolean register(User user) {
		return jt.update(SQL.USER_INSERT_NEW, user.getName(), user.getEmail(),
				new Date(user.getBirthday().toDateTimeAtStartOfDay(jodaTzUTC).getMillis()),
				(user.isAdmin()) ? 1 : 0) > 0;
	}

	@Override
	public Boolean remove(User user) {
		return jt.update(SQL.USER_DELETE, user.getId()) > 0;
	}

	@Override
	public User getUserById(long id) {
		return getFirstUserFromList(jt.query(SQL.USER_SELECT_BY_ID, new Object[] { id }, getUserRowMapper()));
	}

	@Override
	public User getUserByEmail(String email) {
		return getFirstUserFromList(jt.query(SQL.USER_SELECT_BY_EMAIL, new Object[] { email }, getUserRowMapper()));
	}

	@Override
	public User getUserByName(String name) {
		return getFirstUserFromList(jt.query(SQL.USER_SELECT_BY_NAME, new Object[] { name }, getUserRowMapper()));
	}

	@Override
	public List<Ticket> getBookedTickets(User user) {
		return jt.query(SQL.BOOKED_TICKETS_SELECT_BY_USER_ID, new Object[] { user.getId() }, getTicketRowMapper());
	}

	private RowMapper<User> getUserRowMapper() {
		return new RowMapper<User>() {
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				long id = rs.getLong("Id");
				String name = rs.getString("Name");
				String email = rs.getString("Email");
				LocalDate birthday = new LocalDate(rs.getDate("Birthday"));
				boolean admin = rs.getBoolean("IsAdmin");
				User user = new User(id, name, email, birthday, admin);
				return user;
			}
		};
	}

	private RowMapper<Ticket> getTicketRowMapper() {
		return new RowMapper<Ticket>() {
			public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
				long id = rs.getLong("Id");
				User user = getUserById(rs.getLong("UserId"));
				Event event = JdbcEventDaoImpl.getById(rs.getLong("EventId"));
				String seat = rs.getString("Seat");
				double price = rs.getDouble("Price");
				Ticket ticket = new Ticket(id, user, event, seat, price);
				return ticket;
			}
		};
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		jt = jdbcTemplate;

		jt.execute(SQL.BOOKED_TICKETS_CREATE_TABLE);
		jt.execute(SQL.BOOKED_TICKETS_INSERT_DATA);

		jt.execute(SQL.USER_CREATE_TABLE);
		jt.execute(SQL.USER_INSERT_DATA);
	}
	
	private User getFirstUserFromList(List<User> users) {
		if (users == null || users.isEmpty())
			return null;
		else
			return users.get(0);
	}

}
