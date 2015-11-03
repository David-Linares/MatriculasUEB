package co.ueb.matriculas.logical;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.ueb.matriculas.model.Carrera;
import co.ueb.matriculas.model.Facultad;
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
	System.out.println("entro a consultar materias - materia logical");
		List<Materia> materias = new ArrayList<Materia>();
		String sql = "select m from Materia as m order by m.idMateria";
		Session session = HibernateSession.getSf().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery(sql);
		materias = query.list();
		session.getTransaction().commit();
		System.out.println("salio de consultar materias");
System.out.println(materias);
		return materias;
	}
	
	public boolean modificarMateria(Materia editaMateria){
		Session sesion  = HibernateSession.getSf().getCurrentSession();
		System.out.println("modificar materia entro ");
		try{
			sesion.beginTransaction();
			sesion.update(editaMateria);
			sesion.getTransaction().commit();
			return true;
		}catch(Exception e){
			sesion.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
	}

	public Materia getMateriaByName(String nombre_materia){
		Materia m = null;
		Session session = HibernateSession.getSf().openSession();
		try {
			session.beginTransaction();
			String hql = "FROM Materia WHERE nombre_materia= '" + nombre_materia + "'";
			Query query = session.createQuery(hql);
			m = (Materia) query.uniqueResult();
			if (m == null) {
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return m;
	}
}
