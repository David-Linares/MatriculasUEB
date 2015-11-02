package co.ueb.matriculas.beans;

import java.util.List;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import javax.faces.model.SelectItem;

import co.ueb.matriculas.logical.CarreraLogical;
import co.ueb.matriculas.model.Carrera;
import co.ueb.matriculas.model.Facultad;
import co.ueb.matriculas.model.Materia;
import co.ueb.matriculas.model.Persona;

public class CarreraBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9166065171751439973L;
	String nombreCarrera = "";
	BigDecimal totalCreditos;
	Facultad facultadCarrera = null;
	FacultadBean facultadesList = new FacultadBean(); 
	CarreraLogical cl = new CarreraLogical();
	List<Carrera> listadoCarreras = cl.consultarCarreras();
	List<Facultad> listadoFacultades = facultadesList.getListadoFacultades();
	List<SelectItem> facultadSelect;
	Carrera carreraAux = null;
	boolean banderaEdit = false;
	boolean estadoCarreraEditar = false;
	String mensajeRespuesta = "";
	String auxNombreValidacion = "";
	
	public FacultadBean getFacultadesList() {
		return facultadesList;
	}

	public void setFacultadesList(FacultadBean facultadesList) {
		this.facultadesList = facultadesList;
	}

	public Facultad getFacultadCarrera() {
		return facultadCarrera;
	}
	
	public void setFacultadCarrera(Facultad facultadCarrera) {
		this.facultadCarrera = facultadCarrera;
	}
	
	public List<Facultad> getListadoFacultades() {
		return listadoFacultades;
	}
	
	public void setListadoFacultades(List<Facultad> facultades){
		FacultadBean facultadesList = new FacultadBean(); 
		this.listadoFacultades = facultadesList.getListadoFacultades();
		System.out.println("[CarreraBean] - listadoFacultades => " + this.listadoFacultades);
	}

	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}

	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}
	
	public String getAuxNombreValidacion() {
		return auxNombreValidacion;
	}

	public void setAuxNombreValidacion(String auxNombreValidacion) {
		this.auxNombreValidacion = auxNombreValidacion;
	}

	
	public boolean isEstadoCarreraEditar() {
		return estadoCarreraEditar;
	}

	public void setEstadoCarreraEditar(boolean estadoCarreraEditar) {
		this.estadoCarreraEditar = estadoCarreraEditar;
	}
	
	public boolean isBanderaEdit() {
		return banderaEdit;
	}

	public void setBanderaEdit(boolean banderaEdit) {
		this.banderaEdit = banderaEdit;
	}

	public Carrera getCarreraAux(){
		return this.carreraAux;
	}


	public List<Carrera> getListadoCarreras() {
		return listadoCarreras;
	}

	public void setListadoCarreras(List<Carrera> carreras) {
		this.listadoCarreras = cl.consultarCarreras();
	}

	public String getNombreCarrera() {
		return nombreCarrera;
	}

	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}
	
	public List<SelectItem> getFacultadSelect() {
		if(this.facultadSelect == null){
			Facultad facultad = new Facultad();
	//		List<Facultad> listaFacultades = facultad.listar();
		}
		return facultadSelect;
	}

	public void setFacultadSelect(List<SelectItem> facultadSelect) {
		this.facultadSelect = facultadSelect;
	}

	public void setCarreraAux(Carrera carreraAux){
		System.out.println("[CarreraBean] - setCarreraAux || Va a cambiar => "+carreraAux);
		if(carreraAux != null){
			System.out.println(carreraAux.getNombreCarrera());
			this.setAuxNombreValidacion(carreraAux.getNombreCarrera());
			this.carreraAux = carreraAux;
			if(this.carreraAux.getEstadoCarrera().compareTo('1') == 0){
				this.setEstadoCarreraEditar(true);
			}else{
				this.setEstadoCarreraEditar(false);
			}
		}
	}

	public BigDecimal getTotalCreditos() {
		return totalCreditos;
	}

	public void setTotalCreditos(BigDecimal totalCreditos) {
		this.totalCreditos = totalCreditos;
	}

	public String editarCarrera(){
		System.out.println("[CarreraBean] - editarCarrera || Entró a editar");
		System.out.println("[CarreraBean] - editarCarrera || Nueva Carrera => "+this.getCarreraAux());
		System.out.println("[CarreraBean] - editarCarrera || Estado => "+this.estadoCarreraEditar);
		System.out.println("[CarreraBean] - editarCarrera || Campo nombre => "+this.getCarreraAux().getNombreCarrera().equals(""));
		if(this.getCarreraAux().getNombreCarrera().equals("")){
			this.setMensajeRespuesta("El campo nombre no puede estar vacío");
			System.out.println("[CarreraBean] - editarCarrera || "+this.getMensajeRespuesta());
			return null;
		}
		if(this.estadoCarreraEditar){
			this.getCarreraAux().setEstadoCarrera('1');
		}else{
			this.getCarreraAux().setEstadoCarrera('0');
		}
		boolean guardado = cl.modificarCarrera(this.getCarreraAux());
		if(guardado){
			this.getListadoCarreras();
			return "paginaCarrera";
		}else{
			return "error";
		}
	}

	public String crearCarrera(){
		System.out.println("[CarreraBean] - crearCarrera || Entró a crear");
		if(this.getNombreCarrera().equals("")){
			System.out.println("Vacio el nombre");
			this.setMensajeRespuesta("El campo nombre no puede estar vacío");
			return null;
		}
		Set<Materia> materias = new HashSet<Materia>(0);
		Set<Persona> personas = new HashSet<Persona>(0);
		System.out.println("Entra a construis la carrera");
		System.out.println("La facultad es " + getFacultadCarrera());
		Carrera nuevaCarrera = new Carrera(new BigDecimal(0), this.getFacultadCarrera(), this.nombreCarrera, this.totalCreditos, '1', personas, materias);
		System.out.println("[CarreraBean] - crearCarrera || Nueva Carrera => "+ nuevaCarrera);
		boolean guardado = cl.crearNuevaCarrera(nuevaCarrera);
		if(guardado){
			this.setMensajeRespuesta("");
			this.listadoCarreras.add(nuevaCarrera);
			return "paginaCarrera";			
		}else{
			return "error";
		}
	}
	
	
	
	public String eliminarCarrera(){
		boolean eliminada = cl.eliminarCarrera(this.getCarreraAux());
		if(eliminada){
			return "paginaCarrera";			
		}else{
			return "error";
		}
	}
}