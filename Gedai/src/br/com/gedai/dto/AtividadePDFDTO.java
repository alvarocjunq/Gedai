package br.com.gedai.dto;

import java.io.Serializable;
import java.util.Date;

import br.com.gedai.annotation.PositionExcel;
import br.com.gedai.utils.DateUtils;

public class AtividadePDFDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nomeAtividade;
	private String descAtividade;
	private String nomeResp;
	private String nomeUsuarioInclusao;
	private Date dataInicioPrevista;
	private Date dataFimPrevista;
	private Date dataFimRealizada;
	private Date dataInicioRealizada;
	
	public AtividadePDFDTO(){
		
	}
	@PositionExcel(posicao={0})
	public String getNomeAtividade() {
		return nomeAtividade;
	}
	
	@PositionExcel(posicao={1})
	public String getDescAtividade() {
		return descAtividade;
	}
	
	@PositionExcel(posicao={2})
	public String getNomeResp() {
		return nomeResp;
	}
	
	@PositionExcel(posicao={3})
	public String getNomeUsuarioInclusao() {
		return nomeUsuarioInclusao;
	}
	
	@PositionExcel(posicao={4})
	public String getDataInicioPrevista() {
		return DateUtils.getDataString(dataInicioPrevista,"dd/MM/yyyy");
	}
	
	@PositionExcel(posicao={5})
	public String getDataFimPrevista() {
		return DateUtils.getDataString(dataFimPrevista,"dd/MM/yyyy");
	}

	@PositionExcel(posicao={7})
	public String getDataFimRealizada() {
		return DateUtils.getDataString(dataFimRealizada,"dd/MM/yyyy");
	}

	@PositionExcel(posicao={6})
	public String getDataInicioRealizada() {
		return DateUtils.getDataString(dataInicioRealizada, "dd/MM/yyyy");
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
	
}
