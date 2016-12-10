package com.techelevator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("currentUser")
public class LandingController {

	@RequestMapping(path="/")
	public String showLandingPage(ModelMap map)
	{
		if(map.containsAttribute("currentUser"))
			return "redirect:home";
		else
			return "login";
	}
}