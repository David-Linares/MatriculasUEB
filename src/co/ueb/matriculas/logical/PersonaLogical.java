package co.ueb.matriculas.logical;

import java.util.List;

import co.ueb.matriculas.model.Persona;

public interface PersonaLogical {
	
	public Persona consultarPersona(Persona persona);
	public Persona login(Persona persona);
	public List<Persona> finAll();
	public boolean crearPersona(Persona persona);
	public boolean editarPersona(Persona persona);
	public boolean eliminarPersona(Integer idPersona);
	

}
