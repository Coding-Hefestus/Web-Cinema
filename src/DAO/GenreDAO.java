package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import model.Actor;
import model.Genre;;


public class GenreDAO {

public static ArrayList<Genre> getAllGenres() throws SQLException{
		
		ArrayList<Genre> genres = new ArrayList<Genre>();
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM Genre";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			int index;
			
			
			while (rset.next()) {
				index = 1;
				
				Genre genre = new Genre();
				
				genre.setId(rset.getInt(index++));
				genre.setActive(rset.getInt(index++) == 1 ? true : false);
				genre.setName(rset.getString(index++));
				
				genres.add(genre);
				
			}
			
			
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
		}
		
		return genres;
	}


	public static Genre getGenreById(int id) throws SQLException {
	
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM Genre WHERE Id = ?";
	
		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			rset = pstmt.executeQuery();

			int index;
			if (rset.next()) {
				index = 1;
				return new Genre(rset.getInt(index++), rset.getInt(index++) == 1 ? true : false, rset.getString(index++));
			}

		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
		}
	
	
		return null;
	}
	
	public static boolean removeGenreFromMovie(int idMovie, int idGenre) throws SQLException {
		
		
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		//ResultSet rset = null;
		String query = "DELETE FROM MovieGenre WHERE idMovie = ? AND idGenre = ?";
		
		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idMovie);
			pstmt.setInt(2, idGenre);
			
			int affectedRows = pstmt.executeUpdate();

			return affectedRows == 1 ? true : false;

		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			//try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
		}

	}
	
	public static boolean addGenreInMovie(int idMovie, int idGenre) throws SQLException {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		//ResultSet rset = null;
		String query = "INSERT INTO MovieGenre (idMovie, idGenre) VALUES (?, ?)";
		
		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idMovie);
			pstmt.setInt(2, idGenre);
			
			int affectedRows = pstmt.executeUpdate();

			return affectedRows == 1 ? true : false;

		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			//try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
		}
	}
	
	
	public static void cleanAllGenresForMovie(int idMovie) throws SQLException {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		//ResultSet rset = null;
		String query = "DELETE FROM MovieGenre WHERE idMovie = ? AND idGenre IN (" + getGenresIdsAsStrings() + ")";
		
		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idMovie);
			pstmt.executeUpdate();

		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			//try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
		}
		
	}
	
	private static String getGenresIdsAsStrings() throws SQLException {
		ArrayList<Genre> allGenres = getAllGenres();
		return allGenres.stream().map(Genre::getId)
									.map(id -> id.toString())
									.collect(Collectors.joining(", "));
	}
	
	
	
	
	
	

}
