package co.ueb.matriculas.logical;

import org.hibernate.Session;

import co.ueb.matriculas.model.Facultad;
import co.ueb.matriculas.util.HibernateSession;

public class FacultadLogical {

	public void crearNuevaFacultad(Facultad nuevaFacultad){
		Session sesion  = HibernateSession.getSf().getCurrentSession();
		sesion.beginTransaction();
		sesion.persist(nuevaFacultad);
		sesion.getTransaction().commit();
	}
	
}
