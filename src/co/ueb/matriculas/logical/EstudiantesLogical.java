package co.ueb.matriculas.logical;

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
	public List<Persona> getEstudiantes(){
		String sql = "select p from Persona as p";

		Session session = HibernateSession.getSf()
				.getCurrentSession();
		session.beginTransaction();

		Query query = session.createQuery(sql);

		listadoEstudiantes = query.list();

		session.getTransaction().commit();
		
		return listadoEstudiantes;
	}
	
	
}
