import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import model.Contato;
import model.ContatoDAOImpl;
import model.ServicoUsuarioImpl;

public class ContatoDAOImplTest {
	private String arq;
	private ContatoDAOImpl daoImpl;
	private Contato c;
	
	public ContatoDAOImplTest() {
		this.arq = "data_uabc0123.txt";
		this.daoImpl = new ContatoDAOImpl(arq);
		this.c = new Contato();
	}
	
	@Test
	public void inserirDAOTest() {
		c.setNomeContato("Mariana da Silva");
		c.setEmail("mari@yahoo.com");
		c.setEndereco("Rua dos Vinhedos");
		c.setTelefone("12345678");
		assertEquals(c, daoImpl.inserir(c));
		daoImpl.removerContato(c);
	}
	
	@Test
	public void removerDAOTest() {
		c.setNomeContato("Mariana da Silva");
		c.setEmail("mari@yahoo.com");
		c.setEndereco("Rua dos Vinhedos");
		c.setTelefone("12345678");
		daoImpl.inserir(c);
		assertEquals(true, daoImpl.removerContato(c));
	}
	
	@Test
	public void buscarDAOTest() {
		c.setNomeContato("Mariana da Silva");
		c.setEmail("mari@yahoo.com");
		c.setEndereco("Rua dos Vinhedos");
		c.setTelefone("12345678");
		daoImpl.inserir(c);
		String sub = "Mariana";
		daoImpl = new ContatoDAOImpl(arq, sub);
		List<Contato> contatos = daoImpl.listarTodosContatos();
		assertEquals(false, daoImpl.buscarPorParteNome(sub).isEmpty());
		daoImpl.removerContato(c);
	}
	
	@Test
	public void atualizarDAOTest() {
		c.setNomeContato("Mariana da Silva");
		c.setEmail("mari@yahoo.com");
		c.setEndereco("Rua dos Vinhedos");
		c.setTelefone("12345678");
		daoImpl.inserir(c);
		Contato cAtual = new Contato();
		cAtual.setNomeContato("Mariana da Silva Costa");
		cAtual.setEmail(c.getEmail());
		cAtual.setEndereco(c.getEndereco());
		cAtual.setTelefone(c.getTelefone());
		assertEquals(cAtual, daoImpl.atualizarContato(c, cAtual));
		daoImpl.removerContato(c);
		daoImpl.removerContato(cAtual);
	}
	
	@Test
	public void listarDAOTest() {
		c.setNomeContato("Mariana da Silva");
		c.setEmail("mari@yahoo.com");
		c.setEndereco("Rua dos Vinhedos");
		c.setTelefone("12345678");
		daoImpl.inserir(c);
		assertEquals(false, daoImpl.listarTodosContatos().isEmpty());
		daoImpl.removerContato(c);
	}
	
	@AfterAll
	public static void limpar() {
		ServicoUsuarioImpl usuImpl = new ServicoUsuarioImpl();
    	usuImpl.Limpeza();
	}

}
