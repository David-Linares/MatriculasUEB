package co.ueb.matriculas.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.model.SelectItem;

import org.jboss.logging.Logger;

import co.ueb.matriculas.logical.CarreraLogical;
import co.ueb.matriculas.logical.MateriaLogical;
import co.ueb.matriculas.model.Carrera;
import co.ueb.matriculas.model.Materia;
import co.ueb.matriculas.model.MateriaMatricula;
import co.ueb.matriculas.util.Constants;

public class MateriaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4517717609128767575L;

	// Datos de log
	private Logger log = Logger.getLogger(MateriaBean.class);

	private MateriaLogical ml = new MateriaLogical();

	// Atributos de materia para la vista
	private String mensajeRespuesta = "";
	private String nombreMateria = "";
	private CarreraBean carreraList = new CarreraBean();
	private CarreraLogical cl = new CarreraLogical();
	private Carrera carreraMateria = new Carrera();
	private List<SelectItem> listCarreraSelect;
	private List<Materia> listadoMaterias = ml.consultarMaterias();
	private List<Carrera> listadoCarreras = carreraList.getListadoCarreras();

	// Atributos Auxiliares
	private Materia materiaAux = null;
	private Materia materiaAuxEditar = null;
	private BigDecimal cantidadCreditos;
	private boolean banderaEdit = false;
	private boolean estadoMateriaEditar = false;
	private boolean mensajeError = false;
	private String auxNombreValidacion = "";

	// Getters y Setters

	public BigDecimal getCantidadCreditos() {
		return cantidadCreditos;
	}

	public void setCantidadCreditos(BigDecimal cantidadCreditos) {
		this.cantidadCreditos = cantidadCreditos;
	}

	public Materia getMateriaAuxEditar() {
		return materiaAuxEditar;
	}

	public void setMateriaAuxEditar(Materia materiaAuxEditar) {
		this.materiaAuxEditar = materiaAuxEditar;
	}

	public CarreraBean getCarreraList() {
		return carreraList;
	}

	public void setCarreraList(CarreraBean carreraList) {
		this.carreraList = carreraList;
	}

	public List<Carrera> getListadoCarreras() {
		return listadoCarreras;
	}

	public MateriaBean() {
		this.setListCarreraSelect(new ArrayList<SelectItem>());
		List<Carrera> listCarreras = cl.consultarCarreras();

		if (listCarreras != null && !listCarreras.isEmpty()) {
			SelectItem itemCarrera;
			for (Carrera carreraList : listCarreras) {
				itemCarrera = new SelectItem(carreraList.getIdCarrera(),carreraList.getNombreCarrera());
				listCarreraSelect.add(itemCarrera);
			}
		}
	}

	public void setListadoCarreras(List<Carrera> listadoCarreras) {
		CarreraBean carrerasList = new CarreraBean();
		this.listadoCarreras = carrerasList.getListadoCarreras();

	}

	public Carrera getCarreraMateria() {
		return carreraMateria;
	}

	public void setCarreraMateria(Carrera carreraMateria) {
		this.carreraMateria = carreraMateria;
	}

	public String getAuxNombreValidacion() {
		return auxNombreValidacion;
	}

	public void setAuxNombreValidacion(String auxNombreValidacion) {
		this.auxNombreValidacion = auxNombreValidacion;
	}

	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}

	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}

	public boolean isBanderaEdit() {
		return banderaEdit;
	}

	public void setBanderaEdit(boolean banderaEdit) {
		this.banderaEdit = banderaEdit;
	}

	public String getNombreMateria() {
		return nombreMateria;
	}

	public void setNombreMateria(String nombreMateria) {
		this.nombreMateria = nombreMateria;
	}

	public List<Materia> getListadoMaterias() {
		return listadoMaterias;
	}

	public void setListadoMaterias(List<Materia> listadoMaterias) {
		this.listadoMaterias = listadoMaterias;
	}

	public boolean isEstadoMateriaEditar() {
		return estadoMateriaEditar;
	}

	public void setEstadoMateriaEditar(boolean estadoMateriaEditar) {
		this.estadoMateriaEditar = estadoMateriaEditar;
	}

	public Materia getMateriaAux() {
		return materiaAux;
	}

	public void setListCarreraSelect(List<SelectItem> listCarreraSelect) {
		this.listCarreraSelect = listCarreraSelect;
	}

	public boolean isMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(boolean mensajeError) {
		this.mensajeError = mensajeError;
	}

	// Funcion para asignar la materia auxiliar
	// que es la que sirve para editar y guardar la materia temporal
	public void setMateriaAux(Materia copiaMateriaAux) {
		log.info("Set de materia para editar");
		log.info("Copia materia Aux: " + copiaMateriaAux);
		if (copiaMateriaAux != null) {
			this.setMateriaAux(copiaMateriaAux);
			log.info("Copia materia Aux: " + this.getMateriaAux());
			this.setMateriaAuxEditar(copiaMateriaAux);
			log.info("Copia materia Aux: " + this.getMateriaAuxEditar());
			if (this.getMateriaAux().getEstadoMateria().compareTo('1') == 0) {
				this.setEstadoMateriaEditar(true);
			} else {
				this.setEstadoMateriaEditar(false);
			}
			log.info("Copia materia Aux: " + this.isEstadoMateriaEditar());
		}
	}

	// funcion para crear una materia
	public String crearMateria() {
		System.out.println("[MateriaBean] - crearMateria || Entra a crear");
		if (this.getNombreMateria().equals("")) {
			System.out.println("Vacio el nombre");
			this.setMensajeRespuesta(Constants.MSJ_NOMBRE_VACIO);
			this.setMensajeError(true);
			return null;
		}
		Set<MateriaMatricula> materiaMatricula = new HashSet<MateriaMatricula>(
				0);
		BigDecimal idMateriaAux = new BigDecimal(1);
		if (listadoMaterias.size() != 0) {
			idMateriaAux = listadoMaterias.get(listadoMaterias.size() - 1)
					.getIdMateria().add(new BigDecimal(1));
		}
		Materia nuevaMateria = new Materia(idMateriaAux,
				this.getCarreraMateria(), this.getNombreMateria(),
				this.getCantidadCreditos(), '1', materiaMatricula);

		this.getCarreraMateria().getMaterias().add(nuevaMateria);
		String respuesta = ml.crearNuevaMateria(nuevaMateria);
		switch (respuesta) {
		case "ok": // Respuesta guardado correctamente
			this.setMensajeRespuesta(Constants.MATERIA_CREADA);
			this.setMensajeError(false);
			this.listadoMaterias.add(nuevaMateria);
			break;

		case "duplicado":
			this.setMensajeRespuesta(Constants.MSJ_NOMBRE_REPETIDO + " "
					+ nuevaMateria.getNombreMateria() + ". "
					+ Constants.MSJ_INTENTO);
			this.setMensajeError(true);
			break;
		default:
			this.setMensajeRespuesta(Constants.MSJ_ERROR_GUARDADO + " "
					+ respuesta + ". " + Constants.MSJ_INTENTO);
			this.setMensajeError(true);
			break;
		}
		return Constants.NAVEGACION_MATERIA;
	}

	// Funcion para editar una materia
	public String editarMateria() {

		log.info("Editando materia...");
		log.info(this.getMateriaAux());
		if (!validarCamposMateria(this.getMateriaAux())) {
			return null;
		}
		if (this.isEstadoMateriaEditar()) {
			this.getMateriaAux().setEstadoMateria('1');
		} else {
			this.getMateriaAux().setEstadoMateria('0');
		}
		String respuesta = ml.modificarMateria(this.getMateriaAux());
		switch (respuesta) {
		case "ok": // Respuesta guardado correctamente
			this.setMensajeRespuesta(Constants.MATERIA_ACTUALIZADA);
			break;
		case "duplicado":
			this.setMensajeRespuesta(Constants.MSJ_NOMBRE_REPETIDO + ": "
					+ this.getMateriaAux().getNombreMateria() + " "
					+ Constants.MSJ_INTENTO);
			this.setMensajeError(true);
			this.getMateriaAux().setNombreMateria(
					this.getMateriaAuxEditar().getNombreMateria());
			break;
		default:
			this.setMensajeRespuesta(Constants.MSJ_ERROR_GUARDADO + " "
					+ respuesta + ". " + Constants.MSJ_INTENTO);
			this.setMensajeError(true);
			break;
		}
		return Constants.NAVEGACION_MATERIA;

	}

	// no se para que funciona
	public List<SelectItem> getListCarreraSelect() {
		return listCarreraSelect;
	}

	// Funcion para verificar que los campos no queden vacios
	public boolean validarCamposMateria(Materia validarMateria) {

		if (validarMateria.getNombreMateria().equals("")) {
			this.getMateriaAux().setNombreMateria(
					materiaAuxEditar.getNombreMateria());
			this.setMensajeRespuesta("El campo nombre no puede estar vac�o");
			return false;
		}
		if (validarMateria.getCreditos().equals("")) {
			this.getMateriaAux().setCreditos(materiaAuxEditar.getCreditos());
			this.setMensajeRespuesta("El campo creditos no puede estar vac�o");
			return false;
		}
		if (validarMateria.getCarrera().equals("")) {
			this.getMateriaAux().setCarrera(materiaAuxEditar.getCarrera());
			this.setMensajeRespuesta("El campo carrera no puede estar vac�o");
			return false;
		}
		return true;
	}

}
