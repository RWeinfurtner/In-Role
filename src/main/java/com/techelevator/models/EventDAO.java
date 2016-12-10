package com.techelevator.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface EventDAO {
	
	public Event createNewEvent(LocalDate date, String name, LocalTime startTime, LocalTime endTime, int interviewLength);
	public List<Event> getAllEvents();
	public List<Event> getEventsByName(String name);
	public Event getEventById(int eventId);
	public void signUpEmployerForEventByEmployerId(int eventId, int employerId);
	public void linkAttendeeWithEvent(int eventId, int attendeeId);
	public List<Event> getEventsByCohort(int cohort);
	public boolean isEmployerSignedUpForEvent(int employerId);


}
