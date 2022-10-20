package edu.indra.comun.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;


@Entity
@Table(name = "cursos")
public class Curso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //autoinc en mysql
	private Long id;
	
	private String nombre;
	
	//private List<Alumno> alumnos;
	
	@PrePersist//antes que se inserte un alumno en base datos, se ejecuta el método así anotado
	private void generarFechaCreacion ()
	{
		this.creadoEn = new Date();//obtengo la fecha actual
	}
	
	@Column(name = "creado_en")
	private Date creadoEn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getCreadoEn() {
		return creadoEn;
	}

	public void setCreadoEn(Date creadoEn) {
		this.creadoEn = creadoEn;
	}
	
	public Curso() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Curso [id=" + id + ", nombre=" + nombre + ", creadoEn=" + creadoEn + "]";
	}

	
}
