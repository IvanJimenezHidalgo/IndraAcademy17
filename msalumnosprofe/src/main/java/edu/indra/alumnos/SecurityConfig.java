package edu.indra.alumnos;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity	
@Configuration //para que Spring la cargue al levantar
public class SecurityConfig  extends WebSecurityConfigurerAdapter
{

	//1 método para definir usuarios y roles de mi micorservicio
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.inMemoryAuthentication().withUser("usuario1").password("{noop}usuario1").roles("USER").
		and().withUser("admin").password("{noop}admin").roles("USER", "ADMIN");
		//super.configure(auth);
		
		//os paso ejemplos para configuraciones con contraseñas encriptadas
				/*
				 * String pw1=new BCryptPasswordEncoder().encode("user1");
				 * System.out.println(pw1); auth.inMemoryAuthentication() .withUser("user1")
				 * .password("{bcrypt}"+pw1) //.password(pw1) .roles("USER") .and()
				 * .withUser("admin") .password(new BCryptPasswordEncoder().encode("admin"))
				 * .roles("USER", "ADMIN"); 
				 */
				
				//otra configuracion con usuarios en base de datos
				
				/*
				 * auth.jdbcAuthentication().dataSource(datasourceSecurity)
				 * .usersByUsernameQuery("select username, password, enabled" +
				 * " from users where username=?")
				 * .authoritiesByUsernameQuery("select username, authority " +
				 * "from authorities where username=?");
				 */
	}
	
	
	//2 método para definir las restricciones de seguridad
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//sólo dejamos borrar alumnos a usuarios con rol ADMIN
		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.DELETE, "/alumno/*").hasRole("ADMIN").and().httpBasic();
	}
}
