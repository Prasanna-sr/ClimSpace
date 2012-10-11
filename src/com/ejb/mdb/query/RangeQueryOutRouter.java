package com.ejb.mdb.query;

import java.io.File;
import java.io.FileWriter;
import java.util.Random;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import com.google.gson.Gson;
import de.micromata.opengis.kml.v_2_2_0.Kml;

import com.entity.jpa.Util;


@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "query/out"),
		@ActivationConfigProperty(propertyName = "maxSession", propertyValue = "10") })
// note if we add max sessions it is a jboss only property
public class RangeQueryOutRouter implements MessageListener {

	// since our app server reuses objects, this value can be dirty (!= 0 as one
	// would assume).
	private int count;

	public RangeQueryOutRouter() {

	}

	/**
	 * messages forwarded to this method
	 * 
	 * @param msg
	 */
	public void onMessage(Message msg) {
		try {
			//TextMessage tm = (TextMessage) msg;
			ObjectMessage om = (ObjectMessage) msg;
			count++;
			System.out.println("In RangeQueryOutRouter OnMessage got the message: "	);		
			
			String[] query = (String[])om.getObject();
			for (int i=0; i<query.length; i++)
			{
			System.out.println("Return Message for Range Query" + i + query[i]);
			
			}

			// 2 second delay to simulate work load and demonstrate queuing
			Thread.sleep(2000);
			
			// write from here to a file for view by client for analysis data.
			// writing to Json begins here
			String location = Util.getOutFileLocation();			
			String filepath = location + "Analystfile.json";
			FileWriter out = new FileWriter(filepath);
			
			for (int j=0; j<query.length; j++)
			 {
			 String[] convert;
			 String delimiter = ",";
			 convert = query[j].split(delimiter);		 
	         ToConverter obj = new ToConverter(convert);
	         System.out.println("ToConverter: to String -> " + obj.toString());
	              	   		
	     			obj = new ToConverter(convert);// fill the obj with data
	     			out.write(toJSon(obj));
	     	 }//end of for
				out.close();// done writing to json file
				
				// writing to KML file starts here.
				final Kml kml = new Kml();
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.newDocument();
				TransformerFactory tranFactory = TransformerFactory.newInstance();
				Transformer aTransformer = tranFactory.newTransformer();				
				
				Element root = doc.createElement("kml");
				root.setAttribute("xmlns", "http://earth.google.com/kml/2.1");
				doc.appendChild(root);
				Element dnode = doc.createElement("Document");
				root.appendChild(dnode);
				for (int k=0; k<query.length; k++)
				{
					 String[] convert;
					 String delimiter = ",";
					 convert = query[k].split(delimiter);		 
			         ToConverter obj = new ToConverter(convert);
			         
			        Element placemark = doc.createElement("Placemark");
			 		dnode.appendChild(placemark);
			 		Element name = doc.createElement("name");
			 		name.appendChild(doc.createTextNode(obj.station));
			 		placemark.appendChild(name);//here is the change in code to display name
			 		Element point = doc.createElement("Point");
			 		Element coordinates = doc.createElement("coordinates");
			 		coordinates.appendChild(doc.createTextNode(obj.latitude + "," + obj.longitude));
			 		point.appendChild(coordinates);
			 		placemark.appendChild(point);
			 		}
			 		
			 		Source src = new DOMSource(doc);
			 		String filepath2 = location + "AnalystQuery"  + ".kml";
			 		Result dest = new StreamResult(new File(filepath2));
			 		aTransformer.transform(src, dest);
			 		System.out.println("Completed writing to file " + filepath2);		

		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println("Json file created at "  + filepath);
		}
		
		
		
	}// end of onMessage
	
	
	
	
	
	//Function To convert to Json
	public static String toJSon(Object obj) 
	 {
		Gson gson = new Gson();
		String json = gson.toJson(obj);
		return json;
		}
		
}

class ToConverter
{
	public String latitude,longitude,station,temp,state;
	
   public ToConverter(String[] convert)
   {
	    latitude = convert [4];
	    longitude = convert [3];
	    station = convert[0];
	    temp = convert[6];
	}
   
   public String toString()
   {
	    return "latitude: " + latitude + ", longitude: " + longitude + ", station: " + station  +  ", temp: "+ temp  + ", state: " + state ;
	}
}
