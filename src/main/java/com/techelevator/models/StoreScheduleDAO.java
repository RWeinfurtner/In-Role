package com.techelevator.models;

import java.time.LocalTime;
import java.util.Map;

public interface StoreScheduleDAO {

	public void addNewEmployerSchedule(EmployerSchedule employerSchedule);
	public void addNewInterview(Map.Entry<LocalTime,Student> interview, int employerId, int eventId);
	
}
