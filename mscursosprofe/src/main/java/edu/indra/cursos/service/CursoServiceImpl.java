package edu.indra.cursos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.indra.comun.entity.Alumno;
import edu.indra.comun.entity.Curso;
import edu.indra.cursos.repository.CursoRespository;

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

	@Override
	@Transactional
	public Optional<Curso> asignarAlumnos(List<Alumno> alumnos, Long id) {
		Optional<Curso> optional = Optional.empty();
		
				optional = this.cursoRespository.findById(id);
				if (optional.isPresent())
				{
					Curso curso_leido = optional.get();
					alumnos.forEach(alumno -> curso_leido.addAlumno(alumno));
					optional = Optional.of(curso_leido);
				}
		
		return optional;
	}

	@Override
	@Transactional
	public Optional<Curso> eliminarAlumno(Alumno alumno, Long id) {
		Optional<Curso> optional = Optional.empty();
		
			optional = this.cursoRespository.findById(id);
			if (optional.isPresent())
			{
				Curso curso_leido = optional.get();
				curso_leido.deleteAlumno(alumno);
				optional = Optional.of(curso_leido);
			}
		
		return optional;
	}

}
