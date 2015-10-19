package co.ueb.matriculas.beans;

import java.io.Serializable;


import javax.faces.context.FacesContext;

import org.apache.commons.codec.digest.DigestUtils;

import co.ueb.matriculas.logical.PersonaLogical;
import co.ueb.matriculas.model.Persona;

@SuppressWarnings("serial")
public class LoginBean implements Serializable {

	private String usuario;
	private String contrasena;
	private String resultado;

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

	public String verificarDatos() throws Exception {
		System.out.println("entr� a verificar Datos");
		String encript = DigestUtils.md5Hex(this.contrasena);
		this.contrasena= encript;
		Persona nuevaPersona = new Persona(usuario, contrasena);
		PersonaLogical personaLogical = new PersonaLogical();
		Persona nuevoUsuario;
		try {
			System.out.println("entr� try 1");
			nuevoUsuario = personaLogical.verificarDatos(nuevaPersona);
			System.out.println("entr� a try 2");
			if (nuevoUsuario != null) {
				FacesContext.getCurrentInstance().getExternalContext()
						.getSessionMap().put("usuario", nuevoUsuario);
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
