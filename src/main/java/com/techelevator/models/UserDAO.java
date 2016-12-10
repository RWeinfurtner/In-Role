package com.techelevator.models;

public interface UserDAO {
	
	public User createUser(String email, String password, UserType userType);
	public boolean searchForEmailAndPassword(String email, String password);
	public void linkEmployeeUserWithEmployer(int linkUserId, int linkEmployerId);
	public int getUserIdByEmail(String email);
	public User getUserByEmail(String email);
	public User getUserById(int userId);


}
