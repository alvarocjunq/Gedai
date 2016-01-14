package br.com.gedai.data;

import java.io.Serializable;
import java.util.List;

public class DemandaLista implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer idDemanda;
	private Integer qtd;
	private TipoLista tipoLista;
	private String nome;
	private List<DemandaListaAtividade> lstAtividades;
	
	public DemandaLista() {
		tipoLista = new TipoLista();
	}

	public Integer getId() {
		return id;
	}

	public Integer getIdDemanda() {
		return idDemanda;
	}

	public TipoLista getTipoLista() {
		return tipoLista;
	}

	public String getNome() {
		return nome;
	}
	
	public List<DemandaListaAtividade> getLstAtividades() {
		return lstAtividades;
	}

	public void setLstAtividades(List<DemandaListaAtividade> lstAtividades) {
		this.lstAtividades = lstAtividades;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIdDemanda(Integer idDemanda) {
		this.idDemanda = idDemanda;
	}

	public void setTipoLista(TipoLista tipoLista) {
		this.tipoLista = tipoLista;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQtd() {
		return qtd;
	}

	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}
}
