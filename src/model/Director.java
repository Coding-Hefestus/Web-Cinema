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
	
	public boolean equals(Object obj) {
		if (obj == null) return false;
	    if (!(obj instanceof Director))
	        return false;
	    if (obj == this)
	        return true;
	    return this.getId() == ((Director) obj).getId();
	}
	
	
	@Override
	public int hashCode() {

		return this.id;
	}
	

}
