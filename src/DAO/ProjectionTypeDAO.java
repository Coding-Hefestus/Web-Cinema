package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Dimension;
import model.Movie;
import model.Projection;
import model.ProjectionType;

public class ProjectionTypeDAO {

	public static ProjectionType getById(int idProjectionType) throws SQLException {
		
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			
			String query = "SELECT * FROM ProjectionType WHERE id = ?";
			
			pstmt = conn.prepareStatement(query);	
			pstmt.setInt(1, idProjectionType);
			rset = pstmt.executeQuery();
		
			if (rset.next()) {
				return new  ProjectionType(rset.getInt(1), rset.getInt(2) == 1 ? true : false, Dimension.valueOf(rset.getString(3)));
			}
			
			
		}finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
			//kako?
		}
		
		
		return null;
	}

}
