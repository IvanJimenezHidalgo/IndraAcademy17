package edu.indra.cursos.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.indra.cursos.repository.CursoRespository;
import edu.indra.cursos.repository.entity.Curso;

@Service
public class CursoServiceImpl implements CursoService{
	
	@Autowired
	private CursoRespository cursoRespository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Curso> findAll() {
		
		return this.cursoRespository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Curso> findById(Long id) {
		return this.cursoRespository.findById(id);
	}

	@Override
	@Transactional
	public Curso save(Curso curso) {
		return this.cursoRespository.save(curso);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		 this.cursoRespository.deleteById(id);
		
	}

	@Override
	@Transactional
	public Optional<Curso> update(Curso curso, Long id) {
		Optional<Curso> optional = Optional.empty();
		
		//leer por el id
		Optional<Curso> aOptional = this.cursoRespository.findById(id);
		if (aOptional.isPresent())
		{
			Curso curso_leido = aOptional.get();//PERSISTENCE
			//curso_leido.setNombre(curso.getNombre());
			BeanUtils.copyProperties(curso, curso_leido, "id", "creadoEn");
			optional = Optional.of(curso_leido);
		}
		//si existe, lo modifico
	
	return optional;
	}

}
