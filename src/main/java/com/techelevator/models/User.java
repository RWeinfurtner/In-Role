package com.techelevator.models;

public class User {
	
	private UserType type;
	private int id;
	private String email;
	
	
	
	public User(UserType type, int id, String email) {
		this.type = type;
		this.id = id;
		this.email = email;
	}

	public UserType getType() {
		return type;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId(){
		return id;
	}
	
	public String toString()
	{
		return email;
	}
	
	

}
