package co.ueb.matriculas.beans;

import java.util.List;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;


import co.ueb.matriculas.logical.FacultadLogical;
import co.ueb.matriculas.model.Carrera;
import co.ueb.matriculas.model.Facultad;

public class FacultadBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9166065171751439973L;
	private final Logger log = Logger.getLogger("Facultad Bean -- ");
	FacultadLogical fl = new FacultadLogical();
	String nombreFacultad = "";
	List<Facultad> listadoFacultades = fl.consultarFacultades();
	Facultad facultadAux = null;
	boolean banderaEdit = false;
	boolean estadoFacultadEditar = false;
	String mensajeRespuesta = "";
	String auxNombreValidacion = "";
		
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
	
	public boolean isEstadoFacultadEditar() {
		return estadoFacultadEditar;
	}

	public void setEstadoFacultadEditar(boolean estadoFacultadEditar) {
		this.estadoFacultadEditar = estadoFacultadEditar;
	}
	
	public boolean isBanderaEdit() {
		return banderaEdit;
	}

	public void setBanderaEdit(boolean banderaEdit) {
		this.banderaEdit = banderaEdit;
	}


	public Facultad getFacultadAux(){
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
	
	public void setFacultadAux(Facultad facultadAux){
		log.info("Va a cambiar => "+facultadAux);
		if(facultadAux != null){
			log.info("Nombre de la facultadAux: " + facultadAux.getNombreFacultad());
			this.setAuxNombreValidacion(facultadAux.getNombreFacultad());
			this.facultadAux = facultadAux;
			if(this.facultadAux.getEstadoFacultad().compareTo('1') == 0){
				this.setEstadoFacultadEditar(true);
			}else{
				this.setEstadoFacultadEditar(false);
			}
			this.setBanderaEdit(true);
		}
	}
	
	public String editarFacultad(){
		if(this.getFacultadAux().getNombreFacultad().equals("")){
			this.getFacultadAux().setNombreFacultad(this.getAuxNombreValidacion());
			this.setMensajeRespuesta("El campo nombre no puede estar vacío");
			return null;
		}
		if(this.estadoFacultadEditar){
			this.getFacultadAux().setEstadoFacultad('1');
		}else{
			this.getFacultadAux().setEstadoFacultad('0');
		}
		boolean guardado = fl.modificarFacultad(this.getFacultadAux());
		if(guardado){
			this.setMensajeRespuesta("");
			this.getListadoFacultades();
			return "paginaFacultad";
		}else{
			return "error";
		}
	}

	public String crearFacultad(){
		log.info("Entró a crear");
		if(this.getNombreFacultad().equals("")){
			log.warning("El campo viene vacio");
			this.setMensajeRespuesta("El campo nombre no puede estar vacío");
			return null;
		}
		Set<Carrera> carreras = new HashSet<Carrera>(0);
		Facultad nuevaFacultad = new Facultad(new BigDecimal(0), this.nombreFacultad, '1', carreras);
		log.info("Nueva Facultad => "+nuevaFacultad);
		boolean guardado = fl.crearNuevaFacultad(nuevaFacultad);
		if(guardado){
			this.setMensajeRespuesta("");
			this.listadoFacultades.add(nuevaFacultad);
			return "paginaFacultad";			
		}else{
			return "error";
		}
	}
	
	
	
	public String eliminarFacultad(){
		boolean eliminada = fl.eliminarFacultad(this.getFacultadAux());
		if(eliminada){
			return "paginaFacultad";			
		}else{
			return "error";
		}
	}
	
}
