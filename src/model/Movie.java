package model;

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
	
	
	
	
	
	
}
