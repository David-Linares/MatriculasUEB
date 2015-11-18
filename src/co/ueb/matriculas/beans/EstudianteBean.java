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
			this.estudianteAuxEditar = copiaEstudianteAux;
			
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
		log.info("## Entra a editar Estudiante ##");
		if(!validarCampos(this.getEstudianteAux())){
			this.setEstudianteAux(estudianteAuxEditar);
			this.setMensajeError(true);
			this.setMensajeRespuesta(Constants.MSJ_CAMPOS_VACIOS);
			return Constants.NAVEGACION_ESTUDIANTE;
		}
		log.info("Pasó validación");
		if (this.estadoEstudianteEditar) {
			this.getEstudianteAux().setEstadoPersona('1');
		} else {
			this.getEstudianteAux().setEstadoPersona('0');
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		log.info(this.getFechaNacimiento());
		try {
			Date nfecha = dateFormat.parse(this.getFechaNacimiento());
			this.getEstudianteAux().setFechaNacimiento(nfecha);
		} catch (ParseException e) {
			log.error("Error al momento de convertir la fecha");
			log.error(e);
			e.printStackTrace();
		}
		this.getEstudianteAux().setContrasena(encriptarContrasena(this.estudianteAux.getContrasena()));
		log.info("Objeto a enviar");
		log.info(this.getEstudianteAux());
		log.info(" ");
		String respuesta= el.modificarEstudiante(this.getEstudianteAux());
		switch (respuesta) {
		case "ok": // Respuesta guardado correctamente
			this.setMensajeRespuesta(Constants.ESTUDIANTE_ACTUALIZADO);
			this.setMensajeError(false);
			break;
		case "duplicado":
			log.info(respuesta);
			this.setMensajeRespuesta(Constants.MSJ_IDENTIFICACION_REPETIDO + " "
					+ estudianteAux.getIdPersona() + ". "+ Constants.MSJ_INTENTO);
			this.setMensajeError(true);
			break;
		case "usuario_duplicado":
			this.setMensajeRespuesta(Constants.MSJ_USUARIO_REPETIDO + " "
					+ estudianteAux.getUsuario() + ". "+ Constants.MSJ_INTENTO);
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
		if(!validarCampos(this.getNuevoEstudiante())){
			this.setMensajeError(true);
			this.setMensajeRespuesta(Constants.MSJ_CAMPOS_VACIOS);
			return Constants.NAVEGACION_ESTUDIANTE;
		}
		log.info(nuevoEstudiante);
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		nuevoEstudiante.setPerfil(perfil); //Cambiar por Select
		nuevoEstudiante.setPromedio(new BigDecimal(5.0));
		log.info(this.getFechaNacimiento());
		nuevoEstudiante.setContrasena(encriptarContrasena(nuevoEstudiante.getContrasena()));
		nuevoEstudiante.setEstadoPersona('1');
		nuevoEstudiante.setUsuario(nuevoEstudiante.getUsuario().trim());
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

	//Funcion para validar que los campos no esten vacios
	public boolean validarCampos(Persona estudianteCampos){
		log.info("validacion david canson");
		log.info(estudianteCampos);
		log.info(estudianteCampos.getIdPersona().equals(new BigDecimal(0)) || estudianteCampos.getIdPersona().equals(""));
		log.info(estudianteCampos.getNombrePersona()== null || estudianteCampos.getNombrePersona().equals(""));
		log.info(estudianteCampos.getApellidosPersona()==null || estudianteCampos.getApellidosPersona().equals(""));
		log.info(estudianteCampos.getFechaNacimiento()==null || estudianteCampos.getFechaNacimiento().equals(""));
		log.info(estudianteCampos.getLugarNacimiento()==null || estudianteCampos.getLugarNacimiento().equals(""));
		log.info(estudianteCampos.getDireccion()==null || estudianteCampos.getDireccion().equals(""));
		log.info(estudianteCampos.getCorreoElectronico()==null || estudianteCampos.getCorreoElectronico().equals(""));
		log.info(estudianteCampos.getUsuario()==null || estudianteCampos.getUsuario().equals(""));
		log.info(estudianteCampos.getContrasena()==null || estudianteCampos.getContrasena().equals(""));

		if(estudianteCampos.getIdPersona().equals(new BigDecimal(0)) || estudianteCampos.getIdPersona().equals("")){
			return false;
		}
		if(estudianteCampos.getNombrePersona()== null || estudianteCampos.getNombrePersona().equals("")){
			return false;
		}
		if(estudianteCampos.getApellidosPersona()==null || estudianteCampos.getApellidosPersona().equals("")){
			return false;
		}
		if(estudianteCampos.getLugarNacimiento()==null || estudianteCampos.getLugarNacimiento().equals("")){
			return false;
		}
		if(estudianteCampos.getDireccion()==null || estudianteCampos.getDireccion().equals("")){
			return false;
		}
		if(estudianteCampos.getCorreoElectronico()==null || estudianteCampos.getCorreoElectronico().equals("")){
			return false;
		}
		if(estudianteCampos.getUsuario()==null || estudianteCampos.getUsuario().equals("")){
			return false;
		}
		if (estudianteCampos.getContrasena()==null || estudianteCampos.getContrasena().equals("")){
			return false;
		}
		return true;
	}
	
	//Funcion para vaciar el formulario de crear estudiante
	public void vaciarCampos(){
		this.setNuevoEstudiante(new Persona());
	}
}
