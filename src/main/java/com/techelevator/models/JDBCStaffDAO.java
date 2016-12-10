package com.techelevator.models;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCStaffDAO implements StaffDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCStaffDAO(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	public Staff addNewStaffMember(int userId, String firstName, String lastName)
	{
		String sqlAddNewStaffMember = "INSERT INTO staff_detail(user_id, first_name, last_name) VALUES(?,?,?);";
		jdbcTemplate.update(sqlAddNewStaffMember, userId, firstName, lastName);
		return new Staff(userId, firstName, lastName); 
	}
	
	public Staff getStaffMemberByUserId(int userId)
	{
		String sqlGetStaffMemberByUserId = "SELECT * FROM staff_detail WHERE user_id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetStaffMemberByUserId, userId);
		if(results.next())
		{
			String firstName = results.getString("first_name");
			String lastName = results.getString("last_name");
			return new Staff(userId, firstName, lastName);
		}
		return null;
	}


}
