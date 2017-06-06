package com.archisoft.b2b;

import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

public class SecurityConfiguration {

	@Configuration
	@EnableResourceServer
	@EnableZuulProxy
	protected static class PermissionConfiguration extends ResourceServerConfigurerAdapter {

		@Override
	    public void configure(ResourceServerSecurityConfigurer config) {
	        config.tokenServices(tokenServices());
	    }
	 
	    @Bean
	    public TokenStore tokenStore() {
	        return new JwtTokenStore(accessTokenConverter());
	    }

//	    @Bean
//	    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//	        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//	        KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("keystore.jks"), "foobar".toCharArray())
//	                .getKeyPair("test");
//	        converter.setKeyPair(keyPair);
//	        return converter;
//	    }
	    
	    @Bean
	    public JwtAccessTokenConverter accessTokenConverter() {
	        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	        Resource resource = new ClassPathResource("public.txt");
	        String publicKey = null;
	        try {
	            publicKey = IOUtils.toString(resource.getInputStream());
	        } catch (final IOException e) {
	            throw new RuntimeException(e);
	        }
	        converter.setVerifierKey(publicKey);
	        return converter;
	    }
	 
	 
	    @Bean
	    @Primary
	    public DefaultTokenServices tokenServices() {
	        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
	        defaultTokenServices.setTokenStore(tokenStore());
	        return defaultTokenServices;
	    }
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
			//TESTING222222333
			

			http.authorizeRequests().antMatchers(HttpMethod.GET, "/protected").hasRole("READER")	
			.antMatchers(HttpMethod.POST, "/protected").hasRole("WRITER")
			.anyRequest()
					.permitAll();

		}

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
