package co.ueb.matriculas.beans;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import co.ueb.matriculas.logical.FacultadLogical;
import co.ueb.matriculas.model.Facultad;

public class FacultadBean {
	
	FacultadLogical fl = new FacultadLogical();
	String nombreFacultad = "";
	
	public String crearFacultad(){
		Set carreras = new HashSet(0);
		Facultad nuevaFacultad = new Facultad(new BigDecimal(0), this.nombreFacultad, '1', carreras);
		fl.crearNuevaFacultad(nuevaFacultad);
		return "paginaFacultad";
	}
}
