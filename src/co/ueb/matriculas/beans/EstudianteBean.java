/**
 * 
 */
package co.ueb.matriculas.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import co.ueb.matriculas.logical.CarreraLogical;
import co.ueb.matriculas.logical.EstudiantesLogical;
import co.ueb.matriculas.model.Carrera;
import co.ueb.matriculas.model.Matricula;
import co.ueb.matriculas.model.Perfil;
import co.ueb.matriculas.model.Persona;

public class EstudianteBean {

	private String nombreEstudiante = "Nuevo mensaje";
	private List<Persona> listadoEstudiantes;
	private EstudiantesLogical el = new EstudiantesLogical();
	private CarreraLogical cl = new CarreraLogical();
	private Carrera carreraEstudiante = new Carrera();
	private List<Carrera> listadoCarreras = cl.consultarCarreras();

	public List<Carrera> getListadoCarreras() {
		return listadoCarreras;
	}

	public void setListadoCarreras(List<Carrera> listadoCarreras) {
		this.listadoCarreras = listadoCarreras;
	}

	public Carrera getCarreraEstudiante() {
		return carreraEstudiante;
	}

	public void setCarreraEstudiante(Carrera carreraEstudiante) {
		this.carreraEstudiante = carreraEstudiante;
	}

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
	
	@SuppressWarnings("unchecked")
	public String crearEstudiante(){
		
		System.out.println("Carrera Seleccionada en estudiante " + this.getCarreraEstudiante());
		
//		Persona nuevoEstudiante = new Persona(new BigDecimal(12), new Perfil(new BigDecimal(1)),"Goku","Saiyan",new Date(), "Bogotá", "Calle 123", "est@unbosque.edu.co",
//				'1', new BigDecimal(3.2), "goku", "827ccb0eea8a706c4c34a16891f84e7b");
//		
//		nuevoEstudiante.getCarreraEstudiantes().add(this.getCarreraEstudiante());
		return null;
	}
	
}
