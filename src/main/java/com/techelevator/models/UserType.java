package com.techelevator.models;

public enum UserType {
	ADMIN, STAFF, STUDENT, EMPLOYER;

	public static UserType fromInt(int typeNum) {
		return UserType.values()[typeNum-1];
	}
	
	public int toInt() {
		return this.ordinal()+1;
	}
}
