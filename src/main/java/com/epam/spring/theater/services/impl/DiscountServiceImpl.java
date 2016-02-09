package com.epam.spring.theater.services.impl;

import java.util.List;

import org.joda.time.LocalDate;

import com.epam.spring.theater.domain.Event;
import com.epam.spring.theater.domain.User;
import com.epam.spring.theater.domain.discounts.DiscountStrategy;
import com.epam.spring.theater.services.DiscountService;

public class DiscountServiceImpl extends Service implements DiscountService {

	private List<DiscountStrategy> discontStrategies;

	public DiscountServiceImpl(List<DiscountStrategy> discontStrategies) {
		setDiscontStrategies(discontStrategies);
	}

	public double getDiscount(User user, Event event, String date) {
		return getDiscount(user, event, LocalDate.parse(date));
	}

	public double getDiscount(User user, Event event, LocalDate date) {
		double maxDiscont = 0.0;
		double discont;

		if (isNull(user, User.class.getSimpleName()))
			return 0.0;

		if (isNull(event, Event.class.getSimpleName()))
			return 0.0;

		for (DiscountStrategy strategy : discontStrategies) {
			discont = strategy.getDiscount(user, event, date);
			if (discont > maxDiscont && discont < 1.0)
				maxDiscont = discont;
		}
		return maxDiscont;
	}

	private void setDiscontStrategies(List<DiscountStrategy> discontStrategies) {
		this.discontStrategies = discontStrategies;
	}

}
