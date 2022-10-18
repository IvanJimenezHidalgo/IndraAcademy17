package edu.indra.alumnos.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = {"edu.indra.alumnos"})  // oye, tú eres la clase que escucha/trata las excepciones
public class GestionExcepciones {
	
	
	//definir un método para gestionar cada tipo de excepción o fallo
	//para este tipo de excepción org.springframework.dao.EmptyResultDataAccessException
	@ExceptionHandler(org.springframework.dao.EmptyResultDataAccessException.class)
	public ResponseEntity<?> idAlumnoBorradoNoExiste (EmptyResultDataAccessException excepcion)
	{
		ResponseEntity<?> responseEntity = null;
		String mensaje_error = null;
		
			mensaje_error = "Error borrando un alumno por id" + excepcion.getMessage();
			responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensaje_error);
		
		
		return responseEntity;
	}
	
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<?> tratamientoExcecpcionGenerica (Throwable excepcion_generica)
	{
		ResponseEntity<?> responseEntity = null;
		String mensaje_error = null;
		
			mensaje_error = "Ha ocurrido un fallo " + excepcion_generica.getMessage();
			responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensaje_error);
		
		
		return responseEntity;
	}

}
