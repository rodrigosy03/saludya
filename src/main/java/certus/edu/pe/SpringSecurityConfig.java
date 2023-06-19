package certus.edu.pe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Asignamos nuestras rutas publicas
		http.authorizeRequests()
		.antMatchers("/css/**", "/js/**", "/assets/**","/","principal","/home","/inicio","/logeo","login","/rest/**").permitAll()
		// Asignamos las rutas que queremos PROTEGER
		.antMatchers("/doctores/listar","/citas/listar","/especialidades/listar","/centros-medicos/listar","/areas-medicas/listar").hasAnyRole("USER","ADMIN","CREADOR","DEPURADOR","EDITOR","LECTOR")
		.antMatchers("/doctores/agregar","/especialidades/agregar","/centros-medicos/agregar","/areas-medicas/agregar","/usuarios/agregar").hasAnyRole("CREADOR","ADMIN")
		.antMatchers("/citas/agregar").hasAnyRole("ADMIN","USER","CREADOR")
		.antMatchers("/doctores/editar","/especialidades/editar","/centros-medicos/editar","/areas-medicas/editar","usuarios/editar","/citas/editar").hasAnyRole("EDITOR","ADMIN")
		.antMatchers("/doctores/eliminar/**","/especialidades/eliminar/**","/centros-medicos/eliminar/**","/areas-medicas/eliminar/**","/citas/eliminar/**","/usuarios/eliminar/**").hasAnyRole("DEPURADOR","ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login").defaultSuccessUrl("/principal", true).permitAll()
		.and()
		.logout().logoutSuccessUrl("/login?logout=true")
        .invalidateHttpSession(true).permitAll();
	}

	
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {
		
		PasswordEncoder encoder = 	PasswordEncoderFactories.createDelegatingPasswordEncoder();	
		// Creamos usuarios y asignamos roles
		builder.inMemoryAuthentication()
		.withUser("maria.gonzalez@example.com").password(encoder.encode("maria")).roles("USER").and()
		.withUser("admin@gmail.com").password(encoder.encode("admin")).roles("ADMIN").and()
		.withUser("editor@gmail.com").password(encoder.encode("editor")).roles("EDITOR").and()
		.withUser("lector@gmail.com").password(encoder.encode("lector")).roles("LECTOR").and()
		.withUser("creador@gmail.com").password(encoder.encode("creador")).roles("CREADOR").and()
		.withUser("depurador@gmail.com").password(encoder.encode("depurador")).roles("DEPURADOR");
		
		
	}
}
