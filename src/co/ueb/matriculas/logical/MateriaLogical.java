package co.ueb.matriculas.logical;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.jboss.logging.Logger;

import co.ueb.matriculas.model.Materia;
import co.ueb.matriculas.util.HibernateSession;

public class MateriaLogical {

	private Logger log = Logger.getLogger(MateriaLogical.class);
	private Session sesion;
	private List<Materia> materias;
	private String sql;
	private Materia materiaQuery;
	private Query query;

	public boolean crearNuevaMateria(Materia nuevaMateria) {
		sesion = HibernateSession.getSf().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.persist(nuevaMateria);
			sesion.getTransaction().commit();
			return true;
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Materia> consultarMaterias() {
		log.debug("Entró a consultar Materias");
		sql = "select m from Materia as m order by m.idMateria";
		try{
			sesion = HibernateSession.getSf().getCurrentSession();
			sesion.beginTransaction();
			query = sesion.createQuery(sql);
			materias = query.list();
			sesion.getTransaction().commit();
			log.debug("Consultó Materias");
			log.debug(materias);
		}catch(Exception e){
			sesion.getTransaction().rollback();
			log.error(e);
			e.printStackTrace();
		}
		return materias;
	}

	public boolean eliminarMateria(Materia materia) {
		sesion = HibernateSession.getSf().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.delete(materia);
			sesion.getTransaction().commit();
			return true;
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			log.error(e);
			e.printStackTrace();
			return false;
		}
	}

	public boolean modificarMateria(Materia editaMateria) {
		Session sesion = HibernateSession.getSf().getCurrentSession();
		log.info("Entró A Modificar la Materia");
		try {
			sesion.beginTransaction();
			sesion.update(editaMateria);
			sesion.getTransaction().commit();
			return true;
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
	}

	public Materia getMateriaByName(String nombre_materia) {
		sesion = HibernateSession.getSf().getCurrentSession();
		try {
			sesion.beginTransaction();
			String hql = "FROM Materia WHERE nombre_materia= '"
					+ nombre_materia + "'";
			query = sesion.createQuery(hql);
			materiaQuery = (Materia) query.uniqueResult();
			if (materiaQuery == null) {
				return null;
			}
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			e.printStackTrace();
		}
		return materiaQuery;
	}
}
