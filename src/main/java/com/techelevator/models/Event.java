package com.techelevator.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event {
	
	int eventId;
	LocalDate day;
	String name;
	LocalTime startTime;
	LocalTime endTime;
	int interviewLength;
	
	public Event()
	{
		
	}
	
	public Event(int eventId, LocalDate day, String name, LocalTime startTime, LocalTime endTime, int interviewLength) {
		this.eventId = eventId;
		this.day = day;
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.interviewLength = interviewLength;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public LocalDate getDay() {
		return day;
	}
	public void setDay(LocalDate day) {
		this.day = day;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	public int getInterviewLength() {
		return interviewLength;
	}
	public void setInterviewLength(int interviewLength) {
		this.interviewLength = interviewLength;
	}
	
	

}
