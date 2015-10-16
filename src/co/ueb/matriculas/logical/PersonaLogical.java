package co.ueb.matriculas.logical;

import org.hibernate.Query;
import org.hibernate.Session;

import co.ueb.matriculas.model.Persona;
import co.ueb.matriculas.util.HibernateSession;

public class PersonaLogical {

	private Session sesion;

	public Persona verificarDatos(Persona persona) throws Exception {
		Persona p = null;
		try {
			System.out.println("entrò persona logical");
			sesion = HibernateSession.getSf().getCurrentSession();
			sesion.beginTransaction();
			String hql = "FROM Persona WHERE usuario= '" + persona.getUsuario()
					+ "' and contrasena = '" + persona.getContrasena()
					+ "' and estadoPersona= '1'";
			Query query = sesion.createQuery(hql);
			System.out.println(query.list().isEmpty());
			if (!query.list().isEmpty()) {
				p = (Persona) query.list().get(0);
				System.out.println("entrò if");
			}
			System.out.println(p.toString());
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			throw e;
		}
		return p;
	}
}
