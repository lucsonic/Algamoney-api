package com.lsinformatica.algamoney.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lsinformatica.algamoney.dto.UsuarioDTO;
import com.lsinformatica.algamoney.entities.Pessoa;
import com.lsinformatica.algamoney.entities.Usuario;
import com.lsinformatica.algamoney.repositories.UsuarioRepository;
import com.lsinformatica.algamoney.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return repository.findAll();
	}
	
	@Transactional(readOnly = true)
	public UsuarioDTO findById(Long codigo) {
		Optional<Usuario> obj = repository.findById(codigo);
		Usuario entity = obj.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
		return new UsuarioDTO(entity);
	}

	public UsuarioDTO insert(UsuarioDTO dto) {
		Usuario entity = new Usuario();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);

		return new UsuarioDTO(entity);
	}	

	@Transactional
	public UsuarioDTO update(Long codigo, UsuarioDTO dto) {
		try {
			Usuario entity = repository.getReferenceById(codigo);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);

			return new UsuarioDTO(entity);
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
	
	private void copyDtoToEntity(UsuarioDTO dto, Usuario entity) {
		entity.setNome(dto.getNome());
		entity.setCpf(dto.getCpf().replaceAll("[^0-9]", ""));
		entity.setLogin(dto.getLogin());
		entity.setPassword(encoder.encode(dto.getPassword()));
	}
}
