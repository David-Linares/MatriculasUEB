package co.ueb.matriculas.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Persona generated by hbm2java
 */
public class Persona implements java.io.Serializable {

	private BigDecimal idPersona;
	private Perfil perfil;
	private String nombrePersona;
	private String apellidosPersona;
	private Date fechaNacimiento;
	private String lugarNacimiento;
	private String direccion;
	private String correoElectronico;
	private Character estadoPersona;
	private String contrasena;
	private String persona;
	private Set matriculas = new HashSet(0);
	private Set carreras = new HashSet(0);
	private Set telefonoses = new HashSet(0);

	public Persona() {
	}

	public Persona(BigDecimal idPersona) {
		this.idPersona = idPersona;
	}

	public Persona(BigDecimal idPersona, Perfil perfil, String nombrePersona,
			String apellidosPersona, Date fechaNacimiento,
			String lugarNacimiento, String direccion, String correoElectronico,
			Character estadoPersona, String contrasena, String persona,
			Set matriculas, Set carreraEstudiantes, Set telefonoses) {
		this.idPersona = idPersona;
		this.perfil = perfil;
		this.nombrePersona = nombrePersona;
		this.apellidosPersona = apellidosPersona;
		this.fechaNacimiento = fechaNacimiento;
		this.lugarNacimiento = lugarNacimiento;
		this.direccion = direccion;
		this.correoElectronico = correoElectronico;
		this.estadoPersona = estadoPersona;
		this.contrasena = contrasena;
		this.persona = persona;
		this.matriculas = matriculas;
		this.carreras = carreras;
		this.telefonoses = telefonoses;
	}

	public BigDecimal getIdPersona() {
		return this.idPersona;
	}

	public void setIdPersona(BigDecimal idPersona) {
		this.idPersona = idPersona;
	}

	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public String getNombrePersona() {
		return this.nombrePersona;
	}

	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}

	public String getApellidosPersona() {
		return this.apellidosPersona;
	}

	public void setApellidosPersona(String apellidosPersona) {
		this.apellidosPersona = apellidosPersona;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getLugarNacimiento() {
		return this.lugarNacimiento;
	}

	public void setLugarNacimiento(String lugarNacimiento) {
		this.lugarNacimiento = lugarNacimiento;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreoElectronico() {
		return this.correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public Character getEstadoPersona() {
		return this.estadoPersona;
	}

	public void setEstadoPersona(Character estadoPersona) {
		this.estadoPersona = estadoPersona;
	}

	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getPersona() {
		return this.persona;
	}

	public void setPersona(String persona) {
		this.persona = persona;
	}

	public Set getMatriculas() {
		return this.matriculas;
	}

	public void setMatriculas(Set matriculas) {
		this.matriculas = matriculas;
	}

	public Set getCarreras() {
		return this.carreras;
	}

	public void setCarreras(Set carreras) {
		this.carreras = carreras;
	}

	public Set getTelefonoses() {
		return this.telefonoses;
	}

	public void setTelefonoses(Set telefonoses) {
		this.telefonoses = telefonoses;
	}

}
