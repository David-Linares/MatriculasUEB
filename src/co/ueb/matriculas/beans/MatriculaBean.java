package co.ueb.matriculas.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.jboss.logging.Logger;

import co.ueb.matriculas.logical.EstudianteLogical;
import co.ueb.matriculas.logical.MateriaLogical;
import co.ueb.matriculas.logical.MatriculaLogical;
import co.ueb.matriculas.model.Materia;
import co.ueb.matriculas.model.MateriaMatricula;
import co.ueb.matriculas.model.Matricula;
import co.ueb.matriculas.model.Persona;
import co.ueb.matriculas.util.Constants;

public class MatriculaBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4982995413040238168L;
	private static final Logger log = Logger.getLogger(MatriculaBean.class);
	EstudianteLogical el = new EstudianteLogical();
	MatriculaLogical ml = new MatriculaLogical();
	MateriaLogical mtl = new MateriaLogical();
	Persona usuarioActual = (Persona) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
	private String promedio = el.obtenerPromedio(usuarioActual.getIdPersona());
	private List<Materia> materiasListado = mtl.consultarMaterias();
	private List<Materia> materiasMatricula = new ArrayList<Materia>();
	private List<MateriaMatricula> listadoMateriasMatriculadas = ml.obtenerMateriasMatriculadas(usuarioActual.getIdPersona()); 
	private Map<Materia,Boolean> checkMap = new HashMap<Materia,Boolean>();
	private Matricula datosMatricula = ml.obtenerDatosMatricula(usuarioActual.getIdPersona());
	private int creditosPermitidos = Double.parseDouble(promedio) > 3.5 ? 16 : 8;
	private int creditosMatriculados;
	private String mensajeRespuesta = "";
	private Boolean mensajeError = false;
	

	public void valueChangeMethod(Materia materiaselect){
//		this.setCreditosMatriculados(this.creditosMatriculados + creditos); 
		log.info("entro");
		log.info(materiaselect);
		this.materiasMatricula.add(materiaselect);
		log.info(this.materiasMatricula);
	}
	
	public Map<Materia, Boolean> getCheckMap() {
		for (Iterator iterator = materiasListado.iterator(); iterator.hasNext();) {
			Materia materia = (Materia) iterator.next();
			log.info(materia);
			checkMap.put(materia, Boolean.FALSE);
		}
		return checkMap;
	}
	
	public int getCreditosPermitidos() {
		return creditosPermitidos;
	}

	public void setCreditosPermitidos(int creditosPermitidos) {
		this.creditosPermitidos = creditosPermitidos;
	}

	public String getPromedio() {
		return promedio;
	}

	public void setPromedio(String promedio) {
		this.promedio = promedio;
	}

	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}

	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}

	public Matricula getDatosMatricula() {
		return datosMatricula;
	}

	public void setDatosMatricula(Matricula datosMatricula) {
		this.datosMatricula = datosMatricula;
	}

	/**
	 * @return the materiasListado
	 */
	public List<Materia> getMateriasListado() {
		return materiasListado;
	}

	/**
	 * @param materiasListado the materiasListado to set
	 */
	public void setMateriasListado(List<Materia> materiasListado) {
		this.materiasListado = materiasListado;
	}

	/**
	 * @return the creditosMatriculados
	 */
	public int getCreditosMatriculados() {
		return creditosMatriculados;
	}

	/**
	 * @param creditosMatriculados the creditosMatriculados to set
	 */
	public void setCreditosMatriculados(int creditosMatriculados) {
		this.creditosMatriculados = creditosMatriculados;
	}
	
	public String hacerMatricula(){
		log.info("##Se prepara para hacer la matricula##");
		
		for (Iterator iterator = materiasMatricula.iterator(); iterator.hasNext();) {
			Materia materia = (Materia) iterator.next();
			this.creditosMatriculados += Integer.parseInt(materia.getCreditos().toString());
		}
		try{
			if(Double.parseDouble(this.promedio) < 3.5){
				if(this.creditosMatriculados > (Constants.CREDITOS_PERMITIDOS / 2)){
					this.setMensajeRespuesta(Constants.MSJ_CREDITOS_EXCEDIDOS+" "+(Constants.CREDITOS_PERMITIDOS / 2));
					this.setMensajeError(true);
					return Constants.NAVEGACION_MATRICULA;
				}
			}
			
			ml.crearMatricula(this.materiasMatricula, this.usuarioActual.getIdPersona());
			
		}catch(Exception e){
			this.setMensajeRespuesta(Constants.MSJ_USUARIO_NO_EXISTENTE+" ");
			this.setMensajeError(true);
			log.error(e);
			e.printStackTrace();
		}
		
		return Constants.NAVEGACION_MATRICULA;
	}

	/**
	 * @return the mensajeError
	 */
	public Boolean getMensajeError() {
		return mensajeError;
	}

	/**
	 * @param mensajeError the mensajeError to set
	 */
	public void setMensajeError(Boolean mensajeError) {
		this.mensajeError = mensajeError;
	}

	/**
	 * @return the listadoMateriasMatriculadas
	 */
	public List<MateriaMatricula> getListadoMateriasMatriculadas() {
		return listadoMateriasMatriculadas;
	}

	/**
	 * @param listadoMateriasMatriculadas the listadoMateriasMatriculadas to set
	 */
	public void setListadoMateriasMatriculadas(
			List<MateriaMatricula> listadoMateriasMatriculadas) {
		this.listadoMateriasMatriculadas = listadoMateriasMatriculadas;
	}	
	
}
