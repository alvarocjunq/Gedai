package br.com.gedai.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	private List<DemandaTarefaUsuario> lstUsuarioTarefa;
	
	public DemandaTarefa() {
		this.lstUsuarioTarefa = new ArrayList<DemandaTarefaUsuario>();
	}

	
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

	public List<DemandaTarefaUsuario> getLstUsuarioTarefa() {
		return lstUsuarioTarefa;
	}

	public void setLstUsuarioTarefa(List<DemandaTarefaUsuario> lstUsuarioTarefa) {
		this.lstUsuarioTarefa = lstUsuarioTarefa;
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
