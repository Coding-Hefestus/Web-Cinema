package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Actor;


public class ActorDAO {

public static ArrayList<Actor> getAllActors() throws SQLException{
		
		ArrayList<Actor> actors = new ArrayList<Actor>();
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM Actor";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			int index;
			
			
			while (rset.next()) {
				index = 1;
				
				Actor actor = new Actor();
				
				actor.setId(rset.getInt(index++));
				actor.setActive(rset.getInt(index++) == 1 ? true : false);
				actor.setName(rset.getString(index++));
				
				actors.add(actor);
				
			}
			
			
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
		}
		
		return actors;
	}


	public static Actor getActorById(int id) throws SQLException {
	
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM Actor WHERE Id = ?";
	
		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			rset = pstmt.executeQuery();

			int index;
			if (rset.next()) {
				index = 1;
				return new Actor(rset.getInt(index++), rset.getInt(index++) == 1 ? true : false, rset.getString(index++));
			}

		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
		}
	
	
		return null;
	}
	
	public static boolean removeActorFromMovie(int idMovie, int idActor) throws SQLException {
		
		
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		//ResultSet rset = null;
		String query = "DELETE FROM Acting WHERE idMovie = ? AND idActor = ?";
		
		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idMovie);
			pstmt.setInt(2, idActor);
			
			int affectedRows = pstmt.executeUpdate();

			return affectedRows == 1 ? true : false;

		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			//try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
		}

	}
	
	public static boolean addActorInMovie(int idMovie, int idActor) throws SQLException {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		//ResultSet rset = null;
		String query = "INSERT INTO Acting (idMovie, idActor) VALUES (?, ?)";
		
		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idMovie);
			pstmt.setInt(2, idActor);
			
			int affectedRows = pstmt.executeUpdate();

			return affectedRows == 1 ? true : false;

		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			//try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
		}
	}
	
	
	
	
	

}
