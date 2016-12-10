package com.techelevator;

import org.junit.Test;
import org.optaplanner.core.api.solver.SolverFactory;

import com.techelevator.models.MatchMakingSolution;

public class SolverTest {

	private SolverFactory<MatchMakingSolution> sf;
	public void setup() {
		sf = SolverFactory.createFromXmlResource("com/techelevator/solver/matchMakingSolverConfig.xml");
	}
	
	@Test
	public void exampleTest() {
		MatchMakingSolution solution = sf.buildSolver();
	}
	
}
