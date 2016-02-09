package com.epam.spring.theater;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.spring.theater.domain.Auditorium;
import com.epam.spring.theater.services.AuditoriumService;

import org.junit.Test;
import junit.framework.TestCase;

/**
 * Unit tests for AuditoriumService
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
public class TestAuditoriumService extends TestCase {

	@Autowired
	private AuditoriumService auditoriumService;

	@Autowired
	private List<Auditorium> auditoriums;

	@Test
	public void testGetAuditoriums() {
		assertEquals("Serveice incudes expected auditoriums", auditoriums, auditoriumService.getAuditoriums());
	}

	@Test
	public void testGetSeatsNumber() {
		for (Auditorium audit : auditoriums) {
			assertTrue("getSeatsNumber retuns string with seats", auditoriumService.getSeatsNumber(audit).length() > 0);
		}
	}

	@Test
	public void testGetVipSeats() {
		for (Auditorium audit : auditoriums) {
			assertTrue("getVipSeats retuns string with vip seats", auditoriumService.getVipSeats(audit).length() > 0);
		}
	}

}
