package br.com.gedai.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.gedai.data.Usuario;

@Component
public interface UsuarioMapper {
	List<Usuario> obterTodos();
	Usuario obterPorId(Integer id);
	Usuario obterPorLogin(Usuario usuario);
	Usuario obterLoginSenha(Usuario usuario);
	void updateSenha(Usuario usuario);
}
