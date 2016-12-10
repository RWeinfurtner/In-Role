package com.techelevator.models;

import java.time.Duration;

public class EventSet {
	
	Event eventStart;
	Event eventEnd;
	Duration breakTime;
	
	public EventSet(Event eventStart, Event eventEnd, Duration breakTime) {
		this.eventStart = eventStart;
		this.eventEnd = eventEnd;
		this.breakTime = breakTime;
	}
	
	public Event getEventStart() {
		return eventStart;
	}
	public void setEventStart(Event eventStart) {
		this.eventStart = eventStart;
	}
	public Event getEventEnd() {
		return eventEnd;
	}
	public void setEventEnd(Event eventEnd) {
		this.eventEnd = eventEnd;
	}
	public Duration getBreakTime() {
		return breakTime;
	}
	public void setBreakTime(Duration breakTime) {
		this.breakTime = breakTime;
	}
	
	

}
