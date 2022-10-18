package edu.indra.alumnos.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.indra.alumnos.repository.AlumnoRepository;
import edu.indra.alumnos.repository.entity.Alumno;

@Service
public class AlumnoServiceImpl implements AlumnoService {
	
	@Autowired
	AlumnoRepository alumnoRepository;

	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Alumno> findAll() {
		return this.alumnoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Alumno> findById(Long id) {
		
		return this.alumnoRepository.findById(id);
	}

	@Override
	@Transactional
	public Alumno save(Alumno alumno) {
		
		return this.alumnoRepository.save(alumno);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		this.alumnoRepository.deleteById(id);
		
	}

	@Override
	@Transactional
	public Optional<Alumno> update(Alumno alumno, Long id) {
		Optional<Alumno> optional = Optional.empty();
		
			//leer por el id
			Optional<Alumno> aOptional = this.alumnoRepository.findById(id);
			if (aOptional.isPresent())
			{
				Alumno alumno_leido = aOptional.get();//PERSISTENCE
				//alumno_leido.setNombre(alumno.getNombre());
				BeanUtils.copyProperties(alumno, alumno_leido, "id", "creadoEn");
				//this.alumnoRepository.save(alumno_leido);
				optional = Optional.of(alumno_leido);
			}
			//si existe, lo modifico
		
		return optional;
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Alumno> findByEdadBetween(int edad_min, int edad_max) {
		return this.alumnoRepository.findByEdadBetween(edad_min, edad_max);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Alumno> findByNombreContaining(String patron) {
		return this.alumnoRepository.findByNombreContaining(patron);
	}

}
