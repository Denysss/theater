package com.epam.spring.theater.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

import com.epam.spring.theater.domain.Auditorium;
import com.epam.spring.theater.domain.Event;
import com.epam.spring.theater.services.EventService;

public class EventServiceImpl extends Service implements EventService {

	private List<Event> events;

	public EventServiceImpl(List<Event> events) {
		this.events = events;
	}

	public Boolean create(Event event) {

		if (isNull(event, Event.class.getSimpleName()))
			return false;

		if (isUnique(event)) {
			events.add(event);
			System.out.println("Event is created; " + event.toString());
			return true;
		} else {
			System.out.println("The event creating is impossible, event should be unique by Date, "
					+ "Time and Auditorium; " + event.toString());
			return false;
		}
	}

	public Boolean remove(Event event) {

		if (isNull(event, Event.class.getSimpleName()))
			return false;

		System.out.print(event.toString());
		if (events.remove(event)) {
			System.out.println(" is removed");
			return true;
		} else {
			System.out.println(" is not removed (the event doesn't exist)");
			return false;
		}
	}

	public List<Event> getByName(String name) {
		List<Event> list = new ArrayList<Event>();

		for (Event currentEvent : events)
			if (currentEvent.getMovie().getName().equals(name))
				list.add(currentEvent);

		return list;
	}

	public List<Event> getAll() {
		return events;
	}

	public List<Event> getForDateRange(String from, String to) {

		if (from.isEmpty())
			return getNextEvents(to);

		if (to.isEmpty())
			return getAfterEvents(LocalDate.parse(from));

		return getForDateRange(LocalDate.parse(from), LocalDate.parse(to));
	}

	public List<Event> getForDateRange(LocalDate from, LocalDate to) {

		if (from == null)
			return getNextEvents(to);

		if (to == null)
			return getAfterEvents(from);

		List<Event> list = new ArrayList<Event>();

		from = from.minusDays(1);
		to = to.plusDays(1);
		for (Event currentEvent : events)
			if (currentEvent.getDate().isAfter(from) && currentEvent.getDate().isBefore(to))
				list.add(currentEvent);

		return list;
	}

	public List<Event> getNextEvents(String to) {
		if (to.isEmpty())
			return getAll();
		return getNextEvents(LocalDate.parse(to));
	}

	public List<Event> getNextEvents(LocalDate to) {

		if (to == null)
			return getAll();

		List<Event> list = new ArrayList<Event>();

		to = to.plusDays(1);
		for (Event currentEvent : events)
			if (currentEvent.getDate().isBefore(to))
				list.add(currentEvent);

		return list;
	}

	private List<Event> getAfterEvents(LocalDate from) {

		if (from == null)
			return getAll();

		List<Event> list = new ArrayList<Event>();

		from = from.minusDays(1);
		for (Event currentEvent : events)
			if (currentEvent.getDate().isAfter(from))
				list.add(currentEvent);

		return list;
	}

	public Boolean assignAuditorium(Event event, Auditorium auditorium) {

		if (isNull(auditorium, Auditorium.class.getSimpleName()) || isNull(event, Event.class.getSimpleName()))
			return false;

		event.setAuditorium(auditorium);
		System.out.println("Auditorium is assigned to event");
		return true;
	}

	private boolean isUnique(Event event) {

		for (Event currentEvent : events)
			if (currentEvent.getDate().equals(event.getDate()) && currentEvent.getTime().equals(event.getTime())
					&& currentEvent.getAuditorium().getName().equals(event.getAuditorium().getName()))
				return false;

		return true;
	}
}
