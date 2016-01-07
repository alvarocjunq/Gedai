package br.com.gedai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gedai.bo.DemandaBO;

@Controller
public class DemandaController {
	
	@Autowired
	private DemandaBO demandaBO; 
	
	@RequestMapping("demanda")
	public String demanda(Model model) {
		model.addAttribute("demandas", demandaBO.obterTodos());
		return "demanda";
	}
}
