package com.lsinformatica.algamoney.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lsinformatica.algamoney.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	public Optional<Usuario> findByLogin(String login);
	public Optional<Usuario> findByCpf(String cpf);
}
