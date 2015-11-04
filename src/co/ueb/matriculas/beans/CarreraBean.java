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
	private static final long serialVersionUID = -6396704435134939521L;
	private final static Logger log = Logger.getLogger("CarreraBean -- ");
	String nombreCarrera = "";
	BigDecimal totalCreditos;
	Facultad facultadCarrera = null;
	FacultadBean facultadesList = new FacultadBean(); 
	CarreraLogical cl = new CarreraLogical();
	List<Carrera> listadoCarreras = cl.consultarCarreras();
	List<Facultad> listadoFacultades = facultadesList.getListadoFacultades();
	Carrera carreraAux = null;
	Carrera copiaCarrera = null;
	boolean banderaEdit = false;
	boolean estadoCarreraEditar = false;
	String mensajeRespuesta = "";
	
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

	public Carrera getCopiaCarrera() {
		return copiaCarrera;
	}

	public void setCopiaCarrera(Carrera copiaCarrera) {
		this.copiaCarrera = copiaCarrera;
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
	
	public void setCarreraAux(Carrera carreraAux){
		System.out.println("[CarreraBean] - setCarreraAux ||a Va a cambiar => "+ carreraAux);
		if(carreraAux != null){
			System.out.println("[CarreraBean] CarreraAux NO esta vacio");
			System.out.println("[CarreraBean] El nombre de la CarreraAux es" + carreraAux.getNombreCarrera());
			//this.setAuxNombreValidacion(carreraAux.getNombreCarrera());
			this.carreraAux = carreraAux;
			this.copiaCarrera = carreraAux;
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
		System.out.println("[CarreraBean] Entro a editarCarrera");
		if(this.getCarreraAux().getNombreCarrera().equals("")){
			this.getCarreraAux().setNombreCarrera(this.getCopiaCarrera().getNombreCarrera());
			this.getCarreraAux().setTotalCreditos(this.getCopiaCarrera().getTotalCreditos());
			this.setMensajeRespuesta("El campo nombre no puede estar vacio");
			return null;
		}
		if(this.estadoCarreraEditar){
			this.getCarreraAux().setEstadoCarrera('1');
		}else{
			this.getCarreraAux().setEstadoCarrera('0');
		}
		System.out.println("[CarreraBean] editarCarrera --> getCarreraAux " + this.getCarreraAux().toString());
	//	log.info(this.getCarreraAux().toString());
		boolean guardado = cl.modificarCarrera(this.getCarreraAux());
		if(guardado){
			this.setMensajeRespuesta("");
			this.getListadoCarreras();
			return "paginaCarrera";
		}else{
			return "error";
		}
	}

	public String crearCarrera(){
		System.out.println("[CarreraBean] crearCarrera || Entro a crear");
		if(this.getNombreCarrera().equals("")){
			System.out.println("[CarreraBean] vacio el nombre");
			this.setMensajeRespuesta("El campo nombre no puede estar vacio");
			return null;
		}
		Set<Materia> materias = new HashSet<Materia>(0);
		Set<Persona> personas = new HashSet<Persona>(0);
		System.out.println("[CarreraBean] Entra a construir la carrera");
		System.out.println("[CarreraBean] La facultad es " + getFacultadCarrera());
		BigDecimal idCarreraAux = new BigDecimal(1);
		if (listadoCarreras.size()!=0) {
			idCarreraAux = listadoCarreras.get(listadoCarreras.size() - 1).getIdCarrera().add(new BigDecimal(1));
		}
		Carrera nuevaCarrera = new Carrera(idCarreraAux, this.getFacultadCarrera(), this.nombreCarrera, this.totalCreditos, '1', materias, personas);
	//	this.getFacultadCarrera().getCarreras().add(nuevaCarrera);
		System.out.println("[CarreraBean] crearCarrera || Nueva Carrera => "+ nuevaCarrera);
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