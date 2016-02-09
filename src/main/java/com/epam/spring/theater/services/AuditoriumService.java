package com.epam.spring.theater.services;

import java.util.List;

import com.epam.spring.theater.domain.Auditorium;

/**
 * 
 * AuditoriumService - Returns info about auditoriums and places
 * 
 * Since auditorium information is usually static, store it in some property
 * file. The information that needs to be stored is: - name - number of seats -
 * vip seats (comma-separated list of expensive seats) Several auditoriums can
 * be stored in separate property files, information from them could be injected
 * into the AuditoriumService
 *
 * getAuditoriums() getSeatsNumber() getVipSeats()
 *
 */

public interface AuditoriumService {

	List<Auditorium> getAuditoriums();

	String getSeatsNumber(Auditorium auditorium);

	String getVipSeats(Auditorium auditorium);

}
