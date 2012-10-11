package com.client;

import com.ejb.mdb.upload.Upload;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

public class UploadClient implements Upload {

	private static final long serialVersionUID = 1L;

	protected String endpoint = "localhost:1099";
	protected Context ctx;

	public UploadClient() {
	}

	public UploadClient(String endpoint) {
		this.endpoint = endpoint;
	}

	public void echo(String message, String auth) {
		try {
			Upload svr = connectV6();
			svr.echo(message, auth);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(String[] aRow) { 
		try {
			Upload svr = connectV6();
			svr.update(aRow);
	} catch (Exception e) {
		e.printStackTrace();
	}
}

	/**
	 * Using older (JBoss 6)
	 * 
	 * @return
	 * @throws Exception
	 */
	public Upload connectV6() throws Exception {
		// These properties specify the implementation to use, as well as the
		// location of the server
		Properties jndi = new Properties();
		jndi.setProperty(Context.URL_PKG_PREFIXES,
				"org.jboss.ejb.client.naming");
		jndi.setProperty(Context.PROVIDER_URL, "jnp://localhost:1099/");
		jndi.setProperty(Context.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");

		Upload svr = null;
		try {
			InitialContext context = new InitialContext(jndi);

			Object ref = context.lookup("UploadEJB/remote");
			svr = (Upload) PortableRemoteObject.narrow(ref, Upload.class);
		} catch (NamingException e) {
			e.printStackTrace();
		}

		return svr;
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UploadClient clt = new UploadClient();

		//clt.echo("Hello I'm a test from babu", "mykey");
		//String[] aRow = {"STN", "1234", "San Jose", "Feb 20, 2012"};
		//String[] data={"PRK_point_test","20120201/2200","37.84","-109.46","19.33","15.74"};
		String[] aRow = RssUtil.getStationData("FWP");
		/*for (int i=0; i<aRow.length; i++)
		{
			System.out.print(" Uploading" +  aRow[i]);	
		}*/
		//String[] aRow = {"bt1","20120201/1900","8.00","37.84","-109.46","3453.00","19.33","10.01","207.00","15.74","-9999.00","-9999.00","3.65","49.74","-9999.00","-9999.00"};
		clt.update(aRow);
	}
}
