package br.com.gedai.data;

import java.io.Serializable;

import br.com.gedai.utils.StringUtils;

public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private String usuario;
	private String senha;
	
	public Usuario() {}

	
	public Integer getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}

	public String getUsuario() {
		return usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String toString() {
		return StringUtils.concat("Usuario=", usuario, ", id=", id, ", nome=", nome);
	}
}
