package com.epam.spring.theater;

import com.epam.spring.theater.services.*;

public class ServiceProvider {

	public UserService userService;
	public AuditoriumService auditoriumService;
	public BookingService bookingService;
	public DiscountService discountService;
	public EventService eventService;

	public ServiceProvider(UserService userService, AuditoriumService auditoriumService, BookingService bookingService,
			DiscountService discountService, EventService eventService) {

		this.userService = userService;
		this.auditoriumService = auditoriumService;
		this.bookingService = bookingService;
		this.discountService = discountService;
		this.eventService = eventService;
	}

}
