package br.com.gedai.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import br.com.gedai.data.DemandaListaAtividade;
import br.com.gedai.dto.AtividadePDFDTO;


@Component
public interface DemandaListaAtividadeMapper {
	
	void insert(DemandaListaAtividade demandaListaAtividade);
	void update(DemandaListaAtividade demandaListaAtividade);
	void delete(Integer idDemandaListaAtividade);
	void finalizarAtividade(Integer idAtividade);
	
	int nextSequence();
	int countParaAgenda(Integer idUsuarioLogado);
	
	DemandaListaAtividade obterPorId(Integer idDemandaListaAtividade);

	List<AtividadePDFDTO> obterAtividadePorDemandaPDF(Integer idDemanda);
	
	List<DemandaListaAtividade> obterPorLista(Integer idDemandaLista);
	List<DemandaListaAtividade> obterPorUUID(@Param("lstUuid") List<String> lstUuid);
	List<DemandaListaAtividade> obterAtividadesPendentes(Integer idUsuarioLogado);
	List<DemandaListaAtividade> obterParaAgenda(Integer idUsuarioLogado);
}
