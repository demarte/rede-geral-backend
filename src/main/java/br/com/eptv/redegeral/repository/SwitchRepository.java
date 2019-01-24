package br.com.eptv.redegeral.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.com.eptv.redegeral.models.Switch;

public interface SwitchRepository extends PagingAndSortingRepository<Switch, Long>{

	Optional<Switch> findByLocalizacaoId(@Param("id") long id);
	
	
}
