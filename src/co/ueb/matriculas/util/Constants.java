package co.ueb.matriculas.util;

/**
 * @author davidlinares
 * @date Nov 11, 2015
 * @Modificación Agregación  de las constantes de consulta de facultad.
 */
public class Constants {
	
	/*
	 * Constantes Generales
	 * 
	 * */
	
	public static final String HIBERNATE_FILE_NAME = "hibernate.cfg.xml";
	public static final String MSJ_INTENTO = "Por favor Inténtalo de nuevo";
	public static final String MSJ_ERROR_GUARDADO = "Ups, parece que ocurrió un error al momento de guardar el registro";
	public static final String MSJ_NOMBRE_REPETIDO = "Ya existe un registro con el nombre";
	public static final String MSJ_NOMBRE_VACIO = "El campo nombre no puede estar vacío";
	
	
	/*
	 * 
	 * Constantes Reglas de navegación
	 * 
	 * */
	
	public static final String NAVEGACION_FACULTAD = "paginaFacultad";
		
	/*
	 * Constantes de Facultad
	 * 
	 * */
	
	public static final String FACULTAD_CREADA = "Facultad Creada Correctamente!!";
	public static final String FACULTAD_ACTUALIZADA = "Facultad Actualizada Correctamente!!";
	public static final String CONSULTA_FACULTADES = "select f from Facultad as f order by f.idFacultad";
	public static final String PROCEDIMIENTO_INSERTAR_FACULTAD = "{call INSERTAR_FACULTAD(?,?)}";
	public static final String PROCEDIMIENTO_MODIFICAR_FACULTAD = "{call MODIFICAR_FACULTAD(?,?,?,?)}";
	
	/*
	 * Constantes de Carrera
	 * 
	 * */
	
	public static final String MSJ_CARRERA_OK = "Carrera Creada Correctamente!!";
	
	/*
	 * Constantes de Materia
	 * 
	 * */
	
	public static final String MSJ_MATERIA_OK = "Materia Creada Correctamente!!";
	
	/*
	 * Constantes de Estudiante
	 * 
	 * */
	
	public static final String MSJ_ESTUDIANTE_OK = "Estudiante Creado Correctamente!!";
}
