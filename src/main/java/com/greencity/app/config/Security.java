package com.greencity.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.greencity.app.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {

		return new JwtAuthenticationFilter();
	}

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Autowired
	public AuthenticationManager authenticationManagerBean() throws Exception {

		return super.authenticationManagerBean();
	}

	public void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.cors();
		httpSecurity.csrf().disable().authorizeRequests().antMatchers("/api/auth/**").permitAll();
		httpSecurity.csrf().disable().authorizeRequests().antMatchers("/api/public/**").permitAll();
//		httpSecurity.csrf().disable().authorizeRequests().antMatchers("/api/profile/user/**").permitAll();
//		httpSecurity.csrf().disable().authorizeRequests().antMatchers("/api/profile/collectionCenter/**").permitAll();
//		httpSecurity.csrf().disable().authorizeRequests().antMatchers("/api/profile/collectionCenter/collectionCenter").permitAll();
		httpSecurity.csrf().disable().authorizeRequests().antMatchers("/api/profile/user/**").access("hasRole('USER') or hasRole('ADMIN')");
		httpSecurity.csrf().disable().authorizeRequests().antMatchers("/api/profile/collectionCenter/**").access("hasRole('COLLECTION_CENTER') or hasRole('ADMIN')");
		httpSecurity.csrf().disable().authorizeRequests().antMatchers("/api/request/**").access("hasRole('COLLECTION_CENTER') or hasRole('USER') or hasRole('ADMIN')");
		
		
		httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}
}