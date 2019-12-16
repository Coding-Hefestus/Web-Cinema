package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class User extends Moviefiable {

	private String username;
	private String password;
	private LocalDateTime registrationDate;
	private Role role;

	public User(int id, boolean active, String username, String password, LocalDateTime registrationDate, Role role) {
		super(id, active);
		this.username = username;
		this.password = password;
		this.registrationDate = registrationDate;
		this.role = role;
	}


	public User() {this(-1, false, "", "", null, Role.UNSPECIFIED);}
	
	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
}
