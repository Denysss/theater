package com.epam.spring.theater.services.impl;

import java.util.List;

import com.epam.spring.theater.domain.Auditorium;
import com.epam.spring.theater.domain.Seat;
import com.epam.spring.theater.services.AuditoriumService;

public class AuditoriumServiceImpl extends Service implements AuditoriumService {

	protected static List<Auditorium> auditoriums;

	public AuditoriumServiceImpl(List<Auditorium> auditoriums) {
		AuditoriumServiceImpl.auditoriums = auditoriums;
	}

	public List<Auditorium> getAuditoriums() {
		return auditoriums;
	}

	public String getSeatsNumber(Auditorium auditorium) {

		if (isNull(auditorium, Auditorium.class.getSimpleName()))
			return null;

		for (Auditorium currentAuditorium : auditoriums) {
			if (currentAuditorium.equals(auditorium))
				return convertSeats(auditorium.getSeats());
		}

		return auditoriumIsNotFound(auditorium.toString());
	}

	public String getVipSeats(Auditorium auditorium) {

		if (isNull(auditorium, Auditorium.class.getSimpleName()))
			return null;

		for (Auditorium currentAuditorium : auditoriums) {
			if (currentAuditorium.equals(auditorium)) {
				String vipSeats = convertSeats(auditorium.getVipSeats());
				if (vipSeats.length() > 0)
					return vipSeats;
				else
					return "There are no vip seats";
			}
		}

		return auditoriumIsNotFound(auditorium.toString());
	}

	protected String auditoriumIsNotFound(String auditoriumDetails) {
		System.out.println("Auditorium is not found; " + auditoriumDetails);
		return null;
	}

	protected String convertSeats(List<Seat> seats) {
		String strSeats = "";

		for (Seat seat : seats) {
			strSeats = strSeats + seat.getSeatNumber();
			strSeats = strSeats + ", ";
		}

		if (strSeats.length() > 1)
			strSeats = strSeats.substring(0, strSeats.length() - 2);

		return strSeats;
	}

}
