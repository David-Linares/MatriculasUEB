package co.ueb.matriculas.beans;

import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import co.ueb.matriculas.logical.FacultadLogical;
import co.ueb.matriculas.model.Facultad;

public class FacultadBean {
	
	FacultadLogical fl = new FacultadLogical();
	String nombreFacultad = "";
	List<Facultad> listadoFacultades = fl.consultarFacultades();

	public List<Facultad> getListadoFacultades() {
		return listadoFacultades;
	}

	public void setListadoFacultades() {
		this.listadoFacultades = fl.consultarFacultades();
	}

	public String getNombreFacultad() {
		return nombreFacultad;
	}

	public void setNombreFacultad(String nombreFacultad) {
		this.nombreFacultad = nombreFacultad;
	}

	public String crearFacultad(){
		Set carreras = new HashSet(0);
		Facultad nuevaFacultad = new Facultad(new BigDecimal(0), this.nombreFacultad, '1', carreras);
		boolean guardado = fl.crearNuevaFacultad(nuevaFacultad);
		if(guardado){
			this.setListadoFacultades();
			return "paginaFacultad";			
		}else{
			return "error";
		}
	}
	
}
