package com.epam.spring.theater.domain;

public class Ticket {

	private Event event;
	private String seat;
	private double price;

	public Ticket(Event event, String seat, double price) {
		setEvent(event);
		setSeat(seat);
		setPrice(price);
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

	protected void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Ticket [ movieName=" + event.getMovie().getName() + ", date=" + event.getDate() + ", time="
				+ event.getTime() + ", seat=" + seat + ", price=" + price + "]";
	}
}
