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
	FacultadLogical fl = new FacultadLogical();
	String nombreFacultad = "";
	List<Facultad> listadoFacultades = fl.consultarFacultades();
	Facultad facultadAux = null;
	boolean banderaEdit = false;
	boolean estadoFacultadEditar = false;	
		
	public void setFacultadAux(Facultad facultadAux){
		System.out.println("[FacultadBean] - setFacultadAux || Va a cambiar => "+facultadAux);
		if(facultadAux != null){
			this.facultadAux = facultadAux;
			if(this.facultadAux.getEstadoFacultad().compareTo('1') == 0){
				this.setEstadoFacultadEditar(true);
			}else{
				this.setEstadoFacultadEditar(false);
			}
			this.setBanderaEdit(true);
		}
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
	
	public String editarFacultad(){
		System.out.println("[FacultadBean] - editarFacultad || Entró a editar");
		System.out.println("[FacultadBean] - editarFacultad || Nueva Facultad => "+this.getFacultadAux());
		System.out.println("[FacultadBean] - editarFacultad || Estado => "+this.estadoFacultadEditar);
		if(this.estadoFacultadEditar){
			this.getFacultadAux().setEstadoFacultad('1');
		}else{
			this.getFacultadAux().setEstadoFacultad('0');
		}
		boolean guardado = fl.modificarFacultad(this.getFacultadAux());
		if(guardado){
			this.getListadoFacultades();
			return "paginaFacultad";
		}else{
			return "error";
		}
	}

	public String crearFacultad(){
		System.out.println("[FacultadBean] - crearFacultad || Entró a crear");
		Set<Carrera> carreras = new HashSet<Carrera>(0);
		Facultad nuevaFacultad = new Facultad((listadoFacultades.get(listadoFacultades.size() - 1).getIdFacultad().add(new BigDecimal(1))), this.nombreFacultad, '1', carreras);
		System.out.println("[FacultadBean] - crearFacultad || Nueva Facultad => "+nuevaFacultad);
		boolean guardado = fl.crearNuevaFacultad(nuevaFacultad);
		if(guardado){
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
