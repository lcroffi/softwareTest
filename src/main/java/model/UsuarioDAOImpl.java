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

public class UsuarioDAOImpl implements UsuarioDAO{
	
	Criptografia c = new Criptografia();

	@Override
	public Usuario inserir(Usuario u) {
		List<Usuario> usuarios = listarTodosUsuarios();
		usuarios.add(u);
		Collections.sort(usuarios);
		try {
			salvarUsuarios(usuarios);
			return u;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public Usuario buscarPorNomeUsuario(String nomeUsuario) {
		List<Usuario> usuarios = listarTodosUsuarios();
		if (usuarios != null) {
			for (int i = 0; i < usuarios.size(); i++) {
				if (usuarios.get(i).getNomeUsuario().equals(nomeUsuario)) {
					return usuarios.get(i);
				}
			}
		}
		return null;
	}

	@Override
	public boolean removerUsuario(Usuario u) {
		List<Usuario> usuarios = listarTodosUsuarios();
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getNomeUsuario().equals(u.getNomeUsuario())) {
				usuarios.remove(i);
			}
		}
		try {
			salvarUsuarios(usuarios);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Usuario atualizarUsuario(Usuario uAnt, Usuario uAtual) {
		List<Usuario> usuarios = listarTodosUsuarios();
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getNomeUsuario().equals(uAnt.getNomeUsuario())) {
				usuarios.remove(i);
			}
		}
		usuarios.add(uAtual);
		try {
			salvarUsuarios(usuarios);
			return uAtual;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Usuario> listarTodosUsuarios() {
		List <Usuario> usuarios = new ArrayList<Usuario>();
		Collections.sort(usuarios);
		String[] elementos = new String[2];
		File users = new File("src/main/resources/users.txt");
		if (users.exists()) {
			FileReader fLeitor;
			try {
				fLeitor = new FileReader(users);
				BufferedReader leitor = new BufferedReader(fLeitor);
				String linha = leitor.readLine();
				if (linha != null) {
					elementos = linha.split(";");
					Usuario usuario = new Usuario();
					usuario.setNomeUsuario(c.cripto(elementos[0], -3));
					usuario.setSenha(c.cripto(elementos[1], -3));
					usuarios.add(usuario);
					while (linha != null) {
							linha = leitor.readLine();
							if (linha != null) {
								elementos = linha.split(";");
								usuario = new Usuario();
								usuario.setNomeUsuario(c.cripto(elementos[0], -3));
								usuario.setSenha(c.cripto(elementos[1], -3));
								usuarios.add(usuario);
							}
					}
				}
				leitor.close();
				fLeitor.close();
				return usuarios;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public void salvarUsuarios (List<Usuario> usuarios) throws IOException {
		File users = new File("src/main/resources/users.txt");
		BufferedWriter escritor = new BufferedWriter(new FileWriter(users));
		for (Usuario usuario : usuarios) {
			escritor.write(usuario.dadosFormatados());
			escritor.newLine();
		}
		escritor.close();
	}
	
	public boolean validarLogin (String nome, String pwd) {
		List<Usuario> usuarios = listarTodosUsuarios();
		if (usuarios != null) {
			for (int i = 0; i < usuarios.size(); i++) {
				if (usuarios.get(i).getNomeUsuario().equals(nome) && usuarios.get(i).getSenha().equals(pwd)) {
					return true;
				}
			}
		}
		return false;	
	}
	
	public void Limpeza() throws IOException {
		List<Usuario> usuarios = listarTodosUsuarios();
		String dir = "src/main/resources/";
		File diretorio = new File(dir);
		String[] fList = diretorio.list();
		for (String nome : fList) {
			File temp = new File(diretorio.getPath(), nome);
			if (nome.startsWith("data_u")) {
				if (!procura(usuarios, nome)) {
					temp.delete();
				}
			}
		}

	}
	
	public boolean procura (List<Usuario> usuarios, String nome) {
		Criptografia c = new Criptografia();
		for (Usuario usuario : usuarios) {
			if (nome.substring(6, 6 + usuario.getNomeUsuario().length())
					.equals(c.cripto(usuario.getNomeUsuario(), 3))) {
				return true;
			}
		}
		return false;
	}
}
