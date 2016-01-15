package br.com.gedai.data;

import java.io.Serializable;
import java.util.List;

public class Demanda implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer progresso;
	private String nome;
	private List<DemandaLista> lstProgressoRacional;
	
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
}
