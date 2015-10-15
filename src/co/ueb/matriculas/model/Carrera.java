package co.ueb.matriculas.model;

// default package
// Generated 14-oct-2015 14:38:56 by Hibernate Tools 3.4.0.CR1

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
	private Set personas = new HashSet(0);
	private Set materias = new HashSet(0);

	public Carrera() {
	}

	public Carrera(BigDecimal idCarrera) {
		this.idCarrera = idCarrera;
	}

	public Carrera(BigDecimal idCarrera, Facultad facultad,
			String nombreCarrera, BigDecimal totalCreditos,
			Character estadoCarrera, Set personas, Set materias) {
		this.idCarrera = idCarrera;
		this.facultad = facultad;
		this.nombreCarrera = nombreCarrera;
		this.totalCreditos = totalCreditos;
		this.estadoCarrera = estadoCarrera;
		this.personas = personas;
		this.materias = materias;
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

	public Set getPersonas() {
		return this.personas;
	}

	public void setPersonas(Set personas) {
		this.personas = personas;
	}

	public Set getMaterias() {
		return this.materias;
	}

	public void setMaterias(Set materias) {
		this.materias = materias;
	}

}
