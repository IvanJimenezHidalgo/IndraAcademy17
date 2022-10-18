package edu.indra.alumnos.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

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

}
