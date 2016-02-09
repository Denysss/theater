package com.epam.spring.theater;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.spring.theater.domain.Event;
import com.epam.spring.theater.domain.User;
import com.epam.spring.theater.services.DiscountService;

import org.joda.time.LocalDate;
import org.junit.Test;
import junit.framework.TestCase;

/**
 * Unit tests for DiscountService
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
public class TestDiscountService extends TestCase {

	@Autowired
	private DiscountService discountService;

	@Autowired
	private User user_2, user_1;
	@Autowired
	private Event event_1, event_10;

	@Test
	public void testGetDiscountForBirthday() {
		assertEquals("Discount for birthday user", 0.05, discountService.getDiscount(user_1, event_1, LocalDate.now()));
	}

	@Test
	public void testGetDiscountForTenthTicket() {
		assertEquals("Discount for tenth ticket", 0.5, discountService.getDiscount(user_2, event_1, LocalDate.now()));
	}

	@Test
	public void testGetDiscount_NoDiscount() {
		assertEquals("No discount", 0.0, discountService.getDiscount(user_1, event_10, LocalDate.now()));
	}
}
