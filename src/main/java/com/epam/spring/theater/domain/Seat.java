package com.epam.spring.theater.domain;

public class Seat {

	private String seatNumber;
	private boolean vipSeat = false;

	public Seat(String seatNumber) {
		setSeatNumber(seatNumber);
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	protected void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public boolean isVipSeat() {
		return vipSeat;
	}

	protected void setVipSeat(boolean vipSeat) {
		this.vipSeat = vipSeat;
	}

	public boolean hasSameSeatNumber(Seat seat) {
		return seat.getSeatNumber().equals(seatNumber);
	}

	@Override
	public String toString() {
		return "Seat [" + seatNumber + ", " + vipSeat + "]";
	}
}
