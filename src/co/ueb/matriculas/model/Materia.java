package co.ueb.matriculas.model;

// Generated 02-nov-2015 12:47:33 by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Materia generated by hbm2java
 */
public class Materia implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3582811175728351510L;
	private BigDecimal idMateria;
	private Carrera carrera;
	private String nombreMateria;
	private BigDecimal creditos;
	private Character estadoMateria;
	private Set<?> materiaMatriculas = new HashSet<Object>(0);

	public Materia() {
	}

	public Materia(BigDecimal idMateria) {
		this.idMateria = idMateria;
	}

	public Materia(BigDecimal idMateria, Carrera carrera, String nombreMateria,
			BigDecimal creditos, Character estadoMateria, Set<?> materiaMatriculas) {
		this.idMateria = idMateria;
		this.carrera = carrera;
		this.nombreMateria = nombreMateria;
		this.creditos = creditos;
		this.estadoMateria = estadoMateria;
		this.materiaMatriculas = materiaMatriculas;
	}

	public BigDecimal getIdMateria() {
		return this.idMateria;
	}

	public void setIdMateria(BigDecimal idMateria) {
		this.idMateria = idMateria;
	}

	public Carrera getCarrera() {
		return this.carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	public String getNombreMateria() {
		return this.nombreMateria;
	}

	public void setNombreMateria(String nombreMateria) {
		this.nombreMateria = nombreMateria;
	}

	public BigDecimal getCreditos() {
		return this.creditos;
	}

	public void setCreditos(BigDecimal creditos) {
		this.creditos = creditos;
	}

	public Character getEstadoMateria() {
		return this.estadoMateria;
	}

	public void setEstadoMateria(Character estadoMateria) {
		this.estadoMateria = estadoMateria;
	}

	public Set<?> getMateriaMatriculas() {
		return this.materiaMatriculas;
	}

	public void setMateriaMatriculas(Set<?> materiaMatriculas) {
		this.materiaMatriculas = materiaMatriculas;
	}

	@Override
	public String toString() {
		return "Materia [idMateria=" + idMateria + ", carrera=" + carrera
				+ ", nombreMateria=" + nombreMateria + ", creditos=" + creditos
				+ ", estadoMateria=" + estadoMateria + ", materiaMatriculas="
				+ materiaMatriculas + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carrera == null) ? 0 : carrera.hashCode());
		result = prime * result
				+ ((creditos == null) ? 0 : creditos.hashCode());
		result = prime * result
				+ ((estadoMateria == null) ? 0 : estadoMateria.hashCode());
		result = prime * result
				+ ((idMateria == null) ? 0 : idMateria.hashCode());
		result = prime
				* result
				+ ((materiaMatriculas == null) ? 0 : materiaMatriculas
						.hashCode());
		result = prime * result
				+ ((nombreMateria == null) ? 0 : nombreMateria.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Materia other = (Materia) obj;
		if (carrera == null) {
			if (other.carrera != null)
				return false;
		} else if (!carrera.equals(other.carrera))
			return false;
		if (creditos == null) {
			if (other.creditos != null)
				return false;
		} else if (!creditos.equals(other.creditos))
			return false;
		if (estadoMateria == null) {
			if (other.estadoMateria != null)
				return false;
		} else if (!estadoMateria.equals(other.estadoMateria))
			return false;
		if (idMateria == null) {
			if (other.idMateria != null)
				return false;
		} else if (!idMateria.equals(other.idMateria))
			return false;
		if (materiaMatriculas == null) {
			if (other.materiaMatriculas != null)
				return false;
		} else if (!materiaMatriculas.equals(other.materiaMatriculas))
			return false;
		if (nombreMateria == null) {
			if (other.nombreMateria != null)
				return false;
		} else if (!nombreMateria.equals(other.nombreMateria))
			return false;
		return true;
	}
	
	
}
