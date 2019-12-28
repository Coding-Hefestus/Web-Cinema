package model;

public class ProjectionType extends Moviefiable{

	private String name;
	private Dimension dimension;
	
	public ProjectionType (int id, boolean active, Dimension dimension) {
		super(id, active);
		this.dimension = dimension;
		this.name = dimension.toString();
		
	}
	
	
	public ProjectionType() {this(-1, false, Dimension.UNSPECIFIED);}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Dimension getDimension() {
		return dimension;
	}


	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}
	
	
	
	
}
