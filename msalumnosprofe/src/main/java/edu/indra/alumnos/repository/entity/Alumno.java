package edu.indra.alumnos.repository.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "alumnos")//esta clase la relacionamos con la tabla alumnos de la base de datos
public class Alumno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //autoinc en mysql
	private Long id;//CLAVE PRIMARY 
	
	private String nombre;
	private String apellido;
	private String email;
	private int edad;
	
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public Date getCreadoEn() {
		return creadoEn;
	}

	public void setCreadoEn(Date creadoEn) {
		this.creadoEn = creadoEn;
	}
	
	
	public Alumno() {
		// TODO Auto-generated constructor stub
	}

	public Alumno(Long id, String nombre, String apellido, String email, int edad, Date creadoEn) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.edad = edad;
		this.creadoEn = creadoEn;
	}

	@Override
	public String toString() {
		return "Alumno [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", edad="
				+ edad + ", creadoEn=" + creadoEn + "]";
	}
	
	

}
