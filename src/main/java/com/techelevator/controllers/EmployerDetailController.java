package com.techelevator.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.models.Employer;
import com.techelevator.models.EmployerDAO;

@Controller
@SessionAttributes("currentUser")
public class EmployerDetailController {
	
	private EmployerDAO employerDAO;
	
	@Autowired
	public EmployerDetailController(EmployerDAO employerDAO){
		this.employerDAO = employerDAO;
	}
	
	@RequestMapping(path="/hiringNetwork", method = RequestMethod.GET)
	public String displayHiringNetwork(ModelMap map){
		
		List<Employer> employers = employerDAO.getAllEmployers();
		map.put("employers", employers);
		
		return "hiringNetwork";
	}
}
