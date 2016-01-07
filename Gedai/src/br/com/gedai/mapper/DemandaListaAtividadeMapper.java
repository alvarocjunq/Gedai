package br.com.gedai.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.gedai.data.DemandaListaAtividade;


@Component
public interface DemandaListaAtividadeMapper {
	void insert(DemandaListaAtividade demandaListaAtividade);
	void update(DemandaListaAtividade demandaListaAtividade);
	void delete(Integer idDemandaListaAtividade);
	List<DemandaListaAtividade> obterPorLista(Integer idDemandaLista);
}
