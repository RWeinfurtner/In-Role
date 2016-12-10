package com.techelevator.models;

import java.time.LocalDateTime;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCInviteDAO implements InviteDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCInviteDAO(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Invite createInvite(UserType type, String email) {
		Invite invite = new Invite(getNextId(), type);
		invite.setEmail(email);
		String sqlInsertNewInvite = "INSERT INTO invitations (invitation_id, type, email, time_created)"
				+ " VALUES (?,?,?,?)";
		jdbcTemplate.update(sqlInsertNewInvite, invite.getId(), invite.getUserType().toInt(), invite.getEmail(), invite.getTimeCreated());
		return invite;
	}
	
	public Invite getInvite(int id) {
		
		String sqlGetInvite = "SELECT * FROM invitations WHERE invitation_id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetInvite, id);
		if(results.next())
		{
			UserType userType = UserType.fromInt(results.getInt("type"));
			String email = results.getString("email");
			LocalDateTime timeCreated = results.getTimestamp("time_created").toLocalDateTime();
			Invite selectedInvite = new Invite(id, userType, email, timeCreated);
			return selectedInvite;
		}
		return null;
	}

	public boolean checkForOpenInvite(int id) {
		// TODO Auto-generated method stub
		return false;
	}


	public void useInvite(int id) {
		// TODO Auto-generated method stub
		
	}
	
	//copied from other JDBCDAOs, redundancy
	private int getNextId() {
		String sqlSelectNextId = "SELECT pseudo_encrypt(NEXTVAL('seq_invitationId'))";
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
