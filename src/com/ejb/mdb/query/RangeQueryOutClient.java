package com.ejb.mdb.query;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

public class RangeQueryOutClient implements RangeQueryOut {

	private static final long serialVersionUID = 1L;

	protected String endpoint = "localhost:1099";
	protected Context ctx;

	public RangeQueryOutClient() {
	}

	public RangeQueryOutClient(String endpoint) {
		this.endpoint = endpoint;
	}
	
	public void setRangeData(String[] aRow) { 
		try {
			RangeQueryOut svr = connectV6();
			svr.setRangeData(aRow);
	} catch (Exception e) {
		e.printStackTrace();
	}
}

	public RangeQueryOut connectV6() throws Exception {
		// These properties specify the implementation to use, as well as the
		// location of the server
		Properties jndi = new Properties();
		jndi.setProperty(Context.URL_PKG_PREFIXES,
				"org.jboss.ejb.client.naming");
		jndi.setProperty(Context.PROVIDER_URL, "jnp://localhost:1099/");
		jndi.setProperty(Context.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");

		RangeQueryOut svr = null;
		try {
			InitialContext context = new InitialContext(jndi);

			Object ref = context.lookup("RangeQueryOutEJB/remote");
			svr = (RangeQueryOut) PortableRemoteObject.narrow(ref, RangeQueryOut.class);
		} catch (NamingException e) {
			e.printStackTrace();
		}

		return svr;
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RangeQueryOutClient clt = new RangeQueryOutClient();


		String[] aRow = {"STN", "HATUT", "BULLF", "SLCBY", "SNI"};
		String[] aRow2 = {"TIME"," 20120201/1900", "0120201/1815", "20120201/1845", "20120201/1830"};
		/*for (int i=0; i<aRow.length; i++)
		{
			System.out.print(" Uploading" +  aRow[i]);	
		}*/
		clt.setRangeData(aRow);
	}
}
