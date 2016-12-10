package com.techelevator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.models.Employer;
import com.techelevator.models.EmployerDAO;
import com.techelevator.models.InviteDAO;
import com.techelevator.models.Student;
import com.techelevator.models.StudentDAO;
import com.techelevator.models.User;
import com.techelevator.models.UserDAO;

@RestController
@SessionAttributes("currentUser")
public class UpdateProfileController {
	
	private UserDAO userDAO;
	private EmployerDAO employerDAO;
	private StudentDAO studentDAO;
	
	@Autowired
	public UpdateProfileController(UserDAO userDAO, EmployerDAO employerDAO, StudentDAO studentDAO)
	{
		this.userDAO = userDAO;
		this.employerDAO = employerDAO;
		this.studentDAO = studentDAO;
	}
	
//	@RequestMapping(path="/viewProfile", method=RequestMethod.POST)
//	public String updateEmployerProfile(@RequestParam String description){
////		Employer dbEmployer = employerDAO.getEmployerById(employer.getId());
////		employer.setName(dbEmployer.getName()); //data integrity for things that shouldn't be mutable
////		return employerDAO.updateProfile(employer);
//		return "";
//	}
}
