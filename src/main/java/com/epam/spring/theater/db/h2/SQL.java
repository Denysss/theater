package com.epam.spring.theater.db.h2;

import com.epam.spring.theater.enums.Rating;

public class SQL {

	/* CREATE TABLES */
	public static String AUDITORIUM_CREATE_TABLE = "CREATE TABLE AUDITORIUM(Id BIGINT AUTO_INCREMENT, Name VARCHAR(255) NOT NULL, Seat VARCHAR(255) NOT NULL, IsVip BOOLEAN NOT NULL)";

	public static String EVENT_CREATE_TABLE = "CREATE TABLE EVENT(Id BIGINT AUTO_INCREMENT, MovieId BIGINT NOT NULL, AuditoriumId BIGINT NOT NULL, BasePriceForTicket DOUBLE, Rating VARCHAR(255), DATE Date, TIME Time)";

	public static String USER_CREATE_TABLE = "CREATE TABLE USER(Id BIGINT AUTO_INCREMENT, Name VARCHAR(255) NOT NULL, Email VARCHAR(255) NOT NULL, Birthday DATE, IsAdmin INT, PRIMARY KEY (Id))";

	public static String BOOKED_TICKETS_CREATE_TABLE = "CREATE TABLE BOOKEDTICKETS(Id BIGINT AUTO_INCREMENT, EventId BIGINT NOT NULL, UserId BIGINT, Seat VARCHAR(255) NOT NULL, Price DOUBLE NOT NULL)";

	public static String MOVIE_CREATE_TABLE = "CREATE TABLE MOVIE(Id BIGINT AUTO_INCREMENT, Name VARCHAR(255) NOT NULL)";

	/* INSERT TEST DATA */
	public static String AUDITORIUM_INSERT_DATA = "INSERT INTO AUDITORIUM VALUES(1L, 'Red','1',TRUE);"
			+ "INSERT INTO AUDITORIUM VALUES(1L, 'Red', '2', TRUE);"
			+ "INSERT INTO AUDITORIUM VALUES(1L, 'Red', '3', FALSE);"
			+ "INSERT INTO AUDITORIUM VALUES(2L, 'Green', 'one', FALSE);"
			+ "INSERT INTO AUDITORIUM VALUES(2L, 'Green', 'two', FALSE);"
			+ "INSERT INTO AUDITORIUM VALUES(2L, 'Green', 'three', FALSE);";

	public static String EVENT_INSERT_DATA = "INSERT INTO EVENT VALUES(null, 1L, 1L, 65.50,'" + Rating.high
			+ "', PARSEDATETIME('2016-02-18', 'yyyy-MM-dd'), PARSEDATETIME('16:30:00', 'HH:mm:ss'));"
			+ "INSERT INTO EVENT VALUES(null, 2L, 2L, 75.50,'" + Rating.mid
			+ "', PARSEDATETIME('2016-02-18', 'yyyy-MM-dd'), PARSEDATETIME('14:00:00', 'HH:mm:ss'));";

	public static String USER_INSERT_DATA = "INSERT INTO USER VALUES(null, 'Bom Bom', 'Bom.Bom@top.com', PARSEDATETIME('1976-01-11', 'yyyy-MM-dd'), TRUE);";

	public static String BOOKED_TICKETS_INSERT_DATA = "INSERT INTO BOOKEDTICKETS VALUES(null, 1L, 1L, '2', 101.0);";

	public static String MOVIE_INSERT_DATA = "INSERT INTO MOVIE VALUES(null, 'Movie 1');"
			+ "INSERT INTO MOVIE VALUES(null, 'Movie 2');";

	/* SELECT */
	public static String AUDITORIUM_SELECT_ALL = "SELECT Id, Name, Seat, IsVip FROM AUDITORIUM ORDER BY Name";

	public static String AUDITORIUM_SELECT_SEATS_FOR_ONE_AUDITORIUM = "SELECT Seat FROM AUDITORIUM WHERE Name=?";

	public static String AUDITORIUM_SELECT_VIP_SEATS_FOR_ONE_AUDITORIUM = "SELECT Seat FROM AUDITORIUM WHERE IsVip = TRUE and Name=?";

	public static String AUDITORIUM_SELECT_BY_ID = "SELECT Id, Name, Seat, IsVip FROM AUDITORIUM WHERE Id=?";

	public static String EVENT_SELECT_ALL = "SELECT DISTINCT E.*, M.*, A.Id, A.Name FROM EVENT AS E INNER JOIN MOVIE AS M ON M.Id=E.MovieId INNER JOIN AUDITORIUM AS A ON A.Id=E.AuditoriumId";
	//Id, E.MovieId, E.AuditoriumId, E.BasePriceForTicket, E.Rating, E.Date, E.Time

	public static String EVENT_SELECT_BY_MOVIENAME = EVENT_SELECT_ALL + " WHERE M.Name=?";

	public static String EVENT_SELECT_NEXT_DATE = EVENT_SELECT_ALL + " WHERE E.Date>=?";

	public static String EVENT_SELECT_FOR_DATE_RANGE = EVENT_SELECT_NEXT_DATE + " AND E.Date<=?";

	public static String USER_SELECT_BY_ID = "SELECT Id, Name, Email, Birthday, IsAdmin FROM USER WHERE Id=?";

	public static String USER_SELECT_BY_EMAIL = "SELECT TOP 1 Id, Name, Email, Birthday, IsAdmin FROM USER WHERE Email=?";

	public static String USER_SELECT_BY_NAME = "SELECT TOP 1 Id, Name, Email, Birthday, IsAdmin FROM USER WHERE Name=?";

	public static String BOOKED_TICKETS_SELECT_BY_USER_ID = "SELECT Id, EventId, UserId, Seat, Price FROM BOOKEDTICKETS WHERE Id=?";

	public static String MOVIE_SELECT_BY_ID = "SELECT Id, Name FROM MOVIE WHERE Id=?";

	/* INSERT */
	public static String EVENT_INSERT_NEW = "INSERT INTO EVENT (Id, MovieId, AuditoriumId, BasePriceForTicket, Rating, Date, Time) VALUES(null,?,?,?,?,?,?)";

	public static String USER_INSERT_NEW = "INSERT INTO USER (Name, Email, Birthday, IsAdmin) VALUES(?,?,?,?)";

	/* UPDATE */
	public static String EVENT_UPDATE_AUDITORIUM = "UPDATE EVENT SET AuditoriumId=? WHERE Id=?";

	/* DELETE */
	public static String USER_DELETE = "DELETE FROM USER WHERE Id=?";

	public static String EVENT_DELETE = "DELETE FROM EVENT WHERE Id=?";
}
