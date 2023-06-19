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
		.antMatchers("/cliente/listar").hasAnyRole("LECTOR","ADMIN","CREADOR","EDITOR","DEPURADOR")
		.antMatchers("/cliente/nuevo").hasAnyRole("ADMIN")
		.antMatchers("/cliente/guardar").hasAnyRole("ADMIN","CREADOR","EDITOR")
		.antMatchers("/cliente/actualizar/**").hasAnyRole("ADMIN","EDITOR")
		.antMatchers("/cliente/eliminar/**").hasAnyRole("ADMIN","DEPURADOR")
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
		.withUser("JESUS").password(encoder.encode("JESUS")).roles("ADMIN").and()
		.withUser("DANIEL").password(encoder.encode("DANIEL")).roles("EDITOR").and()
		.withUser("HUGO").password(encoder.encode("HUGO")).roles("LECTOR").and()
		.withUser("EDUARDO").password(encoder.encode("EDUARDO")).roles("CREADOR").and()
		.withUser("EDGAR").password(encoder.encode("EDGAR")).roles("DEPURADOR");
		
		
	}
}
