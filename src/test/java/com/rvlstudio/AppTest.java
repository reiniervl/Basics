package com.rvlstudio;

import java.util.Properties;
import java.util.ResourceBundle;

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
		ResourceBundle rb = ResourceBundle.getBundle("db");
		ConceptSqlDAO dao = ConceptSqlDAO.getDAO(rb.getString("db_url"), rb.getString("username"), rb.getString("password"));
		Concept c = new Concept(java.util.UUID.randomUUID(), "ResourceBundle", "ResourceBundle rb = ResourceBundle.getBundle(\"db\");");
		dao.addConcept(c);
		assertTrue(true);
	}
}
