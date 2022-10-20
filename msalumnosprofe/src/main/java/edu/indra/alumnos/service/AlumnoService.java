package edu.indra.alumnos.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import edu.indra.alumnos.model.FraseChuckNorris;
import edu.indra.alumnos.repository.entity.Alumno;

//qué hace mi sistema
//abmc alumnos

public interface AlumnoService {
	
	
	public Iterable<Alumno> findAll ();//consultar todos
	
	public Optional<Alumno> findById (Long id); //consultaPorId
	
	public Alumno save (Alumno alumno);//guardar alumno
	
	public void deleteById (Long id);//borrar alumno
	
	public Optional<Alumno> update (Alumno alumno, Long id);//modificar
	
	public Iterable<Alumno> findByEdadBetween (int edad_min, int edad_max);
	
	public Iterable<Alumno> findByNombreContaining(String patron);
	
	public Iterable<Alumno> busquedaPorNombreOApellidoJPQL (String patron);
	
	public Iterable<Alumno> busquedaPorNombreOApellidoNativa (String patron);
	
	public Iterable<Alumno> procedimientoAlumnosAltaHoy ();
	
	public Iterable<Alumno> procedimientoAlumnosNombreComo (String patron);
	
	public Map<String, Number> procedimientoAlumnosEstadisticosEdad ();
	
	public Page<Alumno> findAll (Pageable pageable);//consultar todos
	
	public Page<Alumno> findByEdadBetween (int edad_min, int edad_max, Pageable pageable);
	
	public Page<Alumno> getAllAlumnosPaginadosOrdenados(Pageable pageable);
	
	public Optional<FraseChuckNorris> obtenerFraseAlatoriaChuckNorrris ();

}
