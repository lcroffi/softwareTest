/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author fabriciogmc
 */
public class ServicoUsuarioImpl implements ServicoUsuario{
	
	private String aviso;

    public ServicoUsuarioImpl(){
    }
    
    @Override
	public Usuario inserir(String nomeUsuario, String senha, List<Contato> contatos, String confirmarSenha) {
    	if (nomeUsuario.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()) {
			setAviso("É necessário preencher todos os campos.");
			return null;
		}
    	else if (nomeUsuario.length() < 5 || nomeUsuario.length() > 15 || senha.length() < 5 || senha.length() > 15) {
			setAviso("Nome e senha precisam conter entre 5 e 15 caracteres.");
			return null;
		}
		else if (!nomeUsuario.matches("[a-z0-9]*") || !senha.matches("[a-z0-9]*")) {
			setAviso("Nome e senha compostos apenas por números (0-9) e letras minúsculas (a-z)");
			return null;
		}
		else if (buscarPorNomeUsuario(nomeUsuario) != null) {
			setAviso("Nome já cadastrado, escolha outro.");
			return null;
		}
		
		else if (!senha.equals(confirmarSenha)) {
			setAviso("Senha redigitada não confere.");
			return null;
		}
    	Usuario u = new Usuario();
    	u.setNomeUsuario(nomeUsuario);
    	u.setSenha(confirmarSenha);
		return u;
	}

	@Override
	public Usuario inserir(Usuario u) {
			UsuarioDAOImpl usuImpl = new UsuarioDAOImpl();
			usuImpl.inserir(u);
			return u;
	}
	
	@Override
	public Usuario buscarPorNomeUsuario(String nomeUsuario) {
		UsuarioDAOImpl usuImpl = new UsuarioDAOImpl();
		Usuario uBusca = usuImpl.buscarPorNomeUsuario(nomeUsuario);
		if (uBusca != null) return uBusca;
		else return null;
	}
	@Override
	public boolean removerUsuario(Usuario u) {
		UsuarioDAOImpl usuImpl = new UsuarioDAOImpl();
    	List<Usuario> usuarios = usuImpl.listarTodosUsuarios();
    	for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getNomeUsuario().equals(u.getNomeUsuario())) {
				usuImpl.removerUsuario(u);
				return true;
			}
		}
    	return false;
	}
	@Override
	public Usuario atualizarUsuario(Usuario uAnt, Usuario uAtual) {
		UsuarioDAOImpl usuImpl = new UsuarioDAOImpl();
    	usuImpl.atualizarUsuario(uAnt, uAtual);
    	return uAtual;
	}
	
	@Override
	public List<Usuario> listarTodosUsuarios() {
		UsuarioDAOImpl usuImpl = new UsuarioDAOImpl();
    	List<Usuario> usuarios = usuImpl.listarTodosUsuarios();
    	return usuarios;
	}

	public String getAviso() {
		return aviso;
	}

	public void setAviso(String aviso) {
		this.aviso = aviso;
	}
	
	public boolean validarLogin (String nome, String pwd) {
		UsuarioDAOImpl usuImpl = new UsuarioDAOImpl();
		if (usuImpl.validarLogin(nome, pwd)) return true;
		else return false;
	}
	
	public void Limpeza() {
		UsuarioDAOImpl usuImpl = new UsuarioDAOImpl();
		try {
			usuImpl.Limpeza();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
