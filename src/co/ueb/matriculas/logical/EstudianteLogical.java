package co.ueb.matriculas.logical;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.ueb.matriculas.model.Materia;
import co.ueb.matriculas.model.Persona;
import co.ueb.matriculas.util.HibernateSession;

public class EstudianteLogical {

	Persona personaEstudiante = new Persona();
	List<Persona> listadoEstudiantes = new ArrayList<Persona>();

	/*
	 * @SuppressWarnings("unchecked") public List<Persona> getEstudiantes() {
	 * String sql = "select p from Persona as p";
	 * 
	 * Session session = HibernateSession.getSf().getCurrentSession();
	 * session.beginTransaction();
	 * 
	 * Query query = session.createQuery(sql);
	 * 
	 * listadoEstudiantes = query.list();
	 * 
	 * session.getTransaction().commit();
	 * 
	 * return listadoEstudiantes; }
	 */

	public String obtenerPromedio(BigDecimal identificacion) {

		PersonaLogical personaLogica = new PersonaLogical();

		personaEstudiante = personaLogica.getPersonaById(identificacion);
		String promedioRespuesta = null;

		if (personaEstudiante == null)
			promedioRespuesta = "No existe un estudiante con el número de identificación dado";
		else
			promedioRespuesta = personaEstudiante.getPromedio().toString();

		return promedioRespuesta;
	}

	public boolean crearNuevoEstudiante(Persona nuevoEstudiante) {
		Session sesion = HibernateSession.getSf().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.persist(nuevoEstudiante);
			sesion.getTransaction().commit();
			return true;
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			return false;
		}
	}

	public boolean eliminarEstudiante(Persona estudiante) {
		Session sesion = HibernateSession.getSf().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.delete(estudiante);
			sesion.getTransaction().commit();
			return true;
		} catch (Exception e) {
			System.out
					.println("[Estudiante Logical - Eliminar Estudiante] Entra� a Error");
			sesion.getTransaction().rollback();
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Persona> consultarEstudiantes() {
		List<Persona> estudiantes = new ArrayList<Persona>();
		String sql = "select e from Persona as e where e.perfil.idPerfil = 1 order by e.nombrePersona, e.apellidosPersona ";
		Session session = HibernateSession.getSf().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery(sql);
		estudiantes = query.list();
		session.getTransaction().commit();
		return estudiantes;
	}

	public boolean modificarEstudiante(Persona editaEstudiante) {
		Session sesion = HibernateSession.getSf().getCurrentSession();
		System.out.println("modificar estudiante entro ");
		try {
			sesion.beginTransaction();
			sesion.update(editaEstudiante);
			sesion.getTransaction().commit();
			return true;
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
	}

}
