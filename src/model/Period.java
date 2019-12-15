package model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Period {
	
	private int id;
	private GregorianCalendar start;
	private GregorianCalendar end;
	
	
	
	public Period(int id, GregorianCalendar start, GregorianCalendar end) {
		super();
		this.id = id;
		this.start = start;
		this.end = end;
	};
	
	public Period() {this(-1, null, null);}
	
	
	
	public Period(int id,  GregorianCalendar start, int duration) {
		this.id = id;
		this.start = start;
		this.end = (GregorianCalendar) start.clone();
		this.end.add(Calendar.MINUTE, duration);
	}
	
	public boolean overlaps(Period other) {
		if ( this.getStart().before(other.getStart()) && this.getEnd().before(other.getStart())) {
			return false;
		} else if (this.getStart().after(other.getEnd()) && this.getEnd().after(other.getEnd())) {
			return false;
		} else return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public GregorianCalendar getStart() {
		return start;
	}

	public void setStart(GregorianCalendar start) {
		this.start = start;
	}

	public GregorianCalendar getEnd() {
		return end;
	}

	public void setEnd(GregorianCalendar end) {
		this.end = end;
	}
	
	
	
}
