package br.com.gedai.data;

import java.io.Serializable;

public class StatusAtividade implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String descricao;
	
	public StatusAtividade() {
	}

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
