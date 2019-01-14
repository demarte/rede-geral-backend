package br.com.eptv.redegeral.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eptv.redegeral.models.Localizacao;
import br.com.eptv.redegeral.models.Switch;
import br.com.eptv.redegeral.repository.LocalizacaoRepository;
import br.com.eptv.redegeral.repository.SwitchRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("place")
public class LocalizacaoController {

	@Autowired
	private LocalizacaoRepository localizacaoRepository;
	
	@Autowired
	private SwitchRepository switchRepository;

	@GetMapping
	public ResponseEntity<?> getAllPlaces() {
		
		Iterable<Localizacao> lista = localizacaoRepository.findAll();
		return new ResponseEntity<>(lista, HttpStatus.OK);				
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Localizacao localizacao) {
		try {			
			return new ResponseEntity<>(localizacaoRepository.save(localizacao), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		
		if(!localizacaoRepository.findById(id).isPresent())
			return new ResponseEntity<>(ResponseEntity.notFound().build(),HttpStatus.NOT_FOUND);
		
		Optional<Switch> sw = switchRepository.findByLocalizacaoId(id);
		if(sw.isPresent()) 
			sw.get().setLocalizacao(null);
				
		localizacaoRepository.deleteById(id);

		return new ResponseEntity<>(ResponseEntity.ok().build(),HttpStatus.OK);
	}

}
