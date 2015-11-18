package co.ueb.matriculas.logical;

import java.io.Serializable;
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

import com.itextpdf.text.log.SysoCounter;

import co.ueb.matriculas.model.Carrera;
import co.ueb.matriculas.util.Constants;
import co.ueb.matriculas.util.HibernateSession;

public class CarreraLogical implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1760467828935283002L;
	private Logger log = Logger.getLogger(CarreraLogical.class);
	private Session sesion;
	private String msjRespuesta;
	private String sql;
	private Query query;
	private List<Carrera> carreras;
	private Carrera carreraQuery;

	public String crearNuevaCarrera(Carrera nuevaCarrera){
		System.out.println("[CarreraLogical] crearNuevaCarrera");
		sesion  = HibernateSession.getSf().getCurrentSession();
		try{
			sesion.beginTransaction();
			sesion.doWork(new Work(){

				@Override
				public void execute(Connection conexion) throws SQLException {
					CallableStatement callableStatement = conexion.prepareCall(Constants.PROCEDIMIENTO_INSERTAR_CARRERA);
					callableStatement.setString(1, nuevaCarrera.getNombreCarrera());
					callableStatement.setBigDecimal(2, nuevaCarrera.getTotalCreditos());
					callableStatement.setBigDecimal(3, nuevaCarrera.getFacultad().getIdFacultad());
					callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);
					callableStatement.executeUpdate();
					msjRespuesta= callableStatement.getString(4);
				}
				
			});
			sesion.getTransaction().commit();
			
			return msjRespuesta;
		}catch(Exception e){
			System.out.println("[CarreraLogical] crearNuevaCarrera - entro al error ");
			sesion.getTransaction().rollback();
			e.printStackTrace();
			return "error";
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Carrera> consultarCarreras(){
		log.info("## Entro a consultar carreras ##");
		carreras = new ArrayList<Carrera>();
		sql = Constants.CONSULTA_CARRERAS;
		sesion = HibernateSession.getSf().getCurrentSession();
		try{
			sesion.beginTransaction();
			query = sesion.createQuery(sql);
			carreras = query.list();
			sesion.getTransaction().commit();
		}catch(Exception e){
			sesion.getTransaction().rollback();
			log.error(e);
			e.printStackTrace();
		}
		log.info("## Salió de consultar carreras ##");
		log.info(carreras);
		return carreras;
	}

	public String modificarCarrera(Carrera editaCarrera){
		log.info(editaCarrera);
		sesion = HibernateSession.getSf().getCurrentSession();
		try{
			sesion.beginTransaction();
			sesion.doWork(new Work(){
				
				@Override
				public void execute(Connection conexion) throws SQLException {
					System.out.println("[CarreraLogical] modificarCarrera - entro al metodo execute");
					CallableStatement callableStatement = conexion.prepareCall(Constants.PROCEDIMIENTO_MODIFICAR_CARRERA);
					callableStatement.setBigDecimal(1, editaCarrera.getIdCarrera());
					callableStatement.setString(2, editaCarrera.getNombreCarrera());
					callableStatement.setBigDecimal(3, editaCarrera.getTotalCreditos());
					callableStatement.setBigDecimal(4, editaCarrera.getFacultad().getIdFacultad());
					callableStatement.setString(5, editaCarrera.getEstadoCarrera().toString());				
					callableStatement.registerOutParameter(6, java.sql.Types.VARCHAR);
					callableStatement.executeUpdate();
					msjRespuesta= callableStatement.getString(6);
				}
				
			});
			sesion.getTransaction().commit();
			return msjRespuesta;
		}catch(Exception e){
			System.out.println("[CarreraLogical] modificarCarrera - entro al error");
			sesion.getTransaction().rollback();
			e.printStackTrace();
			log.error(e);
			return "error";
		}
	}
	
	public Carrera getCarreraByName(String nombre_carrera){		
		sesion  = HibernateSession.getSf().getCurrentSession();
		try {
			sesion.beginTransaction();
			String hql = "FROM Carrera WHERE nombreCarrera = '" + nombre_carrera + "'";
			query = sesion.createQuery(hql);
			carreraQuery = (Carrera) query.uniqueResult();
			if (carreraQuery == null) {
				return null;
			}
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			e.printStackTrace();
		}
		return carreraQuery;
	}
	
	public Carrera getCarreraById(BigDecimal id_carrera){		
		log.info("##Get Carrera by Id##");
		sesion  = HibernateSession.getSf().getCurrentSession();
		try {
			sesion.beginTransaction();
			String hql = "FROM Carrera WHERE idCarrera = " + id_carrera;
			query = sesion.createQuery(hql);
			carreraQuery = (Carrera) query.uniqueResult();
			if (carreraQuery == null) {
				return null;
			}
			log.info("Encontró => "+carreraQuery);
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			e.printStackTrace();
		}
		return carreraQuery;
	}

}
