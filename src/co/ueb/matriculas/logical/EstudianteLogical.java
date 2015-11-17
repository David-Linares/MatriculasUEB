package co.ueb.matriculas.logical;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.jboss.logging.Logger;

import co.ueb.matriculas.model.Persona;
import co.ueb.matriculas.util.Constants;
import co.ueb.matriculas.util.HibernateSession;

public class EstudianteLogical {
	
	private static final Logger log = Logger.getLogger(EstudianteLogical.class);
	
	private Persona personaEstudiante = new Persona();
	List<Persona> listadoEstudiantes = new ArrayList<Persona>();
	
	private Session sesion;
	private String msjRespuesta;

	/*
	 * @SuppressWarnings("unchecked") public List<Persona> getEstudiantes() {
	 * String sql = "select p from Persona as p";
	 * 
	 * session.beginTransaction();
	 * 
	 * Query query = session.createQuery(sql);
	 * 
	 * listadoEstudiantes = query.list();
	 * 
	 * session.getTransaction().commit();
	 * 
	 * return listadoEstudiantes; }
	 */

	public String obtenerPromedio(BigDecimal identificacion) {

		PersonaLogical personaLogica = new PersonaLogical();

		personaEstudiante = personaLogica.getPersonaById(identificacion);
		String promedioRespuesta = null;

		if (personaEstudiante == null)
			promedioRespuesta = "No existe un estudiante con el número de identificación dado";
		else
			promedioRespuesta = personaEstudiante.getPromedio().toString();

		return promedioRespuesta;
	}

	public String crearNuevoEstudiante(Persona nuevoEstudiante) {
		log.info("## Se prepara para crear estudiante ## ");
		sesion = HibernateSession.getSf().getCurrentSession();
		try {
			sesion.beginTransaction();
			java.util.Date utilStartDate = nuevoEstudiante.getFechaNacimiento();
			java.sql.Date sqlFechaNacimiento = new java.sql.Date(utilStartDate.getTime());
			sesion.doWork(new Work() {
				
				@Override
				public void execute(Connection connection) throws SQLException {
					CallableStatement callableStatement = connection.prepareCall(Constants.PROCEDIMIENTO_INSERTAR_ESTUDIANTE);	
					callableStatement.setBigDecimal(1, nuevoEstudiante.getIdPersona());
					callableStatement.setString(2, nuevoEstudiante.getNombrePersona());
					callableStatement.setString(3, nuevoEstudiante.getApellidosPersona());
					callableStatement.setDate(4, sqlFechaNacimiento);
					callableStatement.setString(5, nuevoEstudiante.getLugarNacimiento());
					callableStatement.setString(6, nuevoEstudiante.getDireccion());
					callableStatement.setString(7, nuevoEstudiante.getCorreoElectronico());
					callableStatement.setBigDecimal(8, nuevoEstudiante.getPromedio());
					callableStatement.setString(9, nuevoEstudiante.getUsuario());
					callableStatement.setString(10, nuevoEstudiante.getContrasena());
					callableStatement.setBigDecimal(11, nuevoEstudiante.getPerfil().getIdPerfil());
					callableStatement.registerOutParameter(12, java.sql.Types.VARCHAR);
					callableStatement.executeUpdate();
					msjRespuesta = callableStatement.getString(12);
				}
			});
			log.info("Respuesta de Procedimiento: "+msjRespuesta);
			sesion.getTransaction().commit();
			return msjRespuesta;
		} catch (Exception e) {
			log.error("## Se produjo un error al momento de crear el estudiante ##");
			log.error(e);
			sesion.getTransaction().rollback();
			e.printStackTrace();
			return "error";
		}
	}

	public boolean eliminarEstudiante(Persona estudiante) {
		Session sesion = HibernateSession.getSf().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.delete(estudiante);
			sesion.getTransaction().commit();
			return true;
		} catch (Exception e) {
			System.out
					.println("[Estudiante Logical - Eliminar Estudiante] Entra� a Error");
			sesion.getTransaction().rollback();
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Persona> consultarEstudiantes() {
		List<Persona> estudiantes = new ArrayList<Persona>();
		String sql = "select e from Persona as e where e.perfil.idPerfil = 1 order by e.nombrePersona, e.apellidosPersona ";
		Session session = HibernateSession.getSf().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery(sql);
		estudiantes = query.list();
		session.getTransaction().commit();
		return estudiantes;
	}

	public boolean modificarEstudiante(Persona editaEstudiante) {
		Session sesion = HibernateSession.getSf().getCurrentSession();
		System.out.println("modificar estudiante entro ");
		try {
			sesion.beginTransaction();
			sesion.update(editaEstudiante);
			sesion.getTransaction().commit();
			return true;
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
	}

}
