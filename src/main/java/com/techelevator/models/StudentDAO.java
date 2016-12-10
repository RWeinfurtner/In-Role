package com.techelevator.models;

import java.util.List;

public interface StudentDAO {
	
	public Student addNewStudent(int userId, String studentFirstName, String studentLastName, int cohort, String language);
	public Student getStudentByUserId(int userId);
	public List<Student> getStudentsByCohort(int cohort);
	public List<Integer> getValidCohorts();
	
}