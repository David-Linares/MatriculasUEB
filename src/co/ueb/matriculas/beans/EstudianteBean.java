/**
 * 
 */
package co.ueb.matriculas.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import co.ueb.matriculas.logical.EstudianteLogical;
import co.ueb.matriculas.model.Carrera;
import co.ueb.matriculas.model.Materia;
import co.ueb.matriculas.model.Persona;

public class EstudianteBean implements Serializable {

	EstudianteLogical el = new EstudianteLogical();

	CarreraBean carreraList = new CarreraBean();

	List<Persona> listadoEstudiantes = el.consultarEstudiantes();
	List<Carrera> listadoCarreras = carreraList.getListadoCarreras();

	Persona nuevoEstudiante = null;
	Persona estudianteAux = null;
	Persona estudianteAuxEditar = null;

	boolean banderaEdit = false;
	boolean estadoEstudianteEditar = false;

	String mensajeRespuesta = "";
	String auxNombreValidacion = "";

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

	public Persona getEstudianteAuxEditar() {
		return estudianteAuxEditar;
	}

	public void setEstudianteAuxEditar(Persona estudianteAuxEditar) {
		this.estudianteAuxEditar = estudianteAuxEditar;
	}

	public boolean isEstadoEstudianteEditar() {
		return estadoEstudianteEditar;
	}

	public void setEstadoEstudianteEditar(boolean estadoEstudianteEditar) {
		this.estadoEstudianteEditar = estadoEstudianteEditar;
	}

	public Persona getEstudianteAux() {
		return estudianteAux;
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

	public Persona getNuevoEstudiante() {
		return nuevoEstudiante;
	}

	public void setNuevoEstudiante(Persona nuevoEstudiante) {
		this.nuevoEstudiante = nuevoEstudiante;
	}

	public void setEstudianteAux(Persona copiaEstudianteAux) {
		if (copiaEstudianteAux != null) {

			this.estudianteAux = copiaEstudianteAux;
			this.estudianteAuxEditar = copiaEstudianteAux;
			if (this.estudianteAux.getEstadoPersona().compareTo('1') == 0) {
				this.setEstadoEstudianteEditar(true);
			} else {
				this.setEstadoEstudianteEditar(false);
			}
		}
	}

	public List<Persona> getListadoEstudiantes() {
		return listadoEstudiantes;
	}

	public void setListadoEstudiantes(List<Persona> listadoEstudiantes) {
		this.listadoEstudiantes = listadoEstudiantes;
	}

	public boolean validarCamposEstudiante(Persona validarEstudiante) {

		if (validarEstudiante.getIdPersona().equals("")) {
			this.estudianteAux.setIdPersona(estudianteAuxEditar.getIdPersona());
			this.setMensajeRespuesta("El campo identificaciòn no puede estar vacìo");
		}

		if (validarEstudiante.getNombrePersona().equals("")) {
			this.estudianteAux.setNombrePersona(estudianteAuxEditar
					.getNombrePersona());
			this.setMensajeRespuesta("El campo nombres no puede estar vacío");
			return false;
		}
		if (validarEstudiante.getApellidosPersona().equals("")) {
			this.estudianteAux.setApellidosPersona(estudianteAuxEditar
					.getApellidosPersona());
			this.setMensajeRespuesta("El campo apellidos no puede estar vacío");
			return false;
		}
		if (validarEstudiante.getFechaNacimiento().equals("")) {
			this.estudianteAux.setFechaNacimiento(estudianteAuxEditar
					.getFechaNacimiento());
			this.setMensajeRespuesta("El campo fecha de nacimiento no puede estar vacío");
			return false;
		}
		if (validarEstudiante.getLugarNacimiento().equals("")) {
			this.estudianteAux.setLugarNacimiento(estudianteAuxEditar
					.getLugarNacimiento());
			this.setMensajeRespuesta("El campo lugar de nacimiento no puede estar vacío");
			return false;
		}
		if (validarEstudiante.getDireccion().equals("")) {
			this.estudianteAux.setDireccion(estudianteAuxEditar.getDireccion());
			this.setMensajeRespuesta("El campo dirección no puede estar vacío");
			return false;
		}
		if (validarEstudiante.getCorreoElectronico().equals("")) {
			this.estudianteAux.setCorreoElectronico(estudianteAuxEditar
					.getCorreoElectronico());
			this.setMensajeRespuesta("El campo correo electronico no puede estar vacío");
			return false;
		}
		if (validarEstudiante.getUsuario().equals("")) {
			this.estudianteAux.setUsuario(estudianteAuxEditar.getUsuario());
			this.setMensajeRespuesta("El campo usuario no puede estar vacío");
			return false;
		}
		if (validarEstudiante.getContrasena().equals("")) {
			this.estudianteAux.setContrasena(estudianteAuxEditar
					.getContrasena());
			this.setMensajeRespuesta("El campo contraseña no puede estar vacío");
			return false;
		}

		return true;
	}

	public String editarEstudiante() {

		if (!validarCamposEstudiante(estudianteAux)) {
			return null;
		}
		if (this.estadoEstudianteEditar) {
			this.getEstudianteAux().setEstadoPersona('1');
		} else {
			this.getEstudianteAux().setEstadoPersona('0');
		}
		boolean guardado = el.modificarEstudiante(this.getEstudianteAux());
		System.out.println("no ha entrado a if de guardado");
		if (guardado) {
			System.out.println("entro a if de guardado");
			this.setMensajeRespuesta("");
			this.getListadoEstudiantes();
			return "paginaEstudiante";
		} else {
			return "error";
		}
	}

	public String crearEstudiante() {
		// Falta validar campos

		BigDecimal idEstudianteAux = new BigDecimal(1);
		if (listadoEstudiantes.size() != 0) {
			idEstudianteAux = listadoEstudiantes
					.get(listadoEstudiantes.size() - 1).getIdPersona()
					.add(new BigDecimal(1));
		}
		/*Persona nuevoEstudiante = new Persona(idEstudianteAux,
				new BigDecimal(1), this.nuevoEstudiante.getNombrePersona(),
				this.nuevoEstudiante.getApellidosPersona(),
				this.nuevoEstudiante.getFechaNacimiento(),
				this.nuevoEstudiante.getLugarNacimiento(),
				this.nuevoEstudiante.getDireccion(),
				this.nuevoEstudiante.getCorreoElectronico(), '1',
				new BigDecimal(0), this.nuevoEstudiante.getUsuario(),
				this.nuevoEstudiante.getContrasena());
 */
		boolean guardado = el.crearNuevoEstudiante(nuevoEstudiante);

		if (guardado) {
			this.setMensajeRespuesta("");
			this.listadoEstudiantes.add(nuevoEstudiante);
			return "paginaEstudiante";
		} else {
			return "error";
		}
	}

	public String eliminarMateria() {
		boolean eliminada = el.eliminarEstudiante(this.getEstudianteAux());
		if (eliminada) {
			return "paginaEstudiante";
		} else {
			return "error";
		}
	}

}
