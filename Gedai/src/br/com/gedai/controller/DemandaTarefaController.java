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

import br.com.gedai.bo.DemandaTarefaBO;
import br.com.gedai.data.DemandaTarefa;

@Controller
public class DemandaTarefaController {
	
	@Autowired
	private DemandaTarefaBO demandaTarefaBO;
	
	@RequestMapping(value = "inserirTarefas", method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<DemandaTarefa> inserirTarefas(@RequestBody List<DemandaTarefa> demandaTarefa, 
															HttpSession session){
		return this.demandaTarefaBO.insert(demandaTarefa, session);
	}
	
	@RequestMapping(value = "updateContador", method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Boolean updateContador(@RequestBody DemandaTarefa demandaTarefa){
		this.demandaTarefaBO.updateContador(demandaTarefa);
		return true;
	}
	
}
