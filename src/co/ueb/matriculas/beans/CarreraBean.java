package co.ueb.matriculas.beans;

import java.util.List;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.faces.model.SelectItem;

import org.jboss.logging.Logger;

import co.ueb.matriculas.logical.CarreraLogical;
import co.ueb.matriculas.logical.FacultadLogical;
import co.ueb.matriculas.model.Carrera;
import co.ueb.matriculas.model.Facultad;
import co.ueb.matriculas.model.Materia;
import co.ueb.matriculas.model.Persona;
import co.ueb.matriculas.util.Constants;

public class CarreraBean implements Serializable {

	/**
	 * 
	 */
	// Datos del log
	private final static Logger log = Logger.getLogger("CarreraBean -- ");
	private CarreraLogical cl = new CarreraLogical();
	private FacultadLogical fl = new FacultadLogical();

	// Atributos para la vista
	private String mensajeRespuesta = "";
	private List<Carrera> listadoCarreras = cl.consultarCarreras();

	// Atributos auxiliares
	private Carrera carreraAux = null;
	private Carrera carreraValidacion = null;
	private Carrera nuevaCarrera = new Carrera();
	private boolean estadoCarreraEditar = false;
	private boolean mensajeError = false;

	// Atributos para Facultad
	private Facultad facultadCarrera = null;
	private FacultadBean facultadesList = new FacultadBean();
	private List<Facultad> listadoFacultades = facultadesList
			.getListadoFacultades();
	private List<SelectItem> listFacultadSelect;

	private static final long serialVersionUID = -6396704435134939521L;

	// Atributos de Carrera
	private String nombreCarrera = "";
	private BigDecimal totalCreditos;

	// Atributos para matricula
	private Materia materiaCarrera = null;
	private Persona personaCarrera = null;

	// Getters y Setters

	public Carrera getNuevaCarrera() {
		return nuevaCarrera;
	}

	public void setNuevaCarrera(Carrera nuevaCarrera) {
		this.nuevaCarrera = nuevaCarrera;
	}

	public FacultadBean getFacultadesList() {
		return facultadesList;
	}

	public void setFacultadesList(FacultadBean facultadesList) {
		this.facultadesList = facultadesList;
	}

	public Facultad getFacultadCarrera() {
		return facultadCarrera;
	}

	public void setFacultadCarrera(Facultad facultadCarrera) {
		this.facultadCarrera = facultadCarrera;
	}

	public List<SelectItem> getListFacultadSelect() {
		return listFacultadSelect;
	}

	public List<Facultad> getListadoFacultades() {
		return listadoFacultades;
	}

