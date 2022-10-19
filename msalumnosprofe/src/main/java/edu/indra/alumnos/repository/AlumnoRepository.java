package edu.indra.alumnos.repository;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.indra.alumnos.repository.entity.Alumno;

@Repository
//public interface AlumnoRepository extends CrudRepository<Alumno, Long>
public interface AlumnoRepository extends PagingAndSortingRepository<Alumno, Long>{

	
	//MÉTODOS ALTERNATIVOS DE ACCESO A DATOS
	
	//1 KEYWORD QUERIES - CONSULTAS POR PALABRAS CLAVE
		
		//1.1 obtener un listado de todos los alumnos en un determinado rango de edad
		public Iterable<Alumno> findByEdadBetween (int edad_min, int edad_max);
		
		//1.2 obtener un listado de todos los alumnos cuyo nombre cumpla un patrón
		public Iterable<Alumno> findByNombreContaining(String patron);
		
	
		//1.3 obtener un listado de todos los alumnos en un determinado rango de edad por paginas
		public Page<Alumno> findByEdadBetween (int edad_min, int edad_max, Pageable pageable);
	
	
	//2 JPQL ("AGNÓSTICO")
		
		@Query("SELECT a FROM Alumno a WHERE a.nombre LIKE %?1% OR a.apellido LIKE %?1%")
		public Iterable<Alumno> busquedaPorNombreOApellidoJPQL (String patron);
		
		
	//3 NATIVAS
		
		@Query(value = "SELECT * FROM alumnos a WHERE a.nombre LIKE %?1% OR a.apellido LIKE %?1%", nativeQuery = true)
		public Iterable<Alumno> busquedaPorNombreOApellidoNativa (String patron);
		
		
	//4 PROCEDIMIENTOS ALMACENADOS
		
		//1 TENER EL PROCEDIMIENTO DEFINIDO EN BASE DE DATOS X 
			// obtener los alumnos que se han dado de alta en el día de hoy 
			//obtener los alumnos con nombre como
			//obtener estadísticos de edad (max, min y media)
		
		//TODO
			//2 REFERENCIAR LOS PROCEDIMIENTOS DESDE LA ENTIDAD DÁNDOLE UN NOMBRE "JAVA" X
			//3 LIGAR LOS MÉTODOS DEL PASO 2 CON MÉTODOS EN ALUMNO REPOSITORY @Procedure
		
		@Procedure(name = "Alumno.alumnosRegistradosHoy" )
		public Iterable<Alumno> procedimientoAlumnosAltaHoy ();
		
		@Procedure(name = "Alumno.alumnosNombreComo" )
		public Iterable<Alumno> procedimientoAlumnosNombreComo (@Param("patron") String patron);
		
		@Procedure(name = "Alumno.alumnosEdadMediaMinMax" )
		public Map<String, Number> procedimientoAlumnosEstadisticosEdad (int edadmax, int edadmin, float edadmedia);
		
	//5 CRITERIA API X

}
