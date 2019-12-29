package model;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.function.Predicate;

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
	
	//dateFilter(from, to)
	public static Predicate<Ticket> dateFilter(LocalDateTime from, LocalDateTime to){
		return t -> t.getPurchasingDate().isAfter(from)  && t.getPurchasingDate().isBefore(to);
	}
	
	public static Predicate<Ticket> userFilter(String filter){
		return t -> t.getUser().getUsername().toLowerCase().contains(filter.toString());
	}
	
	public static Comparator<Ticket> comparatorByPurchasingDate(String direction){
		
		switch(direction) {
		
		case "asc":
			return Comparator.comparing(Ticket::getPurchasingDate);
		case "dsc":
			return Comparator.comparing(Ticket::getPurchasingDate).reversed();
		default: return null;
		}
	}
	
	public static Comparator<Ticket> comparatorByUser(String direction){
		
		switch(direction) {
		
		case "asc":
			return Comparator.comparing(Ticket::getStringUser);
		case "dsc":
			return Comparator.comparing(Ticket::getStringUser).reversed();
		default: return null;
		}
	}
	
	public String getStringUser() {
		return this.getUser().getUsername();
	}
	
	//sortByDate
	public static Comparator<Ticket> sortByDate(){
		
		return Comparator.comparing(Ticket::getPurchasingDate);
	}
	
	//sortByUser
	public static Comparator<Ticket> sortByUser(){
		
		return Comparator.comparing(Ticket::getStringUser);
	}
}
