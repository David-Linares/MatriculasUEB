package co.ueb.matriculas.model;

// Generated 02-nov-2015 12:47:33 by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Carrera generated by hbm2java
 */
public class Carrera implements java.io.Serializable {

	private BigDecimal idCarrera;
	private Facultad facultad;
	private String nombreCarrera;
	private BigDecimal totalCreditos;
	private Character estadoCarrera;
	private Set materias = new HashSet(0);
	private Set carreraEstudiantes = new HashSet(0);

	public Carrera() {
	}

	public Carrera(BigDecimal idCarrera) {
		this.idCarrera = idCarrera;
	}

	public Carrera(BigDecimal idCarrera, Facultad facultad,
			String nombreCarrera, BigDecimal totalCreditos,
			Character estadoCarrera, Set materias, Set carreraEstudiantes) {
		this.idCarrera = idCarrera;
		this.facultad = facultad;
		this.nombreCarrera = nombreCarrera;
		this.totalCreditos = totalCreditos;
		this.estadoCarrera = estadoCarrera;
		this.materias = materias;
		this.carreraEstudiantes = carreraEstudiantes;
	}

	public BigDecimal getIdCarrera() {
		return this.idCarrera;
	}

	public void setIdCarrera(BigDecimal idCarrera) {
		this.idCarrera = idCarrera;
	}

	public Facultad getFacultad() {
		return this.facultad;
	}

	public void setFacultad(Facultad facultad) {
		this.facultad = facultad;
	}

	public String getNombreCarrera() {
		return this.nombreCarrera;
	}

	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}

	public BigDecimal getTotalCreditos() {
		return this.totalCreditos;
	}

	public void setTotalCreditos(BigDecimal totalCreditos) {
		this.totalCreditos = totalCreditos;
	}

	public Character getEstadoCarrera() {
		return this.estadoCarrera;
	}

	public void setEstadoCarrera(Character estadoCarrera) {
		this.estadoCarrera = estadoCarrera;
	}

	public Set getMaterias() {
		return this.materias;
	}

	public void setMaterias(Set materias) {
		this.materias = materias;
	}

	public Set getCarreraEstudiantes() {
		return this.carreraEstudiantes;
	}

	public void setCarreraEstudiantes(Set carreraEstudiantes) {
		this.carreraEstudiantes = carreraEstudiantes;
	}

	@Override
	public String toString() {
		return nombreCarrera;
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((carreraEstudiantes == null) ? 0 : carreraEstudiantes
						.hashCode());
		result = prime * result
				+ ((estadoCarrera == null) ? 0 : estadoCarrera.hashCode());
		result = prime * result
				+ ((facultad == null) ? 0 : facultad.hashCode());
		result = prime * result
				+ ((idCarrera == null) ? 0 : idCarrera.hashCode());
		result = prime * result
				+ ((materias == null) ? 0 : materias.hashCode());
		result = prime * result
				+ ((nombreCarrera == null) ? 0 : nombreCarrera.hashCode());
		result = prime * result
				+ ((totalCreditos == null) ? 0 : totalCreditos.hashCode());
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
		Carrera other = (Carrera) obj;
		if (carreraEstudiantes == null) {
			if (other.carreraEstudiantes != null)
				return false;
		} else if (!carreraEstudiantes.equals(other.carreraEstudiantes))
			return false;
		if (estadoCarrera == null) {
			if (other.estadoCarrera != null)
				return false;
		} else if (!estadoCarrera.equals(other.estadoCarrera))
			return false;
		if (facultad == null) {
			if (other.facultad != null)
				return false;
		} else if (!facultad.equals(other.facultad))
			return false;
		if (idCarrera == null) {
			if (other.idCarrera != null)
				return false;
		} else if (!idCarrera.equals(other.idCarrera))
			return false;
		if (materias == null) {
			if (other.materias != null)
				return false;
		} else if (!materias.equals(other.materias))
			return false;
		if (nombreCarrera == null) {
			if (other.nombreCarrera != null)
				return false;
		} else if (!nombreCarrera.equals(other.nombreCarrera))
			return false;
		if (totalCreditos == null) {
			if (other.totalCreditos != null)
				return false;
		} else if (!totalCreditos.equals(other.totalCreditos))
			return false;
		return true;
	}
}
