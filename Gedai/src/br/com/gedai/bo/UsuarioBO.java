package br.com.gedai.bo;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gedai.data.Usuario;
import br.com.gedai.enums.ConfigEnum;
import br.com.gedai.mapper.UsuarioMapper;
import br.com.gedai.utils.Criptografia;

@Service
public class UsuarioBO {

	@Autowired
	private UsuarioMapper usuarioMapper;
	
	public Usuario obterPorId(Integer id){
		return this.usuarioMapper.obterPorId(id);
	}
	
	public Usuario obterPorLogin(Usuario usuario){
		return this.usuarioMapper.obterPorLogin(usuario);
	}
	
	public Usuario obterLoginSenha(Usuario usuario){
		usuario.setSenha(Criptografia.getSenha(usuario.getSenha()));
		return this.usuarioMapper.obterLoginSenha(usuario);
	}
	
	public List<Usuario> obterTodos(){
		return usuarioMapper.obterTodos();
	}
	
	public void updateSenha(Usuario usuario){
		this.usuarioMapper.updateSenha(usuario);
	}
	
	
	public Usuario getUserSession(HttpSession session){
		return (Usuario) session.getAttribute(ConfigEnum.USUARIO_LOGADO.getValor());
	}
	
}
