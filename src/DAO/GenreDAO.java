package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


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

}
