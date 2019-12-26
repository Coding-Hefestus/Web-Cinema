package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import model.Role;
import model.User;
import utility.Utility;

public class UserDAO {
	
	public static SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	public static User get(String username, String password) throws Exception{
		
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM User WHERE active = 1 AND username = ? AND password = ?";

			pstmt = conn.prepareStatement(query);
			int index = 1;
	
			//pstmt.setInt(index++, 1); 	
			pstmt.setString(index++, username);	//column 2
			pstmt.setString(index++, password);// column 3
			//System.out.println(index);

			//System.out.println(pstmt);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				
				int id = rset.getInt(1);
				
			
				boolean active = (rset.getInt(2) == 0 ? false : true);
				
				String dateTimeString = rset.getString(5);
				
				Timestamp ts = new Timestamp(DATETIME_FORMAT.parse(dateTimeString).getTime());

				LocalDateTime registartionDate = ts.toLocalDateTime();

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
	
	public static boolean alreadyExists(String username, String password) throws SQLException {
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM User WHERE username = ? AND password = ?";

			pstmt = conn.prepareStatement(query);
			int index = 1;

			pstmt.setString(index++, username);	
			pstmt.setString(index++, password);

			rset = pstmt.executeQuery();

			if (rset.next()) return true;
			else return false;
		

		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
		
		}

		
	}
	
	public static boolean add(User newUser) throws SQLException {
		
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		//ResultSet rset = null;
		try {
			String query = "INSERT INTO USER (active, username, password, registrationDate, role) "
					    + "VALUES (?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			int index = 1;

			pstmt.setInt(index++, newUser.isActive() ? 1 : 0);	
			pstmt.setString(index++, newUser.getUsername());
			pstmt.setString(index++, newUser.getPassword());
			//pstmt.setTimestamp(index++, Timestamp.valueOf(newUser.getRegistrationDate()));//(index++, Datetime.); //Timestamp.valueOf(newUser.getRegistrationDate())
			pstmt.setString(index++, Utility.convertDateWithTimeToString(newUser.getRegistrationDate()));
			pstmt.setString(index++, newUser.getRole().toString());
			
			int affectedRows = pstmt.executeUpdate();

			if (affectedRows == 1) {
	            
	        	 try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
	 	            if (generatedKeys.next()) {
	 	            	newUser.setId(generatedKeys.getInt(1));
	 	            	return true;
	 	            }
	 	            else {
	 	                throw new SQLException("Creating user failed, no ID obtained.");
	 	            }
	 	        }
			} else return false;

		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			//try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
		
		}
		
	}


}
