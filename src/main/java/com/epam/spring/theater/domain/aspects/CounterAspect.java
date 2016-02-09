package com.epam.spring.theater.domain.aspects;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

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
	
	private Map<String, Integer> eventCounterByName;
	private Map<String, Integer> eventCounterByPrice;
	private Map<String, Integer> eventCounterByTicket;

	public CounterAspect() {
		eventCounterByName = new HashMap<String,Integer>();
	}
	
	@Pointcut("execution(* *.getByName(String)) && args(String)")
	private void eventsGetByName() {}

	@Before("eventsGetByName() && args(name)")
	private void countEventsThatWereAccessedByName(JoinPoint jp, String name) {
		
		if (eventCounterByName.containsKey(name))
			eventCounterByName.put(name, eventCounterByName.get(name) + 1);
		else
			eventCounterByName.put(name, 1);
	}
	
	public Map<String, Integer> getStatEventsThatWereAccessedByName() {
		return eventCounterByName;
	}
}
