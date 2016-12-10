package com.techelevator.models;

import static java.time.temporal.ChronoUnit.MINUTES;

import java.time.LocalTime;
import java.util.List;

public abstract class EventSchedule {

	public EventSchedule(List<Event> events) {
		for (Event event : events) {
			long totalTime = MINUTES.between(event.getStartTime(), event.getEndTime());
			int length = event.getInterviewLength();
			int totalSlots = (int)totalTime/length;
			for(int i=0; i<totalSlots; i++) {
				LocalTime start = event.getStartTime();
				addTimeSlot(start.plus(i*length, MINUTES));
			}
		}
	}
	
	public abstract boolean isFilled();
	
	public abstract void addTimeSlot(LocalTime time);
}
