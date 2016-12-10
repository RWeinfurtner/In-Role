package com.techelevator;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.techelevator.models.Invite;
import com.techelevator.models.InviteDAO;
import com.techelevator.models.JDBCInviteDAO;
import com.techelevator.models.UserType;

public class JDBCInviteDAOTest extends DAOIntegrationTest {

	InviteDAO dao;
	
	@Before
	public void setupDAO() {
		DataSource dataSource = getDataSource();
		dao = new JDBCInviteDAO(dataSource);
	}
	
	@Test
	public void dao_successfully_creates_and_locates_an_invite_table_entry() {
		Invite invite = dao.createInvite(UserType.STAFF, "st@ff");
		Invite dbInvite = dao.getInvite(invite.getId());
		assertEquals(invite.getId(), dbInvite.getId());
		assertEquals(invite.getUserType(), dbInvite.getUserType());
		assertEquals(invite.getEmail(), dbInvite.getEmail());
		assertEquals(invite.getTimeCreated(), dbInvite.getTimeCreated());
	}
	
}
