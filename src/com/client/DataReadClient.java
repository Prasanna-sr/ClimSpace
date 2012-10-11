package com.client;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import org.apache.log4j.BasicConfigurator;

import com.ejb.mdb.query.DataRead;

public class DataReadClient  {
	// private static Logger log = Logger.getLogger(Client.class);
	String endpoint = "localhost:1099";

	public DataReadClient() {
	}

	public DataReadClient(String endpoint) {
		this.endpoint = endpoint;
	}

	

	public DataRead connectV6() throws Exception {
		// These properties specify the implementation to use, as well as the
		// location of the server
		Properties jndi = new Properties();
		jndi.setProperty(Context.URL_PKG_PREFIXES,
				"org.jboss.ejb.client.naming");
		jndi.setProperty(Context.PROVIDER_URL, "jnp://localhost:1099/");
		jndi.setProperty(Context.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");

		DataRead svr = null;
		try {
			InitialContext context = new InitialContext(jndi);

			Object ref = context.lookup("DataReadEJB/remote");
			svr = (DataRead) PortableRemoteObject.narrow(ref, DataRead.class);
		} catch (NamingException e) {
			e.printStackTrace();
		}

		return svr;
	}

	public void dataRead(String loc) {

		try {
			long st = System.currentTimeMillis();
			DataRead ept = connectV6();
			if (ept != null) {				
				ept.doDataRead(loc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		//BasicConfigurator.configure();

		//for (int n = 0, N = 1; n < N; n++) {
		DataReadClient clt = new DataReadClient();
			// Client clt = new Client("192.168.56.30:1099");
			// Client clt = new Client("centosvr:1099");

			clt.dataRead(args[0]);
			System.out.println("in DataReadClient->" );
		//}

	}

}
