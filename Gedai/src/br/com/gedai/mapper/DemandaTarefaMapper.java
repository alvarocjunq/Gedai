package br.com.gedai.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import br.com.gedai.data.DemandaTarefa;
import br.com.gedai.dto.TarefaPDFDTO;

@Component
public interface DemandaTarefaMapper {
	List<DemandaTarefa> obterPorDemanda(Integer idDemanda);
	List<DemandaTarefa> obterPorUUID(@Param("lstUuid") List<String> lstUuid);
	void updateContador(DemandaTarefa demandaTarefa);
	void insert(DemandaTarefa demandaTarefa);
	DemandaTarefa obterPorId(Integer id);
	void updateNomeTarefa(DemandaTarefa demandaTarefa);
	List<TarefaPDFDTO> obterTarefasPorDemandaPDF(Integer idDemanda);
}
