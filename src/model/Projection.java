package model;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.function.Predicate;

public class Projection extends Moviefiable{
	
	private Movie movie;
	private ProjectionType projectionType;
	private Hall hall;
	private Period period;
	private double ticketPrice;
	private User administrator;
	private int ticketsSold;
	
	
	public Projection(int id, boolean active, Movie movie, ProjectionType projectionType, Hall hall, Period period,
			double ticketPrice, User administrator, int ticketsSold) {
		super(id, active);
		this.movie = movie;
		this.projectionType = projectionType;
		this.hall = hall;
		this.period = period;
		this.ticketPrice = ticketPrice;
		this.administrator = administrator;
		this.ticketsSold = ticketsSold;
	}
	
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
	

	public Projection() {this(-1, false, null, null, null, null, -1, null, 0);}

	public Movie getMovie() {
		return movie;
	}
	
	public String movieName() {
		return this.getMovie().getName();
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public ProjectionType getProjectionType() {
		return projectionType;
	}
	
	public String dimension() {
		return this.getProjectionType().getName();
	}

	public void setProjectionType(ProjectionType projectionType) {
		this.projectionType = projectionType;
	}

	public Hall getHall() {
		return hall;
	}
	
	public String hall() {
		return this.getHall().getName();
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public Period getPeriod() {
		return period;
	}
	
	public LocalDateTime startDate() {
		return this.getPeriod().getStart();
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
	
	public int getTicketsSold() {
		return ticketsSold;
	}


	public void setTicketsSold(int ticketsSold) {
		this.ticketsSold = ticketsSold;
	}


	public static Predicate<Projection> movieFilter(String filter){
		return p -> p.getMovie().getName().toLowerCase().contains(filter.toLowerCase());
	}
	
	public static Predicate<Projection> dateFilter(LocalDateTime from, LocalDateTime to){
		return p -> p.getPeriod().getStart().isAfter(from) && p.getPeriod().getStart().isBefore(to);//getName().toLowerCase().contains(filter.toLowerCase());
	}
	
	public static Predicate<Projection> dimensionFilter(String filter){
		return p -> p.getProjectionType().getName().toLowerCase().contains(filter.toLowerCase());//getName().toLowerCase().contains(filter.toLowerCase());
	}

	
	public static Predicate<Projection> ticketFilter(int from, int to){
		return p -> p.getTicketPrice() >= from && p.getTicketPrice() <= to;//getName().toLowerCase().contains(filter.toLowerCase());
	}
	
	public static Predicate<Projection> hallFilter(String filter){
		return p -> p.getHall().getName().toLowerCase().contains(filter.toLowerCase());//getName().toLowerCase().contains(filter.toLowerCase());
	}
	
	public static Predicate<Projection> afterNow(){
	
		return p -> p.getPeriod().getStart().isAfter(LocalDateTime.now());
	}
	
	public static  Predicate<Projection> hasAvailableSeats(){
		
		return p -> p.getTicketsSold() < p.getHall().getCapacity();
	}
	
	
	public static Comparator<Projection> sortByMovie(){
		return Comparator.comparing(Projection::movieName);
	}
	
	public static Comparator<Projection> sortByMovie(String movie){
		return Comparator.comparing(Projection::movieName);
	}
	
	public static Comparator<Projection> sortByStartDate(){
		return Comparator.comparing(Projection::startDate);
	}
	
	public static Comparator<Projection> comparatorByMovie(String direction){
		
		switch(direction) {
		
		case "asc":
			return Comparator.comparing(Projection::movieName);
		case "dsc":
			return Comparator.comparing(Projection::movieName).reversed();
		default: return null;
		}
	}
	
	public static Comparator<Projection> sortByDate(String direction){
		
		switch(direction) {
		
		case "asc":
			return Comparator.comparing(Projection::startDate);
		case "dsc":
			return Comparator.comparing(Projection::startDate).reversed();
		default: return sortByMovie();
		}
	}
	
	public static Comparator<Projection> sortByDimension(String direction){
		
		switch(direction) {
		
		case "asc":
			return Comparator.comparing(Projection::dimension);
		case "dsc":
			return Comparator.comparing(Projection::dimension).reversed();
		default: return null;
		}
	}
	
	
	public static Comparator<Projection> sortByHall(String direction){
		
		switch(direction) {
		
		case "asc":
			return Comparator.comparing(Projection::hall);
		case "dsc":
			return Comparator.comparing(Projection::hall).reversed();
		default: return null;
		}
	}
	
	public static Comparator<Projection> sortByPrice(String direction){
		
		switch(direction) {
		
		case "asc":
			return Comparator.comparing(Projection::getTicketPrice);
		case "dsc":
			return Comparator.comparing(Projection::getTicketPrice).reversed();
		default: return null;
		}
	}
	
		
		
		
		
	
	
	
	
	
	
	

	
}
