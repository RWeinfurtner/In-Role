package com.techelevator.models;

import java.util.HashMap;
import java.util.List;

public interface StudentPreferencesDAO {
	
	public HashMap<Integer, Integer> getStudentPreferences(int studentId, int eventId);
	public int getLinkId(int studentId, int eventId);
	public void updateStudentEmployerPreferences(boolean deletePrevious, int userId, String eventName, int employerId, int weight);
	public List<Integer> getRelatedEventIdsByName(String eventName);
	public List<StudentPreference> getStudentPreferencesByEmployer(String eventName, int employerId);


}
