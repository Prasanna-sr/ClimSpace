package com.JUnit.Test;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;



@RunWith(Suite.class)
@SuiteClasses({ TestJPA.class, TestKML.class, TestServlet.class })
public class AllTests {
	public static Test pack() {
	TestSuite suite=new TestSuite();
	suite.addTest(TestJPA.pack());
	suite.addTest(TestServlet.pack());
	
	return suite;
	}

public static void main(String[] args)
{
	TestRunner.run (pack());

}
}