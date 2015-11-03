package co.ueb.matriculas.logical;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.ueb.matriculas.model.Materia;
import co.ueb.matriculas.util.HibernateSession;

public class MateriaLogical {

	public boolean crearNuevaMateria(Materia nuevaMateria){
		Session sesion  = HibernateSession.getSf().getCurrentSession();
		try{
			sesion.beginTransaction();
			sesion.persist(nuevaMateria);
			sesion.getTransaction().commit();
			return true;
		}catch(Exception e){
			sesion.getTransaction().rollback();
			return false;
		}
	}
	
	public boolean eliminarMateria(Materia materia){
		Session sesion  = HibernateSession.getSf().getCurrentSession();
		try{
			sesion.beginTransaction();
			sesion.delete(materia);
			sesion.getTransaction().commit();
			return true;
		}catch(Exception e){
			System.out.println("[Materia Logical - Eliminar Materia] Entra� a Error");
			sesion.getTransaction().rollback();
			throw e;
		}
	}
	

	@SuppressWarnings("unchecked")
	public List<Materia> consultarMaterias(){
		List<Materia> materias = new ArrayList<Materia>();
		String sql = "select m from Materia as m order by m.idMateria";
		Session session = HibernateSession.getSf().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery(sql);
		materias = query.list();
		session.getTransaction().commit();
		return materias;
	}
	
	public boolean modificarMateria(Materia editaMateria){
		Session sesion  = HibernateSession.getSf().getCurrentSession();
		System.out.println(editaMateria);
		try{
			sesion.beginTransaction();
			sesion.update(editaMateria);
			sesion.getTransaction().commit();
			return true;
		}catch(Exception e){
			sesion.getTransaction().rollback();
			return false;
		}
	}

}
