package co.ueb.matriculas.logical;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.ueb.matriculas.model.Carrera;
import co.ueb.matriculas.util.HibernateSession;

public class CarreraLogical {

	public boolean crearNuevaCarrera(Carrera nuevaCarrera){
		Session sesion  = HibernateSession.getSf().getCurrentSession();
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
		Session sesion  = HibernateSession.getSf().getCurrentSession();
		try{
			sesion.beginTransaction();
			sesion.delete(carrera);
			sesion.getTransaction().commit();
			return true;
		}catch(Exception e){
			System.out.println("[Carrera Logical - Eliminar Carrera] Entró a Error");
			sesion.getTransaction().rollback();
			throw e;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Carrera> consultarCarreras(){
		List<Carrera> carreras = new ArrayList<Carrera>();
		String sql = "select c from Carrera as c order by c.idCarrera";
		Session session = HibernateSession.getSf().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery(sql);
		carreras = query.list();
		session.getTransaction().commit();
		return carreras;
	}
	
	public boolean modificarCarrera(Carrera editaCarrera){
		Session sesion  = HibernateSession.getSf().getCurrentSession();
		System.out.println(editaCarrera);
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
	
}
