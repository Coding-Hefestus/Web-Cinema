package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import model.Dimension;
import model.Hall;
import model.ProjectionType;

public class HallDAO {

	public static Hall getById(int idHall) throws SQLException {
		
		HashMap<Integer, Hall> hall = new  HashMap<Integer, Hall>();
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			
			String query = "SELECT Hall.id, Hall.active, Hall.capacity, Hall.name, ProjectionType.id, ProjectionType.active, ProjectionType.dimension"
							+" FROM Hall"
							+" LEFT JOIN Supports ON Hall.id = Supports.idHall"
							+" LEFT JOIN ProjectionType ON Supports.idProjectionType = ProjectionType.id"
							+" WHERE Hall.id = ?";
			
			pstmt = conn.prepareStatement(query);	
			pstmt.setInt(1, idHall);
			rset = pstmt.executeQuery();
		
//			if (rset.next()) {
//				return new  Hall(rset.getInt(1), rset.getInt(2) == 1 ? true : false, rset.getString(3));
//			}
			
			while (rset.next()) {
				if (!hall.containsKey(rset.getInt(1))) {
					Hall h = new Hall();
					h.setId(rset.getInt(1));
					h.setActive(rset.getInt(2) == 1 ? true : false);
					h.setCapacity(rset.getInt(3));
					h.setName(rset.getString(4));
					hall.put(rset.getInt(1), h);
				}
				
				if (rset.getInt(4) != 0) {
					hall.get(rset.getInt(1)).getDimensions().add(new ProjectionType(rset.getInt(5), rset.getInt(6) == 1 ? true : false, Dimension.valueOf(rset.getString(7))));
				}
			}
			
			
			
			return hall.get(idHall);
			
		}finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
			//kako?
		}
		
	}

}
	
	
