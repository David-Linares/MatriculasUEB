
package co.ueb.matriculas.logical;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.jboss.logging.Logger;

import co.ueb.matriculas.model.Facultad;
import co.ueb.matriculas.util.HibernateSession;

public class FacultadLogical {
	
	private final Logger log = Logger.getLogger("Facultad Logical --");

	public boolean crearNuevaFacultad(Facultad nuevaFacultad){
		Session sesion  = HibernateSession.getSf().getCurrentSession();
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
	
	public boolean eliminarFacultad(Facultad facultad){
		Session sesion  = HibernateSession.getSf().getCurrentSession();
		try{
			sesion.beginTransaction();
			sesion.delete(facultad);
			sesion.getTransaction().commit();
			return true;
		}catch(Exception e){
			System.out.println("[Facultad Logical - Eliminar Facultad] Entró a Error");
			sesion.getTransaction().rollback();
			throw e;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Facultad> consultarFacultades(){
		List<Facultad> facultades = new ArrayList<Facultad>();
		String sql = "select f from Facultad as f order by f.idFacultad";
		Session session = HibernateSession.getSf().openSession();
		session.beginTransaction();
		Query query = session.createQuery(sql);
		facultades = query.list();
		session.getTransaction().commit();
		return facultades;
	}
	
	public boolean modificarFacultad(Facultad editaFacultad){
		System.out.println(editaFacultad);
		Session sesion  = HibernateSession.getSf().getCurrentSession();
		try{
			sesion.beginTransaction();
			sesion.update(editaFacultad);
			sesion.getTransaction().commit();
			return true;
		}catch(Exception e){
			sesion.getTransaction().rollback();
			return false;
		}
	}
	
	public Facultad getFacultadById(BigDecimal id_facultad){
		Facultad f = null;
		Session sesion  = HibernateSession.getSf().getCurrentSession();
		try {
			sesion.beginTransaction();
			String hql = "FROM Facultad WHERE id_facultad= " + id_facultad;
			log.info(hql);
			Query query = sesion.createQuery(hql);
			f = (Facultad) query.uniqueResult();
			if (f == null) {
				return null;
			}
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			e.printStackTrace();
		}
		return f;
	}
	
}

