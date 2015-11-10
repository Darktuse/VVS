package es.udc.fic.vvs.Practica1;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServidorRespaldoTest {

	private static final String tokenSpecial = "tokenspecial";
	
	private Servidor servidorValidconContenidos() throws InvalidTokenException{
		Servidor servidor = new ServidorImplRespaldo("s2");
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
	
	private Servidor servidorValidvacioConRespaldo(Servidor s) throws InvalidTokenException{
		Servidor servidor = new ServidorImplRespaldo("s1",s);
		return servidor;
	}
	
	private Servidor servidorValidvacioConRespaldoConCancion(Servidor s) throws InvalidTokenException{
		Servidor servidor = new ServidorImplRespaldo("s1",s);
		servidor.agregar(new Cancion("cancion 1", 5), tokenSpecial);
		return servidor;
	}
	@Test
	public void busquedaServidorRespaldoCancionNoExisteTest() throws InvalidTokenException{
		ServidorImplRespaldo respaldo = (ServidorImplRespaldo) servidorValidconContenidos();
		ServidorImplRespaldo servidor = (ServidorImplRespaldo) servidorValidvacioConRespaldo(respaldo);
		String t = servidor.alta();
		
		assertEquals(servidor.buscar("John Cleese", t).size(),0);
	}
	
	@Test
	public void busquedaServidorRespaldoTest() throws InvalidTokenException{
		ServidorImplRespaldo respaldo = (ServidorImplRespaldo) servidorValidconContenidos();
		ServidorImplRespaldo servidor = (ServidorImplRespaldo) servidorValidvacioConRespaldo(respaldo);
		String t = servidor.alta();
		assertEquals(servidor.buscar("cancion 2", t).size(),1);
	}
	
	@Test
	public void busquedaServidorRespaldoConTokenEliminadoTest() throws InvalidTokenException{
		ServidorImplRespaldo respaldo = (ServidorImplRespaldo) servidorValidconContenidos();
		ServidorImplRespaldo servidor = (ServidorImplRespaldo) servidorValidvacioConRespaldo(respaldo);
		String t = servidor.alta();
		assertEquals(servidor.buscar("cancion", t).size(),10);
		assertEquals(servidor.buscar("cancion", t).size(),0);
	}
	
	@Test
	public void busquedaServidorRespaldoConTokenEliminadoconNoveAparicionsTest() throws InvalidTokenException{
		ServidorImplRespaldo respaldo = (ServidorImplRespaldo) servidorValidconContenidos();
		ServidorImplRespaldo servidor = (ServidorImplRespaldo) servidorValidvacioConRespaldo(respaldo);
		String t = servidor.alta();
		assertEquals(servidor.buscar("cancion 1", t).size(),9);
		assertEquals(servidor.buscar("cancion 1", t).size(),1);
		assertEquals(servidor.buscar("cancion 2", t).size(),0);
		
	}
	
	@Test
	public void busquedaServidorRespaldoconAnunciosTest() throws InvalidTokenException{
		ServidorImplRespaldo respaldo = (ServidorImplRespaldo) servidorValidconContenidos();
		ServidorImplRespaldo servidor = (ServidorImplRespaldo) servidorValidvacioConRespaldo(respaldo);
		assertEquals(servidor.buscar("cancion 2", "").get(0).obtenerTitulo(),"PUBLICIDAD");
		assertEquals(servidor.buscar("cancion 2", "").get(1).obtenerTitulo(),"cancion 2");
		assertEquals(servidor.buscar("cancion 2", "").size(),2);
	}
	
	@Test
	public void busquedaServidorRespaldoconAnunciosEnServidorPrincipalTest() throws InvalidTokenException{
		ServidorImplRespaldo respaldo = (ServidorImplRespaldo) servidorValidconContenidos();
		ServidorImplRespaldo servidor = (ServidorImplRespaldo) servidorValidvacioConRespaldoConCancion(respaldo);
		assertEquals(servidor.buscar("cancion 1", "").get(0).obtenerTitulo(),"PUBLICIDAD");
		assertEquals(servidor.buscar("cancion 1", "").get(1).obtenerTitulo(),"cancion 1");
		assertEquals(servidor.buscar("cancion 1", "").size(),2);
	}
	
	@Test(expected = InvalidTokenException.class)
	public void insertarconTokenInvalidoTest() throws InvalidTokenException{
		Servidor servidor = new ServidorImplRespaldo("s2");
		servidor.agregar(new Cancion("cancion 1", 5), "Graham Chapman");
	}
	
	@Test
	public void buscaCancionSoloNoServidorPrincipalTest() throws InvalidTokenException{
		ServidorImplRespaldo respaldo = (ServidorImplRespaldo) servidorValidconContenidos();
		ServidorImplRespaldo servidor = (ServidorImplRespaldo) servidorValidvacioConRespaldoConCancion(respaldo);
		String t = servidor.alta();
		assertEquals(servidor.buscar("cancion", t).get(0).obtenerTitulo(),"cancion 1");
		assertEquals(servidor.buscar("cancion", t).size(),1);
	}
	
	@Test
	public void buscaCancionSoloNoServidorPrincipalConStringEmptyTest() throws InvalidTokenException{
		ServidorImplRespaldo respaldo = (ServidorImplRespaldo) servidorValidconContenidos();
		ServidorImplRespaldo servidor = (ServidorImplRespaldo) servidorValidvacioConRespaldoConCancion(respaldo);
		String t = servidor.alta();
		assertEquals(servidor.buscar("", t).get(0).obtenerTitulo(),"cancion 1");
		assertEquals(servidor.buscar("", t).size(),1);
	}
	
	@Test
	public void busquedaServidorRespaldoConBaixaDeTokenTest() throws InvalidTokenException{
		ServidorImplRespaldo respaldo = (ServidorImplRespaldo) servidorValidconContenidos();
		ServidorImplRespaldo servidor = (ServidorImplRespaldo) servidorValidvacioConRespaldo(respaldo);
		String t = servidor.alta();
		assertEquals(servidor.buscar("cancion 2", t).size(),1);
		servidor.baja(t);
		assertEquals(servidor.buscar("cancion 2", t).size(),0);
	}
	
	@Test(expected = InvalidTokenException.class)
	public void busquedaServidorRespaldoConBaixaDeTokenExceptionTest() throws InvalidTokenException{
		ServidorImplRespaldo respaldo = (ServidorImplRespaldo) servidorValidconContenidos();
		ServidorImplRespaldo servidor = (ServidorImplRespaldo) servidorValidvacioConRespaldo(respaldo);
		String t = servidor.alta();
		servidor.baja(t);
		servidor.baja(t);
	}

}
