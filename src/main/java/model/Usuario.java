package model;

import java.util.List;

public class Usuario implements modeloListagem, Comparable<Usuario> {
	private String nomeUsuario;
	private String senha;
	Criptografia c = new Criptografia();
	List<Contato> contatos;
    
    public Usuario(){}
	
	@Override
	public String dadosFormatados() {
		String dadosFormatados;
		dadosFormatados = c.cripto(this.getNomeUsuario(), 3) + ";";
		dadosFormatados = dadosFormatados + c.cripto(this.getSenha(), 3);
		return dadosFormatados;		
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

	@Override
    public int compareTo(Usuario u) {
		return nomeUsuario.compareTo(u.getNomeUsuario());
	}
    
    @Override
    public boolean equals(Object o){
    	if (o instanceof Usuario && ((Usuario) o).getNomeUsuario().equals(this.nomeUsuario)) {
    		return true;
        }
    	return false;
    }	
}
