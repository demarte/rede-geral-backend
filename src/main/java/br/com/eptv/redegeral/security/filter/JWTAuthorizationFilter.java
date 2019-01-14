package br.com.eptv.redegeral.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import br.com.eptv.redegeral.security.service.CustomUserDetailsService;
import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{

	private final CustomUserDetailsService customUserDetailsService;
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, 
								  CustomUserDetailsService customUserDetailsService) {
		super(authenticationManager);
		this.customUserDetailsService = customUserDetailsService;
		
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
									HttpServletResponse response, 
									FilterChain chain)throws IOException, ServletException {
		
		String header = request.getHeader(Constants.HEADER);
		if(header == null || !header.startsWith(Constants.TOKEN_PREFIX)) {
			return;
		}
		
		UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(request, header);
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		
		chain.doFilter(request, response);
	}
	
	private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request, String token) {
		String username = Jwts.parser()
							   .setSigningKey(Constants.SECRET)
							   .parseClaimsJws(token.replace(Constants.TOKEN_PREFIX, ""))
							   .getBody()
							   .getSubject();

		UserDetails usuario = customUserDetailsService.loadUserByUsername(username);
		return usuario != null ? 
				new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities()) 
				: null;
								
	}
}
