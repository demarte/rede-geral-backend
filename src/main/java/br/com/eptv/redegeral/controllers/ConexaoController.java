package br.com.eptv.redegeral.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eptv.redegeral.models.Conexao;
import br.com.eptv.redegeral.repository.ConexaoRepository;
import br.com.eptv.redegeral.repository.EquipamentoRepository;
import br.com.eptv.redegeral.repository.SwitchRepository;


@RestController
@RequestMapping("connection")
public class ConexaoController {

	@Autowired
	private ConexaoRepository conexaoRepository;
	
	@Autowired
	private EquipamentoRepository equipamentoRepository;
	
	@Autowired
	private SwitchRepository switchRepository;
	
	
	@GetMapping
	public ResponseEntity<?> getConnections(Pageable pageable) {
		
		Iterable<Conexao> lista = conexaoRepository.findAll(pageable);
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/switch/{id}")
	public ResponseEntity<?> getConnectionBySwitch(Pageable pageable, @PathVariable long id) {
		
		Page<Conexao> lista = conexaoRepository.findBySwIdOrderByPorta(pageable, id);
		
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@Valid @RequestBody Conexao conexao) {
				
		if (!switchRepository.findById(conexao.getSw().getId()).isPresent())
			return ResponseEntity.badRequest().build();
		
		equipamentoRepository.save(conexao.getEquipamento());
		return new ResponseEntity<>(conexaoRepository.save(conexao), HttpStatus.CREATED);
	} 
	
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		
		Optional<Conexao> conexao = conexaoRepository.findById(id);
		if (!conexao.isPresent())
			return ResponseEntity.notFound().build();
		
		conexaoRepository.deleteById(id);
		//equipamentoRepository.deleteById(conexao.get().getEquipamento().getId());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

}
