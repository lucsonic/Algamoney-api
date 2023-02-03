package com.lsinformatica.algamoney.dto;

import java.io.Serializable;

import com.lsinformatica.algamoney.entities.Categoria;

public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L; 
	
	private Long codigo;
	private String nome;
	
	public CategoriaDTO() {
	}
	
	public CategoriaDTO(Long codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}
	
	public CategoriaDTO(Categoria entity ) {
		this.codigo = entity.getCodigo();
		this.nome = entity.getNome();
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
}
