package co.uel.matriculas.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import co.ueb.matriculas.logical.CarreraLogical;
import co.ueb.matriculas.model.Carrera;

@FacesConverter(forClass= Carrera.class)

public class CarreraConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if(arg2 != null && arg2.trim().length() > 0){
			System.out.println(arg2);
			CarreraLogical cl = new CarreraLogical();
			return cl.getCarreraByName(arg2);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		// TODO Auto-generated method stub
		return null;
	}

}
