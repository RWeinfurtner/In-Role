package com.techelevator.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.models.Employer;
import com.techelevator.models.EmployerDAO;
import com.techelevator.models.Invite;
import com.techelevator.models.InviteDAO;
import com.techelevator.models.StudentDAO;
import com.techelevator.models.User;
import com.techelevator.models.UserDAO;
import com.techelevator.models.UserType;

@Controller
@Transactional
@SessionAttributes("currentUser")
public class SignupController {
	
	private UserDAO userDAO;
	private EmployerDAO employerDAO;
	private StudentDAO studentDAO;
	private InviteDAO inviteDAO;

	@Autowired
	public SignupController(UserDAO userDAO, EmployerDAO employerDAO, InviteDAO inviteDAO, StudentDAO studentDAO)
	{
		this.userDAO = userDAO;
		this.employerDAO = employerDAO;
		this.inviteDAO = inviteDAO;
		this.studentDAO = studentDAO;
	}
	
	@RequestMapping(path="/createStaff", method=RequestMethod.POST)
	public String createStaffAccount(	@RequestParam String email, 
										@RequestParam String password,
										ModelMap modelMap)
	{
		Invite invite = inviteDAO.createInvite(UserType.STAFF, email);
		userDAO.createUser(email, password, invite.getUserType());
		return "redirect:/home?message=" + "USER_CREATED";
	}
	
	@RequestMapping(path="/invite", method=RequestMethod.POST)
	public String createUserInvitation(	@RequestParam int userType,
										@RequestParam String email,
										ModelMap modelMap)
	{
		Invite invite = inviteDAO.createInvite(UserType.fromInt(userType), email);
		modelMap.addAttribute("email", email);
		return "redirect:/inviteResult?inviteId="+ invite.getId(); 
	}
	
	@RequestMapping(path="/inviteResult", method=RequestMethod.GET)
	public String displayUserInvitationResult(	HttpServletRequest request,
												@RequestParam String inviteId,
												@RequestParam String email,
												ModelMap modelMap)
	{
		String baseUrl = String.format("%s://%s:%d/",request.getScheme(), request.getServerName(), request.getServerPort());
		String signUpUrl = baseUrl + "capstone/signUp?inviteId="+ inviteId;
		modelMap.addAttribute("email", email);
		modelMap.addAttribute("signUpUrl", signUpUrl);
		return "inviteResult"; 
	}
	
	@RequestMapping(path="/signUp", method=RequestMethod.GET)
	public String displaySignUpPage(@RequestParam int inviteId, ModelMap modelMap)
	{
		//lookup invite id in db, send to appropriate jsp
		// ask for company name in signup or after?
		Invite invite = inviteDAO.getInvite(inviteId);
		if (invite == null) {//or already used
			return "badInvite";
		}
		modelMap.addAttribute("invite", invite);
		return "signUp";
	}
	
	@RequestMapping(path="/signUp", method=RequestMethod.POST)
	public String createNewUser(@RequestParam int inviteId, 
								@RequestParam String email, 
								@RequestParam String password,
								@RequestParam(value="employerName", required=false) String employerName,
								@RequestParam(value="studentFirstName", required=false) String studentFirstName,
								@RequestParam(value="studentLastName", required=false) String studentLastName,
								@RequestParam(value="cohort", required=false) Integer cohort,
								@RequestParam(value="language", required=false) String language,
								HttpSession session,
								ModelMap modelMap)
	{
		//lookup invite id in db, send to appropriate jsp
		Invite invite = inviteDAO.getInvite(inviteId);
		String redirect = "redirect:/home";
		if (invite == null) {//or already used
			//add message
			//also, redundant to above method
			return redirect;
		}
		//TODO: company
		User linkUser = userDAO.createUser(email, password, invite.getUserType());
		if(linkUser.getType() == UserType.STUDENT)
		{
			studentDAO.addNewStudent(linkUser.getId(), studentFirstName, studentLastName, cohort, language);
		}
		if (linkUser.getType() == UserType.EMPLOYER)
		{
			Employer employer = employerDAO.getEmployerByName(employerName);
			if (employer == null) //not in database
			{
				employer = employerDAO.addNewEmployer(employerName);
				modelMap.addAttribute("employer", employer);
				redirect = "redirect:/viewProfile";
			}
			userDAO.linkEmployeeUserWithEmployer(linkUser.getId(), employer.getId());
		}
		//below code duplicated from auth controller
		User currentUser = userDAO.getUserByEmail(email);
		session.invalidate();
		modelMap.addAttribute("currentUser", currentUser);
		//close the invite
		return redirect;
	}

}