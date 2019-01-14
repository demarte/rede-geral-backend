package br.com.eptv.redegeral.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.eptv.redegeral.models.Usuario;
import br.com.eptv.redegeral.repository.UsuarioRepository;

@Repository
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		//Optional verifica se é nulo e lança a exception
		Usuario usuario = usuarioRepository.findByLogin(login);
		Optional.ofNullable(usuarioRepository.findByLogin(login))
			.orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado"));
		
		return usuario;
	}


}
