package com.epam.spring.theater.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.epam.spring.theater.domain.Ticket;
import com.epam.spring.theater.domain.User;
import com.epam.spring.theater.services.UserService;

public class UserServiceImpl extends Service implements UserService {

	List<User> users;

	public UserServiceImpl(List<User> users) {
		this.users = users;
	}

	public Boolean register(User user) {

		if (isNull(user, User.class.getSimpleName()) || user.getEmail().isEmpty() || user.getName().length() < 3)
			return false;

		for (User currentUser : users) {

			if (currentUser.getName().equals(user.getName()) && currentUser.getEmail().equals(user.getEmail())) {
				System.out.println("User is already exist with same name and email; " + user.toString());
				return false;
			}

			if (currentUser.getId() == user.getId()) {
				System.out.println("User is already exist with same id; " + user.toString());
				return false;
			}
		}

		users.add(user);
		System.out.println("User is registered; " + user.toString());
		return true;
	}

	public Boolean remove(User user) {

		if (isNull(user, User.class.getSimpleName()))
			return false;

		System.out.print(user.toString());
		if (users.remove(user)) {
			System.out.println(" is removed");
			return true;
		} else {
			System.out.println(" is not removed (the user doesn't exist)");
			return false;
		}
	}

	public User getUserById(long id) {

		for (User user : users)
			if (user.getId() == id)
				return user;

		return null;
	}

	public User getUserByEmail(String email) {

		for (User user : users)
			if (user.getEmail().equals(email))
				return user;

		return null;
	}

	public User getUserByName(String name) {

		for (User user : users)
			if (user.getName().equals(name))
				return user;

		return null;
	}

	public List<Ticket> getBookedTickets(User user) {

		if (isNull(user, User.class.getSimpleName()))
			return new ArrayList<Ticket>();

		for (User currentUser : users) {
			if (currentUser.equals(user)) {
				return currentUser.getTickets();
			}
		}

		return new ArrayList<Ticket>();
	}

}
