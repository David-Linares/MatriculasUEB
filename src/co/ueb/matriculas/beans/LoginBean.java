package co.ueb.matriculas.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.faces.context.FacesContext;

import co.ueb.matriculas.logical.PersonaLogical;
import co.ueb.matriculas.model.Persona;



@SuppressWarnings("serial")
public class LoginBean implements Serializable{

	private String usuario;
	private String contrasena;
	private boolean perfilUsuario = false;
	
	
	public boolean isPerfilUsuario() {
		return perfilUsuario;
	}

	public void setPerfilUsuario(boolean perfilUsuario) {
		this.perfilUsuario = perfilUsuario;
	}

	public LoginBean(){
		Persona sesionPersona = (Persona) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		System.out.println(sesionPersona);
		if( sesionPersona != null){
			perfilUsuario = sesionPersona.getPerfil().getIdPerfil() == new BigDecimal(2);
		}
		
	}

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
		System.out.println("entrò a verificar Datos");
		Persona nuevaPersona = new Persona(usuario, contrasena);
		PersonaLogical personaLogical = new PersonaLogical();
		Persona nuevoUsuario;
		String resultado;
		try {
			System.out.println("entrò try 1");
			nuevoUsuario=personaLogical.verificarDatos(nuevaPersona);
			System.out.println("entró a try 2");
			if(nuevoUsuario!= null){
	//			nomUsuario = true;
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", nuevoUsuario);			
	/*			if(nuevoUsuario.getPerfil().getIdPerfil() !=  new BigDecimal(2)){
					secretarioAcademico = true;
					System.out.println(nuevoUsuario.getPerfil().getIdPerfil());
				}else{
					estudiante = true;
				} */
				resultado="paginaEstudiante";
			System.out.println("entró if");
			}else{
				resultado="error";
			}
		} catch (Exception e) {
			System.out.println("entró a Catch");
			throw e;
		}
		return resultado;
	}

}
