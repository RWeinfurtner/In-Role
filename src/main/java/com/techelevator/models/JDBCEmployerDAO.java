package com.techelevator.models;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCEmployerDAO implements EmployerDAO {
	
private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCEmployerDAO(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Employer addNewEmployer(String employerName)
	{
		Long employerId = getNextId();
		String sqlAddNewEmployer = "INSERT INTO employer_detail(employer_id, employer_name) VALUES (?,?);";
		jdbcTemplate.update(sqlAddNewEmployer, employerId, employerName);
		return new Employer(employerId.intValue(), employerName);
	}
	
	public Employer getEmployerById(int id)
	{
		String sqlGetEmployerIdById = "SELECT * FROM employer_detail WHERE employer_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetEmployerIdById, id);
		if(results.next()) {
			return createEmployerFromRowResult(results);
		}
		return null;
	}
	
	public Employer getEmployerByName(String employerName)
	{
		String sqlGetEmployerIdByName = "SELECT * FROM employer_detail WHERE UPPER(employer_name) = UPPER(?)";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetEmployerIdByName, employerName);
		if(results.next()) {
			return createEmployerFromRowResult(results);
		}
		return null;
	}
	
	public List<Employer> getEmployersRegisteredForEvent(int eventId)
	{
		List<Employer> registeredEmployers = new ArrayList<>();
		String sqlGetEmployersRegisteredForEvent = "SELECT * FROM employer_detail AS ed "
				+ "JOIN attendees AS a ON ed.employer_id = a.employer_id "
				+ "JOIN events_attendees AS ea ON a.attendee_id = ea.attendee_id "
				+ "WHERE ea.event_id = ? ORDER BY ed.employer_id ASC";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetEmployersRegisteredForEvent, eventId);
		while(results.next())
		{
			Employer currentEmployer = createEmployerFromRowResult(results);
			registeredEmployers.add(currentEmployer);
		}
		return registeredEmployers;
	}

	public Employer getEmployerByUserId(int userId) {
		String sqlGetEmployerByUserId = "SELECT * FROM employer_detail AS e "
				+ "JOIN user_employer AS u "
				+ "ON e.employer_id = u.employer_id "
				+ "WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetEmployerByUserId, userId);
		if(results.next()) {
			return createEmployerFromRowResult(results);
		}
		return null;
	}
	
	public List<Employer> getAllEmployers(){
		List<Employer> employers = new ArrayList<>();
		String sqlSelectAllEmployers = "SELECT * FROM employer_detail";
		SqlRowSet row = jdbcTemplate.queryForRowSet(sqlSelectAllEmployers);
		
		while(row.next()){
			Employer employer = createEmployerFromRowResult(row);
			employers.add(employer);
		}
		return employers;
	}
	
	private Long getNextId() {
//		SqlRowSet count = jdbcTemplate.queryForRowSet("SELECT COUNT(*) FROM employer_detail");
//		count.next();
//		jdbcTemplate.update("SELECT SETVAL('seq_employerId', ?)", count.getInt(1));
		String sqlSelectNextId = "SELECT NEXTVAL('seq_employerId')";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectNextId);
		Long id = null;
		if(results.next()) {
			id = results.getLong(1);
		} else {
			throw new RuntimeException("Something strange happened, unable to select next employer id from sequence");
		}
		return id;
	}
	
	public boolean updateProfile(Employer employer) {
		String sqlUpdateEmployer = "UPDATE employer_detail "
				+ "SET employer_name = ?, company_description = ? "
				+ "WHERE employer_id = ?";
		return (jdbcTemplate.update(sqlUpdateEmployer, employer.getName(), employer.getDescription(), employer.getId()) == 1);
	}
	
	private Employer createEmployerFromRowResult(SqlRowSet row) {
		Employer employer = new Employer();
		employer.setId(row.getInt("employer_id"));
		employer.setName(row.getString("employer_name"));
		employer.setDescription(row.getString("company_description"));
		return employer;
	}
}