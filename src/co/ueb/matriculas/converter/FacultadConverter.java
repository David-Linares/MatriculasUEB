package co.ueb.matriculas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import co.ueb.matriculas.logical.CarreraLogical;
import co.ueb.matriculas.logical.FacultadLogical;
import co.ueb.matriculas.model.Carrera;
import co.ueb.matriculas.model.Facultad;

@FacesConverter(forClass = Facultad.class)

public class FacultadConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String idFacultad) {
		System.out.println("getAsObject");
		System.out.println(idFacultad);
		if(idFacultad != null && idFacultad.trim().length() > 0){
			System.out.println("Entró al If GAO");
			System.out.println(idFacultad);
			FacultadLogical fl = new FacultadLogical();
			//Carrera carreraConsultada = cl.getCarreraById(Integer.parseInt(idCarrera));
			//System.out.println(carreraConsultada);
			//return carreraConsultada;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object facultadValue) {
		System.out.println("getAsString");
		System.out.println(facultadValue);
		Facultad facultadParam = (Facultad) facultadValue;
		if (facultadParam != null) {			
			return facultadParam.getIdFacultad().toString();
		}
		return null;
	}
}
