package br.com.gedai.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.gedai.data.DemandaListaAtividadeUsuario;


@Component
public interface DemandaListaAtividadeUsuarioMapper {
	void insert(DemandaListaAtividadeUsuario demandaListaAtividadeUsuario);
	void delete(Integer idDemandaListaAtividadeUsuario);
	List<DemandaListaAtividadeUsuario> obterPorAtividade(Integer idDemandaListaAtividade);
}
