package com.lsinformatica.algamoney.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
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

import com.lsinformatica.algamoney.dto.CategoriaDTO;
import com.lsinformatica.algamoney.entities.Categoria;
import com.lsinformatica.algamoney.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
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
