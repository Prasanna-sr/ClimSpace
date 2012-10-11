package com.ejb.mdb.upload;

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
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "station/upload"),
		@ActivationConfigProperty(propertyName = "maxSession", propertyValue = "10") })
// note if we add max sessions it is a jboss only property
public class UploadRouter implements MessageListener {

	// since our app server reuses objects, this value can be dirty (!= 0 as one
	// would assume).
	private int count;

	public UploadRouter() {

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
			System.out.println("MDB ( N = " + count + ") got the message: "	);		
			
			String[] msgArr = (String[])om.getObject();
			for (int i=0; i<msgArr.length; i++)
			{
				System.out.println ( msgArr[i]);
			}

			// 2 second delay to simulate work load and demonstrate queuing
			Thread.sleep(2000);
			
			// call the JPA interface here to update DB
			// TEST FOR TRING OOUT JPAmain class  loading from web app
			//JPAmain.updateMesoData();
			JPAmain.upload_pointdata(msgArr);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
