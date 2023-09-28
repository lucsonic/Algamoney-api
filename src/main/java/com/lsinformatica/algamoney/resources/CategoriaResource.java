package com.lsinformatica.algamoney.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lsinformatica.algamoney.dto.CategoriaDTO;
import com.lsinformatica.algamoney.entities.Categoria;
import com.lsinformatica.algamoney.repositories.CategoriaRepository;
import com.lsinformatica.algamoney.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	@Autowired
	private CategoriaService service;
	
	private final CategoriaRepository repository;
	
	public CategoriaResource(CategoriaRepository repository) {
		this.repository = repository;
	}
	
//	@GetMapping
//	public ResponseEntity<Page<CategoriaDTO>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
//			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
//			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
//			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {
//
//		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
//
//		Page<CategoriaDTO> list = service.findAllPaged(pageRequest);
//		return ResponseEntity.ok().body(list);
//	}
	
	@GetMapping
	public ResponseEntity<List<Categoria>> findAll() {
		List<Categoria> list = service.findAll();

		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{codigo}")
	public ResponseEntity<CategoriaDTO> findById(@PathVariable Long codigo) {
		CategoriaDTO dto = service.findById(codigo);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping(value = "/buscanome")
	public ResponseEntity<CategoriaDTO> findByNome(@RequestBody CategoriaDTO requestBody) {
		String nome = requestBody.getNome();
		Optional<Categoria> optCategoria = repository.findByNome(nome);
		
		if (!optCategoria.isEmpty()) {
			Categoria categoria = optCategoria.get();
			CategoriaDTO dto = service.findById(categoria.getCodigo());
        	return ResponseEntity.ok().body(dto);
		}
		return null;
	}
	
	@PostMapping
	public ResponseEntity<CategoriaDTO> insert(@RequestBody CategoriaDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}").buildAndExpand(dto.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{codigo}")
	public ResponseEntity<CategoriaDTO> update(@PathVariable Long codigo, @RequestBody CategoriaDTO dto) {
		dto = service.update(codigo, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{codigo}")
	public ResponseEntity<CategoriaDTO> delete(@PathVariable Long codigo) {
		service.delete(codigo);
		return ResponseEntity.noContent().build();
	}
}
