package br.com.gedai.data;

import java.io.Serializable;
import java.util.List;

public class DemandaListaAtividade implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer idDemandaLista;
	private StatusAtividade statusAtividade;
	private String nome;
	private String descricao;
	private List<DemandaListaAtividadeUsuario> lstAtividadeUsuario;
	
	public DemandaListaAtividade() {
		statusAtividade = new StatusAtividade();
	}

	public Integer getId() {
		return id;
	}

	public Integer getIdDemandaLista() {
		return idDemandaLista;
	}

	public StatusAtividade getStatusAtividade() {
		return statusAtividade;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIdDemandaLista(Integer idDemandaLista) {
		this.idDemandaLista = idDemandaLista;
	}

	public void setStatusAtividade(StatusAtividade statusAtividade) {
		this.statusAtividade = statusAtividade;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<DemandaListaAtividadeUsuario> getLstAtividadeUsuario() {
		return lstAtividadeUsuario;
	}

	public void setLstAtividadeUsuario(
			List<DemandaListaAtividadeUsuario> lstAtividadeUsuario) {
		this.lstAtividadeUsuario = lstAtividadeUsuario;
	}
}
