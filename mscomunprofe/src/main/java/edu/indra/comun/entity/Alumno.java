package edu.indra.comun.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.PrePersist;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "alumnos")//esta clase la relacionamos con la tabla alumnos de la base de datos
@NamedStoredProcedureQueries(
		{
			@NamedStoredProcedureQuery(name = "Alumno.alumnosEdadMediaMinMax" ,procedureName = "calcular_max_min_media_edad",
					parameters = {
							@StoredProcedureParameter(mode = ParameterMode.INOUT ,name="edadmax", type = Integer.class),
							@StoredProcedureParameter(mode = ParameterMode.INOUT ,name="edadmin", type = Integer.class),
							@StoredProcedureParameter(mode = ParameterMode.INOUT ,name="edadmedia", type = Float.class)
					}),
			@NamedStoredProcedureQuery(name = "Alumno.alumnosNombreComo", procedureName = "obtenerAlumnosConNombreComo", resultClasses = edu.indra.comun.entity.Alumno.class,
			parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name ="patron", type = String.class)
			}),
			@NamedStoredProcedureQuery(name = "Alumno.alumnosRegistradosHoy", procedureName = "obtenerAlumnosRegistradosHoy", resultClasses = edu.indra.comun.entity.Alumno.class)
		}
		)
public class Alumno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //autoinc en mysql
	private Long id;//CLAVE PRIMARY 
	
	@Size(min = 3, max = 15)
	private String nombre;
	
	@NotEmpty //esto que no sea null y tenga longitud > q 1
	private String apellido;
	
	@Email
	private String email;
	
	@Min(0)
	@Max(130)
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
	
	
	@Override
	public boolean equals(Object obj) {
		boolean iguales = false;
		
			if (this==obj)
			{
				iguales=true;
			} else if (obj instanceof Alumno) //(obj instanceof Alumno alumno) https://docs.oracle.com/en/java/javase/14/language/pattern-matching-instanceof-operator.html
			{
				Alumno alumno = (Alumno)obj;
				iguales = ((this.id!=null) && (this.id.equals(alumno.id)));
			} /*else {// opcional por inicializarlo a false
				iguales = false;
			}*/
		
		return iguales;
	}

}
