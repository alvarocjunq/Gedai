package br.com.gedai.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import br.com.gedai.data.DemandaTarefa;

@Component
public interface DemandaTarefaMapper {
	List<DemandaTarefa> obterPorDemanda(Integer idDemanda);
	List<DemandaTarefa> obterPorUUID(@Param("lstUuid") List<String> lstUuid);
	void updateContador(DemandaTarefa demandaTarefa);
	void insert(DemandaTarefa demandaTarefa);
}
