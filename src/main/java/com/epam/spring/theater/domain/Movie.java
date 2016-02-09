package com.epam.spring.theater.domain;

public class Movie {

	private String name;

	public Movie(String name) {
		setName(name);
	}

	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Movie [name=" + name + "]";
	}

}
