package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Ticket extends Moviefiable {
	
	private Projection projection;
	private Seat seat;
	private LocalDateTime purchasingDate;
	private User user;
	
	
	
	
	
	public Ticket(int id, boolean active, Projection projection, Seat seat, LocalDateTime purchasingDate, User user) {
		super(id, active);
		this.projection = projection;
		this.seat = seat;
		this.purchasingDate = purchasingDate;
		this.user = user;
	}





	public Ticket() {this(-1, false, null, null, null, null);}


	public Projection getProjection() {
		return projection;
	}


	public void setProjection(Projection projection) {
		this.projection = projection;
	}


	public Seat getSeat() {
		return seat;
	}


	public void setSeat(Seat seat) {
		this.seat = seat;
	}


	public LocalDateTime getPurchasingDate() {
		return purchasingDate;
	}


	public void setPurchasingDate(LocalDateTime purchasingDate) {
		this.purchasingDate = purchasingDate;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
