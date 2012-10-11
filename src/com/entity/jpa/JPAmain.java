package com.entity.jpa;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ejb.mdb.query.RangeQueryOutClient;


public class JPAmain {
 
	public static String location = "";
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		StoreData sd= new StoreData(); 
		try{
			
			String[] query ={"ABAUT","BULLF","YSASD"};
			String[] data={"PRK_point_test","20120201/2200","37.84","-109.46","19.33","15.74"};
			
			
			//uploadstationdata1();
			
			//uploadstationdata2();
			
			//sample data to pass
			
			//upload_pointdata(data);
			
			pointQuery("abaut");
			
			//rangeQuery(query);
			
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public static void upload_pointdata(String[] data)
	{
		System.out.println("upload JPA main ");
		Uploadpointdata pd = new Uploadpointdata();
		
		pd.upload_data(data);
		
	}
	
	public static Object[] pointQuery(String stn) {
		Pointquery read = new Pointquery();
		System.out.println("Received Point Query in JPA Server1 for station->" + stn);
		
		//Data aRow = read.JPApointquery(stn);
		Object[] aRow = read.JPApointquery(stn);
		//System.out.println("new row"+aRow);
		//String aRow = "HATUT 31.9 F, 12 MPH/W, 22:15";
		Data aRow1 =null;
		return aRow;
	}
		
	public static void rangeQuery(String[] query) throws Exception {
		
		
		System.out.println("Received rangeQuery in JPAmain, query lenght ->" + query.length);
		
		
		Analystquery aq = new Analystquery();
		String[] strRow;
		String location = Util.getLocation();
		strRow = aq.queryfilesystem(location, query);
		System.out.println("********range Query return from JPAMAin ->, " + strRow.length );
			
		// update MDB queue after query with result rows - String[][]
		//Data1 aRow = read.JPAanalystquery(query);

		RangeQueryOutClient clt = new RangeQueryOutClient();

		System.out.println("Writing output from Hibmain for RangeQuery out to queue");
		clt.setRangeData(strRow);
		}
  
	
	// Upload station data from file mesowest.gz
	public static void uploadstationdata1(String location)
	{
		JPAmain.location = location;
		StoreData sd = new StoreData();
		sd.processData1(location);		
	}
	
	// Upload station data from file mesowest_csv.gz
	public static void uploadstationdata2(String location) 
	{
		StoreData sd = new StoreData();
		sd.processData2(location);
		
	}
	
}
