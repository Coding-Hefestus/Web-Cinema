package model;

public class Actor extends Moviefiable {
	
	
	private String name;
	
	
	public Actor(int id, boolean active, String name) {
		super(id, active);
		this.name = name;
	}

	public Actor() {this(-1, false, "");}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	};
	
	
	


}
