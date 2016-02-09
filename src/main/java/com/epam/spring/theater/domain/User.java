package com.epam.spring.theater.domain;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

public class User {

	private long id;
	private String name;
	private String email;
	private LocalDate birthday;
	private boolean admin;
	private List<Ticket> tickets;

	public User(long id, String name, String email, LocalDate birthday, boolean admin, List<Ticket> tickets) {
		setId(id);
		setName(name);
		setEmail(email);
		setBirthday(birthday);
		setAdmin(admin);
		setTickets(tickets);
	}

	public User(long id, String name, String email, String birthday, boolean admin) {
		this(id, name, email, LocalDate.parse(birthday), admin, new ArrayList<Ticket>());
	}

	public User() {
		this(0, "", "", LocalDate.parse("1900-01-01"), false, new ArrayList<Ticket>());
	}

	public long getId() {
		return id;
	}

	protected void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	protected void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	protected void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public int getNumberOfPurchasedTickets() {
		return tickets.size();
	}

	public boolean isAdmin() {
		return admin;
	}

	protected void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	protected void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public boolean addTicket(Ticket ticket) {

		if (ticket == null) {
			System.out.println("Ticket is null");
			return false;
		}

		tickets.add(ticket);
		System.out.println("Ticket is added to user");
		return true;
	}

	@Override
	public String toString() {
		int size = 0;
		if (tickets != null)
			size = tickets.size();

		return "User [name=" + name + ", email=" + email + ", birthday=" + birthday + ", admin=" + admin
				+ ", numberOfPurchasedTickets=" + size + "]";
	}

}
