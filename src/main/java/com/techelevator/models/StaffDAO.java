package com.techelevator.models;

public interface StaffDAO {
	
	public Staff addNewStaffMember(int userId, String firstName, String lastName);
	public Staff getStaffMemberByUserId(int userId);


}
