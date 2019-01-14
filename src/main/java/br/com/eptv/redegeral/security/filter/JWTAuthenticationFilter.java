package br.com.eptv.redegeral.security.filter;

import java.io.IOException;
import java.sql.Date;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.eptv.redegeral.models.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private AuthenticationManager authenticationManager;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
    //acessa o sistema com login e senha
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			Usuario user = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
			
			return this.authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getSenha()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	//gera o token
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		ZonedDateTime expTimeUTC = ZonedDateTime.now(ZoneOffset.UTC).plus(Constants.EXPIRATION, ChronoUnit.MILLIS);
		String token = Jwts.builder()
							.setSubject(((Usuario) authResult.getPrincipal()).getLogin())
							.setExpiration(Date.from(expTimeUTC.toInstant()))
							.signWith(SignatureAlgorithm.HS256, Constants.SECRET)
							.compact();
		token = Constants.TOKEN_PREFIX + token;
		response.getWriter().write(token);
		response.addHeader("Content-Type", "Text");
		response.addHeader(Constants.HEADER, token);
		
		
	}
	
	
}





