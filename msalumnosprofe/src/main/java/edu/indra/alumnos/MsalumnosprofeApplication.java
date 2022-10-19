package edu.indra.alumnos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsalumnosprofeApplication {
	
	/**PASOS PARA CONFIGURAR EUREKA cliente
	 * 
	 * 1) ADD STARTER DEPENDECIA DE EUREKA-CLIENTE
	 * 2) ESCRIBIR EN EL MAIN DE LA ANOTACION @EnableEurekaClient
	 * 3) CONFIGURAR LAS PROPERTIES para apuntar al servidor de Eureka
	 **/

	public static void main(String[] args) {
		SpringApplication.run(MsalumnosprofeApplication.class, args);
	}

}
