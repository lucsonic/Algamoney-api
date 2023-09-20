package com.lsinformatica.algamoney.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lsinformatica.algamoney.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
