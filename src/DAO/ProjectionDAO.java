package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import model.Hall;
import model.Movie;
import model.Period;
import model.Projection;
import model.ProjectionType;
import model.User;
import utility.Utility;

public class ProjectionDAO {

	public static ArrayList<Projection> getAll() throws SQLException, ParseException{
		
		ArrayList<Projection> projections = new ArrayList<Projection>();
		
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT Projection.id, Projection.active, Projection.idMovie, Projection.idProjectionType, Projection.idHall, Projection.idPeriod, Projection.price, Projection.idAdmin, COUNT(Ticket.id)" 
						   +" FROM Projection" 
					       +" LEFT JOIN Ticket ON Projection.id = Ticket.idProjection" 
					       +" WHERE Projection.active = 1"
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
			
			String query = "SELECT Projection.id, Projection.active, Projection.idMovie, Projection.idProjectionType, Projection.idHall, Projection.idPeriod, Projection.price, Projection.idAdmin, COUNT(Ticket.id)" 
			+" FROM Projection"
			+" LEFT JOIN Ticket ON  Projection.id = Ticket.idProjection"
			+" WHERE Projection.id = ? AND Projection.active = 1" 
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
	
	public static ArrayList<Projection>  getProjectionsForHall(int idHall) throws SQLException, ParseException{
		
		ArrayList<Projection> projectionsForHall = new ArrayList<Projection>();
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			
			String query = "SELECT Projection.id, Projection.active, Projection.idMovie, Projection.idProjectionType, Projection.idHall, Projection.idPeriod, Projection.price, Projection.idAdmin, COUNT(Ticket.id)" 
						  +" FROM Projection" 
						  +" LEFT JOIN Ticket ON Projection.id = Ticket.idProjection" 
						  +" WHERE Projection.active = 1 AND Projection.idHall = ?"
						  +" GROUP BY Projection.id, Ticket.idProjection";

			pstmt = conn.prepareStatement(query);	
			pstmt.setInt(1, idHall);
			rset = pstmt.executeQuery();
			
			int idProjection;
			while  (rset.next()) {
				
				idProjection = rset.getInt(1);
				Projection p = getById(idProjection);
				projectionsForHall.add(p);
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
		return projectionsForHall;

	}
	
	@SuppressWarnings("resource")
	public static boolean add(Projection newProjection) throws Exception {
		
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		
		
		try {
			conn.setAutoCommit(false); 
			conn.commit();
			String query ="INSERT INTO Period (active, startDate, endDate) VALUES (?, ?, ?)";
			
			pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			int index = 1;
			
			pstmt.setInt(index++, newProjection.getPeriod().isActive() == true ? 1 : 0);
			pstmt.setString(index++, Utility.convertDateWithTimeToString(newProjection.getPeriod().getStart()));
			pstmt.setString(index++, Utility.convertDateWithTimeToString(newProjection.getPeriod().getEnd()));

			int affectedRows = pstmt.executeUpdate();
			
			if (affectedRows == 1) {
				//pstmt.close();
				 try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
		 	            if (generatedKeys.next()) {
		 	            	newProjection.getPeriod().setId(generatedKeys.getInt(1));
		 	            }
		 	            else {
		 	                throw new SQLException("Creating movie failed, no ID obtained.");
		 	            }
		 	     }//od try-a
				 
				 query = "INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (?, ?, ?, ?, ?, ?, ?)";
				 pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				 index = 1;
				 
				 pstmt.setInt(index++, newProjection.isActive() == true ? 1 : 0);
				 pstmt.setInt(index++, newProjection.getMovie().getId());
				 pstmt.setInt(index++, newProjection.getProjectionType().getId());
				 pstmt.setInt(index++, newProjection.getHall().getId());
				 pstmt.setInt(index++, newProjection.getPeriod().getId());
				 pstmt.setDouble(index++, newProjection.getTicketPrice());
				 pstmt.setInt(index++, newProjection.getAdministrator().getId());
				 
				 affectedRows = pstmt.executeUpdate();
				 
				 try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
		 	            if (generatedKeys.next()) {
		 	            	newProjection.setId(generatedKeys.getInt(1));
		 	            }
		 	            else {
		 	                throw new SQLException("Creating movie failed, no ID obtained.");
		 	            }
		 	     }//od try-a
				 
				   conn.commit(); // 
			       return affectedRows != 0 && newProjection.getId() != -1;
			} //od ifa-a
			
			
			
		}catch (Exception ex) {
			try {conn.rollback();} catch (Exception ex1) {ex1.printStackTrace();} // ako je 2. commit neuspešan, vratiti bazu u stanje koje je zapamćeno 1. commit-om
			throw ex;		
		} finally {
			try {conn.setAutoCommit(true);} catch (Exception ex1) {ex1.printStackTrace();} // svaku sledeću naredbu izvršavati odmah
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
		}
		
		return false;
	}
	
	

}
