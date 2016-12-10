package com.techelevator.models;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueSchedule {

	private Employer employer;
	private Queue<Student> queue;
	private PriorityQueue<StudentPreference> studentQueue;
	
	private List<StudentPreference> studentPreferences;
	
	public QueueSchedule(List<StudentPreference> studentPreferences){
		this.studentPreferences = studentPreferences;
	}
	
	public static PriorityQueue<StudentPreference> getStudentQueueForEmployer(List<StudentPreference> studentPreferences) {
		PriorityQueue<StudentPreference> studentQueue = new PriorityQueue<>(10, studentComparator);
		for(StudentPreference s : studentPreferences){
			studentQueue.add(s);
		}
		return studentQueue;
	}
	
	public static Comparator<StudentPreference> studentComparator = new Comparator<StudentPreference>(){
		
		@Override
		public int compare(StudentPreference s1, StudentPreference s2) {
            return (int) (s1.getWeight() - s2.getWeight());
        }
	};
	
	public Queue<Student> getQueue(List<Student> students){
		for(Student s: students){
			queue.add(s);
		}
		return queue;
	}

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}
}