package br.com.gedai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gedai.bo.AreaBO;
import br.com.gedai.bo.DemandaBO;

@Controller
public class AreaController {
	
	@Autowired
	private AreaBO areaBO; 
	
	@Autowired
	private DemandaBO demandaBO; 
	
	@RequestMapping("area")
	public String demanda(Model model) {
		model.addAttribute("areas", areaBO.obterTodos());
		model.addAttribute("demandasAll", demandaBO.obterTodos());
		return "area";
	}
}
