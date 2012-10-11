package com.filestorage;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;

public class ReadFile {
	
	
	
	public String queryfile(String filepath) throws Exception
	{
		byte[] out = read2array(filepath);
		String outStr = new String(out);
		
		return outStr;
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
	
	  public byte[] read2array(String file) throws Exception {
	      InputStream in = null;
	      byte[] out             = new byte[0];
	      try{
	         in = new BufferedInputStream(new FileInputStream(file));
	         // the length of a buffer can vary
	         int bufLen = 20000*1024;
	         byte[] buf = new byte[bufLen];
	         byte[] tmp = null;
	         int len    = 0;
	         while((len = in.read(buf,0,bufLen)) != -1){
	            // extend array
	            tmp = new byte[out.length + len];
	            // copy data
	            System.arraycopy(out,0,tmp,0,out.length);
	            System.arraycopy(buf,0,tmp,out.length,len);
	            out = tmp;
	            tmp = null;          
	         }
	      }finally{
	         // always close the stream
	         if (in != null) try{ in.close();}catch (Exception e){}
	      }
	      return out;
	   }

	
}
