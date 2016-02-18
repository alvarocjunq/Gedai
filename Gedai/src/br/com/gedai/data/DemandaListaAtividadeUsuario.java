package br.com.gedai.data;

import java.io.Serializable;

public class DemandaListaAtividadeUsuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer idDemandaListaAtividade;
	private Usuario usuario;
	
	public DemandaListaAtividadeUsuario() {
		usuario = new Usuario();
	}
	
	public DemandaListaAtividadeUsuario(Usuario usuario, Integer idDemandaListaAtividade) {
		this.idDemandaListaAtividade = idDemandaListaAtividade;
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

	public Integer getIdDemandaListaAtividade() {
		return idDemandaListaAtividade;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIdDemandaListaAtividade(Integer idDemandaListaAtividade) {
		this.idDemandaListaAtividade = idDemandaListaAtividade;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
