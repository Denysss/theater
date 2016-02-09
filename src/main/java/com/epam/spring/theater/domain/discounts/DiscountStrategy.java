package com.epam.spring.theater.domain.discounts;

import org.joda.time.LocalDate;

import com.epam.spring.theater.domain.Event;
import com.epam.spring.theater.domain.User;

public interface DiscountStrategy {

	double getDiscount(User user, Event event, LocalDate date);

}
