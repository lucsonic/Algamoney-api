package com.lsinformatica.algamoney.repositories;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lsinformatica.algamoney.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	public Optional<Categoria> findByNome(String nome);
}
