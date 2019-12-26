package utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utility {

	public static String convertDateWithTimeToString(LocalDateTime d) {
		String datum = null;
		String pattern = "dd-MM-yyyy HH:mm";
		DateTimeFormatter formater = DateTimeFormatter.ofPattern(pattern);

		try {
			datum = formater.format(d);
		} catch (Exception e ) {
			 System.out.println("Greska pri konverzija LocalDateTime-a u String");
			 e.printStackTrace();
		}
		return datum;
	}

}
