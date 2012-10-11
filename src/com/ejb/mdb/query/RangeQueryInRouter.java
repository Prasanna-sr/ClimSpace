package com.ejb.mdb.query;

import java.util.Random;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import com.entity.jpa.JPAmain;


@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "query/in"),
		@ActivationConfigProperty(propertyName = "maxSession", propertyValue = "10") })
// note if we add max sessions it is a jboss only property
public class RangeQueryInRouter implements MessageListener {

	// since our app server reuses objects, this value can be dirty (!= 0 as one
	// would assume).
	private int count;

	public RangeQueryInRouter() {

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
			System.out.println("RangeQueryInRouter - got the message: "	);		
			
			String[] query = (String[])om.getObject();
			for (int i=0; i<query.length; i++)
			{
			System.out.println("RangeQueryInRouter - message->" + i + query[i]);
			}

			// 2 second delay to simulate work load and demonstrate queuing
			Thread.sleep(2000);
			
			// call the JPA interface here to query DB
			JPAmain.rangeQuery(query);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
