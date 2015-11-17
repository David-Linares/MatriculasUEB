package co.ueb.matriculas.beans;

import java.util.List;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.jboss.logging.Logger;

import co.ueb.matriculas.logical.FacultadLogical;
import co.ueb.matriculas.model.Carrera;
import co.ueb.matriculas.model.Facultad;
import co.ueb.matriculas.model.Materia;
import co.ueb.matriculas.util.Constants;

/**
 * 
 * @author David Linares
 * Modificado: Noviembre 10 de 2015
 * Descripción Cambio: Arreglo de las funciones para llamados del logical
 *   
 */


public class FacultadBean implements Serializable {

	private static final long serialVersionUID = -9166065171751439973L;
	//Datos de log
	private static final Logger log = Logger.getLogger(FacultadBean.class);
	
	private FacultadLogical fl = new FacultadLogical();
	
	//Atributos de facultad para la vista.
	private String nombreFacultad = "";
	private String mensajeRespuesta = "";
	private List<Facultad> listadoFacultades = fl.consultarFacultades();
	
	//Atributos Auxiliares
	private Facultad facultadAux = null;
	private Facultad facultadValidacion = null;
	private boolean estadoFacultadEditar = false;
	private boolean mensajeError = false; 

	//Getters y Setters
	
	public boolean isMensajeError() {
		return mensajeError;
	}
	
	public void setMensajeError(boolean mensajeError) {
		this.mensajeError = mensajeError;
	}
	
	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}

	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}
	
	public Facultad getFacultadValidacion() {
		return facultadValidacion;
	}

	public void setFacultadValidacion(Facultad facultadValidacion) {
		this.facultadValidacion = facultadValidacion;
	}

	public boolean isEstadoFacultadEditar() {
		return estadoFacultadEditar;
	}

	public void setEstadoFacultadEditar(boolean estadoFacultadEditar) {
		this.estadoFacultadEditar = estadoFacultadEditar;
	}

	public Facultad getFacultadAux() {
		return this.facultadAux;
	}

	public List<Facultad> getListadoFacultades() {
		return listadoFacultades;
	}

	public void setListadoFacultades(List<Facultad> facultades) {
		this.listadoFacultades = fl.consultarFacultades();
	}

	public String getNombreFacultad() {
		return nombreFacultad;
	}

	public void setNombreFacultad(String nombreFacultad) {
		this.nombreFacultad = nombreFacultad;
	}

	//Función para asignar la facultad auxiliar 
	//que es la que sirve para editar y guardar la facultad temporal
	public void setFacultadAux(Facultad facultadAux) {
		log.info("Va a cambiar => " + facultadAux);
		if (facultadAux != null) {
			this.setFacultadValidacion(facultadAux);
			this.facultadAux = facultadAux;
			if (this.facultadAux.getEstadoFacultad().compareTo('1') == 0) {
				this.setEstadoFacultadEditar(true);
			} else {
				this.setEstadoFacultadEditar(false);
			}
		}
	}

	public String editarFacultad() {
		if (this.getFacultadAux().getNombreFacultad().equals("")) {
			this.getFacultadAux().setNombreFacultad(
					this.getFacultadValidacion().getNombreFacultad());
			this.setMensajeRespuesta(Constants.MSJ_NOMBRE_VACIO);
			this.setMensajeError(true);
			return null;
		}
		if (this.estadoFacultadEditar) {
			this.getFacultadAux().setEstadoFacultad('1');
		} else {
			this.getFacultadAux().setEstadoFacultad('0');
		}
		String respuesta = fl.modificarFacultad(this.getFacultadAux());
		switch (respuesta) {
		case "ok": // Respuesta guardado correctamente
			this.setMensajeRespuesta(Constants.FACULTAD_ACTUALIZADA);
			this.setMensajeError(false);
			break;
		case "duplicado":
			this.setMensajeRespuesta(Constants.MSJ_NOMBRE_REPETIDO + ": "
					+ this.getFacultadAux().getNombreFacultad() + " " + Constants.MSJ_INTENTO );
			this.setMensajeError(true);
			this.getFacultadAux().setNombreFacultad(this.getFacultadValidacion().getNombreFacultad());
			break;
		default:
			this.setMensajeRespuesta(Constants.MSJ_ERROR_GUARDADO + " "
					+ respuesta + ". "+ Constants.MSJ_INTENTO);
			this.setMensajeError(true);
			break;
		}
		return Constants.NAVEGACION_FACULTAD;
	}

	//Funcion para crear una nueva facultad 
	public String crearFacultad() {
		log.info("===Crear Facultad===");
		if (!validarCampos(this.getNombreFacultad())) {
			this.setMensajeError(true);
			this.setMensajeRespuesta(Constants.MSJ_CAMPOS_VACIOS);
			return Constants.NAVEGACION_FACULTAD;
		} 
		
		Set<Carrera> carreras = new HashSet<Carrera>(0);
		BigDecimal idFacultadAux = new BigDecimal(1);
		if (this.getListadoFacultades().size() != 0) {
			idFacultadAux = this.getListadoFacultades().get(this.getListadoFacultades().size() - 1)
					.getIdFacultad().add(new BigDecimal(1));
		}
		Facultad nuevaFacultad = new Facultad(idFacultadAux,
				this.nombreFacultad, '1', carreras);
		log.info("Nueva Facultad => " + nuevaFacultad);
		String respuesta = fl.crearNuevaFacultad(nuevaFacultad);
		switch (respuesta) {
		case "ok": // Respuesta guardado correctamente
			this.setMensajeRespuesta(Constants.FACULTAD_CREADA);
			this.setMensajeError(false);
			this.listadoFacultades.add(nuevaFacultad);
			break;
		case "duplicado":
			this.setMensajeRespuesta(Constants.MSJ_NOMBRE_REPETIDO + " "
					+ nuevaFacultad.getNombreFacultad() + ". "+ Constants.MSJ_INTENTO);
			this.setMensajeError(true);
			break;
		default:
			this.setMensajeRespuesta(Constants.MSJ_ERROR_GUARDADO + " "
					+ respuesta + ". "+ Constants.MSJ_INTENTO);
			this.setMensajeError(true);
			break;
		}
		return Constants.NAVEGACION_FACULTAD;
	}

	//Funcion para validar que los campos no esten vacios
		public boolean validarCampos(String nombreFacultadCampos) {
			if (nombreFacultadCampos == null
					|| nombreFacultadCampos.equals(""))
				return false;
			return true;
		}

		//Funcion para vaciar el formulario de crear facultad
		public void vaciarCampos() {
			this.setNombreFacultad("");
		}
	//
}
