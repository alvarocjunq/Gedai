package br.com.gedai.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gedai.data.Area;
import br.com.gedai.mapper.AreaMapper;

@Service
public class AreaBO {

	@Autowired
	private AreaMapper areaMapper;
	
	public List<Area> obterTodos() {
		return areaMapper.obterTodos();
	}

}
