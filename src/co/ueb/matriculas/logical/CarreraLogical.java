package co.ueb.matriculas.logical;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.jboss.logging.Logger;

import co.ueb.matriculas.model.Carrera;
import co.ueb.matriculas.util.HibernateSession;

public class CarreraLogical implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1760467828935283002L;
	private Logger log = Logger.getLogger(CarreraLogical.class);
	private Session sesion;
	private String sql;
	private Query query;
	List<Carrera> carreras;
	private Carrera carreraQuery;

	public boolean crearNuevaCarrera(Carrera nuevaCarrera){
		sesion  = HibernateSession.getSf().getCurrentSession();
		try{
			sesion.beginTransaction();
			sesion.persist(nuevaCarrera);
			sesion.getTransaction().commit();
			return true;
		}catch(Exception e){
			sesion.getTransaction().rollback();
			return false;
		}
	}
	
	public boolean eliminarCarrera(Carrera carrera){
		sesion  = HibernateSession.getSf().getCurrentSession();
		try{
			sesion.beginTransaction();
			sesion.delete(carrera);
			sesion.getTransaction().commit();
			return true;
		}catch(Exception e){
			sesion.getTransaction().rollback();
			log.error(e);
			e.printStackTrace();
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Carrera> consultarCarreras(){
		sql = "select c from Carrera as c order by c.idCarrera";
		try{
			sesion = HibernateSession.getSf().getCurrentSession();
			sesion.beginTransaction();
			query = sesion.createQuery(sql);
			carreras = query.list();
			sesion.getTransaction().commit();
		}catch(Exception e){
			sesion.getTransaction().rollback();
			log.error(e);
			e.printStackTrace();
		}
		return carreras;
	}
	
	public boolean modificarCarrera(Carrera editaCarrera){
		sesion = HibernateSession.getSf().getCurrentSession();
		try{
			sesion.beginTransaction();
			sesion.update(editaCarrera);
			sesion.getTransaction().commit();
			return true;
		}catch(Exception e){
			sesion.getTransaction().rollback();
			return false;
		}
	}
	
	public Carrera getCarreraByName(String nombre_carrera){		
		sesion  = HibernateSession.getSf().getCurrentSession();
		try {
			sesion.beginTransaction();
			String hql = "FROM Carrera WHERE nombreCarrera = '" + nombre_carrera + "'";
			query = sesion.createQuery(hql);
			carreraQuery = (Carrera) query.uniqueResult();
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			e.printStackTrace();
		}
		log.info("## Devuelve carrera ##");
		log.info(carreraQuery);
		return carreraQuery;
	}
	
}
