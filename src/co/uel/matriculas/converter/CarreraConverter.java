package co.uel.matriculas.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import co.ueb.matriculas.model.Carrera;

@FacesConverter(forClass= Carrera.class)

public class CarreraConverter implements Converter{

	private Carrera carrera;
	 
	public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        if (submittedValue == null || submittedValue.isEmpty()) {
            return null;
        }

        try {
            return carrera.getNombreCarrera();
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(String.format("%s is not a valid User ID", submittedValue)), e);
        }
    }
	
	 public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
	        if (modelValue == null) {
	            return "";
	        }

	        if (modelValue instanceof Carrera) {
	            return String.valueOf(((Carrera) modelValue).getIdCarrera());
	        } else {
	            throw new ConverterException(new FacesMessage(String.format("%s is not a valid User", modelValue)));
	        }
	    }

	public Object convert(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
