package com.archisoft.b2b;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

public class SecurityConfiguration {

	@Configuration
	@EnableResourceServer
	@EnableZuulProxy
	protected static class PermissionConfiguration extends ResourceServerConfigurerAdapter {

		// @Autowired
		// private AuthenticationManager authenticationManager;

		@Override
		public void configure(final HttpSecurity http) throws Exception {
			// @formatter:off
			// http.authorizeRequests()
			// .antMatchers(HttpMethod.GET,"/readonly/**").access("#oauth2.hasScope('b2b')
			// and hasRole('READ')")
			//
			// .antMatchers(HttpMethod.POST,"/writeonly/**").access("#oauth2.hasScope('b2b')
			// and hasRole('WRITE')")
			// .anyRequest().permitAll();
			
<<<<<<< HEAD
<<<<<<< HEAD
			 @Override
			 public void configure(final HttpSecurity http) throws Exception {
			        // @formatter:off
//			  http.authorizeRequests()
//			  	.antMatchers(HttpMethod.GET,"/readonly/**").access("#oauth2.hasScope('b2b') and hasRole('READ')")
//			  	
//			  	.antMatchers(HttpMethod.POST,"/writeonly/**").access("#oauth2.hasScope('b2b') and hasRole('WRITE')")
//			    .anyRequest().permitAll();
			  
			  http.authorizeRequests()
			  	.antMatchers(HttpMethod.POST, "/protected").hasRole("WRITER")
			  	.antMatchers(HttpMethod.GET, "/protected").hasRole("READER")
			  	//.anyRequest().authenticated();
			  	.anyRequest().permitAll();
			  
			  
			 }
			
=======
=======

			http.authorizeRequests().antMatchers(HttpMethod.GET, "/protected").hasRole("READER")	
			.antMatchers(HttpMethod.POST, "/protected").hasRole("WRITER")
			.anyRequest()
					.permitAll();

		}
>>>>>>> branch 'master' of https://github.com/archisoftconsulting/apigateway-service-spring

<<<<<<< HEAD
			http.authorizeRequests().antMatchers(HttpMethod.GET, "/protected").hasRole("READER")	
			.antMatchers(HttpMethod.POST, "/protected").hasRole("WRITER")
			.anyRequest()
					.permitAll();

>>>>>>> branch 'master' of https://github.com/archisoftconsulting/apigateway-service-spring
		}

=======
>>>>>>> branch 'master' of https://github.com/archisoftconsulting/apigateway-service-spring
	}

//	@Configuration
//	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
//	protected static class UserConfiguration extends WebSecurityConfigurerAdapter {
//
//		@Autowired
//		public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
//			// @formatter:off
//			auth.inMemoryAuthentication().withUser("reader").password("password").roles("READER").and()
//					.withUser("admin").password("password").roles("READER", "WRITER").and().withUser("writer")
//					.password("password").roles("WRITER");
//		}
//		// CSRF code TODO:
//	}
}
