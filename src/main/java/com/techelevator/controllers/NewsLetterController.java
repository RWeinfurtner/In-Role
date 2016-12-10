package com.techelevator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.techelevator.email.SendNewsletter;

@Controller
public class NewsLetterController {
	
	private SendNewsletter welcomeNewsletter;

	@Autowired
	public NewsLetterController(SendNewsletter welcomeNewsletter)
	{
		this.welcomeNewsletter = welcomeNewsletter;
	}
	
	@RequestMapping(path="/newsletter", method=RequestMethod.POST)
	public String newsletterClick(@RequestParam String newsletterEmail) {
		welcomeNewsletter.setToEmailAddress(newsletterEmail);
		welcomeNewsletter.send(newsletterEmail);
		return "redirect:/login";
	}
}
