package com.ejb.mdb.query;

import java.io.IOException;
import java.util.Random;
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


@Stateless
@Remote(PointQuery.class)
public class PointQueryEJB implements PointQuery {
	private static final long serialVersionUID = 308915085712926017L;
	
	@Resource
	private SessionContext ctx;

	private int count;
	private String uid = UUID.randomUUID().toString();

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String query(String stn) {
		Object[] data = null;
		
		
		try {						
			Random ran = new Random();
			data = JPAmain.pointQuery(stn);
			Thread.sleep(30 * (ran.nextInt(10) + 1));
		}  catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String a = data[0] + ", " + data[2] + "," + data[3] + "," + data[4]+","+data[5]+","+ data[6];
		return a;
	}
	
	@AroundInvoke
	public Object preCheck(InvocationContext ctx) throws Exception {
		System.out.println("Echo's Preflight check!");

		return ctx.proceed();
	}
}
