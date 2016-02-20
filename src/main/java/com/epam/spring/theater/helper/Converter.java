package com.epam.spring.theater.helper;

import java.util.List;

import com.epam.spring.theater.domain.Seat;

public class Converter {

	public static String covert(List<Seat> seats, String separator) {
		String strSeats = "";
		
		for (Seat seat : seats) {
			strSeats = strSeats + seat.getSeatNumber();
			strSeats = strSeats + separator;
		}
		
		return removeSeparator(strSeats, separator);
	}
	
	public static String removeSeparator(String str, String separator) {
		int separatorLen = separator.length();
		int strLen = str.length();
		
		if (separatorLen > 0 && strLen > separatorLen)
			str = str.substring(0, strLen - separatorLen);
		
		return str;
	}
}
