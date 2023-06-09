package com.penta.assignment.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.penta.assignment.service.UserService;

@EnableWebSecurity
public class SecurityConfiguration  {

	@Autowired
	private UserService userService;
	   @Autowired
	    private UserDetailsService userDetailsService;
	
//	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		 auth.userDetailsService(userService);
	}
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception{
//		http.csrf().disable().authorizeHttpRequests().requestMatchers("/regestered","/auth")
//		.permitAll().anyRequest().authenticated();
//		 
//	}
	
//	@Override
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeHttpRequests().requestMatchers("/regestered","/auth")
		.permitAll().anyRequest().authenticated().and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and();//httpBasic();
		return http.build();
		 
	}
	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		
//		return NoOpPasswordEncoder.getInstance();
//	}
//	@Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
//	@Override
//	@Bean
//	protected AuthenticationManager authenticationManager() throws Exception {
////		return super.authenticationManager();
//		return build();
//		}
}
