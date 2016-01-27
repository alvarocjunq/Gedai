package br.com.gedai.mapper;

import org.springframework.stereotype.Component;

import br.com.gedai.data.TipoLista;


@Component
public interface TipoListaMapper {
	TipoLista obterPorId(Integer id);
}
