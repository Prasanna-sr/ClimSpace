package com.entity.jpa;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.zip.GZIPInputStream;

public class HttpClient {
	
	static int BUFFER = 2048;
	
	  public  String[][] Client(String loc) {
		  String op=null;
		  String[][] arString=null;
		  String url = "http://mesowest.utah.edu/data/mesowest.out.gz";
		  try {
			  readMesoWest(url, loc);
			 String file =loc; 
			  //unzip the contents
			  unZip (file);
			  //read file
			  String outFilename = file +".out";	
			  //String rtn =  readFromFileStream(outFilename);


			  // big file read
			  byte[] out = read2array(outFilename);
			  String outStr = new String(out);
			  System.out.println("Reading Contents from File ..");
			  //System.out.println(outStr);
			  
			  // mesowest.tbl.gz.out
			 // String[] array = tokenize(outStr,";");
			  
			  
			  
			  arString = readMesoMD(outStr);
			 			 
			  //System.out.println(outStr);
			  op = outStr;
			  
			  // end
			  System.out.println("Finished");
			  
		  }
		  
	    catch (Exception e) {    // Report any errors that arise
	      System.err.println(e);
	      System.err.println("Usage: java HttpClient <URL> [<filename>]");
	    }
		  return arString;
	  }
	  
	  public static String[][] readMesoMD(String fileStr)
	  {
		  // mesowest.tbl.gz.out
		  // tokenize
		  //BDGER   9999999 Badger Wash    CO US  3934 -10894  1529   8 ACTIVE
		  String[] array = tokenize(fileStr, "\n"); //split each file into lines
		  
		  String[][] mesoTblArray = convertLineToTokens1(array);
		  
		  //String[] array = tokenize(fileStr, ";"); //split each file into lines
		  
		  
		 //String[][] mesoTblArray = linetoTokens(array);
		  
		  
		  /*
		  //get address field(Badger Wash)
		  int start = 16; int end = 49;
		  String[] addressArray = extractFromLine(array, start, end);
		  //get remaining field (BDGER   9999999  CO US  3934 -10894  1529   8 ACTIVE)
		  String[] remainingArray = removeFromLine(array, start, end);
		  
		  String[][] mesoOut = new String[remainingArray.length][];
		  for (int i=0; i<remainingArray.length; i++)
		  {
			  //System.out.println( i + "-> " + array[i]);
			  //String[] aLineArray = tokenize(array[i], ""); 
			  mesoOut[i] = tokenize(remainingArray[i], "");  // each line into tokens
			  if ( mesoOut[i].length != 9)
				  System.out.println("in a Line with mismatch of column" + i );
		  }
		  */
		  
		  // call prasanna api with "String[][] mesoOut" and "String[] addressArray"
		  
		  return mesoTblArray;
		  
	  }

	  public static String[][] convertLineToTokens(String[] array)
	  {	  
		  String[][] retArray = new String[array.length][];
		  for (int i=0; i<array.length; i++)
		  {
			  String[] aRow = new String[10];
		  // col0 start
		  int start = 0; int end = 8;
		  aRow[0] =  array[i].substring(start, end);
		  
		  //col1 start
		  start = 8; end = 16;
		  aRow[1] = array[i].substring(start, end);	 
		  
		  // col2 start
		  start = 16; end = 49;
		  aRow[2] = array[i].substring(start, end);	 
		  
		  // col3 start
		  start = 49; end = 52;
		  aRow[3] = array[i].substring(start, end);	 
		  
		  // col4 start
		  start = 52; end = 56;
		  aRow[4] = array[i].substring(start, end);	 
		  
		  // col5 start
		  start = 56; end = 61;
		  aRow[5] = array[i].substring(start, end);	 
		  
		  // col6 start
		  start = 61; end = 69;
		  aRow[6] = array[i].substring(start, end);	 
		  
		  // col7 start
		  start = 69; end = 75;
		  aRow[7] = array[i].substring(start, end);	 
		  
		  // col8 start
		  start = 75; end = 78;
		  aRow[8] = array[i].substring(start, end);	 
		  
		// col9 start
		  start = 78; end = 85;
		  aRow[9] = array[i].substring(start, array[i].length());	 
			  	  
		  	   
		  
		  retArray[i] = aRow;
			  
		  }
		  return retArray;
		  
	  }
	  
