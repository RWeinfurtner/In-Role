package com.techelevator.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCStudentPreferencesDAO implements StudentPreferencesDAO {
	
	private JdbcTemplate jdbcTemplate;
	private StudentDAO studentDAO;
	
	@Autowired
	public JDBCStudentPreferencesDAO(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	    this.studentDAO = new JDBCStudentDAO(dataSource);
	}
	
	public int getLinkId(int studentId, int eventId) {
		String sqlEncryptIds = "SELECT pseudo_encrypt(?+?)";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlEncryptIds, studentId, eventId);
		int id;
		if(result.next()) {
			id = result.getInt(1);
		} else {
			throw new RuntimeException("Something strange happened, unable to select next user id from sequence");
		}
		return id;
	}

	public void updateStudentEmployerPreferences(boolean deletePrevious, int userId, String eventName, int employerId, int weight)
	{
		List<Integer> eventIds = getRelatedEventIdsByName(eventName);
		if(deletePrevious)
		{
			for(Integer i : eventIds)
			{
				deleteOldStudentEmployerPreferences(i, userId);
			}
		}
		submitStudentEmployerPreferences(userId, eventName, employerId, weight);
	}

	private void deleteOldStudentEmployerPreferences(int eventId, int userId)
	{
		String sqlDeleteOldStudentEmployerPreferences = "DELETE FROM student_preferences WHERE event_id = ? AND user_id = ?;";
		jdbcTemplate.update(sqlDeleteOldStudentEmployerPreferences, eventId, userId);
	}
	
	
	private void submitStudentEmployerPreferences(int userId, String eventName, int employerId, int weight)
	{
		List<Integer> eventIds = getRelatedEventIdsByName(eventName);
		for(Integer i : eventIds)
		{
			String sqlSubmitStudentEmployerPreferences = "INSERT INTO student_preferences (user_id, event_id, employer_id, weight) VALUES(?,?,?,?);";
			jdbcTemplate.update(sqlSubmitStudentEmployerPreferences, userId, i, employerId, weight);
		}
	}

	public HashMap<Integer, Integer> getStudentPreferences(int studentId, int eventId) {
		String sqlSelectPreferencesByStudent = "SELECT * FROM student_preferences WHERE user_id = ? AND event_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectPreferencesByStudent, studentId, eventId);
		HashMap<Integer, Integer> preferences = new HashMap<>();
		while(results.next()) {
			preferences.put(results.getInt("employer_id"), results.getInt("weight"));
		}
		return preferences;
	}
	
	public List<StudentPreference> getStudentPreferencesByEmployer(String eventName, int employerId) {
		String sqlSelectPreferencesByStudent = "SELECT * FROM student_preferences sp JOIN events e ON e.event_id = sp.event_id WHERE sp.employer_id = ? AND e.name = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectPreferencesByStudent, employerId, eventName);
		List<StudentPreference> preferences = new ArrayList<>();
		while(results.next()) {
			StudentPreference preference = new StudentPreference();
			preference.setEmployerId(results.getInt("employer_id"));
			preference.setEventId(results.getInt("event_id"));
			preference.setWeight(results.getInt("weight"));
			preference.setStudent(studentDAO.getStudentByUserId(results.getInt("user_id")));
			preferences.add(preference);
		}
		return preferences;
	}
	
	public List<Integer> getRelatedEventIdsByName(String eventName)
	{
		List<Integer> eventIds = new ArrayList<>();
		String sqlGetRelatedEventsByName = "SELECT * FROM events WHERE UPPER(name) = UPPER(?);";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetRelatedEventsByName, eventName);
		while(results.next())
		{
			Integer eventId = results.getInt("event_id");
			eventIds.add(eventId);
		}
		return eventIds;
	}

}
