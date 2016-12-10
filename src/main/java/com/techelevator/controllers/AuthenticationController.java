package com.techelevator.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.models.User;
import com.techelevator.models.UserDAO;

@Controller
@SessionAttributes("currentUser")
public class AuthenticationController {
	
	private UserDAO userDAO;
	
	@Autowired
	public AuthenticationController(UserDAO userDAO)
	{
		this.userDAO = userDAO;
	}
	
	@RequestMapping(path="/login", method=RequestMethod.GET)
	public String showLoginPage(ModelMap map)
	{
		if(map.containsAttribute("currentUser"))
			return "redirect:home";
		else
			return "login";
	}
	
	@RequestMapping(path="/login", method=RequestMethod.POST)
	public String login(@RequestParam String email, @RequestParam String password, ModelMap map, HttpSession session)
	{
		if(userDAO.searchForEmailAndPassword(email, password))
		{
			User currentUser = userDAO.getUserByEmail(email);
			session.invalidate();
			map.addAttribute("currentUser", currentUser);
			return "redirect:home";
		}
		else
			return "redirect:login";
	}
	@RequestMapping(path="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session, ModelMap map)
	{
		map.remove("currentUser");
		session.removeAttribute("currentUser");
		session.invalidate();
		return "redirect:login";
	}

}