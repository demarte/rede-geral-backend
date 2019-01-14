package br.com.eptv.redegeral.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.eptv.redegeral.models.Usuario;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {

	Usuario findByLogin(String login);
	
}
