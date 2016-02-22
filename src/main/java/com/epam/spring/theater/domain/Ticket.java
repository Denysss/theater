package com.epam.spring.theater.domain;

public class Ticket {

	private long id;
	private User user;
	private Event event;
	private String seat;
	private double price;

	public Ticket(long id, User user, Event event, String seat, double price) {
		setId(id);
		setEvent(event);
		setSeat(seat);
		setPrice(price);
	}

	public Ticket(User user, Event event, String seat, double price) {
		this(0L, user, event, seat, price);
	}

	public long getId() {
		return id;
	}
	
	protected void setId(long id) {
		this.id = id;
	}
	
	public Event getEvent() {
		return event;
	}

	protected void setEvent(Event event) {
		this.event = event;
	}

	public String getSeat() {
		return seat;
	}

	protected void setSeat(String seat) {
		this.seat = seat;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
