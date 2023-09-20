package com.lsinformatica.algamoney.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lsinformatica.algamoney.entities.Estados;
import com.lsinformatica.algamoney.services.EstadosService;

@RestController
@RequestMapping("/estados")
public class EstadosResource {
	
	@Autowired
	private EstadosService service;
	
	@GetMapping
	public ResponseEntity<List<Estados>> findAll() {
		List<Estados> list = service.findAll();

		return ResponseEntity.ok().body(list);
	}

}
