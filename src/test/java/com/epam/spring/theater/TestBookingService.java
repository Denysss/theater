package com.epam.spring.theater;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.spring.theater.domain.Booking;
import com.epam.spring.theater.domain.Event;
import com.epam.spring.theater.domain.Ticket;
import com.epam.spring.theater.domain.User;
import com.epam.spring.theater.services.BookingService;

import org.joda.time.LocalDate;
import org.junit.Test;
import junit.framework.TestCase;

/**
 * Unit tests for BookingService
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
public class TestBookingService extends TestCase {

	@Autowired
	private BookingService bookingService;

	@Autowired
	private Booking booking;
	@Autowired
	private Event event_1, event_2, event_3;
	@Autowired
	private User user_2, user_3;

	@Test
	public void testGetTicketPrice_NullEvent() {
		assertEquals("Ticket price", -1.0, bookingService.getTicketPrice(null, "10", user_3));
	}

	@Test
	public void testGetTicketPrice_NullUser() {
		assertEquals("Ticket price", 65.0, bookingService.getTicketPrice(event_1, "10", null));
	}

	@Test
	public void testGetTicketPrice_SeatDoesntExistInAuditorium() {
		assertEquals("Ticket doesn't exist", -1.0, bookingService.getTicketPrice(event_1, "107", user_3));
	}

	@Test
	public void testGetTicketPrice_Vip() {
		assertEquals("Ticket with low price", 65.0 * 1.2, bookingService.getTicketPrice(event_1, "7", user_3));
	}

	@Test
	public void testGetTicketPrice_Discount() {
		assertEquals("Ticket with low price", 65.0 * 0.5, bookingService.getTicketPrice(event_1, "10", user_2));
	}

	@Test
	public void testGetTicketPrice_Rating() {
		assertEquals("Ticket with low price", 70.0 * 0.85, bookingService.getTicketPrice(event_2, "10", user_3));
		assertEquals("Ticket with high price", 65.0 * 1.15, bookingService.getTicketPrice(event_3, "11", user_3));
	}

	@Test
	public void testGetTicketPrice() {
		assertEquals("Ticket price", 65.0, bookingService.getTicketPrice(event_1, "10", user_3));
	}

	@Test
	public void testBookTicket() {
		Ticket ticket1 = new Ticket(user_3, event_1, "1", 65.0);
		Ticket ticket2 = new Ticket(user_3, event_1, "1", 65.0);

		assertTrue("Ticket is booked", bookingService.bookTicket(user_3, ticket1));
		assertTrue("Ticket was added to user", user_3.getTickets().contains(ticket1));
		assertTrue("Ticket was added to booking list", booking.getTickets().contains(ticket1));

		assertFalse("Ticket is not booked with same event and seat", bookingService.bookTicket(user_3, ticket2));
	}

	@Test
	public void testBookTicket_Null() {
		Ticket ticket1 = new Ticket(user_3, event_1, "15", 65.0);

		assertFalse("Ticket is not booked", bookingService.bookTicket(null, ticket1));
		assertFalse("Ticket wasn't added to booking list", booking.getTickets().contains(ticket1));

		assertFalse("Null is not booked like ticket", bookingService.bookTicket(user_3, null));
		assertFalse("Null wasn't added to booking list", booking.getTickets().contains(null));
		assertFalse("Null wasn't added to user", user_3.getTickets().contains(null));

	}

	@Test
	public void testGetTicketsForEvent_Null() {
		assertEquals("Booked tickets are returned", 0,
				bookingService.getTicketsForEvent(null, LocalDate.parse("2016-01-01")).size());
	}

	@Test
	public void testGetTicketsForEvent_OnlyEvent() {
		assertEquals("Booked tickets are returned", 8, bookingService.getTicketsForEvent(event_2).size());
	}

	@Test
	public void testGetTicketsForEvent_Event_StringDate() {
		assertEquals("Booked tickets are returned", 8, bookingService.getTicketsForEvent(event_2, "2016-01-01").size());
	}

	@Test
	public void testGetTicketsForEvent_Event_Date() {
		assertEquals("Booked tickets are returned", 8,
				bookingService.getTicketsForEvent(event_2, LocalDate.parse("2016-01-01")).size());
	}

}
