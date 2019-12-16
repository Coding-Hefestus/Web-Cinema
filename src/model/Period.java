package model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Period {
	
	private int id;
	private LocalDateTime start;
	private LocalDateTime end;
	
	
	
	public Period(int id, LocalDateTime start, LocalDateTime end) {
		super();
		this.id = id;
		this.start = start;
		this.end = end;
	};
	
	public Period() {this(-1, null, null);}
	
	
	
	public Period(int id,  LocalDateTime start, int duration) {
		this.id = id;
		this.start = start;		
		this.end = LocalDateTime.of
				(start.getYear(), start.getMonth(), start.getDayOfMonth(), 
				start.getHour(), start.getMinute()).plusMinutes(duration);		
	}
	
	public boolean overlaps(Period other) {
		if ( this.getStart().isBefore(other.getStart()) && this.getEnd().isBefore(other.getStart())) {
			return false;
		} else if (this.getStart().isAfter(other.getEnd()) && this.getEnd().isAfter(other.getEnd())) {
			return false;
		} else return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}
	
	
	
}
