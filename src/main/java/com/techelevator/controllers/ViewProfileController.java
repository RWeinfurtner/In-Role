package com.techelevator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.models.Employer;
import com.techelevator.models.EmployerDAO;
import com.techelevator.models.InviteDAO;
import com.techelevator.models.Staff;
import com.techelevator.models.StaffDAO;
import com.techelevator.models.Student;
import com.techelevator.models.StudentDAO;
import com.techelevator.models.User;
import com.techelevator.models.UserDAO;

@Controller
@Transactional
@SessionAttributes("currentUser")
public class ViewProfileController {

	private UserDAO userDAO;
	private EmployerDAO employerDAO;
	private StudentDAO studentDAO;
	private StaffDAO staffDAO;
	
	@Autowired
	public ViewProfileController(UserDAO userDAO, EmployerDAO employerDAO, StudentDAO studentDAO, StaffDAO staffDAO)
	{
		this.userDAO = userDAO;
		this.employerDAO = employerDAO;
		this.studentDAO = studentDAO;
		this.staffDAO = staffDAO;
	}
	
	@RequestMapping(path="/viewProfile", method=RequestMethod.GET)
	public String displayViewProfile(ModelMap modelMap){
		//check for userType, get table data from appropriate dao, route to jsp
		User user = (User)modelMap.get("currentUser");
		switch(user.getType()) {
		case ADMIN:
			return "viewAdminProfile";
		case STAFF:
			Staff staff = staffDAO.getStaffMemberByUserId(user.getId());
			modelMap.addAttribute("staff", staff);
			return "viewStaffProfile";
		case STUDENT:
			Student student = studentDAO.getStudentByUserId(user.getId());
			modelMap.addAttribute("student", student);
			return "viewProfile";
		case EMPLOYER:
			Employer employer = employerDAO.getEmployerByUserId(user.getId());
			modelMap.addAttribute("employer", employer);
			return "viewEmployerProfile";
		}
		return "";
	}
	
	@RequestMapping(path="/viewProfile", method=RequestMethod.POST)
	public String updateEmployerProfile(@RequestParam int id,
										@RequestParam String description,
										ModelMap modelMap){
		Employer employer = employerDAO.getEmployerById(id);
		employer.setDescription(description);
		employerDAO.updateProfile(employer);
		return "redirect:/viewProfile";
	}
}
