package br.com.gedai.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.gedai.data.StatusAtividade;


@Component
public interface StatusAtividadeMapper {
	List<StatusAtividade> obterPorId(Integer id);
}
