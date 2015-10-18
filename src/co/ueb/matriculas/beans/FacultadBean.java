package co.ueb.matriculas.beans;

import java.util.List;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.faces.context.FacesContext;

import co.ueb.matriculas.logical.FacultadLogical;
import co.ueb.matriculas.model.Carrera;
import co.ueb.matriculas.model.Facultad;

public class FacultadBean<listadoFacultades> {
	
	FacultadLogical fl = new FacultadLogical();
	String nombreFacultad = "";
	List<Facultad> listadoFacultades = fl.consultarFacultades();
	Facultad editarFacultad = null;
	boolean banderaEdit = false;
	boolean estadoFacultadEditar = false;
	
		
	public void setEditarFacultad(Facultad facultadEdit){
		if(facultadEdit != null){
			System.out.println("[setEditarFacultad] => " + facultadEdit);
			this.editarFacultad = facultadEdit;
			if(this.editarFacultad.getEstadoFacultad().compareTo('1') == 0){
				this.setEstadoFacultadEditar(true);
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


	public Facultad getEditarFacultad(){
		return this.editarFacultad;
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

	public String crearFacultad(){
		System.out.println("[crearFacultad] // Estado => "+this.isBanderaEdit());
		System.out.println("[crearFacultad] // Facultad en editar => "+this.getEditarFacultad());
		if(this.isBanderaEdit()){
			if(this.estadoFacultadEditar){
				this.getEditarFacultad().setEstadoFacultad('1');	
			}else{
				this.getEditarFacultad().setEstadoFacultad('0');
			}
			boolean guardado = fl.modificarFacultad(this.getEditarFacultad());
			if(guardado){
				System.out.println("editado");
				this.getListadoFacultades();
				return "paginaFacultad";			
			}else{
				return "error";
			}	
		}else{
			Set<Carrera> carreras = new HashSet<Carrera>(0);
			Facultad nuevaFacultad = new Facultad((listadoFacultades.get(listadoFacultades.size() - 1).getIdFacultad().add(new BigDecimal(1))), this.nombreFacultad, '1', carreras);
			boolean guardado = fl.crearNuevaFacultad(nuevaFacultad);
			if(guardado){
				this.listadoFacultades.add(nuevaFacultad);
				return "paginaFacultad";			
			}else{
				return "error";
			}			
		}
	}
	
	public String eliminarFacultad(Facultad eFacultad){
		boolean eliminada = fl.eliminarFacultad(eFacultad);
		if(eliminada){
			return "paginaFacultad";			
		}else{
			return "error";
		}
	}
	
}
