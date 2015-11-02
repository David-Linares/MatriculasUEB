package co.ueb.matriculas.logical;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import co.ueb.matriculas.model.Persona;
import co.ueb.matriculas.util.HibernateSession;

public class EstudiantesLogical {

	Persona personaEstudiante = new Persona();
	List<Persona> listadoEstudiantes = new ArrayList<Persona>();

	@SuppressWarnings("unchecked")
	public List<Persona> getEstudiantes() {
		String sql = "select p from Persona as p";

		Session session = HibernateSession.getSf().getCurrentSession();
		session.beginTransaction();

		Query query = session.createQuery(sql);

		listadoEstudiantes = query.list();

		session.getTransaction().commit();

		return listadoEstudiantes;
	}

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

}
