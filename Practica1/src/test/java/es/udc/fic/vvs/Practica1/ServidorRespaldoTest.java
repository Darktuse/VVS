package es.udc.fic.vvs.Practica1;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServidorRespaldoTest {

	private static final String tokenSpecial = "tokenspecial";
	
	private Servidor servidorValidconContenidos() throws InvalidTokenException{
		Servidor servidor = new ServidorImpl("s2");
		servidor.agregar(new Cancion("cancion 1", 5), tokenSpecial);
		servidor.agregar(new Cancion("cancion 2", 3), tokenSpecial);
		servidor.agregar(new Cancion("cancion 3", 5), tokenSpecial);
		servidor.agregar(new Cancion("cancion 4", 3), tokenSpecial);
		servidor.agregar(new Cancion("cancion 5", 5), tokenSpecial);
		servidor.agregar(new Cancion("cancion 6", 3), tokenSpecial);
		servidor.agregar(new Cancion("cancion 7", 5), tokenSpecial);
		servidor.agregar(new Cancion("cancion 8", 3), tokenSpecial);
		servidor.agregar(new Cancion("cancion 9", 5), tokenSpecial);
		servidor.agregar(new Cancion("cancion 10", 3), tokenSpecial);
		servidor.agregar(new Cancion("cancion 11", 5), tokenSpecial);
		servidor.agregar(new Cancion("cancion 12", 3), tokenSpecial);
		servidor.agregar(new Cancion("cancion 13", 5), tokenSpecial);
		servidor.agregar(new Cancion("cancion 14", 3), tokenSpecial);
		servidor.agregar(new Cancion("cancion 15", 5), tokenSpecial);
		servidor.agregar(new Cancion("cancion 16", 3), tokenSpecial);
		servidor.agregar(new Cancion("cancion 17", 3), tokenSpecial);
		servidor.agregar(new Emisora("emisora 1"), tokenSpecial);
		servidor.agregar(new Emisora("emisora 2"), tokenSpecial);
		servidor.agregar(new Emisora("emisora 3"), tokenSpecial);
		servidor.agregar(new Emisora("emisora 4"), tokenSpecial);
		servidor.agregar(new Emisora("emisora 5"), tokenSpecial);
		servidor.agregar(new Emisora("emisora 6"), tokenSpecial);
		return servidor;
	}
	
	private Servidor servidorValidvacioConRespaldo(Servidor s){
		Servidor servidor = new ServidorImpl("s1",s);
		return servidor;
	}
	@Test
	public void busquedaServidorRespaldoCancionNoExiste() throws InvalidTokenException{
		ServidorImpl respaldo = (ServidorImpl) servidorValidconContenidos();
		Token t = respaldo.getToken(respaldo.alta());
		ServidorImpl servidor = (ServidorImpl) servidorValidvacioConRespaldo(respaldo);
		servidor.setToken(t);
		
		assertEquals(servidor.buscar("nonesta", t.getToken()).size(),0);
	}
	
}
