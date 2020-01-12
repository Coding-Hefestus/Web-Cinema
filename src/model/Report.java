package model;

import java.util.Comparator;

public class Report {
	
	private String movie;
	private int movieId;
	private int projections;
	private int tickets;
	private double income;


	public Report(String movie, int movieId, int projections, int tickets, double income) {
		super();
		this.movie = movie;
		this.projections = projections;
		this.tickets = tickets;
		this.income = income;
	}
	
	public Report() {this(null, -1, 0, 0, 0.0);}
	
	public String getMovie() {
		return movie;
	}
	public void setMovie(String movie) {
		this.movie = movie;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public int getProjections() {
		return projections;
	}
	public void setProjections(int projections) {
		this.projections = projections;
	}
	public int getTickets() {
		return tickets;
	}
	public void setTickets(int tickets) {
		this.tickets = tickets;
	}
	public double getIncome() {
		return income;
	}
	public void setIncome(double income) {
		this.income = income;
	}
	
	public static Comparator<Report> comparatorByMovie(String direction){
		
		switch(direction) {
		
		case "asc":
			return Comparator.comparing(Report::getMovie);
		case "dsc":
			return Comparator.comparing(Report::getMovie).reversed();
		default: return null;
		}
	}
	
	public static Comparator<Report> comparatorByProjections(String direction){
		
		switch(direction) {
		
		case "asc":
			return Comparator.comparing(Report::getProjections);
		case "dsc":
			return Comparator.comparing(Report::getProjections).reversed();
		default: return null;
		}
	}
	
	public static Comparator<Report> comparatorByTickets(String direction){
		
		switch(direction) {
		
		case "asc":
			return Comparator.comparing(Report::getTickets);
		case "dsc":
			return Comparator.comparing(Report::getTickets).reversed();
		default: return null;
		}
	}
	
	public static Comparator<Report> comparatorByIncome(String direction){
		
		switch(direction) {
		
		case "asc":
			return Comparator.comparing(Report::getIncome);
		case "dsc":
			return Comparator.comparing(Report::getIncome).reversed();
		default: return null;
		}
	}
}
