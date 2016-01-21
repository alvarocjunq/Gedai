package br.com.gedai.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.gedai.data.Demanda;


@Component
public interface DemandaMapper {
	void insert(Demanda demanda);
	void update(Demanda demanda);
	List<Demanda> obterTodos();
	List<Demanda> obterPorArea(Integer idArea);
	Demanda obterPorId(Integer id);
}
