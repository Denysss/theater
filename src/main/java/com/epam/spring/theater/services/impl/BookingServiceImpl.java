package com.epam.spring.theater.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

import com.epam.spring.theater.domain.Booking;
import com.epam.spring.theater.domain.Event;
import com.epam.spring.theater.domain.Seat;
import com.epam.spring.theater.domain.Ticket;
import com.epam.spring.theater.domain.User;
import com.epam.spring.theater.services.BookingService;
import com.epam.spring.theater.services.DiscountService;

public class BookingServiceImpl extends Service implements BookingService {

	private double coefficientForVipSeat = 1.0;
	private double coefficientForHighRating = 1.0;
	private double coefficientForMidRating = 1.0;
	private double coefficientForLowRating = 1.0;

	private Booking booking;
	private static DiscountService discountService;

	public BookingServiceImpl(Booking booking, DiscountService discountService) {
		this.booking = booking;
		BookingServiceImpl.discountService = discountService;
	}

	public double getTicketPrice(Event event, String seat, User user) {

		if (isNull(event, Event.class.getSimpleName())) {
			return -1.0;
		} else {
			Seat wantedSeat = new Seat(seat);
			List<Seat> auditSeats = event.getAuditorium().getSeats();

			for (Seat currentSeat : auditSeats) {
				if (currentSeat.hasSameSeatNumber(wantedSeat)) {
					double price;

					price = event.getBasePriceForTicket();

					if (currentSeat.isVipSeat())
						price = price * coefficientForVipSeat;

					switch (event.getRating()) {
					case high:
						price = price * coefficientForHighRating;
						break;

					case mid:
						price = price * coefficientForMidRating;
						break;

					case low:
						price = price * coefficientForLowRating;
						break;
					}

					if (!isNull(user, User.class.getSimpleName()))
						price = price * (1.0 - discountService.getDiscount(user, event, LocalDate.now()));

					return price;
				}
			}

			System.out.println("Seat #" + seat + " doesn't exist in " + event.getAuditorium().getName() + " auditorium "
					+ event.getAuditorium().getSeats().toString());
			return -1.0;
		}
	}

	public boolean bookTicket(User user, Ticket ticket) {

		if (isNull(user, User.class.getSimpleName()) || isNull(ticket, Ticket.class.getSimpleName()))
			return false;

		if (!isBookedTicket(ticket))
			if (booking.addBookedTicket(ticket)) {
				user.addTicket(ticket);
				return true;
			}

		return false;
	}

	private boolean isBookedTicket(Ticket ticket) {
		List<Ticket> bookedTickets = getTicketsForEvent(ticket.getEvent());

		for (Ticket currentTicket : bookedTickets)
			if (currentTicket.getSeat().equals(ticket.getSeat()))
				return true;
		return false;
	}

	public List<Ticket> getTicketsForEvent(Event event) {
		return getTicketsForEvent(event, LocalDate.now());
	}

	public List<Ticket> getTicketsForEvent(Event event, String date) {
		if (date.isEmpty())
			return getTicketsForEvent(event, LocalDate.now());

		return getTicketsForEvent(event, LocalDate.parse(date));
	}

	public List<Ticket> getTicketsForEvent(Event event, LocalDate date) {

		if (isNull(event, Event.class.getSimpleName()))
			return new ArrayList<Ticket>();

		List<Ticket> bookedTickedForEvent = new ArrayList<Ticket>();
		for (Ticket ticket : booking.getTickets()) {
			if (ticket.getEvent().equals(event))
				bookedTickedForEvent.add(ticket);
		}

		return bookedTickedForEvent;
	}

	public double getCoefficientForVipSeat() {
		return coefficientForVipSeat;
	}

	public void setCoefficientForVipSeat(double coefficientForVipSeat) {
		this.coefficientForVipSeat = coefficientForVipSeat;
	}

	public double getCoefficientForHighRating() {
		return coefficientForHighRating;
	}

	public void setCoefficientForHighRating(double coefficientForHighRating) {
		this.coefficientForHighRating = coefficientForHighRating;
	}

	public double getCoefficientForMidRating() {
		return coefficientForMidRating;
	}

	public void setCoefficientForMidRating(double coefficientForMidRating) {
		this.coefficientForMidRating = coefficientForMidRating;
	}

	public double getCoefficientForLowRating() {
		return coefficientForLowRating;
	}

	public void setCoefficientForLowRating(double coefficientForLowRating) {
		this.coefficientForLowRating = coefficientForLowRating;
	}

}
