package com.epam.spring.theater.domain.aspects;

import java.util.Random;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import com.epam.spring.theater.domain.Ticket;
import com.epam.spring.theater.domain.User;

/**
 * LuckyWinnerAspect
 * 
 * every time the bookTicket method is executed perform the checkLucky method
 * for the user that based on some randomness will return true or false. If user
 * is lucky, the ticketPrice changes to zero and ticket is booked, thus user
 * pays nothing. Store the information about this lucky event into the user
 * object (like some system messages or so) - OPTIONAL
 * 
 */
@Aspect
@Service("luckyWinnerAspect")
public class LuckyWinnerAspect {

	private Random randomizer = new Random();

	@Pointcut("execution(boolean *.addBookedTicket(com.epam.spring.theater.domain.Ticket)) && args(ticket)")
	private void drawLottery(Ticket ticket) {
	}

	@Pointcut("execution(boolean *.bookTicket(com.epam.spring.theater.domain.User,com.epam.spring.theater.domain.Ticket)) && args(user,ticket)")
	private void setLotteryResultToUser(User user, Ticket ticket) {
	}

	@Around("drawLottery(ticket)")
	private boolean countEventsForWhichTicketsWereBooked(ProceedingJoinPoint pjp, Ticket ticket) throws Throwable {

		if (checkLucky())
			ticket.setPrice(0.0);

		return (Boolean) pjp.proceed(new Object[] { ticket });
	}

	@AfterReturning(pointcut = "setLotteryResultToUser(user, ticket)", returning = "retVal")
	private void countEventsForWhichTicketsWereBooked(Ticket ticket, User user, boolean retVal) {
		if (retVal && ticket.getPrice() == 0.0)
			user.winLuckyTicket();
	}

	private boolean checkLucky() {
		return randomizer.nextBoolean();
	}
}