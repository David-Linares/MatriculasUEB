/**
 * 
 */
package co.ueb.matriculas.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.jboss.logging.Logger;

import co.ueb.matriculas.logical.CarreraLogical;
import co.ueb.matriculas.logical.EstudianteLogical;
import co.ueb.matriculas.model.Carrera;
import co.ueb.matriculas.model.Perfil;
import co.ueb.matriculas.model.Persona;
import co.ueb.matriculas.util.Constants;

public class EstudianteBean implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6961145382541935022L;
	private static final Logger log = Logger.getLogger(EstudianteBean.class);
	
	private String mensajeRespuesta = "";
	private String auxNombreValidacion = "";
	private boolean mensajeError = false;

	private boolean banderaEdit = false;
	private boolean estadoEstudianteEditar = false;

	private EstudianteLogical el = new EstudianteLogical();
	private CarreraLogical cl = new CarreraLogical();

	private Carrera carreraEstudiante = null;

	private Persona nuevoEstudiante = new Persona();
	private Persona estudianteAux = null;
	private Persona estudianteAuxEditar = null;

	private Perfil perfil = new Perfil(new BigDecimal(1));

	private List<Persona> listadoEstudiantes = el.consultarEstudiantes();
	private List<Carrera> listadoCarreras = cl.consultarCarreras();
	
	private String fechaNacimiento;
	
	
	public boolean isMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(boolean mensajeError) {
		this.mensajeError = mensajeError;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Persona getNuevoEstudiante() {
		return nuevoEstudiante;
	}

	public void setNuevoEstudiante(Persona nuevoEstudiante) {
		this.nuevoEstudiante = nuevoEstudiante;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<Carrera> getListadoCarreras() {
		return listadoCarreras;
	}

	public void setListadoCarreras(List<Carrera> listadoCarreras) {
		this.listadoCarreras = listadoCarreras;
	}

	public Carrera getCarreraEstudiante() {
		return carreraEstudiante;
	}

	public void setCarreraEstudiante(Carrera carreraEstudiante) {
		this.carreraEstudiante = carreraEstudiante;
	}

	public Persona getEstudianteAuxEditar() {
		return estudianteAuxEditar;
	}

	public void setEstudianteAuxEditar(Persona estudianteAuxEditar) {
		this.estudianteAuxEditar = estudianteAuxEditar;
	}

	public boolean isEstadoEstudianteEditar() {
		return estadoEstudianteEditar;
	}

	public void setEstadoEstudianteEditar(boolean estadoEstudianteEditar) {
		this.estadoEstudianteEditar = estadoEstudianteEditar;
	}

	public Persona getEstudianteAux() {
		return estudianteAux;
	}

	public String getAuxNombreValidacion() {
		return auxNombreValidacion;
	}

	public void setAuxNombreValidacion(String auxNombreValidacion) {
		this.auxNombreValidacion = auxNombreValidacion;
	}

	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}

	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}

	public boolean isBanderaEdit() {
		return banderaEdit;
	}

	public void setBanderaEdit(boolean banderaEdit) {
		this.banderaEdit = banderaEdit;
	}

	public String encriptarContrasena(String contrasenaToEncrypt){
		String encrypt = DigestUtils.md5Hex(contrasenaToEncrypt);
		return encrypt;
	}
	
	public void setEstudianteAux(Persona copiaEstudianteAux) {
		log.info("##Entro a setEstudianteAux##");
		log.info(copiaEstudianteAux);
		if (copiaEstudianteAux != null) {
			this.fechaNacimiento = copiaEstudianteAux.getFechaNacimiento().toString();
			this.estudianteAux = copiaEstudianteAux;
			this.setEstudianteAuxEditar(copiaEstudianteAux);
			if (this.getEstudianteAux().getEstadoPersona().compareTo('1') == 0) {
				this.setEstadoEstudianteEditar(true);
			} else {
				this.setEstadoEstudianteEditar(false);
			}
		}
	}

	public List<Persona> getListadoEstudiantes() {
		return listadoEstudiantes;
	}

	public void setListadoEstudiantes(List<Persona> listadoEstudiantes) {
		this.listadoEstudiantes = listadoEstudiantes;
	}

	public String editarEstudiante() {

		if (this.estadoEstudianteEditar) {
			this.getEstudianteAux().setEstadoPersona('1');
		} else {
			this.getEstudianteAux().setEstadoPersona('0');
		}
		this.getEstudianteAux().setContrasena(encriptarContrasena(this.estudianteAux.getContrasena()));
		String respuesta= el.modificarEstudiante(this.getEstudianteAux());
		switch (respuesta) {
		case "ok": // Respuesta guardado correctamente
			this.setMensajeRespuesta(Constants.ESTUDIANTE_MODIFICADO);
			this.setMensajeError(false);
			this.listadoEstudiantes.add(nuevoEstudiante);
			break;
		case "duplicado":
			this.setMensajeRespuesta(Constants.MSJ_IDENTIFICACION_REPETIDO + " "
					+ nuevoEstudiante.getIdPersona() + ". "+ Constants.MSJ_INTENTO);
			this.setMensajeError(true);
			break;
		case "usuario_duplicado":
			this.setMensajeRespuesta(Constants.MSJ_USUARIO_REPETIDO + " "
					+ nuevoEstudiante.getUsuario() + ". "+ Constants.MSJ_INTENTO);
			this.setMensajeError(true);
			break;
		default:
			this.setMensajeRespuesta(Constants.MSJ_ERROR_GUARDADO + " "
					+ respuesta + ". "+ Constants.MSJ_INTENTO);
			this.setMensajeError(true);
			break;
		}
		return Constants.NAVEGACION_ESTUDIANTE;
	}

	public String crearEstudiante() {

		log.info(nuevoEstudiante);
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		nuevoEstudiante.setPerfil(perfil); //Cambiar por Select
		nuevoEstudiante.setPromedio(new BigDecimal(5.0));
		log.info(this.getFechaNacimiento());
		nuevoEstudiante.setContrasena(encriptarContrasena(nuevoEstudiante.getContrasena()));
		nuevoEstudiante.setEstadoPersona('1');
		try {
			Date nfecha = dateFormat.parse(this.getFechaNacimiento());
			nuevoEstudiante.setFechaNacimiento(nfecha);
		} catch (ParseException e) {
			log.error("Error al momento de convertir la fecha");
			log.error(e);
			e.printStackTrace();
		}
		log.info(" ## Antes de enviar el objeto ## ");
		log.info(nuevoEstudiante);
		
		String rptaGuardado = el.crearNuevoEstudiante(nuevoEstudiante);
		switch (rptaGuardado) {
		case "ok": // Respuesta guardado correctamente
			this.setMensajeRespuesta(Constants.ESTUDIANTE_CREADO);
			this.setMensajeError(false);
			this.listadoEstudiantes.add(nuevoEstudiante);
			break;
		case "duplicado":
			this.setMensajeRespuesta(Constants.MSJ_IDENTIFICACION_REPETIDO + " "
					+ nuevoEstudiante.getIdPersona() + ". "+ Constants.MSJ_INTENTO);
			this.setMensajeError(true);
			break;
		case "usuario_duplicado":
			this.setMensajeRespuesta(Constants.MSJ_USUARIO_REPETIDO + " "
					+ nuevoEstudiante.getUsuario() + ". "+ Constants.MSJ_INTENTO);
			this.setMensajeError(true);
			break;
		default:
			this.setMensajeRespuesta(Constants.MSJ_ERROR_GUARDADO + " "
					+ rptaGuardado + ". "+ Constants.MSJ_INTENTO);
			this.setMensajeError(true);
			break;
		}
		
		return Constants.NAVEGACION_ESTUDIANTE;
	}

}
