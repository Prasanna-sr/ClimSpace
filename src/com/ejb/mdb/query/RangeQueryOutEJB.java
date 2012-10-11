package com.ejb.mdb.query;

import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;


@Stateless
@Remote(RangeQueryOut.class)
public class RangeQueryOutEJB implements RangeQueryOut {

	private static final long serialVersionUID = -6463169420605785186L;

	@Resource
	private SessionContext ctx;

		
	public void setRangeData(String[] aRow) {
		System.out.println("RangeQueryOutEJB in update String[]: " );		
		sendMessage(null, aRow, false);
	}

	private boolean authorized(String auth) {
		// TODO add authorization
		return true;
	}
	
	
	private void sendMessage(String msg, String[] msgArr, boolean isText) {
		QueueSession session = null;
		QueueSender sender = null;
		QueueConnection cnn = null;
		try {			
			Queue queue = (Queue) ctx.lookup("query/out");

			QueueConnectionFactory factory = (QueueConnectionFactory) ctx
					.lookup("ConnectionFactory");
			cnn = factory.createQueueConnection();
			session = cnn.createQueueSession(false,
					QueueSession.AUTO_ACKNOWLEDGE);
			sender = session.createSender(queue);

			if (isText) {
				TextMessage m = session.createTextMessage(msg);
				sender.send(m);
			}
			else {
				ObjectMessage o = session.createObjectMessage(msgArr);
				System.out.println("RangeQueryOutEJB sending message");
				sender.send(o);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				sender.close();
				session.close();
				cnn.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
}
