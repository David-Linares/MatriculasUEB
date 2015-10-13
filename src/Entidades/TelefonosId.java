package Entidades;

// Generated 12-oct-2015 22:22:37 by Hibernate Tools 4.3.1

/**
 * TelefonosId generated by hbm2java
 */
public class TelefonosId implements java.io.Serializable {

	private int idPersona;
	private int telefono;

	public TelefonosId() {
	}

	public TelefonosId(int idPersona, int telefono) {
		this.idPersona = idPersona;
		this.telefono = telefono;
	}

	public int getIdPersona() {
		return this.idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public int getTelefono() {
		return this.telefono;
	}

	public void setTelefono(int telefono) {
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

		return (this.getIdPersona() == castOther.getIdPersona())
				&& (this.getTelefono() == castOther.getTelefono());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdPersona();
		result = 37 * result + this.getTelefono();
		return result;
	}

}
