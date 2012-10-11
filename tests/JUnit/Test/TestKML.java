package com.JUnit.Test;

import static org.junit.Assert.*;

import java.io.File;

import junit.framework.Assert;
import junit.framework.TestSuite;

import org.apache.cxf.helpers.FileUtils;
import org.junit.Test;
//import static org.junit.fremework.*;
import org.junit.internal.runners.TestClass;

public class TestKML {
	private static final File dir = new File("tmp");
	//@Test
	
			public void testXYZ() {
				try {
		    final File expected = new File("KMLFileCorrect.kml");
		    final File output = new File(dir, "KMLFile.kml");
		    Assert.assertEquals(FileUtils.readLines(expected), FileUtils.readLines(output));
			
		    final File expected1 = new File("JsonCorrect.kml");
		    final File output1 = new File(dir, "JsonFile.kml");
		    Assert.assertEquals(FileUtils.readLines(expected1), FileUtils.readLines(output1));
		    
		    final File expected2 = new File("AnalystKMLFileCorrect.kml");
		    final File output2 = new File(dir, "AnalystKMLFile.kml");
		    Assert.assertEquals(FileUtils.readLines(expected), FileUtils.readLines(output));
		    
		    final File expected3 = new File("JsonAnalystFileCorrect.kml");
		    final File output3 = new File(dir, "JsonAnalystKMLFile.kml");
		    Assert.assertEquals(FileUtils.readLines(expected), FileUtils.readLines(output));
				
				} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	

	
}


