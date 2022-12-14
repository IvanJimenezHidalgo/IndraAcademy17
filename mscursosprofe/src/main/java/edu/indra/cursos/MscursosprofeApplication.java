package edu.indra.cursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EntityScan("edu.indra.comun")//INDICO EL PAQUETE RAÍZ a SPRING DONDE ESCANEAR LAS ENTIDADES - imprescindible si estamos fuera del paquete raíz de la APP/ms
public class MscursosprofeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscursosprofeApplication.class, args);
	}

}
