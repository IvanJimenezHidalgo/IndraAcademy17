package edu.indra.alumnos.repository;

import org.springframework.data.repository.CrudRepository;

import edu.indra.alumnos.repository.entity.Alumno;

public interface AlumnoRepository extends CrudRepository<Alumno, Long>{

}
