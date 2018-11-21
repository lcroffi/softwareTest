package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContatoDAOImpl implements ContatoDAO {
	String arq;
	String parteNome;
	
	public ContatoDAOImpl (String nome_arq_dados_c) {
		this.arq = nome_arq_dados_c;
	}
	
	public ContatoDAOImpl (String nome_arq_dados_c, String parteNome) {
		this.arq = nome_arq_dados_c;
		this.parteNome = parteNome;
	}

	@Override
	public Contato inserir(Contato c) {
		List<Contato> contatos;
		if (listarTodosContatos() != null) {
			contatos = listarTodosContatos();
		}
		else contatos = new ArrayList<Contato>();
		contatos.add(c);
		Collections.sort(contatos);
		try {
			salvarContatos(contatos);
			return c;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Contato> buscarPorParteNome(String parteNome) {
		int size = parteNome.length();
		List<Contato> contatos = listarTodosContatos();
		List<Contato> resultBusca = new ArrayList<Contato>();
		if (contatos != null) {
			for (Contato contato : contatos) {
				for (int i = 0; i < (contato.getNomeContato().length() - size + 1); i++) {
					if (contato.getNomeContato().substring(i, i + size).equals(parteNome)) {
						resultBusca.add(contato);
					}
				}
			}
		}
		return resultBusca;
	}

	@Override
	public boolean removerContato(Contato c) {
		List<Contato> contatos = listarTodosContatos();
		for (int i = 0; i < contatos.size(); i++) {
			if (contatos.get(i).getNomeContato().equals(c.getNomeContato())) {
				contatos.remove(i);
			}
		}
		try {
			salvarContatos(contatos);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Contato atualizarContato(Contato cAnt, Contato cAtual) {
		List<Contato> contatos = listarTodosContatos();
		for (int i = 0; i < contatos.size(); i++) {
			if (contatos.get(i).getNomeContato().equals(cAnt.getNomeContato())) {
				contatos.remove(i);
			}
		}
		contatos.add(cAtual);
		try {
			salvarContatos(contatos);
			return cAtual;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Contato> listarTodosContatos() {
		List<Contato> contatos = new ArrayList<Contato>();
		String[] elementos = new String[2];
		File contats = new File("src/main/resources/"+arq);
		if (contats.exists()) {
			FileReader fLeitor;
			try {
				fLeitor = new FileReader(contats);
				BufferedReader leitor = new BufferedReader(fLeitor);
				String linha = leitor.readLine();
				if (linha != null) {
					elementos = linha.split(";");
					Contato contato = new Contato();
					contato.setNomeContato(elementos[0].trim());
					contato.setTelefone(elementos[1].trim());
					contato.setEmail(elementos[2].trim());
					contato.setEndereco(elementos[3].trim());
					contatos.add(contato);
					while (linha != null) {
						linha = leitor.readLine();
						if (linha != null) {
							elementos = linha.split(";");
							contato = new Contato();
							contato.setNomeContato(elementos[0].trim());
							contato.setTelefone(elementos[1].trim());
							contato.setEmail(elementos[2].trim());
							contato.setEndereco(elementos[3].trim());
							contatos.add(contato);
						}
					}
				}
				leitor.close();
				fLeitor.close();
				return contatos;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
		return null;
	}
	
	public void salvarContatos(List<Contato> contatos) throws IOException {
		File contats = new File("src/main/resources/"+arq);
		BufferedWriter escritor = new BufferedWriter(new FileWriter(contats));
		for (Contato contato : contatos) {
			escritor.write(contato.dadosFormatados());
			escritor.newLine();
		}
		escritor.close();
	}

}
