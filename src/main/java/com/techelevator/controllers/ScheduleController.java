package com.techelevator.controllers;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.models.Employer;
import com.techelevator.models.EmployerDAO;
import com.techelevator.models.EmployerSchedule;
import com.techelevator.models.Event;
import com.techelevator.models.EventDAO;
import com.techelevator.models.EventSchedule;
import com.techelevator.models.GenerateScheduleDAO;
import com.techelevator.models.Student;
import com.techelevator.models.StudentDAO;
import com.techelevator.models.StudentSchedule;
import com.techelevator.models.User;
import com.techelevator.models.UserType;

@Controller
@SessionAttributes("currentUser")
public class ScheduleController {

	private GenerateScheduleDAO scheduleDAO;
	private EmployerDAO employerDAO;
	private StudentDAO studentDAO;
	private EventDAO eventDAO;
	
	@Autowired
	public ScheduleController(GenerateScheduleDAO scheduleDAO, EmployerDAO employerDAO, StudentDAO studentDAO, EventDAO eventDAO){
		this.scheduleDAO = scheduleDAO;
		this.employerDAO = employerDAO;
		this.studentDAO = studentDAO;
		this.eventDAO = eventDAO;
	}
	
	@RequestMapping(path= "/viewSchedule", method = RequestMethod.GET)
	public String displayViewSchedule(@RequestParam String eventName, ModelMap map){
		
		User currentUser = (User)map.get("currentUser");
		List<Event> events = eventDAO.getEventsByName(eventName);
		map.addAttribute("eventName", eventName);
		List<LocalTime> interviewTimes = new ArrayList<>();
		long slots;
		long beginningSlots;
		long endSlots;
		if(currentUser.getType() == UserType.STAFF) {
			List<Employer> employers = employerDAO.getEmployersRegisteredForEvent(events.get(0).getEventId());
			List<EmployerSchedule> employerSlots = EmployerSchedule.getEmployerScheduleSet(employers, events);
			List<Student> students = studentDAO.getStudentsByCohort(3);
			HashMap<Integer, StudentSchedule> studentSlots = HashMap<Integer, StudentSchedule>
		}
		if(currentUser.getType() == UserType.EMPLOYER)
		{
			if(eventParts.size() == 1)
			{
				Duration eventLength;
				//divide the total length of the event by the interview length in seconds to get the number of slots.
				eventLength = Duration.between(eventParts.get(0).getStartTime(),(eventParts.get(0).getEndTime()));
				slots = eventLength.getSeconds() / (eventParts.get(0).getInterviewLength()*60);
				for(int i = 0; i < slots; i++)
				{
					Duration interviewLength =  Duration.ofMinutes(eventParts.get(0).getInterviewLength());
					if(i==0)
						interviewTimes.add(i, eventParts.get(0).getStartTime());
					else
						interviewTimes.add(i, eventParts.get(0).getStartTime().plus(interviewLength));
				}
			}
			else
			{
				//if there's a break during the day in the event 
				Duration eventBeginningLength = Duration.between(eventParts.get(0).getStartTime(),(eventParts.get(0).getEndTime()));
				Duration eventEndLength = Duration.between(eventParts.get(1).getStartTime(),(eventParts.get(1).getEndTime()));
				beginningSlots = eventBeginningLength.getSeconds() / (eventParts.get(0).getInterviewLength()*60);
				endSlots = eventEndLength.getSeconds() / (eventParts.get(0).getInterviewLength()*60);
				Duration interviewLength =  Duration.ofMinutes(eventParts.get(0).getInterviewLength());
				for(int i = 0;i<beginningSlots;i++)
				{
					if(i==0)
						interviewTimes.add(i, eventParts.get(0).getStartTime());
					else
						interviewTimes.add(i, eventParts.get(0).getStartTime().plus(interviewLength));
				}
				
				for(int i= 0;i<endSlots;i++)
				{
					if(i==0)
						interviewTimes.add(i, eventParts.get(1).getStartTime());
					else
						interviewTimes.add(i, eventParts.get(1).getStartTime().plus(interviewLength));
				}
			}
			map.addAttribute("interviewTimes", interviewTimes);
			
				Employer currentEmployer = employerDAO.getEmployerByUserId(currentUser.getId());
				map.addAttribute("currentEmployer", currentEmployer);
				EmployerSchedule employerSchedule;
				map.addAttribute("employerSchedule", employerSchedule.getInterviews());
		}
		
		else if(currentUser.getType() == UserType.STUDENT)
		{
			Student currentStudent = studentDAO.getStudentByUserId(currentUser.getId());
			map.addAttribute("currentStudent", currentStudent);
			//temp until sched dao
			
			
			map.addAttribute("studentSchedule", studentSchedule.getInterviews());
		}
		return "viewSchedule";
	}
}