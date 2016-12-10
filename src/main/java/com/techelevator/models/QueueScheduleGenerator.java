package com.techelevator.models;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class QueueScheduleGenerator {
	
	private List<EmployerSchedule> employerInterviewSchedules;
	private HashMap<Integer, StudentSchedule> studentInterviewSchedules;
	
	public QueueScheduleGenerator() {
		employerInterviewSchedules = new ArrayList<>();
		studentInterviewSchedules = new HashMap<>();
	}
	
	public void GenerateSchedules(HashMap<Integer, StudentSchedule> studentSchedules, 
									List<EmployerSchedule> employerSchedules,
									HashMap<Employer, PriorityQueue<StudentPreference>> employerToStudentQs) {
		studentInterviewSchedules = studentSchedules;
		List<Integer> studentList = new ArrayList<>(studentSchedules.keySet());
		while (employerInterviewSchedules.size() < employerSchedules.size() && studentList.size() > 0) {
			//loop through employers that aren't filled
			for (EmployerSchedule employerSchedule : employerSchedules) {
				if (!employerInterviewSchedules.contains(employerSchedule)) {
					//get student from prefs
					Student student = null;
					StudentSchedule studentSchedule = null;
					PriorityQueue<StudentPreference> studentQ = employerToStudentQs.get(employerSchedule.getEmployer());
					while(true) {
						StudentPreference studentPreference = studentQ.poll();
						if (studentPreference == null)
							break;
						student = studentPreference.getStudent();
						studentSchedule = studentSchedules.get((Integer)student.getUserId());
						if (!studentSchedule.isFilled()) {
							break;
						}// else poll the next
					}
					if (student == null && studentList.size() > 0) {
						Collections.shuffle(studentList);
						student = studentSchedules.get(studentList.get(0)).getStudent();
						studentSchedule = studentSchedules.get((Integer)student.getUserId());
						//if student sch filled, pulled from list below
					}
					//book an open time for both employer and student
					List<LocalTime> times = employerSchedule.getRandomizedOpenTimeSlots();
					for (LocalTime timeSlot : times) {
						if (studentSchedule.getInterviews().get(timeSlot) == null) {
							studentSchedule.getInterviews().put(timeSlot, employerSchedule.getEmployer());
							if (employerSchedule.book(timeSlot, student)) {
								studentSchedules.put(student.getUserId(), studentSchedule);
								if (studentSchedule.isFilled()) {
									studentList.remove((Integer)student.getUserId());
								}
								break;
							}
						}
					}
					if (employerSchedule.isFilled()) {
						employerInterviewSchedules.add(employerSchedule);
					}
				}
			}
		}
	}
		
	

	public List<EmployerSchedule> getEmployerInterviewSchedules() {
		return employerInterviewSchedules;
	}

	public HashMap<Integer, StudentSchedule> getStudentInterviewSchedules() {
		return studentInterviewSchedules;
	}
}
