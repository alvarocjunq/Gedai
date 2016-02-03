package br.com.gedai.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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
	public @ResponseBody List<DemandaListaAtividade> inserirAtividades(@RequestBody List<DemandaListaAtividade> atividades,
																	   HttpSession session) {
		return demandaListaAtividadeBO.insert(atividades, session);
	}
	
	@RequestMapping(value="atualizarAtividade", method=RequestMethod.POST, 
					produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean atualizarAtividade(@RequestBody DemandaListaAtividade atividade) {
		demandaListaAtividadeBO.update(atividade);
		return true;
	}
	
	@RequestMapping(value="obterAtividade", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody DemandaListaAtividade obterAtividade(Integer idAtividade) {
		return demandaListaAtividadeBO.obterPorId(idAtividade);
	}
	
}
