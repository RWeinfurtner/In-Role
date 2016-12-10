package com.techelevator.models;

import java.util.List;

public interface GenerateScheduleDAO {
	
	public List<QueueSchedule> getEventQueues();
	public QueueSchedule getQueue(int eventId, int employerId);
}
