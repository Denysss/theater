package com.epam.spring.theater;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.spring.theater.domain.User;
import com.epam.spring.theater.services.UserService;

import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;

/**
 * Unit tests for UserService
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
public class TestUserService extends TestCase {

	@Autowired
	private UserService userService;

	@Autowired
	private User admin;
	@Autowired
	private User user_2;

	@Before
	public void beforeTest() {
		// newUser = new User();
	}

	@Test
	public void testRegisterNewUser() {
		long id = 333;
		User newUser = new User(id, "test user name", "test@email.com", "1985-05-06", false);

		assertTrue("New user is registered", userService.register(newUser));
		assertEquals("New user is added to the user list", newUser, userService.getUserById(id));
		assertFalse("the user is not registered again (duplicate)", userService.register(newUser));
	}

	@Test
	public void testRegisterOldUser() {
		assertFalse("User is not registered (duplicate)", userService.register(user_2));
	}

	@Test
	public void testRegisterNull() {
		assertFalse("Null cannot be registered", userService.register(null));
	}

	@Test
	public void testRemoveUser() {
		long id = user_2.getId();

		assertTrue("User is removed", userService.remove(user_2));
		assertEquals("User was removed before, blank user is returned", null, userService.getUserById(id));
		assertTrue("User is removed", userService.register(user_2));
	}

	@Test
	public void testRemoveNull() {
		assertFalse("Null cannot be removed", userService.remove(null));
	}

	@Test
	public void testRemoveUnregisteredUser() {
		User newUser = new User(444, "test user name", "test@email.com", "1985-05-06", false);

		assertFalse("User is not removed", userService.remove(newUser));
	}

	@Test
	public void testGetUserById_0() {
		assertNull("Get user with id=0", userService.getUserById(0));
	}

	@Test
	public void testGetUserByRealId() {
		User foundUser = userService.getUserById(user_2.getId());

		assertEquals("User is found", user_2, foundUser);
	}

	@Test
	public void testGetUserByEmptyEmail() {
		assertNull("User is not found", userService.getUserByEmail(""));
	}

	@Test
	public void testGetUserByUnregisteredEmail() {
		assertNull("User is not found", userService.getUserByEmail("test@unregistered.com"));
	}

	@Test
	public void testGetUserByEmail() {
		assertEquals("User is found", user_2, userService.getUserByEmail(user_2.getEmail()));
		assertEquals("User is found", admin, userService.getUserByEmail(admin.getEmail()));
	}

	@Test
	public void testGetUserByEmptyName() {
		assertNull("User is not found", userService.getUserByName(""));
	}

	@Test
	public void testGetUserByUnregisteredName() {
		assertNull("User is not found", userService.getUserByName("Ivan"));
	}

	@Test
	public void testGetUserByName() {
		assertEquals("User is found", user_2, userService.getUserByName(user_2.getName()));
		assertEquals("User is found", admin, userService.getUserByName(admin.getName()));
	}

	@Test
	public void testGetBookedTickets() {
		assertEquals("There are booked tickets in the user", 9, userService.getBookedTickets(user_2).size());
		assertEquals("There are no booked tickets in the user", 0, userService.getBookedTickets(admin).size());
	}

}
