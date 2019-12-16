package model;

import java.util.function.Predicate;

public class Movie extends Moviefiable{
	
	private String name;
	private int duration;
	private int productionYear;
	private String description;
	

	public Movie(int id, boolean active, String name, int duration, int productionYear, String description) {
		super(id, active);
		this.name = name;
		this.duration = duration;
		this.productionYear = productionYear;
		this.description = description;
	}
	
	
	public Movie() {this(-1, false, "", -1, -1, "");}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}


	public int getProductionYear() {
		return productionYear;
	}


	public void setProductionYear(int productionYear) {
		this.productionYear = productionYear;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	public static Predicate<Movie> nameFilter(String filter){
		return m -> m.getName().toLowerCase().contains(filter.toLowerCase());
	}
	
	
	public static Predicate<Movie> durationFilter(int fromFilter, int toFilter){
		return  m -> fromFilter <= m.getDuration() && m.getDuration() <= toFilter;
	}
	

	public static Predicate<Movie> productionFilter(int fromFilter, int toFilter){			
		return m -> fromFilter <=  m.getProductionYear() &&   m.getProductionYear() <= toFilter;		
	}
	
	public static Predicate<Movie> descriptionFilter(String filter){
		return m -> m.getDescription().toLowerCase().contains(filter.toLowerCase());
	}
	
	
	
	
	
	
	
}
