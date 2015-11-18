/**
 * 
 */
package co.ueb.matriculas.converter;

import java.math.BigDecimal;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import co.ueb.matriculas.logical.CarreraLogical;
import co.ueb.matriculas.model.Carrera;

/**
 * @author davidlinares
 * @date Nov 18, 2015
 */
public class CarreraConverter implements Converter {

	private CarreraLogical carreraLogical = new CarreraLogical();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null ||value.isEmpty()) {
			return null;
		}
		System.out.println(value);
		return carreraLogical.getCarreraById(new BigDecimal(value));
	}

	
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
			if (value == null) {
				return "";
			}
			if (value instanceof Carrera) {
				return String.valueOf(((Carrera) value).getIdCarrera());
			}
			return null;
	}

}
