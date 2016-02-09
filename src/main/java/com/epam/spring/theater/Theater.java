package com.epam.spring.theater;

import java.util.Map;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.spring.theater.domain.aspects.CounterAspect;

public class Theater {
	public static void main(String[] args) {
		System.out.println("Start movie theater");

		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		ServiceProvider sp = (ServiceProvider) ctx.getBean("serviceProvider");
		CounterAspect ca = (CounterAspect) ctx.getBean("counterAspect");
		
		sp.eventService.getByName("Slon");
		sp.eventService.getByName("Slon");
		sp.eventService.getByName("Tick");
		

		
		Map<String, Integer> stat = ca.getStatEventsThatWereAccessedByName();
		
		System.out.println(stat.toString());
		
	}

}