package edu.indra.cursos.service;

import java.util.List;
import java.util.Optional;

import edu.indra.comun.entity.Alumno;
import edu.indra.comun.entity.Curso;


public interface CursoService {

	
	public Iterable<Curso> findAll ();//consultar todos
	
	public Optional<Curso> findById (Long id); //consultaPorId
	
	public Curso save (Curso curso);//guardar alumno
	
	public void deleteById (Long id);//borrar alumno
	
	public Optional<Curso> update (Curso curso, Long id);//modificar
	
	public Optional<Curso> asignarAlumnos (List<Alumno> alumnos, Long id);//modificar
	
	public Optional<Curso> eliminarAlumno (Alumno alumno, Long id);//modificar
}
