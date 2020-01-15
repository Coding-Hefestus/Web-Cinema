package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import model.Report;
import utility.Utility;

public class ReportDAO {
	
	
	
	public static ArrayList<Report> getReports(LocalDateTime from, LocalDateTime to) throws SQLException, ParseException{
		
		HashMap<Integer, Report> mapa = new HashMap<Integer, Report>();
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			
			String query = "SELECT Movie.id, Movie.name, Projection.id, Projection.active, Period.startDate, Period.endDate, Projection.price, count(Ticket.id)"
			             + " FROM Movie" 
			             + " LEFT JOIN Projection ON Movie.id = Projection.idMovie"
			             + " LEFT JOIN Ticket ON Projection.id = Ticket.idProjection"
			             + " LEFT JOIN Period ON Projection.idPeriod = Period.id"
			             + " WHERE Movie.active = 1"
			             + " GROUP BY Projection.id"
			             + " ORDER BY Movie.id";

			pstmt = conn.prepareStatement(query);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				
				
				if (!mapa.containsKey(rset.getInt(1))) {
					Report report = new Report();
					report.setMovieId(rset.getInt(1));
					report.setMovie(rset.getString(2));
					mapa.put(rset.getInt(1), report);				
				} 
				
				if (rset.getInt(3) != 0 && rset.getInt(4) == 1 && isInRange(rset.getString(5), rset.getString(6), from, to)) {
					Report report = mapa.get(rset.getInt(1));
					report.setProjections(report.getProjections() + 1);
					
					if (rset.getInt(7) != 0) {
						report.setTickets(report.getTickets() + rset.getInt(8));
						report.setIncome(report.getIncome() +  rset.getInt(8)*rset.getDouble(7));
					}
				}
			}
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
		
		}

		
		
		return (ArrayList<Report>) mapa.values().stream().collect(Collectors.toList());
	}
	
	private static boolean isInRange(String fromString, String toString, LocalDateTime from, LocalDateTime to) throws ParseException {
		
		LocalDateTime fromDate = Utility.convertStringToDateWithTime(fromString);
		LocalDateTime toDate = Utility.convertStringToDateWithTime(toString);
		
		
		return from.isBefore(fromDate) && to.isAfter(toDate);
	}
}
