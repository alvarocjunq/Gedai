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
	
	List<DemandaListaAtividade> obterPorLista(Integer idDemandaLista);
	
	DemandaListaAtividade obterPorId(Integer idDemandaListaAtividade);
	
	List<DemandaListaAtividade> obterPorUUID(@Param("lstUuid") List<String> lstUuid);
	
	List<AtividadePDFDTO> obterAtividadePorDemandaPDF(Integer idDemanda);
	
	List<DemandaListaAtividade> obterAtividadesPendentes(Integer idUsuarioLogado);
	
}
