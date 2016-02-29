package br.com.gedai.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class DemandaListaAtividade implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer idDemandaLista;
	private String nomeDemandaLista;
	private StatusAtividade statusAtividade;
	private String nome;
	private String descricao;
	private List<DemandaListaAtividadeUsuario> lstAtividadeUsuario;
	private String uuid;
	private Usuario usuarioLogado;
	private Date dataInclusao;
	private Date dataFinalizacao;
	private Date dataInicio;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dataInicioPrevisto;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dataFimPrevisto;
	
	public DemandaListaAtividade() {
		lstAtividadeUsuario = new ArrayList<DemandaListaAtividadeUsuario>();
	}
	
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFinalizacao() {
		return dataFinalizacao;
	}

	public void setDataFinalizacao(Date dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}


	public String getNomeDemandaLista() {
		return nomeDemandaLista;
	}
	public void setNomeDemandaLista(String nomeDemandaLista) {
		this.nomeDemandaLista = nomeDemandaLista;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}


	public Date getDataInclusao() {
		return dataInclusao;
	}


	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}


	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}


	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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

	public Date getDataInicioPrevisto() {
		return dataInicioPrevisto;
	}

	public Date getDataFimPrevisto() {
		return dataFimPrevisto;
	}

	public void setDataInicioPrevisto(Date dataInicioPrevisto) {
		this.dataInicioPrevisto = dataInicioPrevisto;
	}

	public void setDataFimPrevisto(Date dataFimPrevisto) {
		this.dataFimPrevisto = dataFimPrevisto;
	}
}
