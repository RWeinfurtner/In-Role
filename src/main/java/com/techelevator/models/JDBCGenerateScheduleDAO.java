package com.techelevator.models;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCGenerateScheduleDAO implements GenerateScheduleDAO{

	
	private JdbcTemplate jdbcTemplate;
	private List<QueueSchedule> eventQueues = new ArrayList<>();
	
	@Autowired
	public JDBCGenerateScheduleDAO(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
					
	public QueueSchedule getQueue(int eventId, int employerId) {
		List<StudentPreference> preferences = new ArrayList<>();
		
		String sqlGetStudentPreferencesForEvent = "SELECT * FROM student_preferences WHERE "
				+ " event_id = ? AND employer_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetStudentPreferencesForEvent, eventId, employerId);
		while(results.next()){
			StudentPreference preference = new StudentPreference();
			preference.setId(results.getInt("user_id"));
			preference.setWeight(results.getInt("weight"));
			preference.setEmployerId(results.getInt("employer_id"));
			preference.setEventId(results.getInt("event_id"));
			
			preferences.add(preference);
		}
			
		QueueSchedule queue = new QueueSchedule(preferences);
		eventQueues.add(queue);
		return queue;
	}

	@Override
	public List<QueueSchedule> getEventQueues() {
		return eventQueues;
	}

}
