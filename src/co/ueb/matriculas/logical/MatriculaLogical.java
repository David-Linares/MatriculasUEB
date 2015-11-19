/**
 * 
 */
package co.ueb.matriculas.logical;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.jboss.logging.Logger;

import co.ueb.matriculas.model.Facultad;
import co.ueb.matriculas.model.Materia;
import co.ueb.matriculas.model.MateriaMatricula;
import co.ueb.matriculas.model.Matricula;
import co.ueb.matriculas.util.Constants;
import co.ueb.matriculas.util.HibernateSession;

/**
 * @author davidlinares
 * @date Nov 16, 2015
 */
public class MatriculaLogical {

	private String consulta;
	private String msjRespuesta;
	private Query query;
	private static final Logger log = Logger.getLogger(Matricula.class);
	
	public void obtenerMateriasMatriculadas(){
		
		
		
	}
	
	public Matricula obtenerDatosMatricula(BigDecimal id_persona){
		log.info("##Se prepara para consultar los datos de la matricula##");
		Session sesion  = HibernateSession.getSf().getCurrentSession();
		try {
			sesion.beginTransaction();
			consulta = Constants.CONSULTA_DATOS_MATRICULA + id_persona;
			query = sesion.createQuery(consulta);
			log.info(consulta);
			Matricula datosMatricula = (Matricula) query.uniqueResult();
			log.info(datosMatricula);
			sesion.getTransaction().commit();
			return datosMatricula;
		} catch (Exception e) {
			log.error("##Ocurrió un error consultando la matricula##");
			log.error(e);
			sesion.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<MateriaMatricula> obtenerMateriasMatriculadas(BigDecimal id_persona){
		log.info("##Se prepara para consultar los datos de la matricula##");
		Session sesion  = HibernateSession.getSf().getCurrentSession();
		List<MateriaMatricula> resultadoDatosMateria = new ArrayList<MateriaMatricula>();
		MateriaMatricula datosRespuesta = new MateriaMatricula();
		try {
			sesion.beginTransaction();
			consulta = Constants.CONSULTA_DATOS_MATRICULA_MATERIAS + id_persona;
			log.info("consulta | " +consulta);
			query = sesion.createQuery(consulta);
			resultadoDatosMateria = query.list();
			sesion.getTransaction().commit();
			return resultadoDatosMateria;
		} catch (Exception e) {
			log.error("##Ocurrió un error consultando la matricula##");
			log.error(e);
			sesion.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
		
	}
	
	public String crearMatricula(List<Materia> materiasMatricula, BigDecimal id_persona){
		log.info("##Se prepara para Hacer la matricula##");
		Session sesion  = HibernateSession.getSf().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.doWork(new Work() {
				
				@Override
				public void execute(Connection connection) throws SQLException {
					CallableStatement callableStatement = connection.prepareCall(Constants.PROCEDIMIENTO_INSERTAR_MATRICULA);	
					callableStatement.setBigDecimal(1, id_persona);
					callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
					callableStatement.executeUpdate();
					msjRespuesta = callableStatement.getString(2);
					log.info("Respuesta Procedimiento Matricula = "+msjRespuesta);
					try{
						int codMatricula = Integer.parseInt(msjRespuesta);
						log.info("Código de matricula creada = "+codMatricula);
						log.info(materiasMatricula);
						FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("codMatricula", String.valueOf(codMatricula));
						msjRespuesta = "ok";
						for (Iterator iterator = materiasMatricula.iterator(); iterator
								.hasNext();) {
							Materia materia = (Materia) iterator.next();
							CallableStatement callableStatement2 = connection.prepareCall(Constants.PROCEDIMIENTO_INSERTAR_MATERIAS_MATRICULA);	
							callableStatement2.setInt(1, codMatricula);
							callableStatement2.setBigDecimal(2, materia.getIdMateria());
							callableStatement2.registerOutParameter(3, java.sql.Types.VARCHAR);
							callableStatement2.executeUpdate();
							msjRespuesta = callableStatement2.getString(3);
							if (!msjRespuesta.equals("ok")) {
								log.error("Se produjó un error creando las materias");
								log.error(msjRespuesta);
							}
						}
					}catch(Exception e){
						sesion.getTransaction().rollback();
						e.printStackTrace();
						log.error(e);
						msjRespuesta = "error";
					}
				}
			});
			log.info("Terminó generación de matriculas ok");
			sesion.getTransaction().commit();
			return msjRespuesta;
		} catch (Exception e) {
			log.error("##Ocurrió un error Creando la matricula##");
			log.error(e);
			sesion.getTransaction().rollback();
			e.printStackTrace();
			return "error";			
		}
		
	}
	
}
