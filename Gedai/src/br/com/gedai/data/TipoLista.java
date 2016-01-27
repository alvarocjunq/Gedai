package br.com.gedai.data;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("TipoLista")
public class TipoLista implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String descricao;
	
	public TipoLista() {
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
