package model;

public class Genre extends Moviefiable {
	
	private String name;
	
	public Genre(int id, boolean active, String name) {
		super(id, active);
		this.name = name;
	}

	public Genre() {this(-1, false, "");}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
	    if (!(obj instanceof Genre))
	        return false;
	    if (obj == this)
	        return true;
	    return this.getId() == ((Genre) obj).getId();
	}
	
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.id;
	}

}
