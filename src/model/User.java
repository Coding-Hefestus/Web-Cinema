package model;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.function.Predicate;

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
	
	public static Predicate<User> usernameFilter(String filter){
		return m -> m.getUsername().toLowerCase().contains(filter.toLowerCase());
	}
	
	public static Predicate<User> roleFilter(String filter){
		return m -> m.getRole().toString().toLowerCase().contains(filter.toLowerCase());
	}
	
	public static Comparator<User> comparatorByUsername(String direction){
		
		switch (direction) {
		case "asc":
			return Comparator.comparing(User::getUsername);
		case "dsc":		
			return Comparator.comparing(User::getUsername).reversed();
		default: return null;
		}		
	}
	
	public static Comparator<User> comparatorByRole(String direction){
		
		switch (direction) {
		case "asc":
			return Comparator.comparing(User::getRole);
		case "dsc":		
			return Comparator.comparing(User::getRole).reversed();
		default: return null;
		}		
	}
	
	
	
}
