package util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {

	public static LocalDate convertToDate(String date) {

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			LocalDate _date = LocalDate.parse(date, formatter);
			return _date;

		} catch (DateTimeParseException e) {

			System.out.println("Please, Enter the date in the correct format(dd/MM/yyyy) eg. 16/05/2021");
			return null;
		}
	}
}
