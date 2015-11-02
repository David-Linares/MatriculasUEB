package co.ueb.matriculas.logical;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;

import co.ueb.matriculas.model.Persona;
import co.ueb.matriculas.util.HibernateSession;

public class PersonaLogical {

	private Session sesion;
	final Logger log = Logger.getLogger("PersonaLogical");

	@SuppressWarnings("rawtypes")
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
			List resultList = query.list();
			if (!resultList.isEmpty()) {
				p = (Persona) resultList.get(0);
				log.info(p.toString());
			}else{
				return null;
			}
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			e.printStackTrace();
		}
		return p;
	}
	
	@SuppressWarnings("unchecked")
	public Persona getPersonaById(BigDecimal id_persona){
		Persona p = null;
		try {
			sesion = HibernateSession.getSf().getCurrentSession();
			sesion.beginTransaction();
			String hql = "FROM Persona WHERE id_persona= '" + id_persona
					+ "' and estadoPersona= '1'";
			log.info(hql);
			Query query = sesion.createQuery(hql);
			p = (Persona) query.uniqueResult();
			if (p == null) {
				return null;
			}
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			e.printStackTrace();
		}
		return p;
	}
}
