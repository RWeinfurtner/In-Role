package com.techelevator.models;

import java.util.ArrayList;
import java.util.List;

public class Student {

	int userId;
	String firstName;
	String lastName;
	int cohort;
	String language;
	List<StudentPreference> preferences = new ArrayList<>();
	
	public Student(int userId, String firstName, String lastName, int cohort, String language) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.cohort = cohort;
		this.language = language;
	}

	public int getUserId() {
		return userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getCohort() {
		return cohort;
	}

	public String getLanguage() {
		return language;
	}

	public List<StudentPreference> getPreferences() {
		return preferences;
	}

	public void setPreferences(List<StudentPreference> preferences) {
		this.preferences = preferences;
	}
	
	
	
}
