package br.com.eptv.redegeral.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.com.eptv.redegeral.models.Conexao;

public interface ConexaoRepository extends PagingAndSortingRepository<Conexao, Long> {

	Page<Conexao> findBySwIdOrderByPorta(Pageable pageable, @Param("id") long id);
	
	
}
