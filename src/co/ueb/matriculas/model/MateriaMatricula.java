package co.ueb.matriculas.model;

// Generated 02-nov-2015 12:47:33 by Hibernate Tools 4.3.1

/**
 * MateriaMatricula generated by hbm2java
 */
public class MateriaMatricula implements java.io.Serializable {

	private MateriaMatriculaId id;
	private Materia materia;
	private Matricula matricula;

	public MateriaMatricula() {
	}

	public MateriaMatricula(MateriaMatriculaId id, Materia materia,
			Matricula matricula) {
		this.id = id;
		this.materia = materia;
		this.matricula = matricula;
	}

	public MateriaMatriculaId getId() {
		return this.id;
	}

	public void setId(MateriaMatriculaId id) {
		this.id = id;
	}

	public Materia getMateria() {
		return this.materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public Matricula getMatricula() {
		return this.matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}	
	
}
