package com.auto.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{ 
	
	
	@Override
	   protected void configure(HttpSecurity http) throws Exception {
	      http
	         .authorizeRequests()
	            .antMatchers("/cars/**").permitAll()
	            .antMatchers("/swagger-ui.html").permitAll()
	            .antMatchers("/company/**").hasRole("ADMIN")
	            .anyRequest().authenticated();
	      http.httpBasic();
	      http.csrf().disable();
	      http.formLogin().disable();
	      http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	   }

	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

	
	@Bean
    	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
    	}
	
	
	
	   @Autowired
	   public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	      auth
	         .inMemoryAuthentication()
	         .withUser("user").password("password").roles("ADMIN");
	   }
	   
	   @Override
		 public void configure(WebSecurity web) throws Exception {
		     web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**");
		 }
}
