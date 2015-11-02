package co.ueb.matriculas.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;

import org.apache.commons.codec.digest.DigestUtils;

import co.ueb.matriculas.logical.PersonaLogical;
import co.ueb.matriculas.model.Persona;

@SuppressWarnings("serial")
public class LoginBean implements Serializable {

	private String usuario;
	private String contrasena;
	private String resultado;
    private boolean mostrarError;
	private BigDecimal perfilUsuario;
	private Persona sesionPersona;
	final Logger log = Logger.getLogger("LoginBean -- ");
	
	public boolean isMostrarError(){
		return mostrarError;
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
	
	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
		this.mostrarError = true;
	}
	
	public void setPerfilUsuario(){
		sesionPersona = (Persona) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		if( sesionPersona != null){
			perfilUsuario = sesionPersona.getPerfil().getIdPerfil();
		}		
	}
	/*Método que le envía al Logical de Persona para hacer la consulta*/
	public String verificarDatos() {
		long inicio = System.nanoTime();
		log.info("| Verificar Datos");
		String encrypt = DigestUtils.md5Hex(this.contrasena);
		this.contrasena = encrypt;
		Persona nuevaPersona = new Persona(usuario, contrasena);
		PersonaLogical personaLogical = new PersonaLogical();
		Persona nuevoUsuario;
		try {
			log.info("Persona => "+nuevaPersona);
			nuevoUsuario = personaLogical.verificarDatos(nuevaPersona);
			if (nuevoUsuario != null) {
				FacesContext.getCurrentInstance().getExternalContext()
						.getSessionMap().put("usuario", nuevoUsuario);
				this.setPerfilUsuario();
				if(nuevoUsuario.getPerfil().getIdPerfil().toString().equals("2"))
					this.resultado = "paginaFacultad";
				else
					this.resultado = "paginaMatricula";
			} else {
				this.setResultado("Usuario o Password incorrectas");
				return null;	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		long fin = System.nanoTime();
		log.info("Inicio de sesión: " + (fin-inicio));
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
