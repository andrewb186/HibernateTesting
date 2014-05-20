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
import com.example.hibernate.model.UserInfo;

public class MainApplication {

	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	
	private static Logger log = Logger.getLogger(MainApplication.class);
	
	public static void main(String[] args) {
		try{
			Configuration config = new Configuration();
			config.addAnnotatedClass(Address.class);
			config.addAnnotatedClass(PersonInfo.class);
			config.addAnnotatedClass(UserInfo.class);
			
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
			
			sessionFactory = config.buildSessionFactory(serviceRegistry);
			
			Session session = sessionFactory.openSession();
			session.getTransaction().begin();
			
			PersonInfo p = createPersonInfo();
			
			session.save(p);
			session.save((Address)p.getAddress().toArray()[0]);
			session.save((Address)p.getAddress().toArray()[1]);
			
			session.getTransaction().commit();
			session.flush();
			session.close();
			
			
		}catch(Exception ex){
			log.error(ex.getMessage());
		}
	}


	private static PersonInfo createPersonInfo(){
		PersonInfo person = new PersonInfo();
		person.setName("Andrew");
		person.setSurname("Brincat");
		
		
		Address add1 = new Address();
		add1.setAddress1("address1a");
		add1.setAddress2("address2a");
		add1.setCity("Mosta");
		add1.setPostcode("mst011");
		
		
		Address add2 = new Address();
		add2.setAddress1("address1b");
		add2.setAddress2("address2b");
		add2.setCity("Mosta");
		add2.setPostcode("mst011");
		
		person.getAddress().add(add1);
		person.getAddress().add(add2);
		
		return person;
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
