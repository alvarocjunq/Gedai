package br.com.gedai.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import br.com.gedai.data.DemandaTarefaDiaria;

@Component
public interface DemandaTarefaDiariaMapper {
	DemandaTarefaDiaria obterPorId(Long id);
	DemandaTarefaDiaria obterPorUsuario(DemandaTarefaDiaria demandaTarefaDiaria);
	List<DemandaTarefaDiaria> sumTarefaDiariaPorData(@Param("idDemanda") Integer idDemanda, @Param("dataAtualizacao")Date dataAtualizacao);
	void insert(DemandaTarefaDiaria demandaTarefaDiaria);
	void addContadorUsuario(DemandaTarefaDiaria demandaTarefaDiaria);
	void removeContadorUsuario(DemandaTarefaDiaria demandaTarefaDiaria);
}
