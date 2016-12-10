package com.techelevator.models;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class EmployerSchedule extends EventSchedule {
	
	private Employer employer;
	private HashMap<LocalTime, Student> interviews;
	private List<LocalTime> openTimeSlots;
	private Random random;
	
	public EmployerSchedule(List<Event> events) {
		super(events);
		random = new Random();
		openTimeSlots = new ArrayList<LocalTime>(interviews.keySet());
		// TODO Auto-generated constructor stub
	}
	
	public static List<EmployerSchedule> getEmployerScheduleSet(List<Employer> employers, List<Event> events) {
		List<EmployerSchedule> employerSchedules = new ArrayList<>();
		for (Employer employer : employers) {
			EmployerSchedule employerSched = new EmployerSchedule(events);
			employerSched.setEmployer(employer);
			employerSchedules.add(employerSched);
		}
		return employerSchedules;
	}
	
	public boolean book(LocalTime timeSlot, Student student) {
		if (interviews.keySet().contains(timeSlot) && !interviews.containsValue(student)) {
			interviews.put(timeSlot, student);
			openTimeSlots.remove(timeSlot);
			return true;
		}
		return false;
	}
	
	public boolean isFilled() {
		return !interviews.values().contains(null);
	}
	
	public List<LocalTime> getRandomizedOpenTimeSlots(){
		Collections.shuffle(openTimeSlots);
		return openTimeSlots;
	}

	public void addTimeSlot(LocalTime time) {
		if (interviews == null) {
			interviews = new HashMap<LocalTime, Student>();
		}
		interviews.put(time, null);
	}

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	public HashMap<LocalTime, Student> getInterviews() {
		return interviews;
	}

	public void setInterviews(HashMap<LocalTime, Student> interviews) {
		this.interviews = interviews;
	}
}
