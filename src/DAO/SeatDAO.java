package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Hall;
import model.Seat;

public class SeatDAO {
	
	
	public static Seat getById(int idSeat) throws SQLException {
		
		
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM Seat WHERE active = 1 AND id = ?";

			pstmt = conn.prepareStatement(query);
	
			pstmt.setInt(1, idSeat);	

			rset = pstmt.executeQuery();

			if (rset.next()) {
				
				int id = rset.getInt(1);
				
			
				boolean active = (rset.getInt(2) == 0 ? false : true);
				
				int seatNumber = rset.getInt(3);
				
				Hall hall = HallDAO.getById(rset.getInt(4));

				return new Seat(id, active, seatNumber, hall);
				
			}
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
		
		}

		
		return null;
	}
}
