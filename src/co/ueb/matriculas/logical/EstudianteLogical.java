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
	private List<Persona> estudiantes;
	private Session sesion;
	private String msjRespuesta;
	private String sql;
	private Query query; 
	
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

	@SuppressWarnings("unchecked")
	public List<Persona> consultarEstudiantes() {
		estudiantes = new ArrayList<Persona>();
		sql = Constants.CONSULTA_ESTUDIANTES;
		Session session = HibernateSession.getSf().getCurrentSession();
		try {
			session.beginTransaction();
			query = session.createQuery(sql);
			estudiantes = query.list();
			session.getTransaction().commit();
			
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			log.error(e);
			e.printStackTrace();
			
		}
		return estudiantes;
	}

	public String modificarEstudiante(Persona editaEstudiante) {
		Session sesion = HibernateSession.getSf().getCurrentSession();
		try {
			sesion.beginTransaction();
			java.util.Date utilStartDate = editaEstudiante.getFechaNacimiento();
			java.sql.Date sqlFechaNacimiento = new java.sql.Date(utilStartDate.getTime());
			sesion.doWork(new Work() {
				
				@Override
				public void execute(Connection connection) throws SQLException {
					CallableStatement callableStatement = connection.prepareCall(Constants.FUNCION_MODIFICAR_ESTUDIANTE);	
					callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
					callableStatement.setBigDecimal(2, editaEstudiante.getIdPersona());
					callableStatement.setString(3, editaEstudiante.getNombrePersona());
					callableStatement.setString(4, editaEstudiante.getApellidosPersona());
					callableStatement.setDate(5, sqlFechaNacimiento);
					callableStatement.setString(6, editaEstudiante.getLugarNacimiento());
					callableStatement.setString(7, editaEstudiante.getDireccion());
					callableStatement.setString(8, editaEstudiante.getCorreoElectronico());
					callableStatement.setString(9, editaEstudiante.getEstadoPersona().toString());
					callableStatement.setBigDecimal(10, editaEstudiante.getPromedio());
					callableStatement.setString(11, editaEstudiante.getUsuario());
					callableStatement.setString(12, editaEstudiante.getContrasena());
					callableStatement.setBigDecimal(13, editaEstudiante.getPerfil().getIdPerfil());
					callableStatement.executeUpdate();
					msjRespuesta = callableStatement.getString(12);
				}
			});
			sesion.getTransaction().commit();
			return msjRespuesta;
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			e.printStackTrace();
			log.error(e);
			return "error";
		}
	}

}
