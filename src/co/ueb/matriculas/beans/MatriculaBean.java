package co.ueb.matriculas.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;

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
	private Matricula datosMatricula = ml.obtenerDatosMatricula(usuarioActual.getIdPersona());
	private int creditosPermitidos = Double.parseDouble(promedio) > 3.5 ? 16 : 8;
	private int creditosMatriculados;
	private String mensajeRespuesta = "";
	private Boolean mensajeError = false;
	private String materiasHidden;	

	public String getMateriasHidden() {
		return materiasHidden;
	}

	public void setMateriasHidden(String materiasHidden) {
		this.materiasHidden = materiasHidden;
	}

	public void valueChangeMethod(Materia materiaSeleccionada){
//		this.setCreditosMatriculados(this.creditosMatriculados + creditos); 
		log.info("entro");
		log.info(materiaSeleccionada);
		materiasMatricula.add(materiaSeleccionada);
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
		this.creditosMatriculados = 0;
		for (Iterator iterator = materiasMatricula.iterator(); iterator.hasNext();) {
			Materia materia = (Materia) iterator.next();
			this.creditosMatriculados += Integer.parseInt(materia.getCreditos().toString());
		}
		log.info("Créditos matriculados = "+this.creditosMatriculados);
		try{
			if(Double.parseDouble(this.promedio) < 3.5){
				if(this.creditosMatriculados > (Constants.CREDITOS_PERMITIDOS / 2)){
					this.setMensajeRespuesta(Constants.MSJ_CREDITOS_EXCEDIDOS+" "+(Constants.CREDITOS_PERMITIDOS / 2));
					this.setMensajeError(true);
					return Constants.NAVEGACION_MATRICULA;
				}
			}else{
				if(this.creditosMatriculados > Constants.CREDITOS_PERMITIDOS){
					this.setMensajeRespuesta(Constants.MSJ_CREDITOS_EXCEDIDOS+" "+Constants.CREDITOS_PERMITIDOS);
					this.setMensajeError(true);
					return Constants.NAVEGACION_MATRICULA;
				}
			}
			log.info("Va a enviar el objeto");
			log.info(this.materiasMatricula);
			String msjRespuesta = ml.crearMatricula(this.materiasMatricula, this.usuarioActual.getIdPersona());
			switch (msjRespuesta) {
			case "ok":
				int cod_matricula = Integer.parseInt((String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("codMatricula"));
				this.setMensajeError(false);
				this.setMensajeRespuesta(Constants.MATRICULA_CREADA);
				Set<MateriaMatricula> materias_matriculas = new HashSet<MateriaMatricula>();
				for (Iterator iterator = materiasMatricula.iterator(); iterator.hasNext();) {
					Materia materia = (Materia) iterator.next();
					MateriaMatricula materiaMatriculada = new MateriaMatricula(null, new Materia(materia.getIdMateria()), new Matricula(new BigDecimal(cod_matricula)));
					materias_matriculas.add(materiaMatriculada);
				}
				datosMatricula = ml.obtenerDatosMatricula(usuarioActual.getIdPersona());
				datosMatricula.setMateriaMatriculas(materias_matriculas);
				this.setListadoMateriasMatriculadas(ml.obtenerMateriasMatriculadas(usuarioActual.getIdPersona()));
				break;
			default:
				this.setMensajeError(true);
				this.setMensajeRespuesta(Constants.MSJ_ERROR_GUARDADO + " " + msjRespuesta + ". "+Constants.MSJ_INTENTO);
				break;
			}
			return Constants.NAVEGACION_MATRICULA;
		}catch(Exception e){
			this.setMensajeRespuesta(Constants.MSJ_USUARIO_NO_EXISTENTE+" ");
			this.setMensajeError(true);
			log.error(e);
			e.printStackTrace();
		}
		
		return "";
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
