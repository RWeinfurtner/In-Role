package com.techelevator;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

import org.junit.Test;
import static org.junit.Assert.*;

import com.techelevator.models.Employer;
import com.techelevator.models.EmployerDAO;
import com.techelevator.models.EmployerSchedule;
import com.techelevator.models.Event;
import com.techelevator.models.EventDAO;
import com.techelevator.models.JDBCEmployerDAO;
import com.techelevator.models.JDBCEventDAO;
import com.techelevator.models.JDBCStudentDAO;
import com.techelevator.models.JDBCStudentPreferencesDAO;
import com.techelevator.models.QueueSchedule;
import com.techelevator.models.QueueScheduleGenerator;
import com.techelevator.models.Student;
import com.techelevator.models.StudentDAO;
import com.techelevator.models.StudentPreference;
import com.techelevator.models.StudentPreferencesDAO;
import com.techelevator.models.StudentSchedule;


public class QueueScheduleGeneratorTest extends DAOIntegrationTest{

	private QueueScheduleGenerator scheduleGenerator = new QueueScheduleGenerator();
	private EventDAO eventDAO;
	private EmployerDAO employerDAO;
	private StudentDAO studentDAO;
	private StudentPreferencesDAO preferencesDAO;
	
	@org.junit.Before
	public void setUpDAO() {
		eventDAO = new JDBCEventDAO(getDataSource());
		employerDAO = new JDBCEmployerDAO(getDataSource());
		studentDAO = new JDBCStudentDAO(getDataSource());
		preferencesDAO = new JDBCStudentPreferencesDAO(getDataSource());
	}
	
	@Test
	public void generateScheduleFromEventsAndPreferences(){
		//event name
		String eventName = "Fall Matchmaking Event";
		List<Event> events = eventDAO.getEventsByName(eventName);
		//employers attending TODO make this a method
		List<Employer> employers = employerDAO.getEmployersRegisteredForEvent(events.get(0).getEventId());
		List<EmployerSchedule> employerSchedules = new ArrayList<>();
		for (Employer employer : employers) {
			EmployerSchedule employerSched = new EmployerSchedule(events);
			employerSched.setEmployer(employer);
			employerSchedules.add(employerSched);
		}
		//student cohort attending TODO make this a method
		List<Student> students = studentDAO.getStudentsByCohort(3);
		HashMap<Integer, StudentSchedule> studentSchedules = new HashMap<>();
		for (Student student : students) {
			StudentSchedule studentSched = new StudentSchedule(events);
			studentSched.setStudent(student);
			studentSchedules.put(student.getUserId(), studentSched);
		}
		//TODO refactor the crap out of this shit
		HashMap<Employer, PriorityQueue<StudentPreference>> employerStudentQs = new HashMap<>();
		for (Employer employer : employers) {
			List<StudentPreference> allPrefs = preferencesDAO.getStudentPreferencesByEmployer(eventName, employer.getId());
			PriorityQueue<StudentPreference> studentQueue = QueueSchedule.getStudentQueueForEmployer(allPrefs);
			employerStudentQs.put(employer, studentQueue);
		}
		//employer and student schedules
		QueueScheduleGenerator scheduleGen = new QueueScheduleGenerator();
		scheduleGen.GenerateSchedules(studentSchedules, employerSchedules, employerStudentQs);
		
		List<EmployerSchedule> employerResults = scheduleGen.getEmployerInterviewSchedules();
		System.out.println("Employer Schedules");
		System.out.println();
		for (EmployerSchedule employerSchedule : employerResults) {
			System.out.println(employerSchedule.getEmployer().getName());
			HashMap<LocalTime, Student> interviews = employerSchedule.getInterviews();
			for (LocalTime timeSlot : interviews.keySet()) {
				System.out.println(timeSlot);
				System.out.println(interviews.get(timeSlot).getFirstName());
			}
		}
	}
	
}
