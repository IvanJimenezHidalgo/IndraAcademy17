package edu.indra.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MseurekaprofeApplication {
	
	
	/**PASOS PARA CONFIGURAR EUREKA
	 * 
	 * 1) DEFINIR EL MS CON STARTER AÑADIENDO LA DEPEDENCIA DE EUREKA-SERVER
	 * 2) ADD DEPEDENCIA DE JAXB 
	 * 3) ESCRIBIR EN EL MAIN DE EUREKA LA ANOTACION @EnableEurekaServer
	 * 4) CONFIGURAR LAS PROPERTIES
	 **/

	public static void main(String[] args) {
		SpringApplication.run(MseurekaprofeApplication.class, args);
	}

}
