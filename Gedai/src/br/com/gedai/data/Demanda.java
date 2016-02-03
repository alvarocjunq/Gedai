package br.com.gedai.data;

import java.io.Serializable;
import java.util.List;


public class Demanda implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer progresso;
	private String nome;
	private String descricao;
	private Integer idArea;
	private List<DemandaLista> lstProgressoRacional;
	private String uuid;
	
	public Demanda() {	}

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

	public Integer getProgresso() {
		return progresso;
	}

	public void setProgresso(Integer progresso) {
		this.progresso = progresso;
	}

	public List<DemandaLista> getLstProgressoRacional() {
		return lstProgressoRacional;
	}

	public void setLstProgressoRacional(List<DemandaLista> lstProgressoRacional) {
		this.lstProgressoRacional = lstProgressoRacional;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getIdArea() {
		return idArea;
	}

	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	
	
}
