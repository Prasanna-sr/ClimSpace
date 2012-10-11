package com.filestorage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StoreFile {
	
	public void writefileBystation(String location, String[][] data) throws IOException
	{
		System.out.println("Writing data to file storage .. ");
		BufferedWriter out = null;
		String currentpath=null;
		String filepath = null;
		String stData=null;
		String path=null;
		String Year = null;
		String Month = null;
		int Day;
			
		DateFormat formatYear = new SimpleDateFormat("yyyy");
		DateFormat formatMonth = new SimpleDateFormat("MMM");
		DateFormat formatDay = new SimpleDateFormat("dd");
        Date date = new Date();
        
        Year = formatYear.format(date);
        Month = formatMonth.format(date);
        Day = Integer.parseInt(formatDay.format(date));
        		
        
        
        String stRow;
		currentpath = new java.io.File(".").getCanonicalPath();
			
		StringBuffer sb = new StringBuffer();
		
		for(int i=0;i<data.length;i++)
		{
			for(int j=0;j<data[i].length;j++)
			{
				stData = data[i][0].trim();
				if(data[i][j] !=null)
				{
				sb.append(data[i][j].trim());
				}
				if(j<data[i].length - 1)
				{
				sb.append(",");
				}
				
			}
				stRow = sb.toString();
				sb.delete(0, sb.length());
				//filepath = currentpath + "\\File Storage\\1\\"+stData +".log";
				
				path = location + "data/fileArchive/"+ Year + "/"+ Month +"/"+  Day + "/";
				
				filepath =  path + stData +".log";
				File dir = new File(path);
				
				if(!dir.exists())
				{
					dir.mkdirs();
				}
				
				File file = new File(filepath);
		
		     if(!file.exists())
		     {
				file.createNewFile();
		     }


				
			FileWriter fstream = new FileWriter(filepath,true);
			out = new BufferedWriter(fstream);
			
			//out.append(retRrr[i]);
			out.write(stRow);	
			out.newLine();
			out.close();

			}	
		
		    System.out.println("Data copied to file storage successfully");

	}

}
