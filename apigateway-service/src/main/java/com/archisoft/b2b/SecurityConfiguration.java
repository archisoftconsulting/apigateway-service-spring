package com.archisoft.b2b;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

public class SecurityConfiguration {
	// Below code is important to link between resource server and oauth2 server
		@Configuration
		@EnableResourceServer
		@EnableZuulProxy
		protected static class ResourceServerConfig extends ResourceServerConfigurerAdapter {

			@Autowired
			private AuthenticationManager authenticationManager;
			
			 @Override
			 public void configure(final HttpSecurity http) throws Exception {
			        // @formatter:off
			  http.authorizeRequests()
			  	.antMatchers(HttpMethod.POST,"/protected/**").access("#oauth2.hasScope('read')")
			    .anyRequest().permitAll();
			 }
			
		}
		
		//CSRF code TODO:
}
