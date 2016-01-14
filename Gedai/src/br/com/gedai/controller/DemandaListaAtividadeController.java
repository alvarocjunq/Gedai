package br.com.gedai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.gedai.bo.DemandaListaAtividadeBO;
import br.com.gedai.data.DemandaListaAtividade;

@Controller
public class DemandaListaAtividadeController {
	
	@Autowired
	private DemandaListaAtividadeBO demandaListaAtividadeBO; 
	
	@RequestMapping(value = "inserirAtividades", method = RequestMethod.POST, 
					produces = MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean inserirAtividades(@RequestBody List<DemandaListaAtividade> atividades) {
		demandaListaAtividadeBO.insert(atividades);
		return true;
	}
	
	@RequestMapping(value="atualizarAtividade", method=RequestMethod.POST, 
					produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public void atualizarAtividade(@RequestBody DemandaListaAtividade atividade) {
		demandaListaAtividadeBO.update(atividade);
	}
	
}
