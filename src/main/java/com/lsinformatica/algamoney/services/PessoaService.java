package com.lsinformatica.algamoney.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.lsinformatica.algamoney.dto.PessoaDTO;
import com.lsinformatica.algamoney.entities.Pessoa;
import com.lsinformatica.algamoney.repositories.PessoaRepository;
import com.lsinformatica.algamoney.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;

	@CrossOrigin
	@Transactional(readOnly = true)
	public Page<PessoaDTO> findAllPaged(PageRequest pageRequest) {
		Page<Pessoa> list = repository.findAll(pageRequest);

		return list.map(x -> new PessoaDTO(x));
	}

	@Transactional(readOnly = true)
	public PessoaDTO findById(Long codigo) {
		Optional<Pessoa> obj = repository.findById(codigo);
		Pessoa entity = obj.orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));
		return new PessoaDTO(entity);
	}

	public PessoaDTO insert(PessoaDTO dto) {
		Pessoa entity = new Pessoa();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);

		return new PessoaDTO(entity);
	}	

	@Transactional
	public PessoaDTO update(Long codigo, PessoaDTO dto) {
		try {
			Pessoa entity = repository.getReferenceById(codigo);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);

			return new PessoaDTO(entity);
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
	
	private void copyDtoToEntity(PessoaDTO dto, Pessoa entity) {
		entity.setNome(dto.getNome());
		entity.setAtivo(dto.getAtivo());
		entity.setLogradouro(dto.getLogradouro());
		entity.setNumero(dto.getNumero());
		entity.setComplemento(dto.getComplemento());
		entity.setBairro(dto.getBairro());
		entity.setCep(dto.getCep());
		entity.setCidade(dto.getCidade());
		entity.setEstado(dto.getEstado());
	}
}
