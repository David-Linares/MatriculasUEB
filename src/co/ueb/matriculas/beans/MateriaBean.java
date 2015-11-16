package co.ueb.matriculas.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jboss.logging.Logger;

import co.ueb.matriculas.logical.CarreraLogical;
import co.ueb.matriculas.logical.MateriaLogical;
import co.ueb.matriculas.model.Carrera;
import co.ueb.matriculas.model.Materia;
import co.ueb.matriculas.model.MateriaMatricula;
import co.ueb.matriculas.util.Constants;



public class MateriaBean implements Serializable {

	//Datos de log
	private final static Logger log = Logger.getLogger("CarreraBean -- ");
	
	private MateriaLogical ml = new MateriaLogical();

	//Atributos de materia para la vista
	private String nombreMateria = "";
	private String mensajeRespuesta = "";
	private List<Materia> listadoMaterias = ml.consultarMaterias();

	//Atributos Auxiliares
	private Materia materiaAux = null;
	private Materia materiaValidacion = null;
	private BigDecimal cantidadCreditos;
	private boolean estadoMateriaEditar = false;
	private boolean mensajeError= false;
	
	//Atributos para carrera
	private Carrera carreraMateria = null;
	private CarreraBean carrerasList = new CarreraBean(); 
	private List<Carrera> listadoCarreras = carrerasList.getListadoCarreras();

	
	//Getters y Setters
	
	public String getNombreMateria() {
		return nombreMateria;
	}

	public void setNombreMateria(String nombreMateria) {
		this.nombreMateria = nombreMateria;
	}

	public BigDecimal getCantidadCreditos() {
		return cantidadCreditos;
	}

	public void setCantidadCreditos(BigDecimal cantidadCreditos) {
		this.cantidadCreditos = cantidadCreditos;
	}

	public Carrera getCarreraMateria() {
		return carreraMateria;
	}

	public void setCarreraMateria(Carrera carreraMateria) {
		this.carreraMateria = carreraMateria;
	}

	public CarreraBean getCarrerasList() {
		return carrerasList;
	}

	public void setCarrerasList(CarreraBean carrerasList) {
		this.carrerasList = carrerasList;
	}

	public List<Materia> getListadoMaterias() {
		return listadoMaterias;
	}

	public void setListadoMaterias(List<Materia> listadoMaterias) {
		this.listadoMaterias = listadoMaterias;
	}

	public Materia getMateriaAux() {
		return materiaAux;
	}

	public Materia getCopiaMateria() {
		return materiaValidacion;
	}

	public void setCopiaMateria(Materia copiaMateria) {
		this.materiaValidacion = copiaMateria;
	}

	public boolean isEstadoMateriaEditar() {
		return estadoMateriaEditar;
	}

	public void setEstadoMateriaEditar(boolean estadoMateriaEditar) {
		this.estadoMateriaEditar = estadoMateriaEditar;
	}

	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}

	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}

	public List<Carrera> getListadoCarreras() {
		return listadoCarreras;
	}

	public void setListadoCarreras(List<Carrera> carreras){
		CarreraBean carrerasList = new CarreraBean(); 
		this.listadoCarreras = carrerasList.getListadoCarreras();
		System.out.println("[MateriaBean] - listadoCarreras => " + this.listadoCarreras);
	}

	public Materia getMateriaValidacion() {
		return materiaValidacion;
	}

	public void setMateriaValidacion(Materia materiaValidacion) {
		this.materiaValidacion = materiaValidacion;
	}

	public boolean isMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(boolean mensajeError) {
		this.mensajeError = mensajeError;
	}

	public void setMateriaAux(Materia materiaAuxEditar){
		log.info(materiaAuxEditar);
		if(materiaAuxEditar != null){
			this.materiaAux = materiaAuxEditar;
			this.setMateriaValidacion(materiaAuxEditar);
			
			if(this.materiaAux.getEstadoMateria().compareTo('1') == 0){
				this.setEstadoMateriaEditar(true);
			}else{
				this.setEstadoMateriaEditar(false);
			}
		}
	}

	
	public String editarMateria(){
		
		if(this.getMateriaAux().getNombreMateria().equals("")){
			this.getMateriaAux().setNombreMateria(this.getCopiaMateria().getNombreMateria());
			this.getMateriaAux().setCreditos(this.getCopiaMateria().getCreditos());
			this.setMensajeRespuesta(Constants.MSJ_NOMBRE_VACIO);
			this.setMensajeError(true);
			return null;
		}
		if(this.estadoMateriaEditar){
			this.getMateriaAux().setEstadoMateria('1');
		}else{
			this.getMateriaAux().setEstadoMateria('0');
		}
		
		String respuesta = ml.modificarMateria(this.getMateriaAux());
		switch (respuesta) {
		case "ok": //Respuesta guardado correctamente
			this.setMensajeRespuesta(Constants.MATERIA_ACTUALIZADA);
			break;
		case "duplicado":
			this.setMensajeRespuesta(Constants.MSJ_NOMBRE_REPETIDO + ": " + this.getMateriaAux().getNombreMateria() + " " + Constants.MSJ_INTENTO );
			this.setMensajeError(true);
			this.getMateriaAux().setNombreMateria(this.getMateriaValidacion().getNombreMateria());
			break;
		default:
			this.setMensajeRespuesta(Constants.MSJ_ERROR_GUARDADO + " " + respuesta + ". " + Constants.MSJ_INTENTO);
			this.setMensajeError(true);
			break;
		}
		return Constants.NAVEGACION_MATERIA;
	}

	public String crearMateria(){
		
		Set<MateriaMatricula> materiaMatricula = new HashSet<MateriaMatricula>(0);
		BigDecimal idMateriaAux = new BigDecimal(1);
		if (listadoMaterias.size()!=0) {
			idMateriaAux = listadoMaterias.get(listadoMaterias.size() - 1).getIdMateria().add(new BigDecimal(1));
		}
		
		Materia nuevaMateria = new Materia(idMateriaAux,
				this.getCarreraMateria(), this.getNombreMateria(),
				this.getCantidadCreditos(), '1', materiaMatricula);


		//	this.getFacultadCarrera().getCarreras().add(nuevaCarrera);
	
		String respuesta = ml.crearNuevaMateria(nuevaMateria);
		switch (respuesta) {
		case "ok": //Respuesta guardado correctamente
			this.setMensajeRespuesta(Constants.MATERIA_CREADA);
			this.setMensajeError(false);
			this.listadoMaterias.add(nuevaMateria);
			break;

		case "duplicado":
			this.setMensajeRespuesta(Constants.MSJ_NOMBRE_REPETIDO + " " + nuevaMateria.getNombreMateria() + ". " + Constants.MSJ_INTENTO );
			this.setMensajeError(true);
			break;
		default:
			this.setMensajeRespuesta(Constants.MSJ_ERROR_GUARDADO + " " + respuesta + ". " + Constants.MSJ_INTENTO);
			this.setMensajeError(true);
			break;
		}
		return Constants.NAVEGACION_MATERIA;

	}
}
