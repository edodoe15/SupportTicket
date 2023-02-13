package com.ptf.rs.tiket.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class DateConverter {

	public static Date toDate(LocalDate localDate) {
		if (localDate == null)
			return null;

		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	public static LocalDate toLocalDate(Date date) {
	    Instant instant = Instant.ofEpochMilli(date.getTime());
	    return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
	}

	public static java.sql.Date toSqlDate(Date date) {
		if (date == null) {
			return null;
		}

		return new java.sql.Date(date.getTime());
	}

	public static String toSqlDateString(Date date) {
		if (date == null) {
			return null;
		}

		return "'" + (new java.sql.Date(date.getTime())) + "'";
	}

	public static Date parse(Object date) {
		try {
			if (date == null) {
				return null;
			}
			return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(date.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static LocalDate addYears(LocalDate date, int years) {
		LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.now());
		if (years < 0)
			dateTime = dateTime.minusYears(Math.abs(years));
		else
			dateTime = dateTime.plusYears(years);

		return dateTime.toLocalDate();
	}
}
