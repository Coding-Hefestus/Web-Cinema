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
	
	public static String convertDateWithTimeToStringToDB(LocalDateTime d) {
		String datum = null;
		String pattern = "yyyy-MM-dd HH:mm"; //"dd-MM-yyyy HH:mm";
		DateTimeFormatter formater = DateTimeFormatter.ofPattern(pattern);

		try {
			datum = formater.format(d);
		} catch (Exception e ) {
			 System.out.println("Greska pri konverzija LocalDateTime-a u String");
			 e.printStackTrace();
		}
		return datum;
	}
	
//	public static LocalDateTime convertStringWithTimeToStringForProjection(String d) {
//		String datum = null;
//		String pattern =  "yyyy-MM-dd HH"; //"dd-MM-yyyy HH:mm";
//		DateTimeFormatter formater = DateTimeFormatter.ofPattern(pattern);
//
//		try {
//			datum = formater.format(d);
//		} catch (Exception e ) {
//			 System.out.println("Greska pri konverzija LocalDateTime-a u String");
//			 e.printStackTrace();
//		}
//		return datum;
//	}

}
