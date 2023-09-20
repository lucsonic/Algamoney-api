package com.lsinformatica.algamoney.dto;

import java.io.Serializable;

import com.lsinformatica.algamoney.entities.Categoria;
import com.lsinformatica.algamoney.entities.Estados;

public class EstadosDTO implements Serializable {
	private static final long serialVersionUID = 1L; 
	
	private Long codigo;
	private String sigla;
	private String descricao;
	
	public EstadosDTO() {
	}
	
	public EstadosDTO(Long codigo, String sigla, String descricao) {
		this.codigo = codigo;
		this.sigla = sigla;
		this.descricao = descricao;
	}
	
	public EstadosDTO(Estados entity) {
		this.codigo = entity.getCodigo();
		this.sigla = entity.getSigla();
		this.descricao = entity.getDescricao();
	}
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
