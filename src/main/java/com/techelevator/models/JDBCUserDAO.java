package com.techelevator.models;

import javax.sql.DataSource;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.security.PasswordHasher;

@Component
public class JDBCUserDAO implements UserDAO {
	
	private JdbcTemplate jdbcTemplate;
	private PasswordHasher passwordHasher;

	@Autowired
	public JDBCUserDAO(DataSource dataSource, PasswordHasher passwordHasher) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	    this.passwordHasher = passwordHasher;
	}
	
	public User createUser(String email, String password, UserType userType)
	{
		int userId = getNextId();
		
		byte[] salt = passwordHasher.generateRandomSalt();
		String passwordHash = passwordHasher.computeHash(password, salt);
		String saltString = new String(Base64.encode(salt));
		System.out.println(passwordHash);
		
		String sqlCreateUser = "INSERT INTO users(user_id, email, hashedPassword, salt, type) VALUES (?,?,?,?,?)";
		jdbcTemplate.update(sqlCreateUser, userId, email, passwordHash, saltString, userType.toInt());
		
		return new User(userType, userId, email);
		
	}
	
	public void linkEmployeeUserWithEmployer(int linkUserId, int linkEmployerId)
	{
		String sqlLinkEmployeeUserWithEmployer = "INSERT INTO user_employer(user_id, employer_id) VALUES(?,?)";
		jdbcTemplate.update(sqlLinkEmployeeUserWithEmployer, linkUserId, linkEmployerId);
	}
	
	public boolean searchForEmailAndPassword(String email, String password)
	{
		String sqlSelectEmailAndPassword = "SELECT * FROM users WHERE UPPER(email) = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectEmailAndPassword, email.toUpperCase());
		if (results.next()) {
			String storedSalt = results.getString("salt");
			String storedPassword = results.getString("hashedPassword");
			String hashedPassword = passwordHasher.computeHash(password, Base64.decode(storedSalt));
			return storedPassword.equals(hashedPassword);
		}
		else {
			return false;
		}
	}
	
	public User getUserById(int userId) {
		User selectedUser = null;
		String sqlSelectUserById = "SELECT * FROM users WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectUserById, userId);
		if(results.next())
		{
			selectedUser = getUserFromRowResult(results);
		}
		return selectedUser;
	}
	
	public User getUserByEmail(String email)
	{
		User selectedUser = null;
		String sqlGetUserByEmail = "SELECT * FROM users WHERE UPPER(email) = UPPER(?)";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetUserByEmail, email);
		if(results.next())
		{
			selectedUser = getUserFromRowResult(results);
		}
		return selectedUser;
	}

	private User getUserFromRowResult(SqlRowSet results) {
		int userId = results.getInt("user_id");
		UserType userType = UserType.fromInt(results.getInt("type"));
		String email = results.getString("email");
		User selectedUser = new User(userType, userId, email);
		return selectedUser;
	}
	
	public int getUserIdByEmail(String email)
	{
		String sqlGetUserIdByEmail = "SELECT * FROM users WHERE UPPER(email) = UPPER(?)";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetUserIdByEmail, email);
		if(results.next())
		{
			return results.getInt("user_id");
		}
		return -1;
		
	}
	
	private int getNextId() {
		String sqlSelectNextId = "SELECT NEXTVAL('seq_userId')";
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
