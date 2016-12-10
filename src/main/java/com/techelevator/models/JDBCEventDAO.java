package com.techelevator.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCEventDAO implements EventDAO {
	
private JdbcTemplate jdbcTemplate;

	
	@Autowired
	public JDBCEventDAO(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Event createNewEvent(LocalDate date, String name, LocalTime startTime, LocalTime endTime, int interviewLength)
	{
		int eventId = getNextId();
		String sqlCreateNewEvent = "INSERT INTO events (event_id, day, name, start_time, end_time, interview_length) VALUES (?,?,?,?,?,?)";
		jdbcTemplate.update(sqlCreateNewEvent, eventId, date, name, startTime, endTime, interviewLength);
		return new Event(eventId, date, name, startTime, endTime, interviewLength);
	}
	
	public List<Event> getAllEvents()
	{
		List<Event> events = new ArrayList<>();
		String sqlGetEventsByName = "SELECT * FROM events";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetEventsByName);
		while(results.next())
		{
			boolean eventAlreadyInList = false;
			Event currentEvent = makeEventFromRowResult(results);
			for(Event e : events)
			{
				if(e.getName().equals(currentEvent.getName()))
					eventAlreadyInList = true;
			}
			if(!eventAlreadyInList)
				events.add(currentEvent);
		}
		return events;
	}

	private Event makeEventFromRowResult(SqlRowSet results) {
		Event currentEvent = new Event();
		currentEvent.setDay(results.getDate("day").toLocalDate());
		currentEvent.setEndTime(results.getTime("end_time").toLocalTime());
		currentEvent.setStartTime(results.getTime("start_time").toLocalTime());
		currentEvent.setEventId(results.getInt("event_id"));
		currentEvent.setInterviewLength(results.getInt("interview_length"));
		currentEvent.setName(results.getString("name"));
		return currentEvent;
	}
	
//	public EventSet getEventSetByName(String name)
//	{
//		String sqlGetEventSetByName = "SELECT * FROM events WHERE name = ? ORDER BY start_time";
//		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetEventSetByName, name);
//		while(results.next())
//		{
//			Event currentEvent = makeEventFromRowResult(results);
//			events.add(currentEvent);
//		}
//		return events;
//		
//	}
	
	public List<Event> getEventsByName(String name)
	{
		List<Event> events = new ArrayList<>();
		String sqlGetEventsByName = "SELECT * FROM events WHERE name = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetEventsByName, name);
		while(results.next())
		{
			Event currentEvent = makeEventFromRowResult(results);
			events.add(currentEvent);
		}
		return events;
	}
	
	public Event getEventById(int eventId)
	{
		String sqlGetEventById = "SELECT * FROM events WHERE event_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetEventById, eventId);
		if(results.next())
		{
			Event currentEvent = makeEventFromRowResult(results);
			return currentEvent;
		}
		return null;
	}
	
	public List<Event> getEventsByCohort(int cohort) {
		String sqlSelectEventByCohort = "SELECT * FROM events "
				+ "WHERE events.day BETWEEN (SELECT start_date FROM cohorts WHERE cohort = ?) AND (SELECT end_date FROM cohorts WHERE cohort = ?)";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectEventByCohort, cohort, cohort);
		List<Event> events = new ArrayList<>();
		while(results.next()) {
			events.add(makeEventFromRowResult(results));
		}
		return events;
	}
	
	public void signUpEmployerForEventByEmployerId(int eventId, int employerId)
	{
		int attendeeId = getNextAttendeeId();
		String sqlCreateAttendee = "INSERT INTO attendees(attendee_id, attendee_name, employer_id) VALUES (?,?,?);";
		jdbcTemplate.update(sqlCreateAttendee, attendeeId, "Interviewer 1", employerId);
		linkAttendeeWithEvent(eventId, attendeeId);
	}
	
	public boolean isEmployerSignedUpForEvent(int employerId)
	{
		
		String sqlIsEmployerSignedUpForEvent = "SELECT * FROM attendees WHERE employer_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlIsEmployerSignedUpForEvent, employerId);
		if(results.next())
			return true;
		else
			return false;
				
		
	}
	
	public void linkAttendeeWithEvent(int eventId, int attendeeId)
	{
		String sqlLinkAttendeeWithEvent = "INSERT INTO events_attendees(event_id, attendee_id) VALUES(?,?);";
		jdbcTemplate.update(sqlLinkAttendeeWithEvent, eventId, attendeeId);
	}
	
	private int getNextId() {
		String sqlSelectNextId = "SELECT NEXTVAL('seq_eventId')";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectNextId);
		int id;
		if(results.next()) {
			id = results.getInt(1);
		} else {
			throw new RuntimeException("Something strange happened, unable to select next user id from sequence");
		}
		return id;
	}
	
	private int getNextAttendeeId() {
		String sqlSelectNextId = "SELECT NEXTVAL('seq_AttendeeId')";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectNextId);
		int id;
		if(results.next()) {
			id = results.getInt(1);
		} else {
			throw new RuntimeException("Something strange happened, unable to select next user id from sequence");
		}
		return id;
	}

	

}
