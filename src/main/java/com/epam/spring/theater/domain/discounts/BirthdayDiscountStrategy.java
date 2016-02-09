package com.epam.spring.theater.domain.discounts;

import org.joda.time.LocalDate;

import com.epam.spring.theater.domain.Event;
import com.epam.spring.theater.domain.User;

public class BirthdayDiscountStrategy extends Discount implements DiscountStrategy {

	public BirthdayDiscountStrategy(double discount) {
		super(discount);
	}

	public double getDiscount(User user, Event event, LocalDate date) {
		LocalDate eventDate = event.getDate();
		LocalDate birthday = user.getBirthday();

		if (birthday.getDayOfMonth() == eventDate.getDayOfMonth()
				&& birthday.getMonthOfYear() == eventDate.getMonthOfYear())
			return discount;

		return 0.0;
	}

}
