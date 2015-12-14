package es.udc.fic.vvs.Practica1.Servidor;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit test for simple Token.
 */
public class TokenTest {

	@Test
	public void crearTokenTest() throws InvalidTokenException {

		Servidor servidor = new ServidorImplSimple("Servidor");

		String token = servidor.alta();

		Token newToken = new Token(token,5);
		
		assertNotNull(newToken);

	}

	@Test
	public void setTokenTest() throws InvalidTokenException {

		Servidor servidor = new ServidorImplSimple("Servidor");

		String oldTokenStr = servidor.alta();

		Token newToken = new Token(oldTokenStr,5);
		
		assertNotNull(newToken);

		String newTokenStr = servidor.alta();

		newToken.setToken(newTokenStr);
		
	}


	@Test
	public void restarUnTokenTest() throws InvalidTokenException {

		Servidor servidor = new ServidorImplSimple("Servidor");

		String token = servidor.alta();

		Token newToken = new Token(token,5);
		
		assertTrue(newToken.restarUno());
		assertEquals(newToken.getNumero(),4);
		
	}

	
	@Test
	public void restarUnTokenErrorTest() throws InvalidTokenException {

		Servidor servidor = new ServidorImplSimple("Servidor");

		String token = servidor.alta();

		Token newToken = new Token(token,0);
		
		assertFalse(newToken.restarUno());
		
	}
	
	public void restarVariosTokenTest() throws InvalidTokenException {

		Servidor servidor = new ServidorImplSimple("Servidor");

		String token = servidor.alta();

		Token newToken = new Token(token,5);
		
		assertTrue(newToken.restarVarios(3));
		assertEquals(newToken.getNumero(),2);
		
	}

	
	@Test
	public void restarVariosTokenErrorTest() throws InvalidTokenException {

		Servidor servidor = new ServidorImplSimple("Servidor");

		String token = servidor.alta();

		Token newToken = new Token(token,0);
		
		assertFalse(newToken.restarVarios(3));
		
	}
	
	@Test
	public void restarVariosTokenNoSuficientesTest() throws InvalidTokenException {

		Servidor servidor = new ServidorImplSimple("Servidor");

		String token = servidor.alta();

		Token newToken = new Token(token,3);
		
		assertFalse(newToken.restarVarios(4));
		
		newToken.setNumero(5);
		
		assertTrue(newToken.restarVarios(4));
		assertEquals(newToken.getNumero(),1);
	}
	
}
