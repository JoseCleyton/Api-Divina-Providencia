package com.br.apiDivinaProvidencia.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.br.apiDivinaProvidencia.filter.CorsFilter;
import com.br.apiDivinaProvidencia.filter.JwtFilter;
import com.br.apiDivinaProvidencia.services.JwtService;
import com.br.apiDivinaProvidencia.services.impl.UserServiceImpl;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private JwtService jwtService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public OncePerRequestFilter jwtFilter() {
		return new JwtFilter(jwtService, userServiceImpl);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userServiceImpl).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers("/orders/**").hasRole("ADMIN").antMatchers("/products/**")
				.hasRole("ADMIN").antMatchers("/cashier/**").hasRole("ADMIN").antMatchers("/stock/**").hasRole("ADMIN")
				.antMatchers("/client/**").hasRole("ADMIN").antMatchers("/reportCashier/**").hasRole("ADMIN")
				.antMatchers("/accountsReceivable/**").hasRole("ADMIN").antMatchers(HttpMethod.POST, "/user/**")
				.permitAll().anyRequest().authenticated().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class);

	}

}
