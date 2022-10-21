package edu.indra.comun.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;


@Entity
@Table(name = "cursos")
public class Curso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //autoinc en mysql
	private Long id;
	
	private String nombre;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Alumno> alumnos;
	
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
		this.alumnos = new ArrayList<Alumno>();
	}
	
	

	public Curso(Long id, String nombre, List<Alumno> alumnos, Date creadoEn) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.alumnos = new ArrayList<Alumno>();
		this.creadoEn = creadoEn;
	}

	@Override
	public String toString() {
		return "Curso [id=" + id + ", nombre=" + nombre + ", creadoEn=" + creadoEn + "]";
	}

	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public int getNumAlumnos ()
	{
		return this.alumnos.size();
	}
	
	public void addAlumno (Alumno alumno)
	{
		this.alumnos.add(alumno);
	}
	
	public void deleteAlumno (Alumno alumno)
	{
		this.alumnos.remove(alumno);//tira de equals
	}
	
}
