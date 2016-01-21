package br.com.gedai.data;

import java.io.Serializable;

public class Area implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	
	public Integer getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
