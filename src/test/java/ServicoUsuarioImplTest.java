import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import model.ServicoUsuarioImpl;
import model.Usuario;

public class ServicoUsuarioImplTest {
	
	private ServicoUsuarioImpl usuImpl;
	private Usuario u;
	
	public ServicoUsuarioImplTest() {
		this.usuImpl = new ServicoUsuarioImpl();
		this.u = new Usuario();
	}
	
	@Test
	public void camposVaziosInvalido() {
        assertEquals(null, usuImpl.inserir("", "", null, ""));
	}
	
	@Test
	public void caractereInvalido() {
		assertEquals(null, usuImpl.inserir("%$#@", "12345", null, "12345"));
	}
	
	@Test
	public void tamanhoInvalido() {
        assertEquals(null, usuImpl.inserir("M", "12", null, "12"));
	}
	
	@Test
	public void nomeRepetido() {
		u.setNomeUsuario("leroy");
		u.setSenha("12345");
		usuImpl.inserir(u);
		assertEquals(null,usuImpl.inserir("leroy", "12345", null, "12345"));
		usuImpl.removerUsuario(u);
	}
	
	@Test
	public void senhaRedigitadaInvalida() {
		assertEquals(null,usuImpl.inserir("leroy", "12345", null, "123456"));
	}
	
	@Test
	public void inserirUsuTest() {
		u.setNomeUsuario("leroy");
		u.setSenha("12345");
		Usuario u2 = new Usuario();
		u2 = usuImpl.inserir("leroy", "12345", null, "12345");
		assertEquals(u2, u);
		usuImpl.removerUsuario(u);
		usuImpl.removerUsuario(u2);
	}
	
	@Test
	public void inserirUsuTest2() {
		u.setNomeUsuario("leroy");
		u.setSenha("12345");
		Usuario u2 = new Usuario();
		u2 = usuImpl.inserir(u);
		assertEquals(u2, u);
		usuImpl.removerUsuario(u);
		usuImpl.removerUsuario(u2);
	}
	
	@Test
	public void removerUsuTest() {
		u.setNomeUsuario("leroy");
		u.setSenha("12345");
		usuImpl.inserir(u);
		assertEquals(true, usuImpl.removerUsuario(u));
	}
	
	@Test
	public void buscarUsuTest() {
		u.setNomeUsuario("leroy");
		u.setSenha("12345");
		usuImpl.inserir(u);
		assertEquals(u, usuImpl.buscarPorNomeUsuario("leroy"));
		usuImpl.removerUsuario(u);
	}
	
	@Test
	public void atualizarUsuTest() {
		u.setNomeUsuario("leroy");
		u.setSenha("12345");
		usuImpl.inserir(u);
		Usuario uAtual = new Usuario();
		uAtual.setNomeUsuario("leroy2");
		uAtual.setSenha(u.getSenha());
		assertEquals(uAtual, usuImpl.atualizarUsuario(u, uAtual));
		usuImpl.removerUsuario(u);
		usuImpl.removerUsuario(uAtual);
	}
	
	@Test
	public void listarUsuTest() {
		u.setNomeUsuario("leroy");
		u.setSenha("12345");
		usuImpl.inserir(u);
		assertEquals(false, usuImpl.listarTodosUsuarios().isEmpty());
		usuImpl.removerUsuario(u);
	}
	
	@Test
	public void validarLoginTest() {
		u.setNomeUsuario("leroy");
		u.setSenha("12345");
		usuImpl.inserir(u);
		assertEquals(true, usuImpl.validarLogin("leroy", "12345"));
		usuImpl.removerUsuario(u);
	}

}
