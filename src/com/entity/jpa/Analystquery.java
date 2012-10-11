package com.entity.jpa;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.*;
import com.filestorage.ReadFile;
import java.io.File;

//import java.io.*;
//import java.text.*;


public class Analystquery {
	
	int k=0;
	String[] paths= new String[1000];
	public String[] queryfilesystem(String location, String[] station) throws Exception
	{
		//first parameter in station is number of days for range query
		int days = Integer.parseInt(station[0]);	
		
		ReadFile rf = new ReadFile();
		String filepath = null;
		String strCon = null;
		String path = null;
		String outStr=null;
		int Year;
		String Month = null;
		int Day;
		
		DateFormat formatYear = new SimpleDateFormat("yyyy");
		DateFormat formatDay = new SimpleDateFormat("dd");
        Date date = new Date();
        
        int DAY = 1000 * 60 * 60 * 24* 30;
        
        Year = Integer.parseInt(formatYear.format(date));
        Day = Integer.parseInt(formatDay.format(date));
        
        Calendar cal = new GregorianCalendar();
        String[] monthName = {"Jan", "Feb","Mar", "Apr", "May", "Jun", "Jul",
        		"Aug", "Sep", "Oct", "Nov",
        		"Dec"};
        		 
        Month = monthName[cal.get(Calendar.MONTH)];
        
        while(days>0)
        {        	
        	days = getDays(Day,days,Month,Year);
        
        if(Month.equals("Jan"))
        {
        	cal.add(Calendar.YEAR, -1);
        	Year = cal.get(Calendar.YEAR);
        }
        
        cal.add(Calendar.MONTH, -1);
        Month = monthName[cal.get(Calendar.MONTH)];
        
        Day = cal.getActualMaximum(cal.DAY_OF_MONTH);        
        }
        
		path = location + "data/fileArchive/";		
		
		for(int i=1;i<station.length;i++)
		{
			for(int j=0;j<paths.length;j++)
			{
				filepath =  path + paths[j] + station[i] +".log";
				
				File fp = new File(filepath);
				if(fp.exists())
			{
				outStr = rf.queryfile(filepath);
			}
			
				if(outStr!= null && strCon != null)
			{
				strCon = strCon.concat(outStr);
			}
				
				if(outStr!= null && strCon == null)
				{
					strCon = outStr;
				}				
				outStr=null;
			}
		}
	
		String[] array = tokenize(strCon, "\n");
		String[][] arr = linetoTokens(array);
	
	
		return array;
	}
	
	public int getDays(int Day,int days,String Month,int Year)
	{
		
		
		if (days <= Day)
		{
			
			for(int i=Day;i>=days;i--)
			{
			paths[k] =   Year + "/"+ Month + "/"+ i + "/" ;
			k++;
			}
			days = 0;
		}
		else
		{
			for(int i=Day;i>=1;i--)
			{
			paths[k] =   Year + "/"+ Month + "/"+ i + "/" ;
			k++;
			}
			
			days = days - Day;
		}
		
		return days;
	
	}

	  public static String[][] linetoTokens(String[] array)
	  {
		  String[][] retArray = new String[array.length][20];
		  for (int i=0; i<array.length; i++)
		  {
			  String[] aRow = new String[20];
		 	  	  
		  	  
			  aRow = array[i].split("\\,");
					  
			  retArray[i] = aRow;
			  
		  }
		  return retArray;
		
	  }
	
	public static String[] tokenize(String inStr, String sep) 
	   {
		   String[] retStr = null;
		   Vector v = new Vector();
		   StringTokenizer st = null;
		   
		  int i=1;
		   if (inStr != null)
		   {
			   if (sep.length() > 0 )
				   st = new StringTokenizer(inStr, sep); 
			   else
				   st = new StringTokenizer(inStr); 
			   while(st.hasMoreTokens()) { 
					   String aLine = st.nextToken();
				   
				  //System.out.println(aLine + "\t" + aLine);
					   
					 v.add(aLine);
						   
				 // System.out.println(aLine); 

				   } 
		   }
		   String [] s = (String []) v.toArray(new String[v.size()]);
		   return s; 
	 	
	   }
	
	 
}
