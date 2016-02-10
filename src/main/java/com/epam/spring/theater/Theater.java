package com.epam.spring.theater;

import java.util.Map;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.spring.theater.domain.Event;
import com.epam.spring.theater.domain.Ticket;
import com.epam.spring.theater.domain.User;
import com.epam.spring.theater.domain.aspects.CounterAspect;
import com.epam.spring.theater.domain.aspects.LuckyWinnerAspect;

public class Theater {
	public static void main(String[] args) {
		System.out.println("Start movie theater");

		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		ServiceProvider sp = (ServiceProvider) ctx.getBean("serviceProvider");
		CounterAspect ca = (CounterAspect) ctx.getBean("counterAspect");
		LuckyWinnerAspect lwa = (LuckyWinnerAspect) ctx.getBean("luckyWinnerAspect");
		
		Event event1 = sp.eventService.getAll().get(0);
		Event event2 = sp.eventService.getAll().get(2);
		
		User user = sp.userService.getUserByName("Igor Ponomarchuk");
		
		Ticket ticket1 = new Ticket(event1, "11", 75.01);
		Ticket ticket2 = new Ticket(event1, "13", 65.02);
		Ticket ticket3 = new Ticket(event2, "13", 75.02);
		Ticket ticket4 = new Ticket(event2, "13", 80.04);
		
		sp.eventService.getByName("Slon");
		sp.eventService.getByName("Slon");
		sp.eventService.getByName("Tick");
		sp.eventService.getByName("The Green Mile");
		
		sp.bookingService.getTicketPrice(event1, "5", user);
		sp.bookingService.getTicketPrice(null, null, null);
		sp.bookingService.getTicketPrice(event1, "5", user);
		sp.bookingService.getTicketPrice(event1, "10", user);

		sp.bookingService.bookTicket(user, ticket1);
		sp.bookingService.bookTicket(user, ticket2);
		sp.bookingService.bookTicket(user, ticket3);
		sp.bookingService.bookTicket(user, ticket4);
		sp.bookingService.bookTicket(user, null);
		
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
		System.out.println(user.getName() + " has gotten " + user.getNumberOfBookedLuckyTickets() + " lucky tickets of " + user.getNumberOfPurchasedTickets());
		System.out.println("price of ticket 1 = " + ticket1.getPrice());
		System.out.println("price of ticket 2 = " + ticket2.getPrice());
		System.out.println("price of ticket 3 = " + ticket3.getPrice());
		System.out.println("price of ticket 4 = " + ticket4.getPrice());
		System.out.println("-----------------------------------------------------");
		
	}

}