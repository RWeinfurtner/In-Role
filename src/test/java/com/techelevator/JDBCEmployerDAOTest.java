package com.techelevator;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.techelevator.models.Employer;
import com.techelevator.models.JDBCEmployerDAO;

public class JDBCEmployerDAOTest extends DAOIntegrationTest {
	
	JDBCEmployerDAO dao;

	@Before
	public void setupDAO() {
		DataSource dataSource = getDataSource();
		dao = new JDBCEmployerDAO(dataSource);
	}
	
	@Test
	public void dao_creates_a_new_employer_row_and_finds_id_by_name() {
		Employer emp = dao.addNewEmployer("InRole");
		Employer dbEmp = dao.getEmployerByName(emp.getName());
		assertEquals(emp.getId(), dbEmp.getId());
	}
	
	@Test
	public void dao_can_find_an_employer_by_userId() {
		
	}
	
	@Test
	public void dao_can_update_employer_details() {
		Employer empl = dao.getEmployerByName("Skinder");
		empl.setDescription("it's like Tinder...");
		assertTrue(dao.updateProfile(empl));
		assertEquals(empl.getDescription(), dao.getEmployerByName("Skinder").getDescription());
	}
}
