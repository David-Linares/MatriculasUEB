/**
 * 
 */
package co.ueb.matriculas.beans;

import java.util.List;

import co.ueb.matriculas.logical.EstudiantesLogical;
import co.ueb.matriculas.model.Persona;

public class EstudianteBean {

	private String nombreEstudiante = "Nuevo mensaje";
	private List<Persona> listadoEstudiantes;
	private EstudiantesLogical el = new EstudiantesLogical();

	public String getNombreEstudiante() {
		return nombreEstudiante;
	}

	public void setNombreEstudiante(String nombreEstudiante) {
		this.nombreEstudiante = "MEnsaje nuevo, Sin funciona";
	}

	public List<Persona> getListadoEstudiantes() {
		return listadoEstudiantes;
	}

	public void setListadoEstudiantes(List<Persona> listadoEstudiantes) {
		this.listadoEstudiantes = el.getEstudiantes();
	}	
	
	
}
