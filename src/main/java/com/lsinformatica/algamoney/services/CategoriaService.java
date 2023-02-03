package com.lsinformatica.algamoney.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.lsinformatica.algamoney.dto.CategoriaDTO;
import com.lsinformatica.algamoney.entities.Categoria;
import com.lsinformatica.algamoney.repositories.CategoriaRepository;
import com.lsinformatica.algamoney.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	@CrossOrigin
	@Transactional(readOnly = true)
	public Page<CategoriaDTO> findAllPaged(PageRequest pageRequest) {
		Page<Categoria> list = repository.findAll(pageRequest);

		return list.map(x -> new CategoriaDTO(x));
	}

	@Transactional(readOnly = true)
	public CategoriaDTO findById(Long codigo) {
		Optional<Categoria> obj = repository.findById(codigo);
		Categoria entity = obj.orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));
		return new CategoriaDTO(entity);
	}

	public CategoriaDTO insert(CategoriaDTO dto) {
		Categoria entity = new Categoria();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);

		return new CategoriaDTO(entity);
	}	

	@Transactional
	public CategoriaDTO update(Long codigo, CategoriaDTO dto) {
		try {
			Categoria entity = repository.getReferenceById(codigo);
			entity.setNome(dto.getNome());
			entity = repository.save(entity);

			return new CategoriaDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Código " + codigo + " não encontrado");
		}
	}

	public void delete(Long codigo) {
		try {
			repository.deleteById(codigo);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Código " + codigo + " não encontrado");
		}
	}
	
	private void copyDtoToEntity(CategoriaDTO dto, Categoria entity) {
		entity.setNome(dto.getNome());
	}
}
