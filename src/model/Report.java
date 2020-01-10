package model;

import java.util.ArrayList;

public class Report {
	
	private ArrayList<Movie> movies;
	private int projections;
	private int tickets;
	private double income;

	
	public Report(ArrayList<Movie> movies, int projections, int tickets, double income) {
		super();
		this.movies = movies;
		this.projections = projections;
		this.tickets = tickets;
		this.income = income;
	}
	
	public Report() {this(null, 0, 0, 0.0);}
	
	public ArrayList<Movie> getMovies() {
		return movies;
	}
	public void setMovies(ArrayList<Movie> movies) {
		this.movies = movies;
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
	
	
	
}
