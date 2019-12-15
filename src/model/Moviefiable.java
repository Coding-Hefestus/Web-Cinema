package model;

public abstract class Moviefiable {
	

	protected int id;
	protected boolean active;
	
	
	public Moviefiable(int id, boolean active) {
		super();
		this.id = id;
		this.active = active;
	}
	
	public Moviefiable() {this(-1, false);}		
	


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
	
}
