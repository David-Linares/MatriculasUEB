package co.ueb.matriculas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import co.ueb.matriculas.logical.CarreraLogical;
import co.ueb.matriculas.model.Carrera;

@FacesConverter(forClass= Carrera.class)

public class CarreraConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String idCarrera) {
		System.out.println("getAsObject");
		System.out.println(idCarrera);
		if(idCarrera != null && idCarrera.trim().length() > 0){
			System.out.println("Entró al If GAO");
			System.out.println(idCarrera);
			CarreraLogical cl = new CarreraLogical();
			Carrera carreraConsultada = cl.getCarreraById(Integer.parseInt(idCarrera));
			System.out.println(carreraConsultada);
			return carreraConsultada;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object carreraValue) {
		System.out.println("getAsString");
		System.out.println(carreraValue);
		Carrera carreraParam = (Carrera) carreraValue;
		if (carreraParam != null) {			
			return carreraParam.getIdCarrera().toString();
		}
		return null;
	}

}