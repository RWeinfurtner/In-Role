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
		//seed the results map with students and pre-defined time-slots(unavailability)
		studentInterviewSchedules = studentSchedules;
		while (employerInterviewSchedules.size() < employerSchedules.size()) {
			//loop through employers that aren't filled
			for (EmployerSchedule employerSchedule : employerSchedules) {
				if (!employerInterviewSchedules.contains(employerSchedule)) {
					Student student = null;
					StudentSchedule studentSchedule = null;
					//TODO refactor the queue creation and preferences dao to include all students
					PriorityQueue<StudentPreference> studentQ = employerToStudentQs.get(employerSchedule.getEmployer());
					boolean done = false;
					while(!done) {
						//get the next student in line without a full schedule
						student = getNextQueuedStudentForEmployer(studentQ);
						//if none, get a random student without a full schedule
						//TODO merge this into the queue creation
						if (student == null) {
							student = getRandomStudentForEmployer(employerSchedule);
						}
						if (student == null) { //no students with open schedules, finalize the employer schedule
							employerInterviewSchedules.add(employerSchedule);
							break;
						}
						studentSchedule = studentInterviewSchedules.get((Integer)student.getUserId());
						//book an open time for both employer and student
						Iterator<LocalTime> times = employerSchedule.getRandomizedOpenTimeSlots().iterator();
						//TODO fix possible infinite loop, see above re queues, make a for loop
						done = assignInterviewTimeSlot(times, studentSchedule, employerSchedule);
					}
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
		//returns false if there are no matching open time slots
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
