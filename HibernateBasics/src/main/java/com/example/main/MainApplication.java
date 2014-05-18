package com.example.main;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.proxy.HibernateProxyHelper;
import org.hibernate.service.ServiceRegistry;

import com.example.hibernate.model.Address;
import com.example.hibernate.model.PersonInfo;

public class MainApplication {

	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	
	private static Logger log = Logger.getLogger(MainApplication.class);
	
	public static void main(String[] args) {
		try{
			Configuration config = new Configuration(); 
			config.addAnnotatedClass(PersonInfo.class);
			config.addAnnotatedClass(Address.class);
			
			
			
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
			
			sessionFactory = config.buildSessionFactory(serviceRegistry);
			
			Session session = sessionFactory.openSession();
			session.getTransaction().begin();
			log.info("Session Opened");
			session.close();
			log.info("Session Closed");
			
			
		}catch(Exception ex){
			log.error(ex.getMessage());
		}
	}

	
	
/**	
 * Get .class file form package
 * create Class object 
 * and add to config.addAnnotatedClass()
 *
	private static Class getPackageClasses(){
			Class cls = null;
			try {
				cls = Class.forName("com.example.hibernate.model.Address");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return cls;
	}
	
	Configuration config = new Configuration(); 
	config.addAnnotatedClass(getPackageClasses());
	
	*
	*
	*/
	
}
