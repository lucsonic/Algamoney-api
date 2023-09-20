package com.lsinformatica.algamoney.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lsinformatica.algamoney.entities.Estados;
import com.lsinformatica.algamoney.repositories.EstadosRepository;

@Service
public class EstadosService {
	
	@Autowired
	private EstadosRepository repository;

	@Transactional(readOnly = true)
	public List<Estados> findAll() {
		return repository.findAll();
	}
}
