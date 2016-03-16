package br.com.gedai.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.gedai.annotation.PositionExcel;
import br.com.gedai.data.DemandaListaAtividadeUsuario;
import br.com.gedai.utils.DateUtils;

public class AtividadePDFDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nomeAtividade;
	private String nomeDemanda;
	private String descAtividade;
	private String nomeResp;
	private Integer idUsuario;
	private String nomeUsuarioInclusao;
	private Date dataInicioPrevista;
	private Date dataFimPrevista;
	private Date dataFimRealizada;
	private Date dataInicioRealizada;
	private List<DemandaListaAtividadeUsuario> usuariosAssociados;
	
	public AtividadePDFDTO(){
		usuariosAssociados = new ArrayList<DemandaListaAtividadeUsuario>();
	}
	
	@PositionExcel(posicao={0})
	public String getNomeDemanda() {
		return nomeDemanda;
	}
	
	@PositionExcel(posicao={1})
	public String getNomeAtividade() {
		return nomeAtividade;
	}
	
	@PositionExcel(posicao={2})
	public String getDescAtividade() {
		return descAtividade;
	}
	
	@PositionExcel(posicao={3})
	public String getNomeResp() {
		return nomeResp;
	}
	
	@PositionExcel(posicao={4})
	public String getNomeUsuarioInclusao() {
		return nomeUsuarioInclusao;
	}
	
	@PositionExcel(posicao={5})
	public String getDataInicioPrevista() {
		return DateUtils.getDataString(dataInicioPrevista,"dd/MM/yyyy");
	}
	
	@PositionExcel(posicao={6})
	public String getDataFimPrevista() {
		return DateUtils.getDataString(dataFimPrevista,"dd/MM/yyyy");
	}

	@PositionExcel(posicao={7})
	public String getDataFimRealizada() {
		return DateUtils.getDataString(dataFimRealizada,"dd/MM/yyyy");
	}

	@PositionExcel(posicao={8})
	public String getDataInicioRealizada() {
		return DateUtils.getDataString(dataInicioRealizada, "dd/MM/yyyy");
	}
	
	public void setNomeDemanda(String nomeDemanda) {
		this.nomeDemanda = nomeDemanda;
	}

	public void setNomeAtividade(String nomeAtividade) {
		this.nomeAtividade = nomeAtividade;
	}

	public void setDescAtividade(String descAtividade) {
		this.descAtividade = descAtividade;
	}

	public void setDataInicioPrevista(Date dataInicioPrevista) {
		this.dataInicioPrevista = dataInicioPrevista;
	}

	public void setDataFimPrevista(Date dataFimPrevista) {
		this.dataFimPrevista = dataFimPrevista;
	}

	public void setDataFimRealizada(Date dataFimRealizada) {
		this.dataFimRealizada = dataFimRealizada;
	}

	public void setDataInicioRealizada(Date dataInicioRealizada) {
		this.dataInicioRealizada = dataInicioRealizada;
	}
	

	public void setNomeResp(String nomeResp) {
		this.nomeResp = nomeResp;
	}

	public void setNomeUsuarioInclusao(String nomeUsuarioInclusao) {
		this.nomeUsuarioInclusao = nomeUsuarioInclusao;
	}

	@Override
	public String toString() {
		return nomeResp;
	}

	public List<DemandaListaAtividadeUsuario> getUsuarioAssociados() {
		return usuariosAssociados;
	}

	public void setUsuarioAssociados(
			List<DemandaListaAtividadeUsuario> usuarioAssociados) {
		this.usuariosAssociados = usuarioAssociados;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	
}