	  public static String[][] linetoTokens(String[] array)
	  {
		  String[][] retArray = new String[array.length][16];
		  for (int i=0; i<array.length; i++)
		  {
			  String[] aRow = new String[20];
		 	  	  
		  	  
			  aRow = array[i].split("\\,");
					  
			  retArray[i] = aRow;
			  
		  }
		  return retArray;
		
	  }
	  
	  public static String[][] convertLineToTokens1(String[] array)
	  {	  
		  String[][] retArray = new String[array.length][];
		  
		  
		  for (int i=0; i<array.length; i++)
		  {
			  String[] aRow = new String[16];
		  // col0 start
		  int start = 0; int end = 8;
		  aRow[0] =  array[i].substring(start, end);
		  
		//col1 start
		  start = 9; end = 22;
		  aRow[1] = array[i].substring(start, end);	 
		  
		  //col1 start
		  start = 23; end = 32;
		  aRow[2] = array[i].substring(start, end);	 
		  
		  // col2 start
		  start = 36; end = 43;
		  aRow[3] = array[i].substring(start, end);	 
		  
		  // col3 start
		  start = 44; end = 50;
		  aRow[4] = array[i].substring(start, end);	 
		  
		  // col4 start
		  start = 51; end = 59;
		  aRow[5] = array[i].substring(start, end);	 
		  
		  // col5 start
		  start = 60; end = 68;
		  aRow[6] = array[i].substring(start, end);	 
		  
		  // col6 start
		  start = 69; end = 77;
		  aRow[7] = array[i].substring(start, end);	 
		  
		  // col7 start
		  start = 78; end = 86;
		  aRow[8] = array[i].substring(start, end);	 
		  
		  // col8 start
		  start = 87; end = 95;
		  aRow[9] = array[i].substring(start, end);	 
		  
		// col9 start
		  start = 96; end = 104;
		  aRow[10] = array[i].substring(start, end);	 
			
		  start = 105; end = 116;
		  aRow[11] = array[i].substring(start, end);	
		  
		  start = 115; end = 122;
		  aRow[12] = array[i].substring(start, end);	
		  
		  start = 123; end = 131;
		  aRow[13] = array[i].substring(start, end);	
		  
		  start = 132; end = 139;
		  aRow[14] = array[i].substring(start, end);	
		  
			// col9 start
		  start = 140; end = 149;
		  aRow[15] = array[i].substring(start, array[i].length());		  	  
		  
		  retArray[i] = aRow;
			  
		  }
		  return retArray;
		  
	  }
	  
	  
	  public static void readMesoWest(String urlStr, String loc) {
		    try {
		    	
		    	System.out.println("In readMesoWest, reading from URL " +  urlStr);
		      
		      // Get an output stream to write the URL contents to
		      OutputStream to_file;
		      to_file = new FileOutputStream(loc);
		      
		      // Now use the URL class to parse the user-specified URL into
		      // its various parts: protocol, host, port, filename.  Check the protocol
		      URL url = new URL(urlStr);
		      String protocol = url.getProtocol();
		      if (!protocol.equals("http"))
		        throw new IllegalArgumentException("URL must use 'http:' protocol");
		      String host = url.getHost();
		      int port = url.getPort();
		      if (port == -1) port = 80;  // if no port, use the default HTTP port
		      String filename = url.getFile();
		      // Open a network socket connection to the specified host and port
		      Socket socket = new Socket(host, port);
		      // Get input and output streams for the socket
		      InputStream from_server = socket.getInputStream();
		      PrintWriter to_server = 
		        new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		      
		      // Send the HTTP GET command to the Web server, specifying the file.
		      // This uses an old and very simple version of the HTTP protocol
		      to_server.println("GET " + filename);
		      to_server.flush();  // Send it right now!
		      
		      // Now read the server's response, and write it to the file
		      byte[] buffer = new byte[4096];
		      int bytes_read;
		      while((bytes_read = from_server.read(buffer)) != -1)
		        to_file.write(buffer, 0, bytes_read);
		      
		      // When the server closes the connection, we close our stuff
		      socket.close();
		      to_file.close();	      
		     
		      System.out.println("In readMesoWest, done writing to loc " + loc);
		    }
		    catch (Exception e) {    // Report any errors that arise
		      System.err.println(e);
		      System.err.println("Usage: java HttpClient <URL> [<filename>]");
		    }
		  }
	 
