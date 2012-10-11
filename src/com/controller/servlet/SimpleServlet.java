package com.controller.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hornetq.utils.json.JSONException;
import org.hornetq.utils.json.JSONObject;

import de.micromata.opengis.kml.v_2_2_0.Kml;

import com.google.gson.Gson;

import com.entity.jpa.Util;

/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init reached in servlet " );
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		System.out.println("Hello From GET method");
		String format = request.getParameter("format");
		String station = request.getParameter("station");
		PrintWriter writer = response.getWriter();
		PointQueryClient clt = new PointQueryClient();
		String data =  clt.queryStation(station, format);
		System.out.println("Data From GET method" + data);
			
		// writing to kml or json format starts here
		 String[] strData;
		 String delimiter = ",";
		 strData = data.split(delimiter);
		 
		 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
         Date date = new Date();
         String st = dateFormat.format(date);
         String st1 = st.replace('/','_');
         String st2 = st1.replace(':', '_');
         String location = Util.getOutFileLocation();
	 
         ToConverter obj = new ToConverter(strData);
         
         if(format.compareToIgnoreCase("json") == 0)
			{
						       		    
			Gson gson = new Gson();
			String json = gson.toJson(obj);
		 
        	try {
				//write converted json data to a file named "file.json"
				//FileWriter writer1 = new FileWriter("c:\\JsonFile.json");
			String filepath = location + "JsonFile " + st2 + ".json";
				
				File file = new File(filepath);
				
			     if(!file.exists())
			     {
					file.createNewFile();
			     }
			     FileWriter writer1 = new FileWriter(file);
				writer1.write(json);
				writer1.close();
				writer.println("<h3>"+ json + " </h3>");
				//writer.println( convert[0] + convert[1] + convert[2]);
			     
		 
			} catch (Exception e) {
				e.printStackTrace();
			}
		 
			System.out.println(json);
		 
		    } 
			
      			
			else if(format.compareToIgnoreCase("KML") == 0)
			{
				 	 
				final Kml kml = new Kml();
				String longlat = strData[1] + "," + strData[2]; 
				String display = strData[0] + " " +strData[3] + " " + strData[4];
				
			    kml.createAndSetPlacemark()
				   .withName(display).withOpen(Boolean.TRUE)
						.createAndSetPoint().addToCoordinates(longlat);
				//marshals to console
				kml.marshal();				
				//marshals into file
				String filepath = location + "KmlFile"  + st2 + ".kml";
				writer.println("A KML file has been created in " + filepath);
				kml.marshal(new File(filepath));
			
			}
			else
			{
				writer.println("Select a proper format (json or KML)");
			}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}


class ToConverter
{
	public String latitude,longitude,station,state,temp,gust;
	
   public ToConverter(String[] convert)
   {
	    latitude = convert [1];
	    longitude = convert [2];
	    station = convert[0];
	    state = convert[4];
	    temp = convert[3];
	    gust = convert[5];
   }
}
