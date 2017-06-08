package com.archisoft.b2b;

import java.security.KeyPair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

public class ClientConfiguration{
	@Configuration
	@EnableAuthorizationServer
	protected static class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

	   // @Autowired
	    @Qualifier("authenticationManagerBean")
	    private AuthenticationManager authenticationManager;

	    @Bean
	    public JwtAccessTokenConverter jwtAccessTokenConverter() {
	        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	        KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("keystore.jks"), "foobar".toCharArray())
	                .getKeyPair("test");
	        converter.setKeyPair(keyPair);
	        return converter;
	    }

	    @Override
	    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	        clients.inMemory()
	               .withClient("b2b")
		               .secret("b2bsecret")
		               .scopes("b2b")
		               .accessTokenValiditySeconds(120) 
		               .authorizedGrantTypes("authorization_code", "refresh_token","password")
		               .autoApprove(true)
	        		.and()
	        		.withClient("implicit")
		               .secret("implicitsecret")
		               .scopes("implicit")
		               .accessTokenValiditySeconds(120)
		               .authorizedGrantTypes("implicit")
		               .autoApprove(true)
	        		.and()
	        		.withClient("password")
		               .secret("passwordsecret")
		               .scopes("password")
		               .accessTokenValiditySeconds(120)
		               .authorizedGrantTypes("password")
		               .autoApprove(true);
	        	   
	    }

	    @Override
	    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	        endpoints.authenticationManager(authenticationManager).accessTokenConverter(jwtAccessTokenConverter()).tokenStore(tokenStore());
	    }
	    
	    @Bean
	    public TokenStore tokenStore() {
	        return new JwtTokenStore(jwtAccessTokenConverter());
	    }
	
	    @Bean
	    @Primary
	    public DefaultTokenServices tokenServices() {
	        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
	        defaultTokenServices.setTokenStore(tokenStore());
	        defaultTokenServices.setSupportRefreshToken(true);
	        return defaultTokenServices;
	    }

	    @Override
	    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
	        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	    }
	    
	    @Configuration
		@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
		protected static class UserConfiguration extends WebSecurityConfigurerAdapter {

			@Autowired
			public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
				// @formatter:off
				auth.inMemoryAuthentication()
					.withUser("reader").password("password").roles("READER")
				.and()
					.withUser("admin").password("password").roles("READER", "WRITER")
				.and()
					.withUser("writer").password("password").roles("WRITER");
	}
	}
	}
	
}
