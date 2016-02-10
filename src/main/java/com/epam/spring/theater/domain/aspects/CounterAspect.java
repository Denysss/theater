package com.epam.spring.theater.domain.aspects;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import com.epam.spring.theater.domain.Event;
import com.epam.spring.theater.domain.Ticket;

/**
 * CounterAspect
 * 
 * count how many times each event was accessed by name, how many times its
 * prices were queried, how many times its tickets were booked.
 * 
 * Store counters in map for now (later could be replaced by DB dao)
 *
 */

@Aspect
@Service("counterAspect")
public class CounterAspect {

	private final String sNull = "null";

	private Map<String, Integer> eventCounterByName;
	private Map<String, Integer> eventCounterByPrice;
	private Map<String, Integer> eventCounterByTicket;

	public CounterAspect() {
		eventCounterByName = new HashMap<String, Integer>();
		eventCounterByPrice = new HashMap<String, Integer>();
		eventCounterByTicket = new HashMap<String, Integer>();
	}

	@Pointcut("execution(* *.getByName(String)) && args(name)")
	private void eventsGetByName(String name) {
	}

	@Pointcut("execution(* *.getTicketPrice(com.epam.spring.theater.domain.Event,..)) && args(event,..)")
	private void eventsGetByPrice(Event event) {
	}

	@Pointcut("execution(boolean *.bookTicket(..,com.epam.spring.theater.domain.Ticket)) && args(..,ticket)")
	private void eventsGetByTicket(Ticket ticket) {
	}

	@Before("eventsGetByName(name)")
	private void countEventsThatWereAccessedByName(String name) {
		if (name == null)
			name = sNull;

		addEventNameToMap(eventCounterByName, name);
	}

	@Before("eventsGetByPrice(event)")
	private void countEventsForWhichPricesWereQueried(Event event) {
		String movieName;

		if (event == null)
			movieName = sNull;
		else
			movieName = event.getMovie().getName();

		addEventNameToMap(eventCounterByPrice, movieName);
	}

	@AfterReturning(pointcut = "eventsGetByTicket(ticket)", returning = "retVal")
	private void countEventsForWhichTicketsWereBooked(Ticket ticket, boolean retVal) {
		if (retVal)
			addEventNameToMap(eventCounterByTicket, ticket.getEvent().getMovie().getName());
	}

	public Map<String, Integer> getStatEventsThatWereAccessedByName() {
		return eventCounterByName;
	}

	public Map<String, Integer> getStatEventsForWhichPricesWereQueried() {
		return eventCounterByPrice;
	}

	public Map<String, Integer> getStatEventsForWhichTicketsWereBooked() {
		return eventCounterByTicket;
	}

	private void addEventNameToMap(Map<String, Integer> map, String key) {
		if (map.containsKey(key))
			map.put(key, map.get(key) + 1);
		else
			map.put(key, 1);
	}
}
