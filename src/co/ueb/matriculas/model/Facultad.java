package co.ueb.matriculas.model;

// Generated 02-nov-2015 12:47:33 by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


/**
 * Facultad generated by hbm2java
 */

@javax.persistence.NamedNativeQuery(name = "getFunc", query = "{call INSERTAR_FACULTAD(:nombre_facultad) }", 
resultClass = Facultad.class, hints = {
@javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true") })

public class Facultad implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1136990799117076618L;
	private BigDecimal idFacultad;
	private String nombreFacultad;
	private Character estadoFacultad;
	private Set carreras = new HashSet(0);

	public Facultad() {
	}

	public Facultad(BigDecimal idFacultad) {
		this.idFacultad = idFacultad;
	}

	public Facultad(BigDecimal idFacultad, String nombreFacultad,
			Character estadoFacultad, Set carreras) {
		this.idFacultad = idFacultad;
		this.nombreFacultad = nombreFacultad;
		this.estadoFacultad = estadoFacultad;
		this.carreras = carreras;
	}

	public BigDecimal getIdFacultad() {
		return this.idFacultad;
	}

	public void setIdFacultad(BigDecimal idFacultad) {
		this.idFacultad = idFacultad;
	}

	public String getNombreFacultad() {
		return this.nombreFacultad;
	}

	public void setNombreFacultad(String nombreFacultad) {
		this.nombreFacultad = nombreFacultad;
	}

	public Character getEstadoFacultad() {
		return this.estadoFacultad;
	}

	public void setEstadoFacultad(Character estadoFacultad) {
		this.estadoFacultad = estadoFacultad;
	}

	public Set getCarreras() {
		return this.carreras;
	}

	public void setCarreras(Set carreras) {
		this.carreras = carreras;
	}

	@Override
	public String toString() {
		return "Facultad [nombreFacultad=" + nombreFacultad + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idFacultad == null) ? 0 : idFacultad.hashCode());
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
		Facultad other = (Facultad) obj;
		if (idFacultad == null) {
			if (other.idFacultad != null)
				return false;
		} else if (!idFacultad.equals(other.idFacultad))
			return false;
		return true;
	}
	
	

}
