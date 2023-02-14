package com.lsinformatica.algamoney.dto;

import java.io.Serializable;

import com.lsinformatica.algamoney.entities.Usuario;

public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L; 
	
	private Long codigo;
	private String nome;
	private String cpf;
	private String login;
	private String password;
	
	public UsuarioDTO() {		
	}

	public UsuarioDTO(Long codigo, String nome, String cpf, String login, String password) {
		this.codigo = codigo;
		this.nome = nome;
		this.cpf = cpf;
		this.login = login;
		this.password = password;
	}
	
	public UsuarioDTO(Usuario entity ) {
		this.codigo = entity.getCodigo();
		this.nome = entity.getNome();
		this.cpf = entity.getCpf();
		this.login = entity.getLogin();
		this.password = entity.getPassword();
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
