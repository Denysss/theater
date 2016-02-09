package com.epam.spring.theater.domain.discounts;

import com.epam.spring.theater.services.impl.Service;

public abstract class Discount extends Service {

	protected double discount;

	public Discount(double discount) {
		setDiscount(discount);
	}

	protected double getDiscount() {
		return discount;
	}

	protected void setDiscount(double discount) {
		this.discount = discount;
	}
}
