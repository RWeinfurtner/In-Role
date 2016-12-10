package com.techelevator.models;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

public class StudentSchedule extends EventSchedule {
	
	Student student;

	HashMap<LocalTime, Employer> interviews;

	public StudentSchedule(List<Event> events) {
		super(events);
		// TODO Auto-generated constructor stub
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
