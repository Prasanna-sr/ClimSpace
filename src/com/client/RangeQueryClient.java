package com.client;

import com.ejb.mdb.query.RangeQueryIn;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

public class RangeQueryClient implements RangeQueryIn  {

	private int count;

	private static final long serialVersionUID = 1L;

	protected String endpoint = "localhost:1099";
	protected Context ctx;

	public RangeQueryClient() {
	}

	public RangeQueryClient(String endpoint) {
		this.endpoint = endpoint;
	}
	
	public void getRangeData( String[] aRow) { 
		try {
			RangeQueryIn svr = connectV6();
			svr.getRangeData(aRow);
	} catch (Exception e) {
		e.printStackTrace();
	}
}

	public RangeQueryIn connectV6() throws Exception {
		// These properties specify the implementation to use, as well as the
		// location of the server
		Properties jndi = new Properties();
		jndi.setProperty(Context.URL_PKG_PREFIXES,
				"org.jboss.ejb.client.naming");
		jndi.setProperty(Context.PROVIDER_URL, "jnp://localhost:1099/");
		jndi.setProperty(Context.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");

		RangeQueryIn svr = null;
		try {
			InitialContext context = new InitialContext(jndi);

			Object ref = context.lookup("RangeQueryInEJB/remote");
			svr = (RangeQueryIn) PortableRemoteObject.narrow(ref, RangeQueryIn.class);
		} catch (NamingException e) {
			e.printStackTrace();
		}

		return svr;
	}
	

	public static void main(String[] args) {
		RangeQueryClient clt = new RangeQueryClient();


		String[] aRow = {"2", "BULLF", "HAUW3", "HAM"};
		/*for (int i=0; i<aRow.length; i++)
		{
			System.out.print(" Uploading" +  aRow[i]);	
		}*/
		clt.getRangeData(aRow);
	}
}