	  public static void unZip (String inputFile) {
		  //inputFile = inputFile.substring(0, inputFile.length() - 3);
		  try{

			  String inFilename = inputFile;
			  System.out.println("Opening the gzip file........		.................. :  opened");

			  GZIPInputStream gzipInputStream = null;
			  FileInputStream fileInputStream = null;
			  gzipInputStream = new GZIPInputStream(new 		FileInputStream(inFilename));
			  System.out.println("Opening the output file..		........... : opened");

			  String outFilename = inFilename +".out";		  
			  OutputStream out = new FileOutputStream(outFilename);
			  System.out.println("Transferring bytes from the 	compressed file to the output file........:     Transfer successful");
			  
			  byte[] buf = new byte[1024];  //size can be 		changed according to programmer's need.
			  int len;
			  while ((len = gzipInputStream.read(buf)) != -1) {
				  out.write(buf, 0, len);			  		  
			  }
			  gzipInputStream.close();
			  out.close();
		  }
			  catch(IOException e){
			  System.out.println("Exception has been thrown" + e);
			  }
		 }
	  
	  public static String readFromFileStream(String fileName) 
	  {
		String charSet = "UTF-8";
	  	File f = null;
	  	StringBuffer sb = new StringBuffer();
	  	try 
	  	{		
				String cvFileName = fileName;
			
				f = new File(cvFileName);			
					
					// Start reading
					BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), charSet)); 
					
					String tmp = null;
					String retVal = ""; 	
					
					while ((tmp = br.readLine()) != null)
					{
						if (tmp.length() > 1)
						{
							retVal = retVal + tmp;
							//System.out.println("A Line is - " + tmp);
						}
					}
					br.close();// End of read			
					
				//	System.out.println("retVal - " + retVal);
				return retVal;	
				
		} catch(Exception e) {	  
			e.printStackTrace();         
		}
		return sb.toString();
	  }	    

	 
	  private static String[] extractFromLine(String[] array, int start, int end)
	  {
		  String[] retArray = new String[array.length];
		  for (int i=0; i<array.length; i++)
		  {
			  retArray[i] = array[i].substring(start, end);
		  }
		  return retArray;
	  }
	  
	  private static String[] removeFromLine(String[] array, int start, int end)
	  {
		  String[] retArray = new String[array.length];
		  for (int i=0; i<array.length; i++)
		  {
			  retArray[i] = array[i].substring(0, start) +  array[i].substring(end, array[i].length());
		  }
		  return retArray;
	  }
	  
	  /**
	   *  Reads a file storing intermediate data into an array.
	   *  @param file the file to be read
	   *  @return a file data
	   */
	   public static byte[] read2array(String file) throws Exception {
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
					   if(i>2)
					   {
						   v.add(aLine);
					   }
					   i++;
				   } 
		   }
		   String [] s = (String []) v.toArray(new String[v.size()]);
		   return s; 
	 	
	   }


}