	public void setListadoFacultades(List<Facultad> facultades) {
		FacultadBean facultadesList = new FacultadBean();
		this.listadoFacultades = facultadesList.getListadoFacultades();
		System.out.println("[CarreraBean] - listadoFacultades => "
				+ this.listadoFacultades);
	}

	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}

	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}

	public boolean isEstadoCarreraEditar() {
		return estadoCarreraEditar;
	}

	public void setEstadoCarreraEditar(boolean estadoCarreraEditar) {
		this.estadoCarreraEditar = estadoCarreraEditar;
	}

	public Carrera getCarreraAux() {
		return this.carreraAux;
	}

	public void setListFacultadSelect(List<SelectItem> listFacultadSelect) {
		this.listFacultadSelect = listFacultadSelect;
	}

	public List<Carrera> getListadoCarreras() {
		return listadoCarreras;
	}

	public void setListadoCarreras(List<Carrera> carreras) {
		this.listadoCarreras = cl.consultarCarreras();
	}

	public String getNombreCarrera() {
		return nombreCarrera;
	}

	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}

	public Materia getMateriaCarrera() {
		return materiaCarrera;
	}

	public void setMateriaCarrera(Materia materiaCarrera) {
		this.materiaCarrera = materiaCarrera;
	}

	public Persona getPersonaCarrera() {
		return personaCarrera;
	}

	public void setPersonaCarrera(Persona personaCarrera) {
		this.personaCarrera = personaCarrera;
	}

	public boolean isMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(boolean mensajeError) {
		this.mensajeError = mensajeError;
	}

	public Carrera getCarreraValidacion() {
		return carreraValidacion;
	}

	public void setCarreraValidacion(Carrera carreraValidacion) {
		this.carreraValidacion = carreraValidacion;
	}

	// Constructor CarreraBean
	public CarreraBean() {
		this.setListFacultadSelect(new ArrayList<SelectItem>());
		List<Facultad> listFacultades = fl.consultarFacultades();

		if (listFacultades != null && !listFacultades.isEmpty()) {
			SelectItem itemFacultad;
			for (Facultad facultadList : listFacultades) {
				itemFacultad = new SelectItem(facultadList.getIdFacultad(),
						facultadList.getNombreFacultad());
				listFacultadSelect.add(itemFacultad);
			}
		}

	}

	public BigDecimal getTotalCreditos() {
		return totalCreditos;
	}

	public void setTotalCreditos(BigDecimal totalCreditos) {
		this.totalCreditos = totalCreditos;
	}

	// Metodo para obtener una copia de la carrera seleccionada
	public void setCarreraAux(Carrera carreraAuxEditar) {
		log.info(carreraAuxEditar);
		if (carreraAuxEditar != null) {
			this.carreraAux = carreraAuxEditar;
			this.setCarreraValidacion(carreraAuxEditar);

			if (this.carreraAux.getEstadoCarrera().compareTo('1') == 0) {
				this.setEstadoCarreraEditar(true);
			} else {
				this.setEstadoCarreraEditar(false);
			}
		}
	}

	// Metodo para editar Carrera
	public String editarCarrera() {
		log.info("##Entró a Editar Carrera##");
		if (!validarCampos(this.getCarreraAux())) {
			this.setMensajeError(true);
			this.setMensajeRespuesta(Constants.MSJ_CAMPOS_VACIOS);
			log.info("Se quedo en validaciones");
			return Constants.NAVEGACION_CARRERA;
		}
		log.info("Paso validaciones");
		if (this.estadoCarreraEditar) {
			this.getCarreraAux().setEstadoCarrera('1');
		} else {
			this.getCarreraAux().setEstadoCarrera('0');
		}
		log.info("Pasó el if de estado carrera");
		this.getCarreraAux().setFacultad(this.getFacultadCarrera());
		log.info("Objeto que edita ==> " + this.getCarreraAux());
		String respuesta = cl.modificarCarrera(this.getCarreraAux());
		log.info("[CarreraBean] editarCarrera - la respuesta es:" + respuesta);
		switch (respuesta) {
		case "ok": // Respuesta guardado correctamente
			this.setMensajeRespuesta(Constants.CARRERA_ACTUALIZADA);
			this.setMensajeError(false);
			break;
		case "duplicado":
			this.setMensajeRespuesta(Constants.MSJ_NOMBRE_REPETIDO + ": "
					+ this.getCarreraAux().getNombreCarrera() + " "
					+ Constants.MSJ_INTENTO);
			this.setMensajeError(true);
			this.getCarreraAux().setNombreCarrera(
					this.getCarreraValidacion().getNombreCarrera());
			break;
		default:
			this.setMensajeRespuesta(Constants.MSJ_ERROR_GUARDADO + " "
					+ respuesta + ". " + Constants.MSJ_INTENTO);
			this.setMensajeError(true);
			break;
		}
		return Constants.NAVEGACION_CARRERA;
	}

	// Metodo para crear carrera
	public String crearCarrera() {
		if (!validarCampos(this.getNuevaCarrera())) {
			this.setMensajeError(true);
			this.setMensajeRespuesta(Constants.MSJ_CAMPOS_VACIOS);
			return Constants.NAVEGACION_CARRERA;
		}
		log.info("Entra a crear Carrera");
		Set<Materia> materias = new HashSet<Materia>(0);
		Set<Persona> personas = new HashSet<Persona>(0);
		BigDecimal idCarreraAux = new BigDecimal(1);
		if (listadoCarreras.size() != 0) {
			idCarreraAux = listadoCarreras.get(listadoCarreras.size() - 1)
					.getIdCarrera().add(new BigDecimal(1));
		}
		this.getNuevaCarrera().setIdCarrera(idCarreraAux);
		this.getNuevaCarrera().setFacultad(this.getFacultadCarrera());
		// this.getFacultadCarrera().getCarreras().add(nuevaCarrera);
		log.info("El objeto que envía es " + this.getNuevaCarrera());
		String respuesta = cl.crearNuevaCarrera(this.getNuevaCarrera());
		switch (respuesta) {
		case "ok": // Respuesta guardado correctamente
			this.setMensajeRespuesta(Constants.CARRERA_CREADA);
			this.setMensajeError(false);
			this.listadoCarreras.add(nuevaCarrera);
			break;

		case "duplicado":
			this.setMensajeRespuesta(Constants.MSJ_NOMBRE_REPETIDO + " "
					+ nuevaCarrera.getNombreCarrera() + ". "
					+ Constants.MSJ_INTENTO);
			this.setMensajeError(true);
			break;
		default:
			this.setMensajeRespuesta(Constants.MSJ_ERROR_GUARDADO + " "
					+ respuesta + ". " + Constants.MSJ_INTENTO);
			this.setMensajeError(true);
			break;
		}
		return Constants.NAVEGACION_CARRERA;

	}

	// Metodo que valida si estan vacios los campos
	public boolean validarCampos(Carrera carreraCampos) {
		log.info("Validar Campos");
		log.info(carreraCampos);
		if (carreraCampos.getNombreCarrera() == null
				|| carreraCampos.getNombreCarrera().equals(""))
			return false;
		if (carreraCampos.getTotalCreditos().equals(new BigDecimal(0))
				|| carreraCampos.getTotalCreditos().equals(""))
			return false;
		return true;
	}

	// Metodo para vaciar los campos del formulario
	public void vaciarCampos() {
		this.setNuevaCarrera(new Carrera());
	}
}