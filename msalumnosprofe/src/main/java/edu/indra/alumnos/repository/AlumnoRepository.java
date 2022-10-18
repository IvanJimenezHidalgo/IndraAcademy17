package edu.indra.alumnos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.indra.alumnos.repository.entity.Alumno;

@Repository
public interface AlumnoRepository extends CrudRepository<Alumno, Long>{
	
	//MÉTODOS ALTERNATIVOS DE ACCESO A DATOS
	
	//1 KEYWORD QUERIES - CONSULTAS POR PALABRAS CLAVE
		
		//1.1 obtener un listado de todos los alumnos en un determinado rango de edad
		public Iterable<Alumno> findByEdadBetween (int edad_min, int edad_max);
		
		//1.2 obtener un listado de todos los alumnos cuyo nombre cumpla un patrón
	
	
	//2 JPQL ("AGNÓSTICO")
	//3 NATIVAS
	//4 PROCEDIMIENTOS ALMACENADOS
	//5 CRITERIA API X

}
