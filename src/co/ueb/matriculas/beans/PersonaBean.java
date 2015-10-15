package co.ueb.matriculas.beans;

import java.util.ArrayList;
import java.util.List;

import co.ueb.matriculas.logical.PersonaLogical;
import co.ueb.matriculas.logical.PersonaLogicalImpl;
import co.ueb.matriculas.model.Persona;

public class PersonaBean {
	
	public List<Persona> personas;
	public Persona selectedPersona;
	
	public PersonaBean(){
		this.personas= new ArrayList<Persona>();
		this.selectedPersona= new Persona();
	}
	
	public List<Persona> getUPersonas(){
		PersonaLogical personaLogical = new PersonaLogicalImpl();
		this.personas=personaLogical.finAll();
		return personas;
	}
	
	
}
