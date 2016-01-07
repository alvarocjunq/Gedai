package br.com.gedai.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.gedai.data.DemandaLista;


@Component
public interface DemandaListaMapper {
	void insert(DemandaLista demandaLista);
	void update(DemandaLista demandaLista);
	void delete(Integer idDemandaLista);
	List<DemandaLista> obterPorDemanda(Integer idDemanda);
}
