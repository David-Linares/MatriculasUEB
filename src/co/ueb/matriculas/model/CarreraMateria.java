package co.ueb.matriculas.model;

// Generated Oct 15, 2015 11:01:28 AM by Hibernate Tools 4.3.1

/**
 * CarreraMateria generated by hbm2java
 */
public class CarreraMateria implements java.io.Serializable {

	private CarreraMateriaId id;
	private Materia materia;
	private Carrera carrera;

	public CarreraMateria() {
	}

	public CarreraMateria(CarreraMateriaId id, Materia materia, Carrera carrera) {
		this.id = id;
		this.materia = materia;
		this.carrera = carrera;
	}

	public CarreraMateriaId getId() {
		return this.id;
	}

	public void setId(CarreraMateriaId id) {
		this.id = id;
	}

	public Materia getMateria() {
		return this.materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public Carrera getCarrera() {
		return this.carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

}
