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
			
			String query = "SELECT * FROM Projection WHERE active = 1";

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

				projections.add(new Projection(idProjection, active, movie, projectionType, hall, period, price, admin)); 
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

}
