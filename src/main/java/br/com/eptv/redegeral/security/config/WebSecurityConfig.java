package br.com.eptv.redegeral.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import br.com.eptv.redegeral.security.filter.Constants;
import br.com.eptv.redegeral.security.filter.JWTAuthenticationFilter;
import br.com.eptv.redegeral.security.filter.JWTAuthorizationFilter;
import br.com.eptv.redegeral.security.service.CustomUserDetailsService;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity
				.csrf()
				.disable()
				.cors()
				.and()
				.authorizeRequests().anyRequest().authenticated()
				.and()
				.addFilter(new JWTAuthenticationFilter(authenticationManager()))
				.addFilter(new JWTAuthorizationFilter(authenticationManager(), userDetailsService));
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder()); // como o password
																									// esta
																									// criptografado no
																									// banco
	}

	@Override // exclui o path /connection dos filtros
	public void configure(WebSecurity web) throws Exception {
		web
			//.ignoring().antMatchers(HttpMethod.GET, "/connection/**")
			//.and()
			.ignoring().antMatchers(HttpMethod.GET, "/user/**")
			.and()
			.ignoring().antMatchers(HttpMethod.POST, "/user/**");
	}
	
	@Bean
	public CorsFilter corsFilter() {
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    CorsConfiguration config = new CorsConfiguration();
	    config.setAllowCredentials(true);
	    config.addAllowedOrigin("http://localhost:4200");
	    config.addExposedHeader("Authorization,"+Constants.HEADER+", Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, " +
	            "Content-Type, Access-Control-Request-Method, Custom-Filter-Header");
	    config.addAllowedHeader("*");
	    config.addAllowedMethod("OPTIONS");
	    config.addAllowedMethod("GET");
	    config.addAllowedMethod("POST");
	    config.addAllowedMethod("PUT");
	    config.addAllowedMethod("DELETE");
	    source.registerCorsConfiguration("/**", config);
	    return new CorsFilter(source);
	}
}