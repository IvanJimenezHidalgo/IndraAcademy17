package edu.indra.cursos.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.indra.comun.entity.Alumno;
import edu.indra.comun.entity.Curso;
import edu.indra.cursos.service.CursoService;

@RestController
@RequestMapping("/curso")
public class CursoController {
	
	@Autowired
	CursoService cursoService;
	
	Logger log = LoggerFactory.getLogger(CursoController.class);
	
	@GetMapping //GET http://localhost:8081/curso
	public ResponseEntity<?> listarCursos ()
	{
		ResponseEntity<?> responseEntity = null;
		Iterable<Curso> listado_cursos = null;
		
				listado_cursos = this.cursoService.findAll();
				responseEntity = ResponseEntity.ok(listado_cursos);
		
		return responseEntity;
		
	}
	
	@GetMapping("/{id}") //GET http://localhost:8081/curso/1
	public ResponseEntity<?> listarCursoPorId (@PathVariable Long id)
	{
		ResponseEntity<?> responseEntity = null;
		Optional<Curso> o_curso = null;
		
				o_curso = this.cursoService.findById(id);
				if (o_curso.isPresent())
				{
					Curso curso_leido = o_curso.get();
					responseEntity = ResponseEntity.ok(curso_leido);
				} else {
					responseEntity = ResponseEntity.noContent().build();//204
				}
				
		
		return responseEntity;
		
	}
	
	
	@PostMapping //POST http://localhost:8081/curso
	public ResponseEntity<?> insertarCurso (@RequestBody Curso curso)
	{
		ResponseEntity<?> responseEntity = null;
		Curso curso_creado = null;
		
		
				//curso sin errores
				log.debug("curso sin errores " +curso);
				curso_creado = this.cursoService.save(curso);
				responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(curso_creado);//201
				
			
		
		
		return responseEntity;
		
	}
	
	
	@DeleteMapping("/{id}") //DELETE http://localhost:8081/curso/1
	public ResponseEntity<?> borrarCurso (@PathVariable Long id)
	{
		ResponseEntity<?> responseEntity = null;
			
			this.cursoService.deleteById(id);
			responseEntity = ResponseEntity.ok().build();
			
		
		return responseEntity;
		
	}

	
	@PutMapping("/{id}") //PUT http://localhost:8081/curso/1
	public ResponseEntity<?> modificarCurso (@RequestBody Curso curso, @PathVariable Long id)
	{
		ResponseEntity<?> responseEntity = null;
		Optional<Curso> o_curso = null;
		
			o_curso = this.cursoService.update(curso, id);
			if (o_curso.isPresent())
			{
				Curso curso_leido = o_curso.get();
				responseEntity = ResponseEntity.ok(curso_leido);
			} else {
				responseEntity = ResponseEntity.notFound().build();//404
			}
		
		return responseEntity;
		
	}
	
	@PutMapping("/asignarAlumnos/{id}") //PUT http://localhost:8081/curso/asignarAlumnos/1
	public ResponseEntity<?> asignarAlumnos (@RequestBody List<Alumno> alumnos, @PathVariable Long id)
	{
		ResponseEntity<?> responseEntity = null;
		Optional<Curso> o_curso = null;
		
			o_curso = this.cursoService.asignarAlumnos(alumnos, id);
			if (o_curso.isPresent())
			{
				Curso curso_leido = o_curso.get();
				responseEntity = ResponseEntity.ok(curso_leido);
			} else {
				responseEntity = ResponseEntity.notFound().build();//404
			}
		
		return responseEntity;
		
	}
	
	@PutMapping("/eliminarAlumno/{id}") //PUT http://localhost:8081/curso/eliminarAlumno/8
	public ResponseEntity<?> eliminarAlumno (@RequestBody Alumno alumno, @PathVariable Long id)
	{
		ResponseEntity<?> responseEntity = null;
		Optional<Curso> o_curso = null;
		
			o_curso = this.cursoService.eliminarAlumno(alumno, id);
			if (o_curso.isPresent())
			{
				Curso curso_leido = o_curso.get();
				responseEntity = ResponseEntity.ok(curso_leido);
			} else {
				responseEntity = ResponseEntity.notFound().build();//404
			}
		
		return responseEntity;
		
	}

	
	@GetMapping("/obtenerCursoAlumno/{idalumno}") //GET http://localhost:8081/curso/obtenerCursoAlumno/1
	public ResponseEntity<?> obtenerCursoAlumno (@PathVariable Long idalumno)
	{
		ResponseEntity<?> responseEntity = null;
		Optional<Curso> o_curso = null;
		
				o_curso = this.cursoService.cursoAlumno(idalumno);
				if (o_curso.isPresent())
				{
					Curso curso_alumno = o_curso.get();
					responseEntity = ResponseEntity.ok(curso_alumno);
				} else {
					responseEntity = ResponseEntity.noContent().build();//204
				}
				
		
		return responseEntity;
		
	}

}
