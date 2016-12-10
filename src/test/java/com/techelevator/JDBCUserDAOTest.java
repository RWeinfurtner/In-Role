package com.techelevator;

import static org.junit.Assert.assertEquals;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;

import com.techelevator.models.JDBCUserDAO;
import com.techelevator.models.User;
import com.techelevator.models.UserDAO;

import com.techelevator.models.UserType;
import com.techelevator.security.PasswordHasher;

public class JDBCUserDAOTest extends DAOIntegrationTest {
	
	UserDAO dao;
	PasswordHasher hasher;
	
	@Before
	public void setupDAO() {
		DataSource dataSource = getDataSource();
		hasher = new PasswordHasher();
		dao = new JDBCUserDAO(dataSource, hasher);
	}
	
	@Test
	public void dao_can_create_a_user_table_entry_and_find_its_id() {
		String email = "s@a.m";
		User user = dao.createUser(email, "sam", UserType.EMPLOYER);
		assertEquals(user.getId(), dao.getUserIdByEmail(email));
	}
	
	@Test
	public void dao_creates_user_and_pulls_matching_data() {
		String email = "s@a.m";
		User user = dao.createUser(email, "sam", UserType.EMPLOYER);
		User dbUser = null;
		if(dao.searchForEmailAndPassword(email, "sam"))
		{
			dbUser = dao.getUserByEmail(email);
			assertEquals(user.getId(), dbUser.getId());
			assertEquals(user.getEmail(), dbUser.getEmail());
			assertEquals(user.getType(), dbUser.getType());
		}
	}
}
