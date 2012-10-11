package com.JUnit.Test;

import javax.servlet.http.HttpServletRequest;

import static org.easymock.EasyMock.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


import com.controller.servlet.SimpleServlet;

import org.apache.cactus.ServletTestCase;
import org.apache.cactus.WebRequest;
import org.apache.cactus.WebResponse;

public class TestServlet extends TestCase
{
  
	public class ExampleCactusTest extends ServletTestCase {

		  public void beginValidate(WebRequest wr) {
		   wr.addParameter("station", "json");
		   wr.addParameter("format", "kml");
		  }

		  public void testValidate() throws Exception {
		   SimpleServlet es = new SimpleServlet();
		   es.init(config);
		   boolean answer = es.validate("Hutat","xyz");
		   assertTrue("Received wrong response", answer);
		   answer = es.validate(null,"xyz");
		   assertTrue("Received wrong response", answer);
		   answer = es.validate(null,null);
		   assertTrue("Received wrong response", answer);
		   answer = es.validate("BULLF",null);
		   assertTrue("Received wrong response", answer);
		   
		  }

		}	
    	
	public static Test pack()
	{
		
		return new TestSuite(TestServlet.class);
	}	
    	
}