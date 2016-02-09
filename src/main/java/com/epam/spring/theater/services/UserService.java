package com.epam.spring.theater.services;

import java.util.List;

import com.epam.spring.theater.domain.Ticket;
import com.epam.spring.theater.domain.User;

/**
 * UserService - Manages registered users
 *
 * register remove getById getUserByEmail getUsersByName getBookedTickets
 * 
 */

public interface UserService {

	Boolean register(User user);

	Boolean remove(User user);

	User getUserById(long id);

	User getUserByEmail(String email);

	User getUserByName(String name);

	List<Ticket> getBookedTickets(User user);

}
