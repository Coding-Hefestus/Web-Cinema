package model;

public class Seat extends Moviefiable {

	private int number;
	private Hall hall;

	
	
	public Seat(int id, boolean active, int number, Hall hall) {
		super(id, active);
		this.number = number;
		this.hall = hall;
	
	}




	public Seat() {this(-1, false, -1, null);}




	public int getNumber() {
		return number;
	}




	public void setNumber(int number) {
		this.number = number;
	}




	public Hall getHall() {
		return hall;
	}




	public void setHall(Hall hall) {
		this.hall = hall;
	}




	
		
	

}
