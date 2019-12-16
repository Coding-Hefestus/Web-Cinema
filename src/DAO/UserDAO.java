package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import model.Role;
import model.User;

public class UserDAO {

	public static User get(String username, String password) throws Exception{
		
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM User WHERE Active = ? AND username = ? AND password = ?";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			
			pstmt.setInt(index++, 1);
			pstmt.setString(index++, username);
			pstmt.setString(index++, password);

			System.out.println(pstmt);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				int id = rset.getInt(1);
				
				boolean active = false;
				active = (rset.getInt(2) == 0 ? active : true);
				
				Timestamp timestamp = rset.getTimestamp(3);	
				LocalDateTime registartionDate = timestamp.toLocalDateTime();
	
				Role role = Role.valueOf(rset.getString(6));

				return new User(id, active, username, password, registartionDate, role);
			}
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
		}

		return null;
		
		
	}

}
