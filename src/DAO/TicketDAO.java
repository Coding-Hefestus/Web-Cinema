package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

import model.Projection;
import model.Seat;
import model.Ticket;
import model.User;
import utility.Utility;

public class TicketDAO {
	
	
	public static ArrayList<Ticket> getTicketsForProjection(int idProjection) throws SQLException, ParseException{
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			
			String query =   "SELECT *"  
						   +" FROM Ticket" 
					       +" WHERE idProjection = ? AND active = 1";
			
			
			
			pstmt = conn.prepareStatement(query);	
			pstmt.setInt(1, idProjection);
			rset = pstmt.executeQuery();
			int index, id;
			boolean active;
			
			while  (rset.next()) {
				index = 1;

				id = rset.getInt(index++);
				active = rset.getInt(index++) == 1 ? true : false;
				Projection projection = ProjectionDAO.getById(rset.getInt(index++));
				Seat seat = SeatDAO.getById(rset.getInt(index++));
				LocalDateTime purchasingDate = Utility.convertStringToDateWithTime(rset.getString(index++));
				User user = UserDAO.getById(rset.getInt(index++));
				
				tickets.add(new Ticket(id, active, projection, seat, purchasingDate, user));
				
				
				//return new Projection(id, active, movie, projectionType, hall, period, price, admin, ticketsSold); 
			}
			
			
		}finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
			//kako?
		}
		
		return (ArrayList<Ticket>) tickets
				.stream()
				.sorted(Ticket.sortByDate()						
				.thenComparing(Ticket.sortByUser()))
				.collect(Collectors.toList());
	}
	
	
	public static ArrayList<Ticket> getTicketsForUser(User user) throws SQLException, ParseException{
		
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			
			String query =   "SELECT * FROM Ticket WHERE Ticket.idUser = ? AND Ticket.active = 1";

			pstmt = conn.prepareStatement(query);	
			pstmt.setInt(1, user.getId());
			rset = pstmt.executeQuery();
			int index, id;
			boolean active;
			
			while  (rset.next()) {
				index = 1;

				id = rset.getInt(index++);
				active = rset.getInt(index++) == 1 ? true : false;
				Projection projection = ProjectionDAO.getById(rset.getInt(index++));
				Seat seat = SeatDAO.getById(rset.getInt(index++));
				LocalDateTime purchasingDate = Utility.convertStringToDateWithTime(rset.getString(index++));
				
				tickets.add(new Ticket(id, active, projection, seat, purchasingDate, user));
				
				
			}
			
			
		}finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
			
		}
		
		return (ArrayList<Ticket>) tickets
				.stream()
				.sorted(Ticket.sortByDate().reversed())	
				.collect(Collectors.toList());
				
				//.thenComparing(Ticket.sortByUser()))
	}
	
	
	public static boolean add(Ticket newTicket) throws SQLException {
		
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		//ResultSet rset = null;
		try {
			String query = "INSERT INTO Ticket (active, idProjection, idSeat, timeOfSale, idUser) "
					    + "VALUES (?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			int index = 1;

			pstmt.setInt(index++, newTicket.isActive() ? 1 : 0);	
			pstmt.setInt(index++, newTicket.getProjection().getId());
			pstmt.setInt(index++, newTicket.getSeat().getId());
			pstmt.setString(index++, Utility.convertDateWithTimeToString(newTicket.getPurchasingDate()));
			pstmt.setInt(index++, newTicket.getUser().getId());
			
			int affectedRows = pstmt.executeUpdate();

			if (affectedRows == 1) {
	            
	        	 try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
	 	            if (generatedKeys.next()) {
	 	            	newTicket.setId(generatedKeys.getInt(1));
	 	            	return true;
	 	            }
	 	            else {
	 	                throw new SQLException("Creating ticket failed, no ID obtained.");
	 	            }
	 	        }
			} else return false;

		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			//try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
		
		}
		
		
	}
		

	
} //from class
