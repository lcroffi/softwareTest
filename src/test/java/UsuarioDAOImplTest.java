import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import model.Usuario;
import model.UsuarioDAOImpl;

public class UsuarioDAOImplTest {
	
	private UsuarioDAOImpl daoImpl;
	private Usuario u;
	
	public UsuarioDAOImplTest() {
		this.daoImpl = new UsuarioDAOImpl();
		this.u = new Usuario();
	}
	
	@Test
	public void inserirDAOTest() {
		u.setNomeUsuario("leroy");
		u.setSenha("12345");
		Usuario u2 = new Usuario();
		u2 = daoImpl.inserir(u);
		assertEquals(u2, u);
		daoImpl.removerUsuario(u);
		daoImpl.removerUsuario(u2);
	}
	
	@Test
	public void removerDAOTest() {
		u.setNomeUsuario("leroy");
		u.setSenha("12345");
		daoImpl.inserir(u);
		assertEquals(true, daoImpl.removerUsuario(u));
	}
	
	@Test
	public void buscarDAOTest() {
		u.setNomeUsuario("leroy");
		u.setSenha("12345");
		daoImpl.inserir(u);
		assertEquals(u, daoImpl.buscarPorNomeUsuario("leroy"));
		daoImpl.removerUsuario(u);
	}
	
	@Test
	public void atualizarDAOTest() {
		u.setNomeUsuario("leroy");
		u.setSenha("12345");
		daoImpl.inserir(u);
		Usuario uAtual = new Usuario();
		uAtual.setNomeUsuario("leroy2");
		uAtual.setSenha(u.getSenha());
		assertEquals(uAtual, daoImpl.atualizarUsuario(u, uAtual));
		daoImpl.removerUsuario(u);
		daoImpl.removerUsuario(uAtual);
	}
	
	@Test
	public void listarDAOTest() {
		u.setNomeUsuario("leroy");
		u.setSenha("12345");
		daoImpl.inserir(u);
		assertEquals(false, daoImpl.listarTodosUsuarios().isEmpty());
		daoImpl.removerUsuario(u);
	}
	
	@Test
	public void validarLoginTest() {
		u.setNomeUsuario("leroy");
		u.setSenha("12345");
		daoImpl.inserir(u);
		assertEquals(true, daoImpl.validarLogin("leroy", "12345"));
		daoImpl.removerUsuario(u);
	}

}
