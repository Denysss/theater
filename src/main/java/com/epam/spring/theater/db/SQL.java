package com.epam.spring.theater.db;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import com.epam.spring.theater.enums.Rating;

public class SQL {

	public static String AUDITORIUM_CREATE_TABLE = "CREATE TABLE AUDITORIUM(name VARCHAR(255), seat VARCHAR(255), isVip BOOLEAN)";

	public static String AUDITORIUM_INSERT_DATA =
			  "INSERT INTO AUDITORIUM VALUES('Red','1',TRUE);"
			+ "INSERT INTO AUDITORIUM VALUES('Red','2',TRUE);"
			+ "INSERT INTO AUDITORIUM VALUES('Red','3',FALSE);"
			+ "INSERT INTO AUDITORIUM VALUES('Green','one',FALSE);"
			+ "INSERT INTO AUDITORIUM VALUES('Green','two',FALSE);"
			+ "INSERT INTO AUDITORIUM VALUES('Green','three',FALSE);";

	public static String AUDITORIUM_SELECT_ALL = "select name, seat, isVip from AUDITORIUM order by name";
	
	public static String AUDITORIUM_SELECT_SEATS_FOR_ONE_AUDITORIUM = "select seat from AUDITORIUM where name=?";
	
	public static String AUDITORIUM_SELECT_VIP_SEATS_FOR_ONE_AUDITORIUM = "select seat from AUDITORIUM where isVip = TRUE and name=?";
	
	public static String EVENT_CREATE_TABLE = "CREATE TABLE EVENT(movieName VARCHAR(255), auditoriumName VARCHAR(255), basePriceForTicket DOUBLE, rating VARCHAR(255), DATE date, TIME time)";

	public static String EVENT_INSERT_DATA =
			  "INSERT INTO AUDITORIUM VALUES('Movie 1','Red',65.50,"+ Rating.high + ",2016-02-18,16:30:00);"
			+ "INSERT INTO AUDITORIUM VALUES('Movie 2','Green',75.50,"+ Rating.mid + ",2016-02-18,14:00:00);";
	
	public static String INSERT = "";
}
