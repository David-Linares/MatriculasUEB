/**
 * 
 */
package co.ueb.matriculas.converter;

import java.math.BigDecimal;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import co.ueb.matriculas.logical.FacultadLogical;
import co.ueb.matriculas.model.Facultad;

/**
 * @author davidlinares
 * @date Nov 18, 2015
 */
public class FacultadConverter implements Converter {

	private FacultadLogical facultadLogical = new FacultadLogical();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null ||value.isEmpty()) {
			return null;
		}
		System.out.println(value);
		return facultadLogical.getFacultadById(new BigDecimal(value));
	}

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
			if (value == null) {
				return "";
			}
			if (value instanceof Facultad) {
				return String.valueOf(((Facultad) value).getIdFacultad());
			}
			return null;
	}

}
