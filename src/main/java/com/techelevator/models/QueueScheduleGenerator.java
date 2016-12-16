package com.techelevator.models;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

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
//		List<Integer> studentList = new ArrayList<>(studentSchedules.keySet());
		while (employerInterviewSchedules.size() < employerSchedules.size()) {
			//loop through employers that aren't filled
			for (EmployerSchedule employerSchedule : employerSchedules) {
				if (!employerInterviewSchedules.contains(employerSchedule)) {
					//get student from prefs
					Student student = null;
					StudentSchedule studentSchedule = null;
					
					PriorityQueue<StudentPreference> studentQ = employerToStudentQs.get(employerSchedule.getEmployer());
					//get the next student in line without a full schedule
//					while(true) {
//						StudentPreference studentPreference = studentQ.poll();
//						if (studentPreference == null)
//							break;
//						student = studentPreference.getStudent();
//						studentSchedule = studentSchedules.get((Integer)student.getUserId());
//						if (!studentSchedule.isFilled()) {
//							break;
//						}// else poll the next
//					}
					student = getNextQueuedStudentForEmployer(studentQ);
					//if there is no queued student, get a random student without a full schedule
//					if (student == null && studentList.size() > 0) {
//						Collections.shuffle(studentList);
//						student = studentSchedules.get(studentList.get(0)).getStudent();
//						studentSchedule = studentSchedules.get((Integer)student.getUserId());
//						//if student sch filled, pulled from list below
//					}
					if (student == null) {
						student = getRandomStudentForEmployer(employerSchedule);
					}
					if (student == null) {
						employerInterviewSchedules.add(employerSchedule);
						continue;
					}
					studentSchedule = studentSchedules.get((Integer)student.getUserId());
					//book an open time for both employer and student
					Iterator<LocalTime> times = employerSchedule.getRandomizedOpenTimeSlots().iterator();
					assignInterviewTimeSlot(times, studentSchedule, employerSchedule);
//					for (LocalTime timeSlot : times) {
//						if (studentSchedule.getInterviews().get(timeSlot) == null) {
//							studentSchedule.getInterviews().put(timeSlot, employerSchedule.getEmployer());
//							employerSchedule.book(timeSlot, student);
//							studentInterviewSchedules.put(student.getUserId(), studentSchedule);
//							break;
//						}
//					}
					if (employerSchedule.isFilled()) {
						employerInterviewSchedules.add(employerSchedule);
					}
				}
			}
		}
	}
		
	private Student getNextQueuedStudentForEmployer(PriorityQueue<StudentPreference> studentQ) {
		Student student = null;
		StudentPreference studentPreference = studentQ.poll();
		if (studentPreference != null) {
			student = studentPreference.getStudent();
			StudentSchedule schedule = studentInterviewSchedules.get((Integer)student.getUserId());
			if (schedule.isFilled()) {
				student = getNextQueuedStudentForEmployer(studentQ);
			}
		}
		return student;
	}
	
	private Student getRandomStudentForEmployer(EmployerSchedule employerSchedule) {
		List<Integer> studentList = new ArrayList<>(studentInterviewSchedules.keySet());
		studentList.removeIf(s -> employerSchedule.hasStudent(s) || studentInterviewSchedules.get(s).isFilled());
		Collections.shuffle(studentList);
		return studentInterviewSchedules.get(studentList.get(0)).getStudent();
	}
	
	private boolean assignInterviewTimeSlot(Iterator<LocalTime> times, 
											StudentSchedule studentSchedule,
											EmployerSchedule employerSchedule) {
		if (times.hasNext()) {
			LocalTime timeSlot = times.next();
			if (studentSchedule.book(timeSlot, employerSchedule.getEmployer())) {
				employerSchedule.book(timeSlot, studentSchedule.getStudent());
				studentInterviewSchedules.put(studentSchedule.getStudent().getUserId(), studentSchedule);
				return true;
			} else {
				return assignInterviewTimeSlot(times, studentSchedule, employerSchedule);
			}
		} else return false;
	}

	public List<EmployerSchedule> getEmployerInterviewSchedules() {
		return employerInterviewSchedules;
	}

	public HashMap<Integer, StudentSchedule> getStudentInterviewSchedules() {
		return studentInterviewSchedules;
	}
}
