package br.com.gedai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gedai.bo.DemandaListaAtividadeBO;

@Controller
public class DemandaListaAtividadeController {
	
	@Autowired
	private DemandaListaAtividadeBO demandaListaAtividadeBO; 
	
	@RequestMapping("atividade")
	public String demanda(Model model, Integer idDemanda) {
		model.addAttribute("atividades", demandaListaAtividadeBO.obterPorLista(idDemanda));
		return "demandaAtividade";
	}
}
