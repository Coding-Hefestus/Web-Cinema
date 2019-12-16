package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Movie;

public class MovieDAO {

	public static List<Movie> getAll() throws Exception {
		
		ArrayList<Movie> movies = new ArrayList<Movie>();
		
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			
			String query = "SELECT * FROM Movie WHERE Active = ?";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(2, 1); //column 2, value 1 i.e. Active
			
			rset = pstmt.executeQuery();
			
			int index, id, duration, productionYear;
			String name, description;
			boolean active = false;
			
			while(rset.next()) {
				
				index = 1;
				
				Movie movie = new Movie();
				
				id = rset.getInt(index++);
				movie.setId(id);
				
				active = (rset.getInt(index++) == 0 ? active : true);
				movie.setActive(active);
				
				name = rset.getString(index++);
				movie.setName(name);
				
				duration = rset.getInt(index++);
				movie.setDuration(duration);
				
				productionYear = rset.getInt(index++);
				movie.setProductionYear(productionYear);
				
				description = rset.getString(index++);
				movie.setDescription(description);
				
				
				movies.add(movie);
			}
			
		}finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
			//kako?
		}
		
		
		
		
		
		
		
		return null;
	}
	
//	za odredjeno filtriranje...
//	public static List<Movie> getAll(String name, double lowPrice, double highPrice) throws Exception {
//		return new ArrayList<>();
//	}
	
	
} //od klase
