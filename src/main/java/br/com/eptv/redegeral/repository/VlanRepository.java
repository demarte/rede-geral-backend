package br.com.eptv.redegeral.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.eptv.redegeral.models.Vlan;

public interface VlanRepository extends PagingAndSortingRepository<Vlan, Long>{

}
