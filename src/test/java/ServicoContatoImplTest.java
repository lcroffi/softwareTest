import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.ArrayList;

import model.Contato;
import model.ServicoContatoImpl;
import model.ServicoUsuarioImpl;

public class ServicoContatoImplTest {
	
	private String arq;
	private ServicoContatoImpl servCont;
	private Contato c;
	
	/*ClassLoader classLoader = getClass().getClassLoader();
	File file = new File(classLoader.getResource("data_uddddd.txt").getFile());
	private static String arq = file.getAbsolutePath();
	
	@BeforeAll
	public static void iniciarTestes() {
		URL url = ServicoContatoImplTest.getClassLoader().getResource("data_uddddd.txt");
		String path = url.getPath();
	    System.out.println(path);
		Contato c = new Contato();
		c.setNomeContato("Mariana da Silva");
		c.setEmail("mari@yahoo.com");
		c.setEndereco("Rua dos Vinhedos");
		c.setTelefone("12345678");
		servCont.inserir(c);
		contatos = servCont.listarTodosContatos();
	}*/

	public ServicoContatoImplTest() {
		this.arq = "data_uabc0123.txt";
		this.servCont = new ServicoContatoImpl(arq);
		this.c = new Contato();
	}
	
	@Test
	public void camposVaziosInvalido() {
        assertEquals(null, servCont.inserir("", "", "", ""));
	}

	@Test
	public void nomeComSimboloInvalido() {
        assertEquals(null, servCont.inserir("%$#@", "12345678", "mari@yahoo.com", "Rua dos Vinhedos"));
	}

	@Test
	public void espacosBrancosInvalido() {
        assertEquals(null, servCont.inserir("  ", "  ", "  ", "  "));
	}
	
	@Test
	public void tamanhoInvalido() {
        assertEquals(null, servCont.inserir("M", "12", "m", "R"));
	}
	
	@Test
	public void telefoneInvalido() {
        assertEquals(null, servCont.inserir("Mariana da Silva", "abcde", "mari@yahoo.com", "Rua dos Vinhedos"));
	}
	
	@Test
	public void emailInvalido() {
        assertEquals(null, servCont.inserir("Mariana da Silva", "12345678", "@1.1", "Rua dos Vinhedos"));
	}
	
	@Test
	public void inserirServTest() {
		c.setNomeContato("Mariana da Silva");
		c.setEmail("mari@yahoo.com");
		c.setEndereco("Rua dos Vinhedos");
		c.setTelefone("12345678");
		Contato c2 = new Contato();
		c2 = servCont.inserir("Mariana da Silva", "12345678", "mari@yahoo.com", "Rua dos Vinhedos");
		assertEquals(c2, c);
		servCont.removerContato(c);
		servCont.removerContato(c2);
	}
	
	@Test
	public void inserirServTest2() {
		c.setNomeContato("Mariana da Silva");
		c.setEmail("mari@yahoo.com");
		c.setEndereco("Rua dos Vinhedos");
		c.setTelefone("12345678");
		Contato c2 = new Contato();
		c2 = servCont.inserir(c);
		assertEquals(c2, c);
		servCont.removerContato(c);
		servCont.removerContato(c2);
	}
	
	@Test
	public void removerServTest() {
		c.setNomeContato("Mariana da Silva");
		c.setEmail("mari@yahoo.com");
		c.setEndereco("Rua dos Vinhedos");
		c.setTelefone("12345678");
		servCont.inserir(c);
		assertEquals(true, servCont.removerContato(c));
	}
	
	@Test
	public void buscarTestServInvalido() {
		c.setNomeContato("Mariana da Silva");
		c.setEmail("mari@yahoo.com");
		c.setEndereco("Rua dos Vinhedos");
		c.setTelefone("12345678");
		servCont.inserir(c);
		String sub = "x";
		servCont = new ServicoContatoImpl(arq, sub);
		List<Contato> contatos = servCont.listarTodosContatos();
		assertEquals(true, servCont.buscarPorParteNome(sub).isEmpty());
		servCont.removerContato(c);
	}
	
	@Test
	public void buscarServTest() {
		c.setNomeContato("Mariana da Silva");
		c.setEmail("mari@yahoo.com");
		c.setEndereco("Rua dos Vinhedos");
		c.setTelefone("12345678");
		servCont.inserir(c);
		String sub = "Mariana";
		servCont = new ServicoContatoImpl(arq, sub);
		List<Contato> contatos = servCont.listarTodosContatos();
		assertEquals(false, servCont.buscarPorParteNome(sub).isEmpty());
		servCont.removerContato(c);
	}
	
	@Test
	public void atualizarServTest() {
		c.setNomeContato("Mariana da Silva");
		c.setEmail("mari@yahoo.com");
		c.setEndereco("Rua dos Vinhedos");
		c.setTelefone("12345678");
		servCont.inserir(c);
		Contato cAtual = new Contato();
		cAtual.setNomeContato("Mariana da Silva Costa");
		cAtual.setEmail(c.getEmail());
		cAtual.setEndereco(c.getEndereco());
		cAtual.setTelefone(c.getTelefone());
		assertEquals(cAtual, servCont.atualizarContato(c, cAtual));
		servCont.removerContato(c);
		servCont.removerContato(cAtual);
	}
	
	@Test
	public void listarServTest() {
		c.setNomeContato("Mariana da Silva");
		c.setEmail("mari@yahoo.com");
		c.setEndereco("Rua dos Vinhedos");
		c.setTelefone("12345678");
		servCont.inserir(c);
		assertEquals(false, servCont.listarTodosContatos().isEmpty());
		servCont.removerContato(c);
	}
	
	@Test
	public void repetidoCTest() {
		c.setNomeContato("Mariana da Silva");
		c.setEmail("mari@yahoo.com");
		c.setEndereco("Rua dos Vinhedos");
		c.setTelefone("12345678");
		servCont.inserir(c);
		assertEquals(true, servCont.repetidoC("Mariana da Silva"));
		servCont.removerContato(c);
	}
	
	@AfterAll
	public static void limpar() {
		ServicoUsuarioImpl usuImpl = new ServicoUsuarioImpl();
    	usuImpl.Limpeza();
	}
}
