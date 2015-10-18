package co.ueb.matriculas.beans;

import java.io.Serializable;

import javax.faces.context.FacesContext;

import co.ueb.matriculas.logical.PersonaLogical;
import co.ueb.matriculas.model.Persona;



@SuppressWarnings("serial")
public class LoginBean implements Serializable{

	private String usuario;
	private String contrasena;
	
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

	
	
	public String verificarDatos() throws Exception{
		System.out.println("entr� a verificar Datos");
		Persona nuevaPersona = new Persona(usuario, contrasena);
		PersonaLogical personaLogical = new PersonaLogical();
		Persona nuevoUsuario;
		String resultado;
		try {
			System.out.println("entr� try 1");
			nuevoUsuario=personaLogical.verificarDatos(nuevaPersona);
			System.out.println("entr� a try 2");
			if(nuevoUsuario!= null){
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", nuevoUsuario);			
				resultado="paginaEstudiante";
			System.out.println("entr� if");	
			}else{
				resultado="error";
			}
		} catch (Exception e) {
			System.out.println("entr� a Catch");
			throw e;
		}
		return resultado;
	}

}
