package co.ueb.matriculas.beans;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.model.SelectItem;

import co.ueb.matriculas.logical.FacultadLogical;
import co.ueb.matriculas.logical.MateriaLogical;
import co.ueb.matriculas.model.Carrera;
import co.ueb.matriculas.model.Facultad;
import co.ueb.matriculas.model.Materia;
import co.ueb.matriculas.model.MateriaMatricula;

public class MateriaBean {

	MateriaLogical ml = new MateriaLogical();
	String nombreMateria = "";
	BigDecimal cantidadCreditos;
	CarreraBean carreraList = new CarreraBean();
	List<SelectItem> carreraSelect;
	List<Materia> listadoMaterias = ml.consultarMaterias();
	List<Carrera> listadoCarreras = carreraList.getListadoCarreras();
	Materia materiaAux = null;
	Carrera carreraMateria = null;
	boolean banderaEdit = false;
	boolean estadoMateriaEditar = false;
	String mensajeRespuesta = "";

	public CarreraBean getCarreraList() {
		return carreraList;
	}

	public void setCarreraList(CarreraBean carreraList) {
		this.carreraList = carreraList;
	}

	public List<SelectItem> getCarreraSelect() {
		return carreraSelect;
	}

	public void setCarreraSelect(List<SelectItem> carreraSelect) {
		this.carreraSelect = carreraSelect;
	}

	public List<Carrera> getListadoCarreras() {
		return listadoCarreras;
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

	String auxNombreValidacion = "";

	public BigDecimal getCantidadCreditos() {
		return cantidadCreditos;
	}

	public void setCantidadCreditos(BigDecimal cantidadCreditos) {
		this.cantidadCreditos = cantidadCreditos;
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

	public void setMateriaAux(Materia materiaAux) {
		System.out.println("[MateriaBean] - setMateriaAux ||Â Va a cambiar => "
				+ materiaAux);
		if (materiaAux != null) {
			System.out.println(materiaAux.getNombreMateria());
			this.setAuxNombreValidacion(materiaAux.getNombreMateria());
			this.materiaAux = materiaAux;
			if (this.materiaAux.getEstadoMateria().compareTo('1') == 0) {
				this.setEstadoMateriaEditar(true);
			} else {
				this.setEstadoMateriaEditar(false);
			}
			this.setBanderaEdit(true);
		}
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

	public String editarMateria() {
		if (this.getMateriaAux().getNombreMateria().equals("")) {
			this.getMateriaAux()
					.setNombreMateria(this.getAuxNombreValidacion());
			this.setMensajeRespuesta("El campo nombre no puede estar vacío");
			return null;
		}
		if (this.estadoMateriaEditar) {
			this.getMateriaAux().setEstadoMateria('1');
		} else {
			this.getMateriaAux().setEstadoMateria('0');
		}
		boolean guardado = ml.modificarMateria(this.getMateriaAux());
		if (guardado) {
			this.setMensajeRespuesta("");
			this.getListadoMaterias();
			return "paginaMateria";
		} else {
			return "error";
		}
	}

	public String crearMateria() {
		System.out.println("[MateriaBean] - crearMateria || Entra a crear");
		if (this.getNombreMateria().equals("")) {
			System.out.println("Vacio el nombre");
			this.setMensajeRespuesta("El campo nombre no puede estar vacio");
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
				this.getCarreraMateria(), this.nombreMateria,
				this.cantidadCreditos, '1', materiaMatricula);
		System.out.println("[MateriaBean] - crearMateria || Nueva Materia => "
				+ nuevaMateria);
		boolean guardado = ml.crearNuevaMateria(nuevaMateria);
		this.getCarreraMateria().getMaterias().add(nuevaMateria);
		if (guardado) {
			this.setMensajeRespuesta("");
			this.listadoMaterias.add(nuevaMateria);
			return "paginaMateria";
		} else {
			return "error";
		}
	}

	public String eliminarMateria() {
		boolean eliminada = ml.eliminarMateria(getMateriaAux());
		if (eliminada) {
			return "paginaMateria";
		} else {
			return "error";
		}
	}

}
