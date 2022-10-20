package edu.indra.cursos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.indra.cursos.repository.entity.Curso;

@Repository
public interface CursoRespository extends CrudRepository<Curso, Long>{

}