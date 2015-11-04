package co.uel.matriculas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import co.ueb.matriculas.logical.FacultadLogical;
import co.ueb.matriculas.model.Facultad;

@FacesConverter(forClass = Facultad.class)
public class FacultadConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if(arg2 != null && arg2.trim().length() > 0){
			System.out.println("[FacultadConverter] agumento2" + arg2) ;
	//		System.out.println(arg2);
			FacultadLogical fl = new FacultadLogical();
			return fl.getFacultadByName(arg2);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		// TODO Auto-generated method stub
		return null;
	}
}
