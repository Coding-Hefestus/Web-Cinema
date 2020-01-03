package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import model.Hall;
import model.Movie;
import model.Period;
import model.Projection;
import model.ProjectionType;
import model.User;

public class ProjectionDAO {

	public static ArrayList<Projection> getAll() throws SQLException, ParseException{
		
		ArrayList<Projection> projections = new ArrayList<Projection>();
		
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT Projection.id, Projection.active, Projection.idMovie, Projection.idProjectionType, Projection.idHall, Projection.idPeriod, Projection.price, Projection.idAdmin, COUNT(*)" 
						   +" FROM Projection" 
					       +" LEFT JOIN Ticket ON Projection.id = Ticket.idProjection" 
					       +" WHERE Projection.active = 1 AND Ticket.active = 1"
					       +" GROUP BY Projection.id, Ticket.idProjection";


			pstmt = conn.prepareStatement(query);			
			rset = pstmt.executeQuery();
			int index;
			while (rset.next()) {
				index = 1;

				int idProjection = rset.getInt(index++);
				boolean active = rset.getInt(index++) == 1 ? true : false;
				Movie movie = MovieDAO.getById(rset.getInt(index++));
				ProjectionType projectionType = ProjectionTypeDAO.getById(rset.getInt(index++));//				
				Hall hall = HallDAO.getById(rset.getInt(index++));	
				Period period = PeriodDAO.getById(rset.getInt(index++));
				double price = rset.getInt(index++);				
				User admin = UserDAO.getById(rset.getInt(index++));
				int ticketsSold = rset.getInt(index++);
				projections.add(new Projection(idProjection, active, movie, projectionType, hall, period, price, admin, ticketsSold)); 
			}
			
			
		}finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
			//kako?
		}
		
		//return projections;
		return (ArrayList<Projection>) projections
				  						.stream()
										.sorted(Projection.sortByMovie()
										.thenComparing(Projection.sortByMovie()))
										.collect(Collectors.toList());
		
	}
	
	public static Projection getById(int idProjection) throws SQLException, ParseException {
		
		
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			
			String query = "SELECT Projection.id, Projection.active, Projection.idMovie, Projection.idProjectionType, Projection.idHall, Projection.idPeriod, Projection.price, Projection.idAdmin, COUNT(*)" 
			+" FROM Projection"
			+" LEFT JOIN Ticket ON  Projection.id = Ticket.idProjection"
			+" WHERE Projection.id = ? AND Projection.active = 1 AND Ticket.active = 1" 
			+" GROUP BY Projection.id, Ticket.idProjection";
			
			
			
			pstmt = conn.prepareStatement(query);	
			pstmt.setInt(1, idProjection);
			rset = pstmt.executeQuery();
			int index;
			if  (rset.next()) {
				index = 1;

				int id = rset.getInt(index++);
				boolean active = rset.getInt(index++) == 1 ? true : false;
				Movie movie = MovieDAO.getById(rset.getInt(index++));
				ProjectionType projectionType = ProjectionTypeDAO.getById(rset.getInt(index++));//				
				Hall hall = HallDAO.getById(rset.getInt(index++));	
				Period period = PeriodDAO.getById(rset.getInt(index++));
				double price = rset.getInt(index++);				
				User admin = UserDAO.getById(rset.getInt(index++));
				int ticketsSold = rset.getInt(index++);
				
				return new Projection(id, active, movie, projectionType, hall, period, price, admin, ticketsSold); 
			}
			
			
		}finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
			//kako?
		}
		
		//return projections;
		return null;
	}
	
	
	public static ArrayList<Projection> getProjectionsForMovie(int idMovie) throws SQLException, ParseException{
		
		ArrayList<Projection> projectionsForMovie = new ArrayList<Projection>();
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			
			String query = "SELECT Projection.id FROM Projection WHERE idMovie = ? AND active = 1";

			pstmt = conn.prepareStatement(query);	
			pstmt.setInt(1, idMovie);
			rset = pstmt.executeQuery();
			
			int idProjection;
			while  (rset.next()) {
				
				idProjection = rset.getInt(1);
				Projection p = getById(idProjection);
				projectionsForMovie.add(p);
				//return new Projection(id, active, movie, projectionType, hall, period, price, admin, ticketsSold); 
			}
			
			
		}finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
			//kako?
		}
		
		//System.exit(1);
		//return projections;
		return projectionsForMovie;
	
	}
	
	public static void delete(int idProjection) throws SQLException, ParseException{
		
		
		Projection projection = getById(idProjection);
		projection.setActive(false);
		
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		
		
		try {
			
			String query = "UPDATE Projection SET active = 0 WHERE id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idProjection);
			pstmt.executeUpdate();
			
			return;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
		
		}
	}

}
