package com.epam.spring.theater;

import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.spring.theater.domain.Auditorium;
import com.epam.spring.theater.domain.Event;
import com.epam.spring.theater.domain.Movie;
import com.epam.spring.theater.enums.Rating;
import com.epam.spring.theater.services.EventService;

import junit.framework.TestCase;

/**
 * Unit tests for EventService
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
public class TestEventService extends TestCase {

	@Autowired
	private EventService eventService;

	@Autowired
	private List<Event> events;
	@Autowired
	private Auditorium green, red;
	@Autowired
	private Event event_1;

	@Test
	public void testCreateNewEvent() {
		String movieName = "Test 2";

		assertTrue("New event is not created yet", eventService.getByName(movieName).size() == 0);

		Event newEvent = new Event(new Movie(movieName), green, "2016-02-14", "20:05:00", 105.0, Rating.mid);
		assertTrue("New event is created", eventService.create(newEvent));

		assertTrue("Created event is found", eventService.getByName(movieName).size() == 1);

		assertTrue("The event is removed", eventService.remove(newEvent));
	}

	@Test
	public void testCreateEvent_Null() {
		int numberOfEvent = eventService.getAll().size();

		assertFalse("Event is not created", eventService.create(null));
		assertEquals("Number of events is not changed", numberOfEvent, eventService.getAll().size());
	}

	@Test
	public void testCreateEvent_NotUnique() {
		Event notUniueEvent = events.get(0);
		int numberOfEvent = eventService.getAll().size();

		assertFalse("Event is not created", eventService.create(notUniueEvent));
		assertEquals("Event was not added", numberOfEvent, eventService.getAll().size());
	}

	@Test
	public void testRemoveUncreatedEvent() {
		String movieName = "Test 2";
		Event newEvent = new Event(new Movie(movieName), green, "2016-02-14", "20:05:00", 105.0, Rating.mid);
		int numberOfEvent = eventService.getAll().size();

		assertFalse("Uncreated event cannot be removed", eventService.remove(newEvent));
		assertEquals("Number of events is not changed", numberOfEvent, eventService.getAll().size());
	}

	@Test
	public void testRemoveEvent_Null() {
		int numberOfEvent = eventService.getAll().size();

		assertFalse("Uncreated event cannot be removed", eventService.remove(null));
		assertTrue("Number of events is not changed", eventService.getAll().size() == numberOfEvent);
	}

	@Test
	public void testRemoveEvent() {
		String movieName = "Test 2";
		Event newEvent = new Event(new Movie(movieName), green, "2016-02-14", "20:05:00", 105.0, Rating.mid);

		assertTrue("New event is created", eventService.create(newEvent));
		assertTrue("Created event is found", eventService.getByName(movieName).size() == 1);
		int numberOfEvent = eventService.getAll().size();

		assertTrue("The event is removed", eventService.remove(newEvent));
		assertEquals("Number of events was decreased", numberOfEvent - 1, eventService.getAll().size());
	}

	@Test
	public void testGetByEmptyName() {
		assertTrue("Event is not found", eventService.getByName("").size() == 0);
	}

	@Test
	public void testGetUnregisteredName() {
		assertTrue("Event is not found", eventService.getByName("Proof").size() == 0);
	}

	@Test
	public void testGetByName() {
		String currentMovieName;

		for (Event currentEvent : events) {

			currentMovieName = currentEvent.getMovie().getName();
			for (Event foundEvent : eventService.getByName(currentMovieName))
				assertEquals("Event is found", currentMovieName, foundEvent.getMovie().getName());
		}
	}

	@Test
	public void testGetAll() {
		List<Event> actualEvents = eventService.getAll();

		assertEquals("All events are returned", events.size(), actualEvents.size());

		assertTrue("All event are contained in eventService", events.containsAll(actualEvents));
	}

	@Test
	public void testGetForDateRange_FromTo() {
		assertEquals("Events are returned", 9, eventService.getForDateRange("2016-02-11", "2016-02-12").size());
		assertEquals("Events are returned", 4,
				eventService.getForDateRange(LocalDate.parse("2016-02-12"), LocalDate.parse("2016-02-13")).size());
	}

	public void testGetForDateRange_OnlyFrom() {
		assertEquals("Events are returned", 10, eventService.getForDateRange("2016-02-11", "").size());
		assertEquals("Events are returned", 4,
				eventService.getForDateRange(LocalDate.parse("2016-02-12"), null).size());
	}

	public void testGetForDateRange_OnlyTo() {
		assertEquals("Events are returned", 6, eventService.getForDateRange("", "2016-02-11").size());
		assertEquals("Events are returned", 9,
				eventService.getForDateRange(LocalDate.parse("2016-02-12"), null).size());
	}

	@Test
	public void testGetForDateRange_Empty() {
		int numberOfEvent = eventService.getAll().size();
		assertEquals("Events are returned", numberOfEvent, eventService.getForDateRange("", "").size());
	}

	@Test
	public void testGetNextEvents_To() {
		assertEquals("Events are returned", 9, eventService.getNextEvents("2016-02-12").size());
		assertEquals("Events are returned", 6, eventService.getNextEvents(LocalDate.parse("2016-02-11")).size());
	}

	public void testGetNextEvents_Empty() {
		assertEquals("Events are returned", 10, eventService.getNextEvents("").size());
	}

	public void testAssignAuditorium() {
		assertTrue("Auditorium is assigned to event", eventService.assignAuditorium(event_1, red));

		assertEquals("Event has correct auditorium", red, event_1.getAuditorium());
	}

	public void testAssignAuditorium_Null() {
		assertFalse("Auditorium is not assigned to event", eventService.assignAuditorium(event_1, null));
		assertEquals("Event has correct auditorium", green, event_1.getAuditorium());

		assertFalse("Auditorium is not assigned to event", eventService.assignAuditorium(null, red));
		assertEquals("Event has correct auditorium", green, event_1.getAuditorium());

	}
}
