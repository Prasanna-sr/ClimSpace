package com.controller.servlet;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import org.apache.log4j.BasicConfigurator;

import com.ejb.mdb.query.PointQuery;

public class PointQueryClient  {
	// private static Logger log = Logger.getLogger(Client.class);
	String endpoint = "localhost:1099";

	public PointQueryClient() {
	}

	public PointQueryClient(String endpoint) {
		this.endpoint = endpoint;
	}

	

	public PointQuery connectV6() throws Exception {
		// These properties specify the implementation to use, as well as the
		// location of the server
		Properties jndi = new Properties();
		jndi.setProperty(Context.URL_PKG_PREFIXES,
				"org.jboss.ejb.client.naming");
		jndi.setProperty(Context.PROVIDER_URL, "jnp://localhost:1099/");
		jndi.setProperty(Context.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");

		PointQuery svr = null;
		try {
			InitialContext context = new InitialContext(jndi);

			Object ref = context.lookup("PointQueryEJB/remote");
			svr = (PointQuery) PortableRemoteObject.narrow(ref, PointQuery.class);
		} catch (NamingException e) {
			e.printStackTrace();
		}

		return svr;
	}

	public String queryStation(String stn, String format) {

		try {
			long st = System.currentTimeMillis();
			PointQuery ept = connectV6();
			if (ept != null) {
				long mt = System.currentTimeMillis();
				System.out.println("From the server: " + ept.query(stn));
				long et = System.currentTimeMillis();
				
				
				if (format.equalsIgnoreCase("kml")) {
					//data format to KML
					return ept.query(stn);
				}
				else if (format.equalsIgnoreCase("json")) {
					//data format to KML
					return ept.query(stn);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		BasicConfigurator.configure();

		//for (int n = 0, N = 1; n < N; n++) {
			PointQueryClient clt = new PointQueryClient();
			// Client clt = new Client("192.168.56.30:1099");
			// Client clt = new Client("centosvr:1099");

			String data =  clt.queryStation("ANDG", "json");
			System.out.println("Return from point query client->" + data);
		//}

	}

}
