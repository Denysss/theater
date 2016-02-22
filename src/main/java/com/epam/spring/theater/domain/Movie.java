package com.epam.spring.theater.domain;

public class Movie {

	private long id;
	private String name;

	public Movie(String name) {
		setName(name);
	}
	
	public Movie(long id, String name) {
		setId(id);
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
