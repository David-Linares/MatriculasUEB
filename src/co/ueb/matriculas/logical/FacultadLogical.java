
package co.ueb.matriculas.logical;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.ueb.matriculas.model.Facultad;
import co.ueb.matriculas.util.HibernateSession;

public class FacultadLogical {

	public boolean crearNuevaFacultad(Facultad nuevaFacultad){
		Session sesion  = HibernateSession.getSf().getCurrentSession();
		System.out.println(sesion);
		try{
			sesion.beginTransaction();
			sesion.persist(nuevaFacultad);
			sesion.getTransaction().commit();
			return true;
		}catch(Exception e){
			sesion.getTransaction().rollback();
			return false;
		}
	}
	
	public List<Facultad> consultarFacultades(){
		List<Facultad> facultades = new ArrayList<Facultad>();
		String sql = "select f from Facultad as f order by f.idFacultad";
		Session session = HibernateSession.getSf().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery(sql);
		facultades = query.list();
		session.getTransaction().commit();
		return facultades; 
	}
	
}

