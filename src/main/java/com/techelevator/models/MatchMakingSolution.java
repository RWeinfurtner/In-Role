package com.techelevator.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.Solution;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore;

@PlanningSolution
public class MatchMakingSolution implements Solution<HardMediumSoftScore>{

	private List<Employer> employerList;
	private List<Slot> slotList;
	
	private List<Student> studentList;
	private List<StudentAssignment> studentAssignmentList;
	
	
	private HardMediumSoftScore score;
	
	@ValueRangeProvider(id ="employerRange" )
	public List<Employer> getEmployerList() {
		return employerList;
	}

	public List<Student> getStudentList() {
		return studentList;
	}
	
	@PlanningEntityCollectionProperty
	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	public List<Slot> getSlotList() {
		return slotList;
	}

	public void setSlotList(List<Slot> slotList) {
		this.slotList = slotList;
	}

	public HardMediumSoftScore getScore() {
		return score;
	}

	public void setScore(HardMediumSoftScore score) {
		this.score = score;
	}

	public void setEmployerList(List<Employer> employerList) {
		this.employerList = employerList;
	}

	@Override
	public Collection<? extends Object> getProblemFacts() {
		List<Object> facts = new ArrayList<Object>();
		facts.addAll(employerList);
		facts.addAll(slotList);
		facts.addAll(studentList);
		return facts;
	}
	
	
	
	
}
