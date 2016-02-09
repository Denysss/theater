package com.epam.spring.theater.domain;

import java.util.List;

public class Booking {

	private List<Ticket> bookedTickes;

	public Booking(List<Ticket> bookedTickes) {
		setBookedTickes(bookedTickes);
	}

	public List<Ticket> getTickets() {
		return bookedTickes;
	}

	private void setBookedTickes(List<Ticket> bookedTickes) {
		this.bookedTickes = bookedTickes;
	}

	public boolean addBookedTicket(Ticket ticket) {

		if (ticket == null) {
			System.out.println("Ticket is null");
			return false;
		}

		bookedTickes.add(ticket);
		return true;
	}

}
