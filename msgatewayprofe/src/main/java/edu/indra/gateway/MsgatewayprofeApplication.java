package edu.indra.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsgatewayprofeApplication {
	
	/**
	 * PASOS PARA CONFIGURAR GATEWAY
	 * 
	 * 1) CON SPRING STARTER PROYECT, ADD LAS DEPENDECIAS DE GATEWAY Y DE EUREKA CLIENT
	 * 2) ADD LA ANOTACION DE @EnableEurekaClient
	 * 3) CONFIGURO PROPERTIES (YML)
	 * 
	 */

	public static void main(String[] args) {
		SpringApplication.run(MsgatewayprofeApplication.class, args);
	}

}
