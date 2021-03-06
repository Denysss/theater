package com.epam.spring.theater.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Auditorium {

	private long id;
	private String name;
	private List<Seat> seats;
	private String vipSeats;

	public Auditorium(String name, List<Seat> seats, String vipSeats) {
		setName(name);
		setSeats(seats);
		setVipSeats(vipSeats);
	}
	
	public Auditorium(long id, String name, List<Seat> seats, String vipSeats) {
		setId(id);
		setName(name);
		setSeats(seats);
		setVipSeats(vipSeats);
	}

	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	protected void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public List<Seat> getVipSeats() {
		List<Seat> vipSeats = new ArrayList<Seat>();

		for (Seat seat : seats) {
			if (seat.isVipSeat()) {
				vipSeats.add(seat);
			}
		}

		return vipSeats;
	}

	protected void setVipSeats(String vipSeats) {
		this.vipSeats = vipSeats;
	}

	private void initVipSeats() {
		if (vipSeats != null && vipSeats.length() > 0) {
			List<String> numbersOfVipSeats;

			numbersOfVipSeats = Arrays.asList(vipSeats.split("\\s*,\\s*"));

			for (Seat seat : seats) {
				if (numbersOfVipSeats.contains(seat.getSeatNumber()))
					seat.setVipSeat(true);
			}
		}
	}

	@Override
	public String toString() {
		String strSize;
		
		if (seats != null)
			strSize = ", NumberOfSeats=" + seats.size();
		else 
			strSize = "";
		
		return "Auditorium [name=" + name + strSize + ", vipSeats=" + vipSeats.toString()
				+ "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
