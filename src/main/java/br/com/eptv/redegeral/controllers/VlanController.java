package br.com.eptv.redegeral.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eptv.redegeral.models.Vlan;
import br.com.eptv.redegeral.repository.VlanRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("vlan")
public class VlanController {

	@Autowired
	private VlanRepository vlanRepository;

	@GetMapping
	public ResponseEntity<?> getAllUsers(Pageable pageable) {
		
		Iterable<Vlan> lista = vlanRepository.findAll(pageable);
		return new ResponseEntity<>(lista, HttpStatus.OK);				
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Vlan vlan) {
		try {
			return new ResponseEntity<>(vlanRepository.save(vlan), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		
		if (!vlanRepository.findById(id).isPresent())
			return new ResponseEntity<>(ResponseEntity.notFound().build(), HttpStatus.NOT_FOUND);
		
		vlanRepository.deleteById(id);
		return new ResponseEntity<>(ResponseEntity.ok().build(),HttpStatus.OK);
	}
}
