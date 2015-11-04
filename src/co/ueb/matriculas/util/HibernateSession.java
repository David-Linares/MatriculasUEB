package co.ueb.matriculas.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSession {
	
	private static SessionFactory sf;
	private static String filename = Constants.hibernate_file_name;
	
	private static SessionFactory buildSessionFactory(){
		sf = new Configuration().configure(filename).buildSessionFactory();
		return sf;
	}

	public static SessionFactory getSf() {
		if(sf==null){
			sf = buildSessionFactory();
		}
		return sf;
	}
}