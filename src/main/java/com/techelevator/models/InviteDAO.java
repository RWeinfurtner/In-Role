package com.techelevator.models;

public interface InviteDAO {

	public Invite createInvite(UserType type, String email);
	
	public Invite getInvite(int id);
	
	public boolean checkForOpenInvite(int id);
	
	public void useInvite(int id);
}
