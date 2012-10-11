package com.entity.jpa;

import java.util.StringTokenizer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.filestorage.StoreFile;

public class Uploadpointdata {
	
	public void upload_data(String dataNew[])
	{
		String[] data= new String[15];
		data[0]=dataNew[0];
		data[1]=dataNew[1];
		data[3]=dataNew[2];
		data[4]=dataNew[3];
		data[6]=dataNew[4];
		data[9]=dataNew[5];
		
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		
		try{
			em.getTransaction().begin();
			Data d = new Data();
			
		for(int j=0;j<data.length;j++)
		{

		            if(j==0)
		            {
		            	d.setSTN(data[j]);
		            }
		            if(j==1)
		            {
		            	d.setYYMMDD_HHMM(data[j]);
		            }
		            if(j==2)
		            {
		            	//Float f = new Float(data1);
		            	d.setMNET(data[j]);
		            }
		            if(j==3)
		            {
		            	//Float f = new Float(data1);
		            	d.setSLAT(data[j]);
		            }
		            if(j==4)
		            {
		            	//Float f = new Float(data1);
		            	d.setSLON(data[j]);
		            }
		            if(j==5)
		            {
		            	//Float f = new Float(data1);
		            	d.setSELV(data[j]);
		            }
		            if(j==6)
		            {
		            	//Float f = new Float(data1);
		            	d.setTMPF(data[j]);
		            }
		            if(j==7)
		            {
		            	//Float f = new Float(data1);
	            		d.setSKNT(data[j]);
		            }
		            if(j==8)
		            {
		            	//Float f = new Float(data1);
		            	d.setDRCT(data[j]);
		            }
		            if(j==9)
		            {
		            	//Float f = new Float(data1);
		            	d.setGUST(data[j]);
		            }
		            if(j==10)
		            {
		            	//Float f = new Float(ata1);
		            	d.setPMSL(data[j]);
		            }
		            if(j==11)
		            {
		            	//Float f = new Float(data1);
		            	d.setALTI(data[j]);
		            }
		            if(j==12)
		            {
		            	//Float f = new Float(data1);
		            	d.setDWPF(data[j]);
		            }
		            
		            if(j==13)
		            {
		            	//Float f = new Float(data1);
		            	d.setRELH(data[j]);
		            }
		            if(j==14)
		            {
		            	//Float f = new Float(data1);
		            	d.setWTHR(data[j]);
		            }
		            if(j==15)
		            {
		            	//Float f = new Float(data1);
		            	d.setP24I(data[j]);
		            }
		    //}     
		}      
		em.persist(d);
		em.getTransaction().commit();			
		
		System.out.println("Transaction completed. Data stored in database !");
		String[][] sData = new String[1][16];
		sData[0]= data;
		StoreFile sf = new StoreFile();
		//sf.writefileBystation(sData);
		
	}catch(Exception e){
		System.out.println(e.getMessage());
	}finally{
		em.close();		
		}

	}

	
	
}
