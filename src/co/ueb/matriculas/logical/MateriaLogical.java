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

import co.ueb.matriculas.model.Materia;
import co.ueb.matriculas.util.Constants;
import co.ueb.matriculas.util.HibernateSession;

public class MateriaLogical {

	private Logger log = Logger.getLogger(MateriaLogical.class);
	private Session sesion;
	private List<Materia> materias;
	private String sql;
	private Query query;
	private String msjRespuesta;
	
	public String crearNuevaMateria(Materia nuevaMateria) {
		sesion = HibernateSession.getSf().getCurrentSession();
		try {
			sesion.beginTransaction();
			sesion.doWork(new Work(){

				@Override
				public void execute(Connection conexion) throws SQLException {
					CallableStatement callableStatement = conexion.prepareCall(Constants.PROCEDIMIENTO_INSERTAR_MATERIA);
					callableStatement.setString(1, nuevaMateria.getNombreMateria());
					callableStatement.setBigDecimal(2, nuevaMateria.getCreditos());
					callableStatement.setBigDecimal(3,nuevaMateria.getCarrera().getIdCarrera());
					callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);
					callableStatement.executeUpdate();
					msjRespuesta= callableStatement.getString(4);
				}
				
			});
			sesion.getTransaction().commit();
			return msjRespuesta;
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			e.printStackTrace();
			return "error";
		}
	}

	@SuppressWarnings("unchecked")
	public List<Materia> consultarMaterias() {
		log.debug("Entró a consultar Materias");
		materias = new ArrayList<Materia>();
		sql = Constants.CONSULTA_MATERIAS;
		sesion = HibernateSession.getSf().getCurrentSession();
		
		try{
			sesion.beginTransaction();
			query = sesion.createQuery(sql);
			materias = query.list();
			sesion.getTransaction().commit();
			log.debug("Consultó Materias");
			log.debug(materias);
		}catch(Exception e){
			sesion.getTransaction().rollback();
			log.error(e);
			e.printStackTrace();
		}
		return materias;
	}

	public String modificarMateria(Materia editaMateria) {
		Session sesion = HibernateSession.getSf().getCurrentSession();
		log.info("Entró A Modificar la Materia");
		try {
			sesion.beginTransaction();
			sesion.doWork(new Work(){

				@Override
				public void execute(Connection conexion) throws SQLException {
					CallableStatement callableStatement = conexion.prepareCall(Constants.PROCEDIMIENTO_MODIFICAR_MATERIA);
					callableStatement.setBigDecimal(1, editaMateria.getIdMateria());
					callableStatement.setString(2, editaMateria.getNombreMateria());
					callableStatement.setBigDecimal(3, editaMateria.getCreditos());
					callableStatement.setObject(4, editaMateria.getEstadoMateria(),java.sql.Types.CHAR);
					callableStatement.setBigDecimal(5, editaMateria.getCarrera().getIdCarrera());
					callableStatement.registerOutParameter(6, java.sql.Types.VARCHAR);
					callableStatement.executeUpdate();
					msjRespuesta= callableStatement.getString(6);
				}
				
			});
			sesion.update(editaMateria);
			sesion.getTransaction().commit();
			return msjRespuesta;
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			e.printStackTrace();
			return "error";
		}
	}
	
	public Materia getMateriaById(BigDecimal id_materia){
		Materia m = null;
		Session sesion  = HibernateSession.getSf().getCurrentSession();
		try {
			sesion.beginTransaction();
			String hql = "FROM Materia WHERE id_materia= " + id_materia;
			log.info(hql);
			query = sesion.createQuery(hql);
			m = (Materia) query.uniqueResult();
			if (m == null) {
				return null;
			}
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			e.printStackTrace();
		}
		return m;
	}
	public Materia getMateriaByName(String nombre_materia) {
		Materia m = null;
		sesion = HibernateSession.getSf().getCurrentSession();
		try {
			sesion.beginTransaction();
			String hql = "FROM Materia WHERE nombre_materia= '" + nombre_materia + "'";
			Query  query = sesion.createQuery(hql);
			m = (Materia) query.uniqueResult();
			if (m == null) {
				return null;
			}
			sesion.getTransaction().commit();
		} catch (Exception e) {
			sesion.getTransaction().rollback();
			e.printStackTrace();
		}
		return m;
	}
}
