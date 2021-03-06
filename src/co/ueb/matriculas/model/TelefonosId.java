package co.ueb.matriculas.model;

// Generated 02-nov-2015 12:47:33 by Hibernate Tools 4.3.1

import java.math.BigDecimal;

/**
 * TelefonosId generated by hbm2java
 */
public class TelefonosId implements java.io.Serializable {

	private BigDecimal idPersona;
	private BigDecimal telefono;

	public TelefonosId() {
	}

	public TelefonosId(BigDecimal idPersona, BigDecimal telefono) {
		this.idPersona = idPersona;
		this.telefono = telefono;
	}

	public BigDecimal getIdPersona() {
		return this.idPersona;
	}

	public void setIdPersona(BigDecimal idPersona) {
		this.idPersona = idPersona;
	}

	public BigDecimal getTelefono() {
		return this.telefono;
	}

	public void setTelefono(BigDecimal telefono) {
		this.telefono = telefono;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TelefonosId))
			return false;
		TelefonosId castOther = (TelefonosId) other;

		return ((this.getIdPersona() == castOther.getIdPersona()) || (this
				.getIdPersona() != null && castOther.getIdPersona() != null && this
				.getIdPersona().equals(castOther.getIdPersona())))
				&& ((this.getTelefono() == castOther.getTelefono()) || (this
						.getTelefono() != null
						&& castOther.getTelefono() != null && this
						.getTelefono().equals(castOther.getTelefono())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getIdPersona() == null ? 0 : this.getIdPersona().hashCode());
		result = 37 * result
				+ (getTelefono() == null ? 0 : this.getTelefono().hashCode());
		return result;
	}

}
