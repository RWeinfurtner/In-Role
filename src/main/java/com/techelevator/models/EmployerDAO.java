package com.techelevator.models;

import java.util.List;

public interface EmployerDAO {
	
	public Employer addNewEmployer(String employerName);
	public Employer getEmployerById(int id);
	public Employer getEmployerByName(String employerName);
	public Employer getEmployerByUserId(int userId);
	public List<Employer> getAllEmployers();
	public boolean updateProfile(Employer employer);
	public List<Employer> getEmployersRegisteredForEvent(int eventId);


}