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
		
		ArrayList<Movie> movies = new ArrayList<>();
		
		
		ConnectionManager.open();
		Connection conn = ConnectionManager.getConnection();
		//PreparedStatement ps = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		
		try {
			
			String query = "SELECT * FROM Movie";
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			Movie m;
			int index;
			
			while (rset.next()) {
				m = new Movie();
				index = 1;
				
				m.setId(rset.getInt(index++));
				m.setActive(rset.getBoolean(index++));
				m.setName(rset.getString(index++));
				m.setDuration(rset.getInt(index++));
				m.setProductionYear(rset.getInt(index++));
				m.setDescription(rset.getString(index++));
				
				movies.add(m);
				
				
				
			}
			
			
			
		} finally {
			try {
				stmt.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		return movies;
	}
	
	
//	za odredjeno filtriranje...
//	public static List<Movie> getAll(String name, double lowPrice, double highPrice) throws Exception {
//		return new ArrayList<>();
//	}
}
