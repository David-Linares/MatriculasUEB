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
	public static final String MSJ_INTENTO = "Por favor Int�ntalo de nuevo";
	public static final String MSJ_ERROR_GUARDADO = "Ups, parece que ocurri� un error al momento de guardar el registro";
	public static final String MSJ_NOMBRE_REPETIDO = "Ya existe un registro con el nombre";
	public static final String MSJ_USUARIO_REPETIDO = "Ya existe un registro con este nombre de usuario";
	public static final String MSJ_IDENTIFICACION_REPETIDO = "Ya existe un registro con este n�mero de identificaci�n";
	public static final String MSJ_NOMBRE_VACIO = "El campo nombre no puede estar vac�o";
	public static final String MSJ_CREDITOS_EXCEDIDOS = "Excedi� la cantidad de cr�ditos permitidos.";
	public static final String MSJ_USUARIO_NO_EXISTENTE = "El usuario no est� registrado en la base de datos de las notas";
	public static final int CREDITOS_PERMITIDOS = 16;
	public static final String MSJ_CAMPOS_VACIOS ="Los Campos no pueden estar vacios";
	
	
	/*
	 * 
	 * Constantes Reglas de navegación
	 * 
	 * */
	
	public static final String NAVEGACION_FACULTAD = "paginaFacultad";
	public static final String NAVEGACION_MATERIA = "paginaMateria";
	public static final String NAVEGACION_ESTUDIANTE = "paginaEstudiante";
	public static final String NAVEGACION_CARRERA = "paginaCarrera";
	public static final String NAVEGACION_MATRICULA = "paginaMatricula";
		
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
	public static final String CARRERA_CREADA = "Carrera Creada Correctamente!!";
	public static final String CARRERA_ACTUALIZADA = "Carrera Actualizada Correctamente!!";
	public static final String CONSULTA_CARRERAS = "select m from Carrera as m order by m.idCarrera";
	public static final String PROCEDIMIENTO_INSERTAR_CARRERA = "{call INSERTAR_CARRERA(?,?,?,?)}";
	public static final String PROCEDIMIENTO_MODIFICAR_CARRERA = "{call MODIFICAR_CARRERA(?,?,?,?,?,?)}";

	
	/*
	 * Constantes de Materia
	 * 
	 * */
	
	public static final String MATERIA_CREADA = "Materia Creada Correctamente!!";
	public static final String MATERIA_ACTUALIZADA = "Materia Actualizada Correctamente!!";
	public static final String CONSULTA_MATERIAS = "select m from Materia as m order by m.idMateria";
	public static final String PROCEDIMIENTO_INSERTAR_MATERIA = "{call INSERTAR_MATERIA(?,?,?,?)}";
	public static final String PROCEDIMIENTO_MODIFICAR_MATERIA = "{call MODIFICAR_MATERIA(?,?,?,?,?,?)}";

	/*
	 * Constantes de Estudiante
	 * 
	 * */
	
	public static final String MSJ_ESTUDIANTE_OK = "Estudiante Creado Correctamente!!";
	public static final String ESTUDIANTE_CREADO = "Estudiante Creado Correctamente!!";
	public static final String ESTUDIANTE_ACTUALIZADO = "Estudiante Actualizado Correctamente!!";
	public static final String CONSULTA_ESTUDIANTES= "select e from Persona as e where e.perfil.idPerfil = 1 order by e.nombrePersona, e.apellidosPersona";
	public static final String ESTUDIANTE_MODIFICADO = "Estudiante modificado Correctamente!!";
	public static final String PROCEDIMIENTO_INSERTAR_ESTUDIANTE = "{call INSERTAR_PERSONA(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	public static final String FUNCION_MODIFICAR_ESTUDIANTE = "{? = call modificar_estudiante(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	/*
	 * Constantes de Matricula
	 * 
	 * */
	
	public static final String CONSULTA_DATOS_MATRICULA = "FROM Matricula m where m.persona.idPersona = ";
	public static final String CONSULTA_DATOS_MATRICULA_MATERIAS = "FROM MateriaMatricula mm where mm.matricula.persona.idPersona = ";
	public static final String PROCEDIMIENTO_INSERTAR_MATRICULA = "{call INSERTAR_MATRICULA(?,?)}";
	public static final String PROCEDIMIENTO_INSERTAR_MATERIAS_MATRICULA = "{call INSERTAR_MATERIAS_MATRICULA(?,?,?)}";
	public static final String MATRICULA_CREADA = "La matricula se generó correctamente!!";
}
