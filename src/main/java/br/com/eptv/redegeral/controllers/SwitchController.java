package br.com.eptv.redegeral.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eptv.redegeral.models.Switch;
import br.com.eptv.redegeral.repository.SwitchRepository;

@RestController
@RequestMapping("switch")
public class SwitchController {
	
	@Autowired
	private SwitchRepository switchRepository;

	@GetMapping
	public ResponseEntity<?> getAllSwitchs() {
		
		Iterable<Switch> lista = switchRepository.findAll();
		return new ResponseEntity<>(lista, HttpStatus.OK);				
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Switch sw) {
		try {			
			return new ResponseEntity<>(switchRepository.save(sw), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		
		if (!switchRepository.findById(id).isPresent())
			return ResponseEntity.notFound().build();
		
		switchRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
