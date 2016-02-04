package br.com.gedai.data;

import java.io.Serializable;
import java.util.Date;

public class DemandaTarefa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer idDemanda;
	private String nome;
	private String descricao;
	private Integer contador;
	private String uuid;
	private Usuario usuarioInclusao;
	private Date dataInclusao;
	
	public DemandaTarefa() {}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuarioInclusao() {
		return usuarioInclusao;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}



	public void setUsuarioInclusao(Usuario usuarioInclusao) {
		this.usuarioInclusao = usuarioInclusao;
	}



	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}



	public Integer getIdDemanda() {
		return idDemanda;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getContador() {
		return contador;
	}

	public String getUuid() {
		return uuid;
	}

	public void setIdDemanda(Integer idDemanda) {
		this.idDemanda = idDemanda;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setContador(Integer contador) {
		this.contador = contador;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
