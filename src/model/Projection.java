package model;

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
	

	public Projection() {this(-1, false, null, null, null, null, -1, null);}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public ProjectionType getProjectionType() {
		return projectionType;
	}

	public void setProjectionType(ProjectionType projectionType) {
		this.projectionType = projectionType;
	}

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public User getAdministrator() {
		return administrator;
	}

	public void setAdministrator(User administrator) {
		this.administrator = administrator;
	}
	
	
	
	
	
	
	

	
}
