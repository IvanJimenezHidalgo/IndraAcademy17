package edu.indra.alumnos.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.indra.alumnos.repository.entity.Alumno;
import edu.indra.alumnos.service.AlumnoService;


//@CrossOrigin(originPatterns = {"*"}, methods = {RequestMethod.GET})
@RestController
@RequestMapping("/alumno")
public class AlumnoController {
	
	
	@Autowired
	AlumnoService alumnoService;
	
	Logger log = LoggerFactory.getLogger(AlumnoController.class);
	
	//OBTENER TODOS LOS ALUMNOS -get x
	//OBTENER UN ALUMNO POR ID -get x
	//INSERTAR ALUMNO - post x
	//MODIFICAR -put
	//BORRAR -delete
	
	@GetMapping("/obtener-alumno-test") //GET http://localhost:8081/alumno/obtener-alumno-test
	public Alumno obtenerAlumnoTest ()
	{
		Alumno alumno = null;
		
				log.debug("en obtenerAlumnoTest ()");
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
			lista_errores.forEach(o_error -> {
					log.error(o_error.toString());
					});
			responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(lista_errores);
		
		return responseEntity;
		
	}
	
	@PostMapping //POST http://localhost:8081/alumno
	public ResponseEntity<?> insertarAlumno (@Valid @RequestBody Alumno alumno, BindingResult bindingResult)
	{
		ResponseEntity<?> responseEntity = null;
		Alumno alumno_creado = null;
		
			//TODO validación de negocio 
			log.debug("en insertarAlumno");
		
			if (bindingResult.hasErrors())
			{
				//el alumno trae errores
				log.debug("el alumno trae errores " +alumno);
				responseEntity = obtenerErrores (bindingResult);
				
			} else {
				//alumno sin errores
				log.debug("alumno sin errores " +alumno);
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
	
	@GetMapping("/listarAlumnoRangoEdad") //GET http://localhost:8081/alumno/listarAlumnoRangoEdad?edadmin=5&edadmax=10
	public ResponseEntity<?> listarAlumnoRangoEdad (
			@RequestParam(required = true, name = "edadmin") int edadmin, 
			@RequestParam(required = true, name = "edadmax") int edadmax)
	{
		ResponseEntity<?> responseEntity = null;
		Iterable<Alumno> lista_alumnos = null;
		
				lista_alumnos = this.alumnoService.findByEdadBetween(edadmin, edadmax);
				responseEntity = ResponseEntity.ok(lista_alumnos);
				
		
		return responseEntity;
		
	}
	
	@GetMapping("/listarAlumnoPorPatron/{patron}") ////GET http://localhost:8081/alumno/listarAlumnoPorPatron/pepe
	public ResponseEntity<?> listarAlumnoPorPatron(@PathVariable String patron) {
		ResponseEntity<?> responseEntity = null;
		Iterable<Alumno> lista_alumnos = null;

			lista_alumnos = this.alumnoService.findByNombreContaining(patron);
			responseEntity = ResponseEntity.ok(lista_alumnos);

		return responseEntity;
	}
	
	
	@GetMapping("/listarAlumnosPorNombreOApellidoJQPL/{patron}") ////GET http://localhost:8081/alumno/listarAlumnosPorNombreOApellidoJQPL/pepe
	public ResponseEntity<?> listarAlumnosPorNombreOApellidoJQPL(@PathVariable String patron) {
		ResponseEntity<?> responseEntity = null;
		Iterable<Alumno> lista_alumnos = null;

			lista_alumnos = this.alumnoService.busquedaPorNombreOApellidoJPQL(patron);
			responseEntity = ResponseEntity.ok(lista_alumnos);

		return responseEntity;
	}
	
	@GetMapping("/listarAlumnosPorNombreOApellidoNativa/{patron}") ////GET http://localhost:8081/alumno/listarAlumnosPorNombreOApellidoNativa/pepe
	public ResponseEntity<?> listarAlumnosPorNombreOApellidoNativa(@PathVariable String patron) {
		ResponseEntity<?> responseEntity = null;
		Iterable<Alumno> lista_alumnos = null;

			lista_alumnos = this.alumnoService.busquedaPorNombreOApellidoNativa(patron);
			responseEntity = ResponseEntity.ok(lista_alumnos);

		return responseEntity;
	}
	
	
	@GetMapping("/listarAlumnosAltaHoy") //GET http://localhost:8081/alumno/listarAlumnosAltaHoy
	public ResponseEntity<?> listarAlumnosAltaHoy ()
	{
		ResponseEntity<?> responseEntity = null;
		Iterable<Alumno> lista_alumnos = null;
		
				lista_alumnos = this.alumnoService.procedimientoAlumnosAltaHoy();
				responseEntity = ResponseEntity.ok(lista_alumnos);
				
		
		return responseEntity;
		
	}
	
	@GetMapping("/listarAlumnosNombreComoProc/{patron}") //GET http://localhost:8081/alumno/listarAlumnosNombreComoProc/patron
	public ResponseEntity<?> listarAlumnosNombreComoProc (@PathVariable String patron)
	{
		ResponseEntity<?> responseEntity = null;
		Iterable<Alumno> lista_alumnos = null;
		
				lista_alumnos = this.alumnoService.procedimientoAlumnosNombreComo(patron);
				responseEntity = ResponseEntity.ok(lista_alumnos);
				
		
		return responseEntity;
		
	}
	
	
	@GetMapping("/obtenerEstadisticosEdad") //GET http://localhost:8081/alumno/obtenerEstadisticosEdad
	public ResponseEntity<?> obtenerEstadisticosEdad ()
	{
		ResponseEntity<?> responseEntity = null;
		Map<String, Number> mapa_estadisticas_edad = null;
		
				mapa_estadisticas_edad = this.alumnoService.procedimientoAlumnosEstadisticosEdad();
				responseEntity = ResponseEntity.ok(mapa_estadisticas_edad);
				
		
		return responseEntity;
		
	}
	
	@GetMapping("/pagina") //GET http://localhost:8081/alumno/pagina?page=1&size=3
	public ResponseEntity<?> obtenerPaginaAlumnos (Pageable pageable)
	{
		ResponseEntity<?> responseEntity = null;
		Iterable<Alumno> pagina_alumnos = null;
		
			pagina_alumnos = this.alumnoService.findAll(pageable);
			responseEntity = ResponseEntity.ok(pagina_alumnos);
		

		return responseEntity;
		
	}
	
	
	@GetMapping("/listarAlumnoRangoEdadPaginado") //GET http://localhost:8081/alumno/listarAlumnoRangoEdadPaginado?edadmin=5&edadmax=10&page=0&size=3
	public ResponseEntity<?> listarAlumnoRangoEdadPaginado (
			@RequestParam(required = true, name = "edadmin") int edadmin, 
			@RequestParam(required = true, name = "edadmax") int edadmax,
			Pageable pageable)
	{
		ResponseEntity<?> responseEntity = null;
		Iterable<Alumno> lista_alumnos = null;
		
				lista_alumnos = this.alumnoService.findByEdadBetween(edadmin, edadmax, pageable);
				responseEntity = ResponseEntity.ok(lista_alumnos);
				
		
		return responseEntity;
		
	}

}
