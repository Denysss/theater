package com.epam.spring.theater.domain;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import com.epam.spring.theater.enums.Rating;

public class Event {

	private Movie movie;
	private Auditorium auditorium;
	private double basePriceForTicket;
	private Rating rating;
	private LocalDate date;
	private LocalTime time;

	public Movie getMovie() {
		return movie;
	}

	protected void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Auditorium getAuditorium() {
		return auditorium;
	}

	public void setAuditorium(Auditorium auditorium) {
		this.auditorium = auditorium;
	}

	public LocalDate getDate() {
		return date;
	}

	protected void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	protected void setTime(LocalTime time) {
		this.time = time;
	}

	public double getBasePriceForTicket() {
		return basePriceForTicket;
	}

	protected void setBasePriceForTicket(double basePriceForTicket) {
		this.basePriceForTicket = basePriceForTicket;
	}

	public Rating getRating() {
		return rating;
	}

	protected void setRating(Rating rating) {
		this.rating = rating;
	}

	public Event(Movie movie, Auditorium auditorium, LocalDate date, LocalTime time, double basePriceForTicket,
			Rating rating) {
		setMovie(movie);
		setAuditorium(auditorium);
		setDate(date);
		setTime(time);
		setBasePriceForTicket(basePriceForTicket);
		setRating(rating);
	}

	public Event(Movie movie, Auditorium auditorium, String date, String time, double basePriceForTicket,
			Rating rating) {
		this(movie, auditorium, LocalDate.parse(date), LocalTime.parse(time), basePriceForTicket, rating);
	}

	@Override
	public String toString() {
		return "Event [movieName=" + movie.getName() + ", auditorium=" + auditorium.getName() + ", date="
				+ date.toString() + ", time=" + time.toString() + ", basePriceForTicket=" + basePriceForTicket
				+ ", rating=" + rating + "]";
	}

}
