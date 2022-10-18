package edu.indra.alumnos.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.indra.alumnos.repository.entity.Alumno;
import edu.indra.alumnos.service.AlumnoService;

@RestController
@RequestMapping("/alumno")
public class AlumnoController {
	
	
	@Autowired
	AlumnoService alumnoService;
	
	//OBTENER TODOS LOS ALUMNOS -get x
	//OBTENER UN ALUMNO POR ID -get x
	//INSERTAR ALUMNO - post x
	//MODIFICAR -put
	//BORRAR -delete
	
	@GetMapping("/obtener-alumno-test") //GET http://localhost:8081/alumno/obtener-alumno-test
	public Alumno obtenerAlumnoTest ()
	{
		Alumno alumno = null;
		
				alumno = new Alumno(1l, "Vale", "Moreno", "vale@gmail.es", 38, new Date());//TRANSIENT
		
		return alumno;
	}
	
	@GetMapping //GET http://localhost:8081/alumno
	public ResponseEntity<?> listarAlumnos ()
	{
		ResponseEntity<?> responseEntity = null;
		Iterable<Alumno> listado_alumnos = null;
		
				listado_alumnos = this.alumnoService.findAll();
				responseEntity = ResponseEntity.ok(listado_alumnos);
		
		return responseEntity;
		
	}
	
	@GetMapping("/{id}") //GET http://localhost:8081/alumno/1
	public ResponseEntity<?> listarAlumnoPorId (@PathVariable Long id)
	{
		ResponseEntity<?> responseEntity = null;
		Optional<Alumno> o_alumno = null;
		
				o_alumno = this.alumnoService.findById(id);
				if (o_alumno.isPresent())
				{
					Alumno alumno_leido = o_alumno.get();
					responseEntity = ResponseEntity.ok(alumno_leido);
				} else {
					responseEntity = ResponseEntity.noContent().build();//204
				}
				
		
		return responseEntity;
		
	}
	
	private ResponseEntity<?> obtenerErrores (BindingResult bindingResult)
	{
		ResponseEntity<?> responseEntity = null;
		List<ObjectError> lista_errores = null;
		
			lista_errores =  bindingResult.getAllErrors();
			responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(lista_errores);
		
		return responseEntity;
		
	}
	
	@PostMapping //POST http://localhost:8081/alumno
	public ResponseEntity<?> insertarAlumno (@Valid @RequestBody Alumno alumno, BindingResult bindingResult)
	{
		ResponseEntity<?> responseEntity = null;
		Alumno alumno_creado = null;
		
			if (bindingResult.hasErrors())
			{
				//el alumno trae errores
				responseEntity = obtenerErrores (bindingResult);
			} else {
				//alumno sin errores
				alumno_creado = this.alumnoService.save(alumno);
				responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(alumno_creado);//201
				
			}
		
			
			
		
		return responseEntity;
		
	}
	
	
	@DeleteMapping("/{id}") //DELETE http://localhost:8081/alumno/1
	public ResponseEntity<?> borrarAlumno (@PathVariable Long id)
	{
		ResponseEntity<?> responseEntity = null;
		
		//try {
			//var cadena = "HOLA"; //tipos inferenciados - var type inference java 11
			//cadena.charAt(5);//unchecked o runtime excepction
			
			
			this.alumnoService.deleteById(id);
			responseEntity = ResponseEntity.ok().build();
//		}catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			throw e;
//		}
			
		
		return responseEntity;
		
	}

	
	@PutMapping("/{id}") //PUT http://localhost:8081/alumno/1
	public ResponseEntity<?> modificarAlumno (@Valid @RequestBody Alumno alumno, BindingResult bindingResult, @PathVariable Long id)
	{
		ResponseEntity<?> responseEntity = null;
		Optional<Alumno> o_alumno = null;
		
		if (bindingResult.hasErrors())
		{
			//tiene errores
			responseEntity = obtenerErrores (bindingResult);
			
		} else {
			//alumno sin errores
			o_alumno = this.alumnoService.update(alumno, id);
			if (o_alumno.isPresent())
			{
				Alumno alumno_leido = o_alumno.get();
				responseEntity = ResponseEntity.ok(alumno_leido);
			} else {
				responseEntity = ResponseEntity.notFound().build();//404
			}
		}
		
				
				
		
		return responseEntity;
		
	}
	
	

}
