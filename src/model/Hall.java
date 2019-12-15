package model;

import java.util.Set;

public class Hall extends Moviefiable{
	
	private Set<Dimension> dimensions;
	private String name;
	
	
	public Hall(int id, boolean active, Set<Dimension> dimensions, String name) {
		super(id, active);
		this.dimensions = dimensions;
		this.name = name;
	}
	
	public Hall() {this(-1, false, null, "");}

	public Set<Dimension> getDimensions() {
		return dimensions;
	}

	public void setDimensions(Set<Dimension> dimensions) {
		this.dimensions = dimensions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	};
	
	
	
	
}
