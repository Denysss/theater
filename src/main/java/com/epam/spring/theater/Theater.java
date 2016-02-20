package com.epam.spring.theater;

import java.util.List;
import java.util.Map;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.spring.theater.domain.Auditorium;
import com.epam.spring.theater.domain.Event;
import com.epam.spring.theater.domain.Ticket;
import com.epam.spring.theater.domain.User;
import com.epam.spring.theater.domain.aspects.CounterAspect;
import com.epam.spring.theater.domain.aspects.DiscountAspect;
import com.epam.spring.theater.domain.aspects.LuckyWinnerAspect;

public class Theater {
	public static void main(String[] args) {
		System.out.println("Start movie theater");

		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		ServiceProvider sp = (ServiceProvider) ctx.getBean("serviceProvider");
		CounterAspect ca = (CounterAspect) ctx.getBean("counterAspect");
		LuckyWinnerAspect lwa = (LuckyWinnerAspect) ctx.getBean("luckyWinnerAspect");
		DiscountAspect da = (DiscountAspect) ctx.getBean("discountAspect");
		
		
		
		DBProvider db = (DBProvider) ctx.getBean("dbProvider");
		
		List<Auditorium> audit = db.jdbcAuditoriumDao.selectAll();
		System.out.println(audit.toString());
		
		String seats = db.jdbcAuditoriumDao.getSeatsNumber("Red");
		System.out.println(seats);
		
		String vipSeats = db.jdbcAuditoriumDao.getVipSeats("Green");
		System.out.println(vipSeats);
		
		
		
		Event event1 = sp.eventService.getAll().get(0);
		Event event2 = sp.eventService.getAll().get(2);
		Event event3 = sp.eventService.getAll().get(6);

		User user = sp.userService.getUserByName("Igor Ponomarchuk");
		User user2 = sp.userService.getUserByName("Alina Subotova");

		Ticket ticket1 = new Ticket(event1, "11", sp.discountService.getDiscount(user, event1, null));
		sp.bookingService.bookTicket(user, ticket1);

		Ticket ticket2 = new Ticket(event1, "13", sp.discountService.getDiscount(user, event1, null));
		sp.bookingService.bookTicket(user, ticket2);

		Ticket ticket3 = new Ticket(event2, "13", sp.discountService.getDiscount(user, event1, null));
		sp.bookingService.bookTicket(user, ticket3);

		Ticket ticket4 = new Ticket(event2, "13", sp.discountService.getDiscount(user, event1, null));
		sp.bookingService.bookTicket(user, ticket4);
		sp.bookingService.bookTicket(user, null);

		Ticket ticket5 = new Ticket(event3, "11", sp.discountService.getDiscount(user, event1, null));
		sp.bookingService.bookTicket(user, ticket5);

		Ticket ticket6 = new Ticket(event3, "12", sp.discountService.getDiscount(user, event1, null));
		sp.bookingService.bookTicket(user, ticket6);

		Ticket ticket7 = new Ticket(event3, "13", sp.discountService.getDiscount(user, event1, null));
		sp.bookingService.bookTicket(user, ticket7);

		Ticket ticket8 = new Ticket(event3, "14", sp.discountService.getDiscount(user, event1, null));
		sp.bookingService.bookTicket(user, ticket8);

		Ticket ticket9 = new Ticket(event3, "15", sp.discountService.getDiscount(user, event1, null));
		sp.bookingService.bookTicket(user, ticket9);

		Ticket ticket10 = new Ticket(event3, "16", sp.discountService.getDiscount(user, event1, null));
		sp.bookingService.bookTicket(user, ticket10);

		sp.eventService.getByName("Slon");
		sp.eventService.getByName("Slon");
		sp.eventService.getByName("Tick");
		sp.eventService.getByName("The Green Mile");

		sp.bookingService.getTicketPrice(event1, "5", user);
		sp.bookingService.getTicketPrice(null, null, null);
		sp.bookingService.getTicketPrice(event1, "5", user);
		sp.bookingService.getTicketPrice(event1, "10", user);

		sp.bookingService.getTicketPrice(event2, "6", user2);
		sp.bookingService.getTicketPrice(event2, "4", null);
		sp.bookingService.getTicketPrice(event2, "7", user2);
		sp.bookingService.getTicketPrice(event2, "8", user2);

		sp.discountService.getDiscount(user, event1, null);
		sp.discountService.getDiscount(user2, event1, null);
		sp.discountService.getDiscount(null, event1, null);

		System.out.println("---------------- CounterAspect ----------------------");
		Map<String, Integer> stat = ca.getStatEventsThatWereAccessedByName();
		System.out.println("1. how many times each event was accessed by name:");
		System.out.println(stat.toString());
		System.out.println();

		System.out.println("2. how many times its prices were queried:");
		stat = ca.getStatEventsForWhichPricesWereQueried();
		System.out.println(stat.toString());
		System.out.println();

		System.out.println("3. how many times its tickets were booked:");
		stat = ca.getStatEventsForWhichTicketsWereBooked();
		System.out.println(stat.toString());
		System.out.println();

		System.out.println("---------------- LuckyWinnerAspect ------------------");
		System.out.println(user.getName() + " has gotten " + user.getNumberOfBookedLuckyTickets() + " lucky tickets of "
				+ user.getNumberOfPurchasedTickets());
		System.out.println("price of ticket 1 = " + ticket1.getPrice());
		System.out.println("price of ticket 2 = " + ticket2.getPrice());
		System.out.println("price of ticket 3 = " + ticket3.getPrice());
		System.out.println("price of ticket 4 = " + ticket4.getPrice());
		System.out.println();

		System.out.println("---------------- DiscountAspect ---------------------");
		System.out.println(da.getDiscountUserStatistics().toString());
		System.out.println(da.getDiscountStrategyStatistics().toString());
		System.out.println("Total: " + da.getDiscountTotalStatistics());
		System.out.println("-----------------------------------------------------");

	}

}