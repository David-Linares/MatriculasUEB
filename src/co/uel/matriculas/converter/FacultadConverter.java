package co.uel.matriculas.converter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.hibernate.engine.config.spi.ConfigurationService.Converter;

import co.ueb.matriculas.model.Facultad;

@ManagedBean
@RequestScoped
@FacesConverter(forClass = Facultad.class)
public class FacultadConverter implements Converter{

	private Facultad facultad;
	 
	public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        if (submittedValue == null || submittedValue.isEmpty()) {
            return null;
        }

        try {
            return facultad.getNombreFacultad();
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(String.format("%s is not a valid User ID", submittedValue)), e);
        }
    }
	
	public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
	        if (modelValue == null) {
	            return "";
	        }

	        if (modelValue instanceof Facultad) {
	            return String.valueOf(((Facultad) modelValue).getIdFacultad());
	        } else {
	            throw new ConverterException(new FacesMessage(String.format("%s is not a valid User", modelValue)));
	        }
	    }

	@Override
	public Object convert(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
