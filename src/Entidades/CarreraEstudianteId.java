package Entidades;

// Generated 12-oct-2015 22:22:37 by Hibernate Tools 4.3.1

/**
 * CarreraEstudianteId generated by hbm2java
 */
public class CarreraEstudianteId implements java.io.Serializable {

	private int idPersona;
	private int idCarrera;

	public CarreraEstudianteId() {
	}

	public CarreraEstudianteId(int idPersona, int idCarrera) {
		this.idPersona = idPersona;
		this.idCarrera = idCarrera;
	}

	public int getIdPersona() {
		return this.idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public int getIdCarrera() {
		return this.idCarrera;
	}

	public void setIdCarrera(int idCarrera) {
		this.idCarrera = idCarrera;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CarreraEstudianteId))
			return false;
		CarreraEstudianteId castOther = (CarreraEstudianteId) other;

		return (this.getIdPersona() == castOther.getIdPersona())
				&& (this.getIdCarrera() == castOther.getIdCarrera());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdPersona();
		result = 37 * result + this.getIdCarrera();
		return result;
	}

}
