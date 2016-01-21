package br.com.gedai.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.gedai.data.Area;


@Component
public interface AreaMapper {
	List<Area> obterTodos();
}
