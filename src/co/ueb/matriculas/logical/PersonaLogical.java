package co.ueb.matriculas.logical;

import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;

import co.ueb.matriculas.model.Persona;
import co.ueb.matriculas.util.HibernateSession;

public class PersonaLogical {

	private Session sesion;
	final Logger log = Logger.getLogger("PersonaLogical");

	public Persona verificarDatos(Persona persona) {
		log.info("verificarDatos "+persona);
		Persona p = null;
		try {
			sesion = HibernateSession.getSf().getCurrentSession();
			sesion.beginTransaction();
			log.info(persona.getContrasena());
			String hql = "FROM Persona WHERE usuario= '" + persona.getUsuario()
					+ "' and contrasena = '" + persona.getContrasena()
					+ "' and estadoPersona= '1'";
			log.info(hql);
			Query query = sesion.createQuery(hql);
			if (!query.list().isEmpty()) {
				p = (Persona) query.list().get(0);
				System.out.println("entr� if");
			}else{
				return null;
			}
			System.out.println(p.toString());
			sesion.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			sesion.getTransaction().rollback();
		}
		return p;
	}
}
