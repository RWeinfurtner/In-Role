package com.techelevator.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.models.AdminMessages;
import com.techelevator.email.SendAccessNotificationEmail;
import com.techelevator.models.Employer;
import com.techelevator.models.EmployerDAO;
import com.techelevator.models.Event;
import com.techelevator.models.EventDAO;
import com.techelevator.models.Student;
import com.techelevator.models.StudentDAO;
import com.techelevator.models.StudentPreferencesDAO;
import com.techelevator.models.User;
import com.techelevator.models.UserDAO;
import com.techelevator.models.UserType;


@Controller
@Transactional
@SessionAttributes("currentUser")
public class EventController {
	
	EventDAO eventDAO;
	UserDAO userDAO;
	EmployerDAO employerDAO;
	StudentDAO studentDAO;
	StudentPreferencesDAO studentPreferencesDAO;
	private SendAccessNotificationEmail userAccessEmail = new SendAccessNotificationEmail();
	
	@Autowired
	public EventController(EventDAO eventDAO, UserDAO userDAO, EmployerDAO employerDAO, StudentDAO studentDAO,
							StudentPreferencesDAO studentPreferencesDAO)
	{
		this.eventDAO = eventDAO;
		this.userDAO = userDAO;
		this.employerDAO = employerDAO;
		this.studentDAO = studentDAO;
		this.studentPreferencesDAO = studentPreferencesDAO;
	}
	
	@RequestMapping(path="/viewAllEvents", method=RequestMethod.GET)
	public String viewAllEvents(ModelMap map)
	{
		User currentUser = (User)map.get("currentUser");
		List<Event> events = eventDAO.getAllEvents();
		map.addAttribute("eventList", events); 
		return "viewAllEvents";
	}
	
	@RequestMapping(path="/viewEvent", method=RequestMethod.GET)
	public String viewEvent(@RequestParam int eventId,
							@RequestParam(required=false) String message,
							ModelMap map){
		if(message!= null)
			map.addAttribute("message", AdminMessages.valueOf(message).toString());
		//show only cohorts with users in them
		List<Integer> validCohorts = studentDAO.getValidCohorts();
		map.addAttribute("validCohorts", validCohorts);
		//get employers registered for the event
		List<Employer> registeredEmployers = employerDAO.getEmployersRegisteredForEvent(eventId);
		map.addAttribute("registeredEmployers", registeredEmployers);
		
		User currentUser = (User)map.get("currentUser");
		Student student = studentDAO.getStudentByUserId(currentUser.getId());
		Event event = eventDAO.getEventById(eventId);
		//check if the student looking at the event is signed up and able to submit employer preferences
		if (studentHasEventAccess(student, event)) {
			map.addAttribute("hasAccess", true);
			HashMap<Integer, Integer> prefs = studentPreferencesDAO.getStudentPreferences(student.getUserId(), event.getEventId());
			map.addAttribute("preferences", prefs);//add this to jsp
		}
		map.addAttribute("selectedEvent", event);
		return "viewEvent";
	}
	
	public boolean studentHasEventAccess(Student student, Event event) {
		if (student != null) {
			List<Event> events = eventDAO.getEventsByCohort(student.getCohort());
			for (Event e : events) {
				if (e.getEventId() == event.getEventId()) {
					return true;
				}
			} 
		} return false;
	}
	
	@RequestMapping(path="/submitEventPreferences", method=RequestMethod.POST)
	public String submitEventPreferences(@RequestParam Integer[] employerPreference,
										@RequestParam int eventId,
										ModelMap map)
	{
		User currentUser = (User)map.get("currentUser");
		Event selectedEvent = eventDAO.getEventById(eventId);
		List<Employer> registeredEmployers = employerDAO.getEmployersRegisteredForEvent(eventId);
		if(registeredEmployers.size() > employerPreference.length)
			return "redirect:viewEvent?eventId=" + eventId + "&message=" + "NEW_EMPLOYER_ADDED";

		//check they only picked one 1 and one 2
		int firstChoices = 0;
		int secondChoices = 0;
		for(int i= 0; i<employerPreference.length; i++)
		{
			if(employerPreference[i] == 1)
				firstChoices++;
			else if(employerPreference[i] == 2)
				secondChoices++;
			if(firstChoices > 1 || secondChoices > 1)
				return "redirect:viewEvent?eventId=" + eventId + "&message=" + "INVALID_SELECTIONS";
		} 
		
		//submit preferences with weight. We set weight at 3 for a choice of 1 because of weighting in the scheduling algorithm.
		
		boolean deletePreviousPreferences = true;
		for(int i= 0; i<employerPreference.length; i++)
		{
			
			if(employerPreference[i] == 1)
				studentPreferencesDAO.updateStudentEmployerPreferences(deletePreviousPreferences, currentUser.getId(), selectedEvent.getName(), registeredEmployers.get(i).getId(), 3);
			if(employerPreference[i] == 2)
				studentPreferencesDAO.updateStudentEmployerPreferences(deletePreviousPreferences, currentUser.getId(), selectedEvent.getName(), registeredEmployers.get(i).getId(), 2);
			if(employerPreference[i] == 3)
				studentPreferencesDAO.updateStudentEmployerPreferences(deletePreviousPreferences, currentUser.getId(), selectedEvent.getName(), registeredEmployers.get(i).getId(), 1);
			deletePreviousPreferences= false;
		}
		return "redirect:/home?message=" + "PREFERENCES_SUBMITTED";
	}

