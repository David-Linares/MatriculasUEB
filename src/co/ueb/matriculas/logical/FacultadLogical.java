
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

import co.ueb.matriculas.model.Facultad;
import co.ueb.matriculas.util.Constants;
import co.ueb.matriculas.util.HibernateSession;

/**
 * @author davidlinares
 * @date Nov 11, 2015
 * @Modificación Cambio de los métodos para usar procedimientos almacenados
 */
public class FacultadLogical {
	
	private final Logger log = Logger.getLogger(FacultadLogical.class);
	private String msjRespuesta;
	private String sql;
	private List<Facultad> facultades;
	private Session sesion;
	private Query query;

	//Funcion para crear una facultad  - invoca un procedimiento de la base de datos 
	public String crearNuevaFacultad(Facultad nuevaFacultad){
		sesion  = HibernateSession.getSf().getCurrentSession();
		try{
			sesion.beginTransaction();
			sesion.doWork(new Work() {
				
				@Override
				public void execute(Connection connection) throws SQLException {
					CallableStatement callableStatement = connection.prepareCall(Constants.PROCEDIMIENTO_INSERTAR_FACULTAD);	
					callableStatement.setString(1, nuevaFacultad.getNombreFacultad());
					callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
					callableStatement.executeUpdate();
					msjRespuesta = callableStatement.getString(2);
				}
			});
			sesion.getTransaction().commit();
			return msjRespuesta;
		}catch(Exception e){
			sesion.getTransaction().rollback();
			e.printStackTrace();
			return "error";
		}
	}
	
	//Funcion para consultar las facultades en la base de datos
	@SuppressWarnings("unchecked")
	public List<Facultad> consultarFacultades(){
		facultades = new ArrayList<Facultad>();
		sql = Constants.CONSULTA_FACULTADES;
		sesion = HibernateSession.getSf().getCurrentSession();
		try{
			sesion.beginTransaction();
			query = sesion.createQuery(sql);
			facultades = query.list();
			sesion.getTransaction().commit();
		}catch(Exception e){
			sesion.getTransaction().rollback();
			log.error(e);
			e.printStackTrace();
		}
		return facultades;
	}
	
	//Funcion para editar una facultad - invoca un procedimiento en la base de datos
	public String modificarFacultad(Facultad editaFacultad){
		log.info("#### Modificación de Facultad ####");
		Session sesion  = HibernateSession.getSf().getCurrentSession();
		try{
			sesion.beginTransaction();
			sesion.doWork(new Work() {
				
				@Override
				public void execute(Connection connection) throws SQLException {
					CallableStatement callableStatement = connection.prepareCall(Constants.PROCEDIMIENTO_MODIFICAR_FACULTAD);	
					callableStatement.setBigDecimal(1, editaFacultad.getIdFacultad());
					callableStatement.setString(2, editaFacultad.getNombreFacultad());
					callableStatement.setString(3, editaFacultad.getEstadoFacultad().toString());
					callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);
					callableStatement.executeUpdate();
					msjRespuesta = callableStatement.getString(4);
				}
			});
			sesion.update(editaFacultad);
			sesion.getTransaction().commit();
			return msjRespuesta;
		}catch(Exception e){
			sesion.getTransaction().rollback();
			log.error(e);
			e.printStackTrace();
			return "error";
		}
	}
	
	public Facultad getFacultadById(BigDecimal id_facultad){
		Facultad f = null;
		Session sesion  = HibernateSession.getSf().getCurrentSession();
		try {
			sesion.beginTransaction();
			String hql = "FROM Facultad WHERE id_facultad= " + id_facultad;
			System.out.println("[FacultadLogical] getFacultadById la consulta fue" + hql);
			log.info(hql);
			query = sesion.createQuery(hql);
			f = (Facultad) query.uniqueResult();
			if (f == null) {
				return null;
			}
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			e.printStackTrace();
		}
		return f;
	}
	
	public Facultad getFacultadByName(String nombre_facultad){
		Facultad f = null;
		Session session = HibernateSession.getSf().getCurrentSession();
		try {
			session.beginTransaction();
			String hql = "FROM Facultad WHERE nombre_facultad= '" + nombre_facultad + "'";
			Query query = session.createQuery(hql);
			f = (Facultad) query.uniqueResult();
			if (f == null) {
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return f;
	}
	
}

