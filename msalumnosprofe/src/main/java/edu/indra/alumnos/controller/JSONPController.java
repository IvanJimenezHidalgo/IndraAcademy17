package edu.indra.alumnos.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
public class JSONPController {
	
	@Autowired
	ObjectMapper om;
	
	@GetMapping("/jsonp/alumno")// GET http://localhost:8081/jsonp/alumno?callback=parseResponse
	public void testJSONP (HttpServletRequest peticion, HttpServletResponse respuesta, 
			@RequestParam (name = "callback", required = true) String callback) throws IOException
	{
		//CREAR UN ALUMNO
		ObjectNode on = om.createObjectNode();
		
		//PASARLO A JSON
		on.put("nombre", "Sara");
		on.put("apellido", "Perez");
		on.put("edad", 33);
		on.put("email", "sara@gmail.com");
		on.put("id", 23);
		on.put("creadoEn", new Date().toString());
		
		String alumno_json = on.toString();//serializo - lo paso a JSON
		
		//CREAR LA FUNCION JS DE VUELTA, CON EL ALUMNO COMO PARÁMETROS
		String cuerpo_respuesta = callback +"("+alumno_json+");";
		
		//ESCRIBIRLO EN EL CUERPO DE LA RESPUESTA
		PrintWriter cuerpo_http = respuesta.getWriter();
		respuesta.setContentType("application/javascript;charset=UTF-8");
		cuerpo_http.print(cuerpo_respuesta);
	}

}
