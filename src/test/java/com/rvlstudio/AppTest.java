package com.rvlstudio;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		ConceptSqlDAO dao = ConceptSqlDAO.getDAO("jdbc:postgresql:basics", "postgres", "vortex");
		Concept c = new Concept(java.util.UUID.randomUUID(), "JUnit testing", "public void testApp() { assertTrue(true); }");
		dao.addConcept(c);
		assertTrue(true);
	}
}
