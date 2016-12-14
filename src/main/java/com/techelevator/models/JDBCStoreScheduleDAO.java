package com.techelevator.models;

import java.time.LocalTime;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.math3.util.MultidimensionalCounter.Iterator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JDBCStoreScheduleDAO implements StoreScheduleDAO {

	private JdbcTemplate jdbcTemplate;
	public JDBCStoreScheduleDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		
	}

	@Override
	public void addNewEmployerSchedule(EmployerSchedule employerSchedule)
	{
		for(Map.Entry<LocalTime, Student> entry : employerSchedule.getInterviews().entrySet())
		{
			addNewInterview(entry, employerSchedule.getEmployer().getId(), employerSchedule.getEventId());
		}
		
		
	}
	@Override
	public void addNewInterview(Map.Entry<LocalTime,Student> interview, int employerId, int eventId)
	{
		String sqlAddNewInterview = "INSERT INTO interviews(user_id, start_time, attendee_id, event_id) VALUES(?,?,?,?);";
		jdbcTemplate.update(sqlAddNewInterview, interview.getValue().getUserId(), interview.getKey(), //attendee id?)
		
	}
}
