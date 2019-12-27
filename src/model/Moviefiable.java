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

	@Override
	public int hashCode() {
		
		return this.id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Moviefiable other = (Moviefiable) obj;
		if (active != other.active)
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
}
