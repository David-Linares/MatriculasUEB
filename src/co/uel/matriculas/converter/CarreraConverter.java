package co.uel.matriculas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import co.ueb.matriculas.logical.CarreraLogical;
import co.ueb.matriculas.model.Carrera;

@FacesConverter(forClass= Carrera.class)

public class CarreraConverter implements Converter{
		
	private Carrera carreraConverter;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String carreraString) {
		if(carreraString != null && carreraString.trim().length() > 0){
			System.out.println("getAsObject");
			System.out.println(carreraString);
			int id_carrera = Integer.parseInt(carreraString);
			CarreraLogical cl = new CarreraLogical();
			carreraConverter = cl.getCarreraById(id_carrera); 
			return carreraConverter;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object carreraObject) {
		if(carreraObject != null){
			carreraConverter = (Carrera) carreraObject;
			System.out.println("getAsString");
			System.out.println(carreraObject);
			return carreraConverter.getIdCarrera().toString();
		}
		return null;
	}

}
