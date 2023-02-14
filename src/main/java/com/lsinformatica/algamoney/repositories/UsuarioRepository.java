package com.lsinformatica.algamoney.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lsinformatica.algamoney.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	public Optional<Usuario> findByLogin(String login);
	
}
