package com.entity.jpa;


import java.util.StringTokenizer;

import java.io.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.filestorage.ReadFile;
import com.filestorage.StoreFile;

import java.util.*;
import java.text.*;


public class StoreData {
	
	public void processData1(String location)
	{
		System.out.println("Location passed as parameter is " + location);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		HttpClient hc = new HttpClient();
		//em.getTransaction().begin();
		//to delete existing data from the system 
				//int deletedCount = em.createQuery("DELETE FROM Data").executeUpdate();
				//em.getTransaction().commit();		  
				System.out.println("Data flushed successfully !");
				
		
	try{
		em.getTransaction().begin();
		int deletedCount = em.createQuery("DELETE FROM Data").executeUpdate();		
		System.out.println("Data flushed successfully !");
		
		String file = location  + "data/realtime/mesowest.gz";

		String[][] data = hc.Client(file);
		FileData fd = new FileData(location, data);
		fd.start();
				
				
		for(int i=0;i<data.length;i++)
		{
			Data d = new Data();
			for(int j=0;j<data[i].length;j++)
			{
			    String st = data[i][j].trim();
			    if(!st.equals(""))
			    {
			    	
				StringTokenizer st1 = new StringTokenizer(st," ");
            	String data1 = st1.nextToken();
			            if(j==0)
			            {
			            	d.setSTN(data1);
			            }
			            if(j==1)
			            {
			            	d.setYYMMDD_HHMM(data1);
			            	
			            }
			            if(j==2)
			            {
			            	//Float f = new Float(data1);
			            	d.setMNET(data1);
			            }
			            if(j==3)
			            {
			            	//Float f = new Float(data1);
			            	d.setSLAT(data1);
			            }
			            if(j==4)
			            {
			            	//Float f = new Float(data1);
			            	d.setSLON(data1);
			            }
			            if(j==5)
			            {
			            	//Float f = new Float(data1);
			            	d.setSELV(data1);
			            }
			            if(j==6)
			            {
			            	//Float f = new Float(data1);
			            	d.setTMPF(data1);
			            }
			            if(j==7)
			            {
			            	//Float f = new Float(data1);
		            		d.setSKNT(data1);
			            }
			            if(j==8)
			            {
			            	//Float f = new Float(data1);
			            	d.setDRCT(data1);
			            }
			            if(j==9)
			            {
			            	//Float f = new Float(data1);
			            	d.setGUST(data1);
			            }
			            if(j==10)
			            {
			            	//Float f = new Float(data1);
			            	d.setPMSL(data1);
			            }
			            if(j==11)
			            {
			            	//Float f = new Float(data1);
			            	d.setALTI(data1);
			            }
			            if(j==12)
			            {
			            	//Float f = new Float(data1);
			            	d.setDWPF(data1);
			            }
			            
			            if(j==13)
			            {
			            	//Float f = new Float(data1);
			            	d.setRELH(data1);
			            }
			            if(j==14)
			            {
			            	//Float f = new Float(data1);
			            	d.setWTHR(data1);
			            }
			            if(j==15)
			            {
			            	//Float f = new Float(data1);
			            	d.setP24I(data1);
			            }
			    }     
			}      
			em.persist(d);

			
		}
		em.getTransaction().commit();			
	

		System.out.println("Transaction completed. Data stored in database !");
		
	}catch(Exception e){
		System.out.println(e.getMessage());
	}finally{
		em.close();		
		}

}
	
/*	public void writetofileStorage1(String[][] data)
	{
		
		String currentpath=null;
		String filepath = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	        Date date = new Date();
	        String st = dateFormat.format(date);
	        String st1 = st.replace('/','_');
	        String st2 = st1.replace(':', '_');
	        String st3 = st2.replace(' ', '_');
	        
			currentpath = new java.io.File(".").getCanonicalPath();
			filepath = currentpath + "\\File Storage\\1\\file" + st2 +".log";
		//btcode		filepath =  location  + "File Storage/1/file" + st2 +".log";

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
			File file = new File(filepath);
		
		     try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		BufferedWriter out = null;
		
		for(int i=0;i<data.length;i++)
		{
			for(int j=0;j<data[i].length;j++)
			{
				try
				{
		FileWriter fstream = new FileWriter(filepath,true);
			out = new BufferedWriter(fstream);
			 //out.append(retRrr[i]);
			 out.write(data[i][j]);			 
			 out.close();
			 
			 
				}
				catch(IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			}
			 try {
				 FileWriter fstream = new FileWriter(filepath,true);
				out = new BufferedWriter(fstream);
				out.newLine();
			     out.close();
			 	} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		System.out.println("Data copied to file storage successfully");
	}
	*/
	public void writetofileStorage2(String location, String[][] data)
	{
		
		String currentpath=null;
		String filepath = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	        Date date = new Date();
	        String st = dateFormat.format(date);
	        String st1 = st.replace('/','_');
	        String st2 = st1.replace(':', '_');
	        String st3 = st2.replace(' ', '_');
	        
			currentpath = new java.io.File(".").getCanonicalPath();
			//filepath = currentpath + "\\File Storage\\2\\file" + st2 +".log";
			filepath =  location  + "File Storage/2/file" + st2 +".log";
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
			File file = new File(filepath);
		
		     try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		BufferedWriter out = null;
		
		for(int i=0;i<data.length;i++)
		{
			for(int j=0;j<data[i].length;j++)
			{
				try
				{
		FileWriter fstream = new FileWriter(filepath,true);
			out = new BufferedWriter(fstream);
			 //out.append(retRrr[i]);
			 out.write(data[i][j]);	
			 out.close();
			 
			 
				}
				catch(IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			}
			 try {
				 FileWriter fstream = new FileWriter(filepath,true);
				out = new BufferedWriter(fstream);
				out.newLine();
			     out.close();
			 	} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		System.out.println("Data copied to file storage successfully !");
	}

	public void processData2(String location) 
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		HttpClient1 hc1 = new HttpClient1();
		
		//em.getTransaction().begin();
		//to delete existing metadata from the system 
				//int deletedCount = em.createQuery("DELETE FROM Metadata").executeUpdate();
				//em.getTransaction().commit();		  
				//System.out.println("MetaData flushed successfully !");
				
		em.getTransaction().begin();
		int deletedCount = em.createQuery("DELETE FROM Metadata").executeUpdate();
		System.out.println("MetaData flushed successfully !");
		String filepath = location  + "data/realtime/mesowest_csv.gz";
		String[][] d1 = hc1.Client(filepath);
		String[][] data = new String[d1.length][20];
		int k=0;
		data = d1;
		for(int i=0;i<data.length;i++)
		{
			Metadata d = new Metadata();
			for(int j=0;j<data[i].length;j++)
			{
			    String st = data[i][j].trim();
			    if(!st.equals(""))
			    {			    	
				StringTokenizer st1 = new StringTokenizer(st," ");
            	String data1 = st1.nextToken();
			            if(j==0)
			            {
			            	d.setPrimaryid(data1);
			            }
			            if(j==1)
			            {
			            	d.setSecondaryid(data1);
			            	
			            }
			            if(j==2)
			            {
			            	
			            	d.setStation_name(data1);
			            }
			            if(j==3)
			            {
			            	
			            	d.setState(data1);
			            }
			            if(j==4)
			            {
			            	
			            	d.setCountry(data1);
			            }
			            if(j==5)
			            {
			            	if(data1.equals("NULL"))
			            	{
			            		d.setLatitude("0");
			            	}
			            	else
			            	{
			            		d.setLatitude(data1);
			            	}
			            	
			            }
			            if(j==6)
			            {
			            	if(data1.equals("NULL"))
			            	{
			            		d.setLongitude("0");
			            	}
			            	else
			            	{
			            		d.setLongitude(data1);
			            	}
			            	
			            }
			            if(j==7)
			            {
			            	
			            	d.setElevation(data1);
			            }
			            if(j==8)
			            {
			            	
		            		d.setMesowest_networkid(data1);
			            }
			            if(j==9)
			            {
			            	
			            	d.setNetwork_name(data1);
			            }
			            if(j==10)
			            {
			            	
			            	d.setStatus(data1);
			            }
			            if(j==11)
			            {
			            	
			            	d.setPrimary_provider_id(data1);
			            }
			            if(j==12)
			            {
			            	
			            	d.setPrimary_provider(data1);
			            }
			            if(j==13)
			            {
			            	
			            	d.setSecondaryid(data1);
			            }
			            
			            if(j==14)
			            {
			            	
			            	d.setSecondary_provider(data1);
			            }
			            if(j==15)
			            {
			            	
			            	d.setTertiary_provider_id(data1);
			            }
			            if(j==16)
			            {
			            	
			            	d.setTertiary_provider(data1);
			            }
			    }     
			}      
			
			//Ignoring the row if it has less than 5 columns
			if(data[i].length>5)
				em.persist(d);
				
			}

		em.getTransaction().commit();
	
		System.out.println("Transaction completed. Metadata stored in database !");
		
		
}
	
	public class FileData extends Thread{
		
		
		private String[][] data;
		private String location = "";
		public  FileData(String location, String[][] data)
		{
			this.data = data;
			this.location = location;
		}
		
		public void run()
		{
			//writetofileStorage1();
			try {
				//writefileBystation();
				StoreFile sf = new StoreFile();
				sf.writefileBystation(location, data);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
		
		/*public void writefileBystation() throws IOException
		{
			System.out.println("Writing data to file storage .. ");
			BufferedWriter out = null;
			String currentpath=null;
			String filepath = null;
			String stData=null;
				
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	        Date date = new Date();
	        String st = dateFormat.format(date);
	        String st1 = st.replace('/','_');
	        String st2 = st1.replace(':', '_');
	        String st3 = st2.replace(' ', '_');
	        String stRow;
			currentpath = new java.io.File(".").getCanonicalPath();
				
			StringBuffer sb = new StringBuffer();
			
			for(int i=0;i<data.length;i++)
			{
				for(int j=0;j<data[i].length;j++)
				{
					stData = data[i][0].trim();
					sb.append(data[i][j].trim());
					if(j<data[i].length - 1)
					{
					sb.append(",");
					}
					
				}
					stRow = sb.toString();
					sb.delete(0, sb.length());
					filepath = currentpath + "\\File Storage\\1\\"+stData +".log";
			
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

		}*/
	}
	
	}


	