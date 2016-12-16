package com.techelevator.models;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentSchedule extends EventSchedule {
	
	private Student student;
	private HashMap<LocalTime, Employer> interviews;
	private List<LocalTime> openTimeSlots;

	public StudentSchedule(List<Event> events) {
		super(events);
		openTimeSlots = new ArrayList<LocalTime>(interviews.keySet());
	}
	
	public static HashMap<Integer, StudentSchedule> getStudentScheduleSet(List<Student> students, List<Event> events) {
		HashMap<Integer, StudentSchedule> studentSchedules = new HashMap<>();
		for (Student student : students) {
			StudentSchedule studentSched = new StudentSchedule(events);
			studentSched.setStudent(student);
			studentSchedules.put(student.getUserId(), studentSched);
		}
		return studentSchedules;
	}

	public void addTimeSlot(LocalTime time) {
		if (interviews == null) {
			interviews = new HashMap<>();
		}
		interviews.put(time, null);
	}
	
	public boolean book(LocalTime timeSlot, Employer employer) {
		if (interviews.keySet().contains(timeSlot) && !interviews.containsValue(employer)) {
			interviews.put(timeSlot, employer);
			openTimeSlots.remove(timeSlot);
			return true;
		}
		return false;
	}
	
	public boolean isFilled() {
		return !interviews.values().contains(null);
	}

	public HashMap<LocalTime, Employer> getInterviews() {
		return interviews;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
}
