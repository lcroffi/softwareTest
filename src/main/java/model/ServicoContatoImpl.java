/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author fabriciogmc
 */
public class ServicoContatoImpl implements ServicoContato{
	
	String nome_arq_dados_c;
	String parteNome;
	private String aviso;

    public ServicoContatoImpl(String nome_arq_dados_c){
        this.nome_arq_dados_c = nome_arq_dados_c;
    }
    
    public ServicoContatoImpl(String nome_arq_dados_c, String parteNome){
        this.nome_arq_dados_c = nome_arq_dados_c;
        this.parteNome = parteNome;
    }
    
    @Override
    public Contato inserir(Contato c) {
    	if (repetidoC(c.getNomeContato())) {
			return null;
		}
    	else {
    		ContatoDAOImpl daoImpl = new ContatoDAOImpl(nome_arq_dados_c);
    		daoImpl.inserir(c);
    		return c;
    	}
    }

    @Override
    public Contato inserir(String nome, String telefone, String email, String endereco) {
		if (nome.isEmpty() || telefone.isEmpty() || email.isEmpty() || endereco.isEmpty()) {
			setAviso("É necessário preencher todos os campos.");
			return null;
		}		
		else if (!nome.matches("^[A-Za-z0-9áàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$")) {
			setAviso("O nome só pode ser composto por letras e números.");
			return null;
		}
		else if (isWhiteSpace(nome) || isWhiteSpace(telefone) || isWhiteSpace(email) || isWhiteSpace(endereco)) {
			setAviso("É necessário preencher todos os campos.");
			return null;
		}
		else if (nome.length() < 1 || nome.length() > 100) {
			setAviso("O nome deve conter entre 1 e 100 caracteres.");
			return null;
		}
		else if (!telefone.matches("^[0-9]+$")) {
			setAviso("O telefone só pode ser composto por números.");
			return null;
		}
		else if (telefone.length() < 8 || telefone.length() > 20) {
			setAviso("O telefone deve conter entre 8 e 20 números.");
			return null;
		}
		else if (!validarEmail(email)) {
			setAviso("E-mail inválido.");
			return null;
		}
		else if (validarEmail2(email)) {
			setAviso("E-mail inválido.");
			return null;
		}
		else if (endereco.length() < 3 || telefone.length() > 255) {
			setAviso("O endereço deve conter entre 3 e 255 caracteres.");
			return null;
		}
		else if (endereco.equals("lugar nenhum") || endereco.equals("judas perdeu as botas") || endereco.equals("casa da mãe joana") || endereco.equals("num sei")) {
			setAviso("Endereço inválido.");
			return null;
		}
		else {
			Contato contato = new Contato();
			contato.setNomeContato(nome);
			contato.setTelefone(telefone);
			contato.setEmail(email);
			contato.setEndereco(endereco);
			return contato;
        }
    }
    
    @Override
    public List<Contato> buscarPorParteNome(String parteNome) {
    	ContatoDAOImpl daoImpl = new ContatoDAOImpl(nome_arq_dados_c, parteNome);
    	List<Contato> resultBusca = daoImpl.buscarPorParteNome(parteNome);
    	return resultBusca;
        }

    @Override
    public boolean removerContato(Contato c) {
    	ContatoDAOImpl daoImpl = new ContatoDAOImpl(nome_arq_dados_c);
    	List<Contato> contatos = daoImpl.listarTodosContatos();
    	for (int i = 0; i < contatos.size(); i++) {
			if (contatos.get(i).getNomeContato().equals(c.getNomeContato())) {
				daoImpl.removerContato(c);
				return true;
			}
		}
    	return false;
    	}

    @Override
    public Contato atualizarContato(Contato cAnt, Contato cAtual) {
    	ContatoDAOImpl daoImpl = new ContatoDAOImpl(nome_arq_dados_c);
    	daoImpl.atualizarContato(cAnt, cAtual);
    	return cAtual;
    }

    @Override
    public List<Contato> listarTodosContatos() {
    	ContatoDAOImpl daoImpl = new ContatoDAOImpl(nome_arq_dados_c);
    	List<Contato> contatos = daoImpl.listarTodosContatos();
    	return contatos;
    }
    
    private static final String EMAIL_PATTERN = 
            "^[_A-Za-z0-9\\+]+(\\.[_A-Za-z0-9]+)*@"
            + "[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{1,})$";
    
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
    public static boolean validarEmail(String email){
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
     }
    
    private static boolean validarEmail2(String email) {
    	String[] elementos = email.split("@");
    	if (elementos[0].matches("^[0-9]+$") || elementos[1].matches("^[0-9]+$")) {
    		return true;
    	}
    	return false;
    }
    
    private static boolean isWhiteSpace(String s) {
        int length = s.length();
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                if (!Character.isWhitespace(s.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

	public String getAviso() {
		return aviso;
	}

	public void setAviso(String aviso) {
		this.aviso = aviso;
	}
	
	public boolean repetidoC(String nome) {
		List<Contato> contatos =  listarTodosContatos();
		if (contatos != null) {
			for (int i = 0; i < contatos.size(); i++) {
				if (contatos.get(i).getNomeContato().equals(nome)) {
					return true;
				}
			}
		}
		return false;
	}

    
}
