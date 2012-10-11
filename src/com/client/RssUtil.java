package com.client;

import java.net.URL;
import java.util.*;

import com.sun.cnpi.rss.elements.Channel;
import com.sun.cnpi.rss.elements.Item;
import com.sun.cnpi.rss.elements.Rss;
import com.sun.cnpi.rss.parser.RssParser;
import com.sun.cnpi.rss.parser.RssParserFactory;


public class RssUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] data = getStationData("HATUT");
		for (int i=0; i<data.length; i++)
		{
			System.out.println(" i ->" +  data[i]);	
		}
	}
	public static String[] getStationData(String station) {
		 String[] retArr = null;
		try {
			RssParser parser = RssParserFactory.createDefault();
	        Rss rss = parser.parse(new URL("http://mesowest.utah.edu/cgi-bin/droman/mesowest.rss?site=" + station));
	        Collection children = rss.getChildren();
	        if (children.size() > 0) {
	            Iterator childrenItr = children.iterator();

	               while (childrenItr.hasNext()) {
	                  Channel channel = (Channel)childrenItr.next();
	                  Collection chChildren = channel.getChildren();
	                  Iterator chItr = chChildren.iterator();

		               while (chItr.hasNext()) {
		                  Object o = chItr.next();
		                  if (o  instanceof  Item)  
		                  {
		                	  Item item = (Item)o;
		                	  System.out.println(" item.getTitle() ->" +  item.getTitle());
		                	  retArr = tokenize(item.getTitle().toString(), "");
		                	  //System.out.println(" item.getDescription() ->" +  item.getDescription());
		                	  //System.out.println(" item.getText() ->" +  item.getText());		                	
		                  }		                  
		               }

	               }
	            } 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return retArr;
	}
	
	 public static String[] tokenize(String inStr, String sep) 
	   {
		   String[] retStr = null;
		   Vector v = new Vector();
		   StringTokenizer st = null;
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
				   } 
		   }
		   String [] s = (String []) v.toArray(new String[v.size()]);
		   return s; 
	 	
	   }

}
