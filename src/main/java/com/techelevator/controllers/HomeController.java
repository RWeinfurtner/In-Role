package com.techelevator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.models.AdminMessages;
import com.techelevator.models.User;
import com.techelevator.models.UserType;

@Controller

@SessionAttributes("currentUser")
public class HomeController {
	
	@RequestMapping(path="/home", method=RequestMethod.GET)
	public String displayHomePage(@RequestParam(value="message", required=false) String message, ModelMap modelMap)
	{
		User user = (User)modelMap.get("currentUser");
		if(message!= null)
			modelMap.addAttribute("message", AdminMessages.valueOf(message).toString());
		UserType userType = user.getType();
		if (userType == UserType.ADMIN) {
			return "admin";
		} else if (userType == UserType.STAFF) {
			return "staffHome";
		} else if (userType == UserType.STUDENT) {
			return "studentHome";
		} else if (userType == UserType.EMPLOYER) {
			return "employerHome";
		} else return "";
	}
	
	
}
