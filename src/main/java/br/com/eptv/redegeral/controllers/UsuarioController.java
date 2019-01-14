package br.com.eptv.redegeral.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eptv.redegeral.models.Usuario;
import br.com.eptv.redegeral.repository.UsuarioRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("user")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	public ResponseEntity<?> getAllUsers() {
		Iterable<Usuario> lista = usuarioRepository.findAll();
		return new ResponseEntity<>(lista, HttpStatus.OK);						
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Usuario usuario) {
		try {			
			String senha = usuario.getSenha();
			usuario.setSenha(new BCryptPasswordEncoder().encode(senha));
			return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		
		if (!usuarioRepository.findById(id).isPresent())
			return new ResponseEntity<>(ResponseEntity.notFound().build(), HttpStatus.NOT_FOUND);
		
		usuarioRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
