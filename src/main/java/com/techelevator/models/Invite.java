package com.techelevator.models;

import java.time.LocalDateTime;

public class Invite {

	private int id;
	private UserType userType;
	private String email;
	private LocalDateTime timeCreated;
	private LocalDateTime timeUsed;
	
	public Invite(int id, UserType userType) {
		this.id = id;
		this.userType = userType;
		this.timeCreated = LocalDateTime.now();
	}
	public Invite(int id, UserType userType, String email, LocalDateTime timeCreated) {
		this.id = id;
		this.userType = userType;
		this.email = email;
		this.timeCreated = timeCreated;
	}
	
	public int getId() {
		return id;
	}
	
	public UserType getUserType() {
		return userType;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getTimeCreated() {
		return timeCreated;
	}

	public LocalDateTime getTimeUsed() {
		return timeUsed;
	}
	public void setTimeUsed(LocalDateTime timeUsed) {
		this.timeUsed = timeUsed;
	}
}
