package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import model.Period;

public class PeriodDAO {
	public static SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	
	public static Period getById(int idPeriod) throws SQLException, ParseException{
		
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			
			String query = "SELECT * FROM Period WHERE id = ?";
			
			pstmt = conn.prepareStatement(query);	
			pstmt.setInt(1, idPeriod);
			rset = pstmt.executeQuery();
		
//			if (rset.next()) {
//				return new  Hall(rset.getInt(1), rset.getInt(2) == 1 ? true : false, rset.getString(3));
//			}
			
			if (rset.next()) {
				
				Period period = new Period();
				
				period.setId(rset.getInt(1));
				period.setActive(rset.getInt(2) == 1 ? true : false);
				
				String dateTimeStringStart = rset.getString(3);
				Timestamp tsStart = new Timestamp(DATETIME_FORMAT.parse(dateTimeStringStart).getTime());
				LocalDateTime start = tsStart.toLocalDateTime();
				period.setStart(start);
				
				String dateTimeStringEnd = rset.getString(4);
				Timestamp tsEnd = new Timestamp(DATETIME_FORMAT.parse(dateTimeStringEnd).getTime());
				LocalDateTime end = tsEnd.toLocalDateTime();
				period.setEnd(end);
				return period;
				
			}
			

			
		}finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
			//kako?
		}
		
		return null;
	}

}
