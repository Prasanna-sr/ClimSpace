package com.entity.jpa;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Pointquery {
	
	
	public Object[] JPApointquery(String stn)
	{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
	EntityManager em = emf.createEntityManager();
	String strdata = null;
	DataOutput data1 = null;
	Data data = null;
	Object[] obj=null;
	
	try{
		
		em.getTransaction().begin();
		//Query query = em.createQuery("SELECT d FROM Data d where d.STN= :station");
       Query query = em.createQuery("select distinct d.STN,d.YYMMDD_HHMM,m.longitude,m.latitude,d.TMPF,m.state,d.GUST FROM Data d,Metadata m where d.STN=m.primaryid and d.STN=:station");

        query.setParameter("station", stn);

        List lst = query.getResultList();

		Iterator it1 = lst.iterator();
		
		while (it1.hasNext()){
			
			obj = (Object[]) it1.next();
			
			//data1 = (DataOutput) it1.next();
			//data = (Data) it1.next();
			
			System.out.print(obj[0]);
			System.out.print(obj[1]);
		
			//System.out.println("SLAT:"+data1.getTMPF());
//			//System.out.println(it.next());
		
//			data1 = (DataOutput) it.next();
//			System.out.print("TMPF:"+data1.getTMPF());			
		}

		em.getTransaction().commit();
	}
	catch(Exception e){
		System.out.println(e.getMessage());
	}
	finally{
		em.close();
		
	}
	return obj;
	}
	
	
	public Data JPAanalystquery(String[] query)
	{
		Data data = null;
		
		return data;
		
	}
	
	}
	

