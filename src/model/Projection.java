package model;

import java.util.GregorianCalendar;

public class Projection extends Moviefiable{
	
	private Movie movie;
	private ProjectionType projectionType;
	private Hall hall;
	private Period period;
	private double ticketPrice;
	private User administrator;
	
	
	public Projection(int id, boolean active, Movie movie, ProjectionType projectionType, Hall hall, Period period,
			double ticketPrice, User administrator) {
		super(id, active);
		this.movie = movie;
		this.projectionType = projectionType;
		this.hall = hall;
		this.period = period;
		this.ticketPrice = ticketPrice;
		this.administrator = administrator;
	}
	
	private Projection() {this(-1, false, null, null, null, null, -1, null);}
	
	
	
	
	
	
	

	
}
