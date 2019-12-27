package model;

import java.util.HashSet;
import java.util.Set;

public class Hall extends Moviefiable{
	
	private Set<ProjectionType> dimensions;
	private String name;
	
	
	public Hall(int id, boolean active, Set<ProjectionType> dimensions, String name) {
		super(id, active);
		this.dimensions = dimensions;
		this.name = name;
	}
	
	public Hall() {this(-1, false, new HashSet<ProjectionType>(), "");}

	public Set<ProjectionType> getDimensions() {
		return dimensions;
	}

	public void setDimensions(Set<ProjectionType> dimensions) {
		this.dimensions = dimensions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	};
	
	
	
	
}
