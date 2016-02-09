package com.epam.spring.theater.services;

import java.util.List;

import org.joda.time.LocalDate;

import com.epam.spring.theater.domain.Auditorium;
import com.epam.spring.theater.domain.Event;

/**
 * EventService - Manages events (movie shows). Event contains only basic
 * information, like name, base price for tickets, rating (high, mid, low).
 * Event can be presented on several dates and several times within each day.
 * For each dateTime an Event will be presented only in single auditorium.
 * 
 * create remove getByName getAll getForDateRange(from, to) - returns events for
 * specified date range (OPTIONAL) getNextEvents(to) - returns events from now
 * till the ‘to’ date (OPTIONAL) assignAuditorium(event, auditorium, date) -
 * assign auditorium for event for specific date. Only one auditorium for Event
 * for specific dateTime
 * 
 */

public interface EventService {

	Boolean create(Event event);

	Boolean remove(Event event);

	List<Event> getByName(String name);

	List<Event> getAll();

	List<Event> getForDateRange(LocalDate from, LocalDate to);

	List<Event> getForDateRange(String from, String to);

	List<Event> getNextEvents(LocalDate to);

	List<Event> getNextEvents(String to);

	Boolean assignAuditorium(Event event, Auditorium auditorium);

}
