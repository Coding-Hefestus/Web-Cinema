package model;

public class Seat extends Moviefiable {

	private int number;
	private Hall hall;
	private boolean taken;
	
	
	public Seat(int id, boolean active, int number, Hall hall, boolean taken) {
		super(id, active);
		this.number = number;
		this.hall = hall;
		this.taken = taken;
	}




	public Seat() {this(-1, false, -1, null, false);}




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




	public boolean isTaken() {
		return taken;
	}




	public void setTaken(boolean taken) {
		this.taken = taken;
	}
		
	

}
