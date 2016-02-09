package com.epam.spring.theater;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Theater {
	public static void main(String[] args) {
		System.out.println("Start movie theater");

		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		ServiceProvider sp = (ServiceProvider) ctx.getBean("serviceProvider");

	}

}
