import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import model.Criptografia;

public class CriptografiaTest {
	private Criptografia c;
	
	public CriptografiaTest() {
		this.c = new Criptografia();
	}
	
	@Test
	public void criptoTest() {
		assertEquals("abc0123",c.cripto("xyz7890",3));
	}
	
	@Test
	public void descriptoTest() {
		assertEquals("xyz7890",c.cripto("abc0123",-3));
	}
}
