package co.ueb.matriculas.model;

// Generated Oct 13, 2015 8:11:29 AM by Hibernate Tools 4.3.1

import java.math.BigDecimal;

/**
 * CarreraMateriaId generated by hbm2java
 */
public class CarreraMateriaId implements java.io.Serializable {

	private BigDecimal idCarrera;
	private BigDecimal idMateria;

	public CarreraMateriaId() {
	}

	public CarreraMateriaId(BigDecimal idCarrera, BigDecimal idMateria) {
		this.idCarrera = idCarrera;
		this.idMateria = idMateria;
	}

	public BigDecimal getIdCarrera() {
		return this.idCarrera;
	}

	public void setIdCarrera(BigDecimal idCarrera) {
		this.idCarrera = idCarrera;
	}

	public BigDecimal getIdMateria() {
		return this.idMateria;
	}

	public void setIdMateria(BigDecimal idMateria) {
		this.idMateria = idMateria;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CarreraMateriaId))
			return false;
		CarreraMateriaId castOther = (CarreraMateriaId) other;

		return ((this.getIdCarrera() == castOther.getIdCarrera()) || (this
				.getIdCarrera() != null && castOther.getIdCarrera() != null && this
				.getIdCarrera().equals(castOther.getIdCarrera())))
				&& ((this.getIdMateria() == castOther.getIdMateria()) || (this
						.getIdMateria() != null
						&& castOther.getIdMateria() != null && this
						.getIdMateria().equals(castOther.getIdMateria())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getIdCarrera() == null ? 0 : this.getIdCarrera().hashCode());
		result = 37 * result
				+ (getIdMateria() == null ? 0 : this.getIdMateria().hashCode());
		return result;
	}

}