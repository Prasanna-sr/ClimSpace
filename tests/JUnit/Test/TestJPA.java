package com.JUnit.Test;


import javax.jms.ObjectMessage;

import com.entity.jpa.Analystquery;
import com.entity.jpa.Data;
import com.entity.jpa.Pointquery;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestJPA extends TestCase {

	public void testPublicUserQuery()
	
	{  
//		String [] station;
//		 station=  new String[1];
//		 station[0] = "HAM";
				
		Pointquery read = new Pointquery();
		Data aRow = read.JPApointquery("HATUT");
		assertEquals("HATUT 31.9 F, 12 MPH/W, 22:15", aRow);
		
		Data aRow2 = read.JPApointquery("ABCD");
		assertEquals(null, aRow2);
		
//		Analystquery aq = new Analystquery();
//		String[] strRow;
//    	strRow = aq.queryfilesystem(station[0], "select  slat,slon,stn,temp from datatbl where stn = HAM ");
//    	assertEquals("44.01,112.24,HAM,18.16", strRow);
	
		
	}
	public static Test pack(){
		return new TestSuite(TestJPA.class);
	}
}
