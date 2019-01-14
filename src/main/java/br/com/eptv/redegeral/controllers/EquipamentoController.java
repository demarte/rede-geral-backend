package br.com.eptv.redegeral.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eptv.redegeral.models.Equipamento;
import br.com.eptv.redegeral.repository.EquipamentoRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("device")
public class EquipamentoController {
	
	@Autowired
	private EquipamentoRepository equipamentoRepository;
		
	@GetMapping
	public ResponseEntity<?> getAllDevices(Pageable pageable) {
		
		Iterable<Equipamento> lista = equipamentoRepository.findAll(pageable);
		return new ResponseEntity<>(lista, HttpStatus.OK);				
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getDevice(@PathVariable Long id) {
			
		if (!equipamentoRepository.findById(id).isPresent())
			return ResponseEntity.notFound().build();
		
		return new ResponseEntity<>(equipamentoRepository.findById(id), HttpStatus.OK);
		
	}
	

}
