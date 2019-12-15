package model;

import java.util.GregorianCalendar;

public class Ticket extends Moviefiable {

	private int projection;
	private int seat; //seat id;
	private GregorianCalendar purchasingTime;
	private int user; //id user;
	
	public Ticket(int id, boolean active, int projection, int seat, GregorianCalendar purchasingTime, int user) {
		super(id, active);
		this.projection = projection;
		this.seat = seat;
		this.purchasingTime = purchasingTime;
		this.user = user;
	}
	
	public Ticket() {this(-1, false, -1, -1, null, -1);}
	
	
	
	
	public int getProjection() {
		return projection;
	}
	public void setProjection(int projection) {
		this.projection = projection;
	}
	public int getSeat() {
		return seat;
	}
	public void setSeat(int seat) {
		this.seat = seat;
	}
	public GregorianCalendar getPurchasingTime() {
		return purchasingTime;
	}
	public void setPurchasingTime(GregorianCalendar purchasingTime) {
		this.purchasingTime = purchasingTime;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	
	

	

}
