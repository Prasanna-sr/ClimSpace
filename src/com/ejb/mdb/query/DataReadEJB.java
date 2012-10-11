package com.ejb.mdb.query;

import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;

import com.entity.jpa.Data;
import com.entity.jpa.JPAmain;
import com.entity.jpa.Util;


@Stateless
@Remote(DataRead.class)
public class DataReadEJB  extends TimerTask implements DataRead{
	private static final long serialVersionUID = 308915085712926099L;
	private final static long fINIT_DELAY_1_MIN = 1000*60;//1000*10;  
	private final static long fEVERY_2_MINS = 1000*60*2;
	private final static long fEVERY_10_MINS = 1000*60*10;
		
	@Resource
	private SessionContext ctx;

	private int count;
	private String uid = UUID.randomUUID().toString();
	private String location = "";

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void doDataRead(String loc) {
		location = loc;	
		Util.setLocation(loc);
		Random ran = new Random();			
		
		Timer timer = new Timer();			
		//load data at intervals
	    timer.scheduleAtFixedRate(this, fINIT_DELAY_1_MIN, fEVERY_10_MINS);
	    
		//load metadata once
	   JPAmain.uploadstationdata2(location);	
	}
	
	public void run(){
	    System.out.println("uploading data 1 ...");
	    
	    JPAmain.uploadstationdata1(location);
	  }
}
