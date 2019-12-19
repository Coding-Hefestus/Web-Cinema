package model;

public class Director extends Moviefiable {
	
	private String name;
	
	public Director(int id, boolean active, String name) {
		super(id, active);
		this.name = name;
	}

	public Director() {this(-1, false, "");}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
