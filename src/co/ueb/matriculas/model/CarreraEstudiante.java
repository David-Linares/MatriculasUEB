package co.ueb.matriculas.model;

// Generated Oct 15, 2015 11:01:28 AM by Hibernate Tools 4.3.1

/**
 * CarreraEstudiante generated by hbm2java
 */
public class CarreraEstudiante implements java.io.Serializable {

	private CarreraEstudianteId id;
	private Persona persona;
	private Carrera carrera;

	public CarreraEstudiante() {
	}

	public CarreraEstudiante(CarreraEstudianteId id, Persona persona,
			Carrera carrera) {
		this.id = id;
		this.persona = persona;
		this.carrera = carrera;
	}

	public CarreraEstudianteId getId() {
		return this.id;
	}

	public void setId(CarreraEstudianteId id) {
		this.id = id;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Carrera getCarrera() {
		return this.carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

}
