package com.techelevator.controllers;

import org.apache.xpath.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.email.SendAccessNotificationEmail;
import com.techelevator.models.User;

@Controller
@SessionAttributes("currentUser")
public class UserAccessNotificationEmailController {

	private SendAccessNotificationEmail userAccessEmail;
	
	@Autowired
	public UserAccessNotificationEmailController(SendAccessNotificationEmail userAccessEmail) {
		this.userAccessEmail = userAccessEmail;	
	}
	
	@RequestMapping(path="/accessEmail", method=RequestMethod.POST)
	public String sendAccessEmail(@RequestParam String link,
								@RequestParam String email,
								ModelMap modelMap) {
		//possibly have email logging
		userAccessEmail.send(email, userAccessEmail.getSignUpMessage(link));
		return "redirect:/home?message=" + "SIGNUP_EMAIL_SENT";
	}
	
}
