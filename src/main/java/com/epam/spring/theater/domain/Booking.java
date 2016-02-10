package com.epam.spring.theater.domain;

import java.util.ArrayList;
import java.util.List;

public class Booking {

	private List<Ticket> bookedTickes;

	public Booking(List<Ticket> bookedTickes) {
		setBookedTickes(bookedTickes);
	}
	
	public Booking() {
		setBookedTickes(new ArrayList<Ticket>());
	} 

	public List<Ticket> getTickets() {
		return bookedTickes;
	}

	private void setBookedTickes(List<Ticket> bookedTickes) {
		this.bookedTickes = bookedTickes;
	}

	public boolean addBookedTicket(Ticket ticket) {
		bookedTickes.add(ticket);
		return true;
	}

}
