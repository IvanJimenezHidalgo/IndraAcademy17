package edu.indra.alumnos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan//también necesario incluir si los COmpoenntes (controller, service y repo) están fuera del paquete raiz
@SpringBootApplication
@EnableEurekaClient
@EntityScan("edu.indra.comun")//INDICO EL PAQUETE RAÍZ a SPRING DONDE ESCANEAR LAS ENTIDADES - imprescindible si estamos fuera del paquete raíz de la APP/ms
@EnableFeignClients
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
