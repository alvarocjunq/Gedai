package br.com.gedai.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.gedai.data.TipoLista;


@Component
public interface TipoListaMapper {
	List<TipoLista> obterPorId();
}
