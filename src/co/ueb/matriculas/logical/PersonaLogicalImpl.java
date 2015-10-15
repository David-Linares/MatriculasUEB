package co.ueb.matriculas.logical;

import java.util.List;

import org.hibernate.Session;

import co.ueb.matriculas.model.Persona;
import co.ueb.matriculas.util.HibernateSession;

public class PersonaLogicalImpl implements PersonaLogical {

	public Persona consultarPersona
	(Persona persona){
		Persona model=null;
		Session sesion  = HibernateSession.getSf().getCurrentSession();
		String sql="FROM Persona WHERE estadoPersona=1 and persona='"+persona.getPersona()+"'";
		try{
			sesion.beginTransaction();
			model=(Persona) sesion.createQuery(sql).uniqueResult();
			sesion.beginTransaction().commit();
		}catch(Exception e){
			sesion.beginTransaction().rollback();
		}
		return model;
	}

	@Override
	public Persona login(Persona persona) {
		Persona model= this.consultarPersona(persona);
		if(!persona.getContrasena().equals(model.getContrasena()));{
			model=null;
		}
		return model;
		
	}

	@Override
	public List<Persona> finAll() {
		List<Persona> listado=null;
		Session sesion  = HibernateSession.getSf().getCurrentSession();
		String sql ="FROM Persona";
		try {
			sesion.beginTransaction();
			listado=sesion.createQuery(sql).list();
			sesion.beginTransaction().commit();
		} catch (Exception e) {
			sesion.beginTransaction().rollback();
		}
		return listado;
	}

	@Override
	public boolean crearPersona(Persona persona) {
		boolean flag;
		Session sesion  = HibernateSession.getSf().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.save(persona);
			sesion.beginTransaction().commit();
			flag= true;
		} catch (Exception e) {
			flag= false;
			sesion.beginTransaction().rollback();
		}
		return flag;
	}

	@Override
	public boolean editarPersona(Persona persona) {
	boolean flag;
	Session sesion = HibernateSession.getSf().getCurrentSession();
	try {
		sesion.beginTransaction();
		Persona personadb = (Persona) sesion.load(Persona.class, persona.getIdPersona());
		personadb.setIdPersona(persona.getIdPersona());
		personadb.setNombrePersona(persona.getNombrePersona());
		//Faltan todos los campos
		sesion.update(personadb);
		sesion.beginTransaction().commit();
		flag=true;
	} catch (Exception e) {
		flag=false;
		sesion.beginTransaction().rollback();
	}
		return flag;
	}

	@Override
	public boolean eliminarPersona(Integer idPersona) {
		boolean flag;
		Session sesion = HibernateSession.getSf().getCurrentSession();
		try {
			sesion.beginTransaction();
			Persona persona=(Persona) sesion.load(Persona.class, idPersona);
			sesion.delete(persona);
			sesion.beginTransaction().commit();
			flag= true;
		} catch (Exception e) {
			flag= false;
			sesion.beginTransaction().rollback();
		}
		
		return flag;
	}

	
}
