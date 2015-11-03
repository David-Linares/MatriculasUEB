package co.ueb.matriculas.beans;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import co.ueb.matriculas.logical.MateriaLogical;
import co.ueb.matriculas.model.Carrera;
import co.ueb.matriculas.model.Materia;
import co.ueb.matriculas.model.MateriaMatricula;

public class MateriaBean {

	MateriaLogical ml = new MateriaLogical();
	BigDecimal cantidadCreditos;
	
	CarreraBean carreraList = new CarreraBean();
	Carrera carreraMateria = null;

	
	List<Materia> listadoMaterias = ml.consultarMaterias();
	List<Carrera> listadoCarreras = carreraList.getListadoCarreras();
	
	Materia materiaAux = null;
	Materia materiaAuxEditar=null;
	
	boolean banderaEdit = false;
	boolean estadoMateriaEditar = false;
		
	String mensajeRespuesta = "";
	String nombreMateria = "";
	
	
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
	
	public void setMateriaAux(Materia copiaMateriaAux) {
			System.out.println(" setMateriaAux ||�Va a cambiar => "	+ copiaMateriaAux);
			if (copiaMateriaAux != null) {
				System.out.println("entro a set materiaa� aux");
				this.materiaAux = copiaMateriaAux;
				materiaAuxEditar= copiaMateriaAux;
				if (this.materiaAux.getEstadoMateria().compareTo('1') == 0) {
					this.setEstadoMateriaEditar(true);
				} else {
					this.setEstadoMateriaEditar(false);
				}
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

	public boolean validarCamposMateria (Materia validarMateria){

		if (validarMateria.getNombreMateria().equals("")) {
			this.materiaAux.setNombreMateria(materiaAuxEditar.getNombreMateria());
			this.setMensajeRespuesta("El campo nombre no puede estar vac�o");
			return false;
		}
		if (validarMateria.getCreditos().equals("")) {
			this.materiaAux.setCreditos(materiaAuxEditar.getCreditos());
			this.setMensajeRespuesta("El campo creditos no puede estar vac�o");
			return false;
		}
		if (validarMateria.getCarrera().equals("")) {
			this.materiaAux.setCarrera(materiaAuxEditar.getCarrera());
			this.setMensajeRespuesta("El campo carrera no puede estar vac�o");
			return false;
		}
		return true;
	}
	public String editarMateria() {
		System.out.println("entro a editar materia");
		System.out.println(materiaAux);
		if (!validarCamposMateria(materiaAux)) {
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
				cantidadCreditos, '1', materiaMatricula);
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

	

}
