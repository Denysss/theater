package com.epam.spring.theater.services;

import org.joda.time.LocalDate;

import com.epam.spring.theater.domain.Event;
import com.epam.spring.theater.domain.User;

/**
 * DiscountService - Counts different discounts for purchased tickets
 * 
 * getDiscount(user, event, date) - returns discount for each ticket for the
 * user on particular event
 * 
 * DiscountStrategy - single class with logic for calculating discount Birthday
 * strategy - give 5% if user has birthday Every 10th ticket - give 50% for
 * every 10th ticket purchased by user
 * 
 * All discount strategies should be injected as list into the DiscountService.
 * The getDiscount method will execute each strategy to get max available
 * discount.
 * 
 * Define DiscountService with all strategies as separate beans in separate
 * configuration file (either separate XML or separate Java config class)
 * 
 */

public interface DiscountService {

	double getDiscount(User user, Event event, LocalDate date);

}
