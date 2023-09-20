package com.lsinformatica.algamoney.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lsinformatica.algamoney.dto.UsuarioDTO;
import com.lsinformatica.algamoney.entities.Pessoa;
import com.lsinformatica.algamoney.entities.Usuario;
import com.lsinformatica.algamoney.repositories.UsuarioRepository;
import com.lsinformatica.algamoney.services.UsuarioService;

@RestController
@RequestMapping("usuarios")
public class UsuarioResource {
	
	private final UsuarioRepository repository;
    private final PasswordEncoder encoder;
    
    public UsuarioResource(UsuarioRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }
	
	@Autowired
	private UsuarioService service;

	@GetMapping
	public ResponseEntity<List<Usuario>> findAll() {
		List<Usuario> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{codigo}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable Long codigo) {
		UsuarioDTO dto = service.findById(codigo);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> insert(@RequestBody UsuarioDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}").buildAndExpand(dto.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{codigo}")
	public ResponseEntity<UsuarioDTO> update(@PathVariable Long codigo, @RequestBody UsuarioDTO dto) {
		dto = service.update(codigo, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{codigo}")
	public ResponseEntity<UsuarioDTO> delete(@PathVariable Long codigo) {
		service.delete(codigo);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/validarSenha")
    public ResponseEntity<Boolean> validarSenha(@RequestParam String login, @RequestParam String password) {

        Optional<Usuario> optUsuario = repository.findByLogin(login);
        if (optUsuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        Usuario usuario = optUsuario.get();
        boolean valid = encoder.matches(password, usuario.getPassword());

        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(valid);

    }
}
