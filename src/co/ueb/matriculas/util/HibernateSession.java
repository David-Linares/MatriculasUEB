package co.ueb.matriculas.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSession {
	
	private static SessionFactory sf;
	private static String filename = "hibernate.cfg.xml";
	
	private static SessionFactory buildSessionFactory(){
		sf = new Configuration().configure(filename).buildSessionFactory();
		return sf;
	}

	public static SessionFactory getSf() {
		sf = buildSessionFactory();
		return sf;
	}
	

}