package br.com.gotorcidaws.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	public static int getAgeFromDate(Date date) {
		LocalDate birthdate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate currentDate = Calendar.getInstance().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return Period.between(birthdate, currentDate).getYears();
	}
	
	public static int getAgeFromCalendar(Calendar calendar) {
		return getAgeFromDate(calendar.getTime());
	}
}
