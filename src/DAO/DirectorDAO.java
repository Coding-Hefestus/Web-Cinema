package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Director;

public class DirectorDAO {

	public static ArrayList<Director> getAllDirectors() throws SQLException{
		
		ArrayList<Director> directors = new ArrayList<Director>();
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM Director";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			int index;
			
			
			while (rset.next()) {
				index = 1;
				
				Director director = new Director();
				
				director.setId(rset.getInt(index++));
				director.setActive(rset.getInt(index++) == 1 ? true : false);
				director.setName(rset.getString(index++));
				
				directors.add(director);
				
			}
			
			
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
		}
		
		return directors;
	}
	
	public static Director getDirectorById(int id) throws SQLException {
		
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM Director WHERE Id = ?";
		
		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			rset = pstmt.executeQuery();

			int index;
			if (rset.next()) {
				index = 1;
				return new Director(rset.getInt(index++), rset.getInt(index++) == 1 ? true : false, rset.getString(index++));
			}

		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
		}
		
		
		return null;
	}
	
	public static boolean removeDirectorFromMovie(int idMovie, int idDirector) throws SQLException {
		
		
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		//ResultSet rset = null;
		String query = "DELETE FROM Directing WHERE idMovie = ? AND idDirector = ?";
		
		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idMovie);
			pstmt.setInt(2, idDirector);
			
			int affectedRows = pstmt.executeUpdate();

			return affectedRows == 1 ? true : false;

		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			//try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
		}

	}
	
	public static boolean addDirectorInMovie(int idMovie, int idDirector) throws SQLException {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		//ResultSet rset = null;
		String query = "INSERT INTO Directing (idMovie, idDirector) VALUES (?, ?)";
		
		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idMovie);
			pstmt.setInt(2, idDirector);
			
			int affectedRows = pstmt.executeUpdate();

			return affectedRows == 1 ? true : false;

		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			//try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
		}
	}
		
	
	

}//od klase
