package co.ueb.matriculas.model;

// Generated Nov 1, 2015 5:44:57 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Perfil generated by hbm2java
 */
public class Perfil implements java.io.Serializable {

	private BigDecimal idPerfil;
	private String nombrePerfil;
	private Character estadoPerfil;
	private Set personas = new HashSet(0);

	public Perfil() {
	}

	public Perfil(BigDecimal idPerfil) {
		this.idPerfil = idPerfil;
	}

	public Perfil(BigDecimal idPerfil, String nombrePerfil,
			Character estadoPerfil, Set personas) {
		this.idPerfil = idPerfil;
		this.nombrePerfil = nombrePerfil;
		this.estadoPerfil = estadoPerfil;
		this.personas = personas;
	}

	public BigDecimal getIdPerfil() {
		return this.idPerfil;
	}

	public void setIdPerfil(BigDecimal idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getNombrePerfil() {
		return this.nombrePerfil;
	}

	public void setNombrePerfil(String nombrePerfil) {
		this.nombrePerfil = nombrePerfil;
	}

	public Character getEstadoPerfil() {
		return this.estadoPerfil;
	}

	public void setEstadoPerfil(Character estadoPerfil) {
		this.estadoPerfil = estadoPerfil;
	}

	public Set getPersonas() {
		return this.personas;
	}

	public void setPersonas(Set personas) {
		this.personas = personas;
	}

}
