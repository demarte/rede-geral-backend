package br.com.eptv.redegeral.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.eptv.redegeral.models.Equipamento;

public interface EquipamentoRepository extends PagingAndSortingRepository<Equipamento, Long> {

	List<Equipamento> findByNomeIgnoreCaseContaining(String nome);
	
}
