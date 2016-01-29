package br.com.gedai.data;

import java.io.Serializable;

public class Area implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private Integer nivel;
	private Integer coluna;
	
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
	public Integer getNivel() {
		return nivel;
	}
	public Integer getColuna() {
		return coluna;
	}
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
	public void setColuna(Integer coluna) {
		this.coluna = coluna;
	}
}