	@RequestMapping(path="/signUpForEvent", method=RequestMethod.POST)
	public String signUpForEvent(HttpServletRequest request,
								@RequestParam int eventId,
								@RequestParam(required=false) Integer cohort,
								ModelMap map)
	{
	Event selectedEvent = eventDAO.getEventById(eventId);
		User currentUser = (User)map.get("currentUser");
		map.addAttribute("selectedEvent", eventDAO.getEventById(eventId));
		if(currentUser.getType().equals(UserType.EMPLOYER))
		{
			Employer currentEmployer = employerDAO.getEmployerByUserId(currentUser.getId());
			if(!eventDAO.isEmployerSignedUpForEvent(currentEmployer.getId()))
			{
				eventDAO.signUpEmployerForEventByEmployerId(selectedEvent.getEventId(), currentEmployer.getId());
				return "redirect:/home?message=" + "EVENT_SIGNUP";
			}
			else
				return "redirect:viewEvent?eventId=" + eventId + "&message=" + "INVALID_ALREADY_REGISTERED"; 
		}
		if(currentUser.getType().equals(UserType.STAFF)) {
			int studentCohort = cohort;
			List<Student> students = studentDAO.getStudentsByCohort(studentCohort);
			for (Student student : students) {
				int studentId = student.getUserId();
				String email = userDAO.getUserById(studentId).getEmail();
				int prefsId = studentPreferencesDAO.getLinkId(studentId, eventId); // this might be superfluous
				String baseUrl = String.format("%s://%s:%d/",request.getScheme(), request.getServerName(), request.getServerPort());
				String linkUrl = baseUrl + "capstone/signUpForEvent";
				userAccessEmail.send(email, userAccessEmail.getStudentEventMessage(linkUrl));
			}
			return "redirect:/home?message=" + "STUDENT_EVENT_INVITES";
		}
		else
			return "redirect:/home?message=" + "ERROR";
	}
	
	
	 
	@RequestMapping(path="/createEvent", method=RequestMethod.GET)
	public String displayCreateEvent(ModelMap map){
		User currentUser = (User)map.get("currentUser");
		if(currentUser.getType().equals(UserType.STAFF))
			return "createEvent";
		else
			return "redirect:/home";
	}
	
	@RequestMapping(path="/createEvent", method=RequestMethod.POST)
	public String createEvent(@RequestParam(value="eventDays") String day,
							@RequestParam String eventName,
							@RequestParam(value="startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
							@RequestParam(value="endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime,
							@RequestParam Integer interviewLength,
							@RequestParam(value="breakEntry", required=false) String breakEntry,
							@RequestParam(value="breakStart", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime breakStart,
							@RequestParam(value="breakEnd", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime breakEnd,
							ModelMap map)
	{
		User currentUser = (User)map.get("currentUser");
		if(currentUser.getType().equals(UserType.STAFF))
		{
			LocalDate selectedDate = LocalDate.parse(day, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
			if(breakEntry == null)
			{
				if(startTime.isBefore(endTime))
					eventDAO.createNewEvent(selectedDate, eventName, startTime, endTime, interviewLength);
				else
					return "redirect:/createEvent?message=" + "INVALID_TIME";
			}
			else
			{
				if(startTime.isBefore(endTime) && startTime.isBefore(breakStart) && breakStart.isBefore(breakEnd) && breakEnd.isBefore(endTime))
				{
					eventDAO.createNewEvent(selectedDate, eventName, startTime, breakStart, interviewLength);
					eventDAO.createNewEvent(selectedDate, eventName, breakEnd, endTime, interviewLength);
				}
				else
					return "redirect:/createEvent?message=" + "INVALID_TIME";
			}
		}
		return "redirect:/home?message=" + "EVENT_CREATED";

	}

}
