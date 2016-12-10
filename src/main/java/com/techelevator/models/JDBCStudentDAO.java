package com.techelevator.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCStudentDAO implements StudentDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCStudentDAO(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Student addNewStudent(int userId, String studentFirstName, String studentLastName, int cohort, String language)
	{
		Student newStudent = null;
		String sqlCreateStudent = "INSERT INTO student_detail(user_id, first_name, last_name, cohort, language) VALUES (?,?,?,?,?)";
		jdbcTemplate.update(sqlCreateStudent, userId, studentFirstName, studentLastName, cohort, language);
		newStudent = new Student(userId, studentFirstName, studentLastName, cohort, language);
		return newStudent;
	}
	
	public Student getStudentByUserId(int userId)
	{
		Student selectedStudent = null;
		String sqlGetStudentByUserId = "SELECT * FROM student_detail WHERE user_id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetStudentByUserId, userId);
		if(results.next())
		{
			selectedStudent = getStudentFromRow(results);
		}
		return selectedStudent;
	}
	
	public List<Student> getStudentsByCohort(int cohort) {
		List<Student> students = new ArrayList<>();
		String sqlSelectStudentsByCohort = "SELECT * FROM student_detail WHERE cohort = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectStudentsByCohort, cohort);
		while(results.next()) {
			students.add(getStudentFromRow(results));
		}
		return students;
	}
	
	public int getCohortForDate(LocalDate date) {
		String sqlSelectCohortForDate = "SELECT * FROM cohorts WHERE start_date <= ? AND end_date >= ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectCohortForDate, date, date);
		if(result.next()) {
			return result.getInt("cohort");
		}
		return -1;
	}
	
	public List<Integer> getValidCohorts()
	{
		List<Integer> validCohorts = new ArrayList<>();
		String sqlGetValidCohorts = "SELECT cohort FROM student_detail GROUP BY cohort";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetValidCohorts);
		while(results.next())
		{
			validCohorts.add(results.getInt("cohort"));
		}
		return validCohorts;
	}

	private Student getStudentFromRow(SqlRowSet results) {
		Student selectedStudent;
		int userId = results.getInt("user_id");
		String studentFirstName = results.getString("first_name");
		String studentLastName = results.getString("last_name");
		int cohort = results.getInt("cohort");
		String language = results.getString("language");
		selectedStudent = new Student(userId, studentFirstName, studentLastName, cohort, language);
		return selectedStudent;
	}

}