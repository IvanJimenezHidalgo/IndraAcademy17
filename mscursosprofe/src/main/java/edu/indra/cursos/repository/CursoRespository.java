package edu.indra.cursos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.indra.comun.entity.Curso;

@Repository
public interface CursoRespository extends CrudRepository<Curso, Long>{
	
	//DADO UN ID DE UN ALUMNO, 
	//A QUÉ CURSO ESTÁ ASIGNADO?¿
	
	//JQPL
	@Query("SELECT c FROM Curso c JOIN c.alumnos a where a.id = ?1")
	public Optional<Curso> cursosAlumnoJPQL (Long id);
	//NATIVA
	
	/*
	//textblocks JAVA 15
	@Query(value = """
			SELECT * FROM Curso WHERE id = 
			 (SELECT curso_id from cursos_alumnos WHERE alumnos_id =?1""", nativeQuery = true)*/
	@Query(value = "SELECT * FROM Curso WHERE id ="+ "(SELECT curso_id from cursos_alumnos WHERE alumnos_id =?1", nativeQuery = true)
	public Optional<Curso> cursosAlumnoNativa (Long id);

}
