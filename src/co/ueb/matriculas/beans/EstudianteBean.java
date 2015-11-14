/**
 * 
 */
package co.ueb.matriculas.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import co.ueb.matriculas.logical.EstudianteLogical;
import co.ueb.matriculas.model.Carrera;
import co.ueb.matriculas.model.Perfil;
import co.ueb.matriculas.model.Persona;

@SuppressWarnings("serial")
public class EstudianteBean implements Serializable {

	private String mensajeRespuesta = "";
	private String auxNombreValidacion = "";

	private boolean banderaEdit = false;
	private boolean estadoEstudianteEditar = false;

	private EstudianteLogical el = new EstudianteLogical();

	private Carrera carreraEstudiante = null;
	private CarreraBean carreraList = new CarreraBean();

	private Persona estudianteAux = null;
	private Persona estudianteAuxEditar = null;

	private Perfil perfil = new Perfil(new BigDecimal(1));

	private List<Persona> listadoEstudiantes = el.consultarEstudiantes();
	private List<Carrera> listadoCarreras = carreraList.getListadoCarreras();

	public EstudianteLogical getEl() {
		return el;
	}

	public void setEl(EstudianteLogical el) {
		this.el = el;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
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
		this.listadoCarreras = listadoCarreras;
	}

	public Carrera getCarreraEstudiante() {
		return carreraEstudiante;
	}

	public void setCarreraEstudiante(Carrera carreraEstudiante) {
		this.carreraEstudiante = carreraEstudiante;
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

	public void encriptarContrasena(String nuevaContrasena){
		String encrypt = DigestUtils.md5Hex(nuevaContrasena);
		this.getEstudianteAux().setContrasena(encrypt);
	}
	
	public void setEstudianteAux(Persona copiaEstudianteAux) {
		if (copiaEstudianteAux != null) {

			this.setEstudianteAux(copiaEstudianteAux);
			this.setEstudianteAuxEditar(copiaEstudianteAux);
			if (this.getEstudianteAux().getEstadoPersona().compareTo('1') == 0) {
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
			this.getEstudianteAux().setIdPersona(
					this.getEstudianteAuxEditar().getIdPersona());
			this.setMensajeRespuesta("El campo identificaciòn no puede estar vacìo");
		}

		if (validarEstudiante.getNombrePersona().equals("")) {
			this.getEstudianteAux().setNombrePersona(
					this.getEstudianteAux().getNombrePersona());
			this.setMensajeRespuesta("El campo nombres no puede estar vacío");
			return false;
		}
		if (validarEstudiante.getApellidosPersona().equals("")) {
			this.getEstudianteAux().setApellidosPersona(
					this.getEstudianteAuxEditar().getApellidosPersona());
			this.setMensajeRespuesta("El campo apellidos no puede estar vacío");
			return false;
		}
		if (validarEstudiante.getFechaNacimiento().equals("")) {
			this.getEstudianteAux().setFechaNacimiento(
					this.getEstudianteAuxEditar().getFechaNacimiento());
			this.setMensajeRespuesta("El campo fecha de nacimiento no puede estar vacío");
			return false;
		}
		if (validarEstudiante.getLugarNacimiento().equals("")) {
			this.getEstudianteAux().setLugarNacimiento(
					this.getEstudianteAuxEditar().getLugarNacimiento());
			this.setMensajeRespuesta("El campo lugar de nacimiento no puede estar vacío");
			return false;
		}
		if (validarEstudiante.getDireccion().equals("")) {
			this.getEstudianteAux().setDireccion(
					this.getEstudianteAuxEditar().getDireccion());
			this.setMensajeRespuesta("El campo dirección no puede estar vacío");
			return false;
		}
		if (validarEstudiante.getCorreoElectronico().equals("")) {
			this.getEstudianteAux().setCorreoElectronico(
					this.getEstudianteAuxEditar().getCorreoElectronico());
			this.setMensajeRespuesta("El campo correo electronico no puede estar vacío");
			return false;
		}
		if (validarEstudiante.getUsuario().equals("")) {
			this.getEstudianteAux().setUsuario(
					this.getEstudianteAuxEditar().getUsuario());
			this.setMensajeRespuesta("El campo usuario no puede estar vacío");
			return false;
		}
		if (validarEstudiante.getContrasena().equals("")) {
			this.getEstudianteAux().setContrasena(
					this.getEstudianteAuxEditar().getContrasena());
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

	@SuppressWarnings("unchecked")
	public String crearEstudiante() {
		if (!validarCamposEstudiante(this.getEstudianteAux())) {
			return null;
		}
		if (this.isEstadoEstudianteEditar()) {
			this.getEstudianteAux().setEstadoPersona('1');
		} else {
			this.getEstudianteAux().setEstadoPersona('0');
		}

		Persona nuevoEstudiante = new Persona(this.getEstudianteAux()
				.getIdPersona(), perfil, this.getEstudianteAux()
				.getNombrePersona(), this.getEstudianteAux()
				.getApellidosPersona(), this.getEstudianteAux()
				.getFechaNacimiento(), this.getEstudianteAux()
				.getLugarNacimiento(), this.getEstudianteAux().getDireccion(),
				this.getEstudianteAux().getCorreoElectronico(), '1',
				new BigDecimal(0), this.getEstudianteAux().getUsuario(), this
						.getEstudianteAux().getContrasena());

		this.getCarreraEstudiante().getCarreraEstudiantes()
				.add(nuevoEstudiante);

		boolean guardado = el.crearNuevoEstudiante(nuevoEstudiante);

		if (guardado) {
			this.setMensajeRespuesta("");
			this.getListadoEstudiantes().add(nuevoEstudiante);
			return "paginaEstudiante";
		} else {
			return "error";
		}
	}

}
