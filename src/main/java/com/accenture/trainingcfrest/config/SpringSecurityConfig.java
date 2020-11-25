package com.accenture.trainingcfrest.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.sap.cloud.security.xsuaa.XsuaaServiceConfiguration;
import com.sap.cloud.security.xsuaa.extractor.AuthenticationMethod;
import com.sap.cloud.security.xsuaa.extractor.TokenBrokerResolver;
import com.sap.cloud.security.xsuaa.token.TokenAuthenticationConverter;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	XsuaaServiceConfiguration xsuaaServiceConfiguration;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		//enforce browser login popup with basic authentication
		BasicAuthenticationEntryPoint authEntryPoint = new BasicAuthenticationEntryPoint();
		authEntryPoint.setRealmName("trainingcfrest");

		// @formatter:off
		http.authorizeRequests()
				//.antMatchers("/Product/testToken").hasAuthority("Display")
				.anyRequest().authenticated()
			.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.exceptionHandling().authenticationEntryPoint(authEntryPoint)
			.and()
				.oauth2ResourceServer()
				.bearerTokenResolver(getTokenBrokerResolver())
				.jwt()
				.jwtAuthenticationConverter(jwtAuthenticationConverter());

		// @formatter:on
	}

	BearerTokenResolver getTokenBrokerResolver() {
		Cache cache = new CaffeineCache("token",
				Caffeine.newBuilder()
						.expireAfterWrite(15, TimeUnit.MINUTES)
						.maximumSize(100).build(), false);

		return new TokenBrokerResolver(xsuaaServiceConfiguration, cache, AuthenticationMethod.BASIC);
	}


	@Bean
	Converter<Jwt, AbstractAuthenticationToken> jwtAuthenticationConverter() {
		TokenAuthenticationConverter converter = new TokenAuthenticationConverter(xsuaaServiceConfiguration);
		converter.setLocalScopeAsAuthorities(true);
		return converter;
	}

}