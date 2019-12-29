package model;

import java.util.HashSet;
import java.util.Set;

public class Hall extends Moviefiable{
	
	private Set<ProjectionType> dimensions;
	private int capacity;
	private String name;
	
	
	
	public Hall(int id, boolean active, Set<ProjectionType> dimensions,int capacity, String name) {
		super(id, active);
		this.dimensions = dimensions;
		this.capacity = capacity;
		this.name = name;
	}
	
	public Hall() {this(-1, false, new HashSet<ProjectionType>(), -1, "");}

	public Set<ProjectionType> getDimensions() {
		return dimensions;
	}

	public void setDimensions(Set<ProjectionType> dimensions) {
		this.dimensions = dimensions;
	}
	
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	};
	
	
	
	
}
