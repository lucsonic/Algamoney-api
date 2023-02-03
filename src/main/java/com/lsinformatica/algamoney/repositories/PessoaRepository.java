package com.lsinformatica.algamoney.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lsinformatica.algamoney.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
