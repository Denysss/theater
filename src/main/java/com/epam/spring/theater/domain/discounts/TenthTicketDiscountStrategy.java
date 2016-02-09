package com.epam.spring.theater.domain.discounts;

import org.joda.time.LocalDate;

import com.epam.spring.theater.domain.Event;
import com.epam.spring.theater.domain.User;

public class TenthTicketDiscountStrategy extends Discount implements DiscountStrategy {

	public TenthTicketDiscountStrategy(double discount) {
		super(discount);
	}

	public double getDiscount(User user, Event event, LocalDate date) {

		if (user.getNumberOfPurchasedTickets() % 10 == 9)
			return discount;

		return 0.0;
	}

}
