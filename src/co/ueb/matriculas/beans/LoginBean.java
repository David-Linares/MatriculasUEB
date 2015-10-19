package co.ueb.matriculas.beans;

import java.io.Serializable;
import java.math.BigDecimal;


import javax.faces.context.FacesContext;

import org.apache.commons.codec.digest.DigestUtils;

import co.ueb.matriculas.logical.PersonaLogical;
import co.ueb.matriculas.model.Persona;

@SuppressWarnings("serial")
public class LoginBean implements Serializable {

	private String usuario;
	private String contrasena;
	private String resultado;
<<<<<<< HEAD
=======
	private BigDecimal perfilUsuario;
	private Persona sesionPersona;
	
	public void setPerfilUsuario(){
		sesionPersona = (Persona) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		if( sesionPersona != null){
			perfilUsuario = sesionPersona.getPerfil().getIdPerfil();
		}
		System.out.println("-------------------");
		System.out.println("El perfil de usuario es: " + perfilUsuario);
		System.out.println("-------------------");
			
	}
	
	public BigDecimal getPerfilUsuario() {
		return perfilUsuario;
	}

	public Persona getSesionPersona() {
		return sesionPersona;
	}

	public void setSesionPersona(Persona sesionPersona) {
		this.sesionPersona = sesionPersona;
	}
>>>>>>> origin/master
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

<<<<<<< HEAD
=======

>>>>>>> origin/master
	public String verificarDatos() throws Exception {
		System.out.println("entrò a verificar Datos");
		String encript = DigestUtils.md5Hex(this.contrasena);
		this.contrasena= encript;
		Persona nuevaPersona = new Persona(usuario, contrasena);
		PersonaLogical personaLogical = new PersonaLogical();
		Persona nuevoUsuario;
		try {
<<<<<<< HEAD
=======
			System.out.println("-------------------");
			System.out.println(perfilUsuario);
			System.out.println("-------------------");
>>>>>>> origin/master
			System.out.println("entrò try 1");
			nuevoUsuario = personaLogical.verificarDatos(nuevaPersona);
			System.out.println("entró a try 2");
			if (nuevoUsuario != null) {
				FacesContext.getCurrentInstance().getExternalContext()
						.getSessionMap().put("usuario", nuevoUsuario);
				this.setPerfilUsuario();
				this.resultado = "paginaEstudiante";
			} else {
				this.resultado = "error";
			}
		} catch (Exception e) {
			throw e;
		}
		return this.resultado;
	}

	public String cerrarSesion() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.clear();
		this.resultado = "login";
		System.out.println("finalizar");
		return this.resultado;
	}

}
