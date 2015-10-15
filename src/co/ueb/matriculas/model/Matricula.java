package co.ueb.matriculas.model;

// Generated Oct 14, 2015 8:54:14 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Matricula generated by hbm2java
 */
public class Matricula implements java.io.Serializable {

	private BigDecimal idMatricula;
	private Persona persona;
	private BigDecimal totalCreditos;
	private BigDecimal totalMatricula;
	private Character estadoMatricula;
	private Set materiaMatriculas = new HashSet(0);

	public Matricula() {
	}

	public Matricula(BigDecimal idMatricula) {
		this.idMatricula = idMatricula;
	}

	public Matricula(BigDecimal idMatricula, Persona persona,
			BigDecimal totalCreditos, BigDecimal totalMatricula,
			Character estadoMatricula, Set materiaMatriculas) {
		this.idMatricula = idMatricula;
		this.persona = persona;
		this.totalCreditos = totalCreditos;
		this.totalMatricula = totalMatricula;
		this.estadoMatricula = estadoMatricula;
		this.materiaMatriculas = materiaMatriculas;
	}

	public BigDecimal getIdMatricula() {
		return this.idMatricula;
	}

	public void setIdMatricula(BigDecimal idMatricula) {
		this.idMatricula = idMatricula;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public BigDecimal getTotalCreditos() {
		return this.totalCreditos;
	}

	public void setTotalCreditos(BigDecimal totalCreditos) {
		this.totalCreditos = totalCreditos;
	}

	public BigDecimal getTotalMatricula() {
		return this.totalMatricula;
	}

	public void setTotalMatricula(BigDecimal totalMatricula) {
		this.totalMatricula = totalMatricula;
	}

	public Character getEstadoMatricula() {
		return this.estadoMatricula;
	}

	public void setEstadoMatricula(Character estadoMatricula) {
		this.estadoMatricula = estadoMatricula;
	}

	public Set getMateriaMatriculas() {
		return this.materiaMatriculas;
	}

	public void setMateriaMatriculas(Set materiaMatriculas) {
		this.materiaMatriculas = materiaMatriculas;
	}

}
