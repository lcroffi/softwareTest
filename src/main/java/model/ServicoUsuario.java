/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author fabriciogmc
 */
public interface ServicoUsuario {
    public Usuario inserir(Usuario u);
    public Usuario inserir(String nomeUsuario, String sennha, List<Contato> contatos, String confirmarSenha);
    public Usuario buscarPorNomeUsuario(String nomeUsuario);
    public boolean removerUsuario(Usuario u);
    public Usuario atualizarUsuario(Usuario uAnt,Usuario uAtual);
    public List<Usuario> listarTodosUsuarios();    
}
