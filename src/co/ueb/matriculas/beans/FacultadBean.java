package co.ueb.matriculas.beans;

import java.util.List;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import co.ueb.matriculas.logical.FacultadLogical;
import co.ueb.matriculas.model.Carrera;
import co.ueb.matriculas.model.Facultad;

public class FacultadBean {
	
	FacultadLogical fl = new FacultadLogical();
	String nombreFacultad = "";
	List<Facultad> listadoFacultades = fl.consultarFacultades();
	Facultad editarFacultad = null;
	
	public String setEditarFacultad(Facultad facultadEdit){
		this.editarFacultad = facultadEdit;
		return "";
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
