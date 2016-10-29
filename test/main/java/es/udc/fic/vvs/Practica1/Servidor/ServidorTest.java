package es.udc.fic.vvs.Practica1.Servidor;

import java.util.Enumeration;
import java.util.List;

import org.junit.Test;
import org.junit.contrib.theories.Theory;
import org.junit.runner.RunWith;

import es.udc.fic.vvs.Practica1.Contenido.Anuncio;
import es.udc.fic.vvs.Practica1.Contenido.Cancion;
import es.udc.fic.vvs.Practica1.Contenido.Contenido;
import es.udc.fic.vvs.Practica1.Contenido.ContenidoInexistenteException;
import es.udc.fic.vvs.Practica1.Contenido.Emisora;
import es.udc.fic.vvs.Practica1.Servidor.InvalidTokenException;
import es.udc.fic.vvs.Practica1.Servidor.Servidor;
import es.udc.fic.vvs.Practica1.Servidor.ServidorImplSimple;
import etm.core.configuration.EtmManager;
import etm.core.monitor.EtmMonitor;
import etm.core.monitor.EtmPoint;
import net.java.quickcheck.generator.PrimitiveGenerators;
import net.java.quickcheck.generator.PrimitiveGeneratorsIterables;
import static net.java.quickcheck.generator.PrimitiveGeneratorsIterables.someIntegers;

import static org.junit.Assert.*;

/**
 * Unit test for simple ServidorSimple.
 */


public class ServidorTest {
	private static final EtmMonitor etmMonitor = EtmManager.getEtmMonitor();
	private static final String tokenSpecial = "tokenspecial";


	@Test
	public void agregarContenidoTest() throws InvalidTokenException {

		Servidor servidor = crearServidor();

		String token = servidor.alta();

		List<Contenido> contenidos = servidor.buscar("4", token);

		assertEquals(contenidos.size(), 1);

		servidor.agregar(contenidos.get(0), tokenSpecial);

		contenidos = servidor.buscar("4", token);

		assertEquals(contenidos.size(), 2);

	};
	
	
	@Test
	public void eliminarContenidoTest() throws InvalidTokenException {

		Servidor servidor = crearServidor();

		String token = servidor.alta();

		List<Contenido> contenidos = servidor.buscar("4", token);

		assertEquals(contenidos.size(), 1);

		servidor.eliminar(contenidos.get(0), tokenSpecial);

		contenidos = servidor.buscar("4", token);

		assertEquals(contenidos.size(), 0);

	};
	

	@Test(expected=InvalidTokenException.class)
	public void agregarContenidoInvalidTokenTest() throws InvalidTokenException {

		Servidor servidor = crearServidor();

		String token = servidor.alta();

		servidor.agregar(new Anuncio(), token);



	};
	
	
	@Test(expected=InvalidTokenException.class)
	public void eliminarContenidoInvalidTokenTest() throws InvalidTokenException {

		Servidor servidor = crearServidor();

		String token = servidor.alta();

		servidor.eliminar(new Anuncio(), token);


	};
	
	
	
	
	@Test(expected = InvalidTokenException.class)
	public void agregarContenidoExceptionTest() throws InvalidTokenException {

		Servidor servidor = crearServidor();

		String token = servidor.alta();

		servidor.agregar(new Anuncio(), token);

	};

	@Test
	public void obtenerContenidoSinTokenTest() throws InvalidTokenException {

		Servidor servidor = crearServidor();

		String token = "No valido";

		assert (servidor.buscar("4", token).isEmpty() == true);

	}

	@Test(expected=InvalidTokenException.class)
	public void darBajaTokenTest() throws InvalidTokenException {

		Servidor servidor = crearServidor();

		String token = servidor.alta();

		List<Contenido> contenidos = servidor.buscar("4", token);

		assert (contenidos.isEmpty() == false);

		servidor.baja(token);

		servidor.buscar("4", token);

	}
	@Test
	public void insertarAnuncioTokenVacioTest() throws InvalidTokenException {

		Servidor servidor = crearServidor();

		List<Contenido> contenidos  = servidor.buscar("4", "");
		contenidos = servidor.buscar("4", "");
		assertEquals(contenidos.size(),1);
		contenidos = servidor.buscar("4", "");
		assertEquals(contenidos.get(0).obtenerTitulo(),"PUBLICIDAD");
		assertEquals(contenidos.size(),2);
		contenidos = servidor.buscar("4", "");
		assertEquals(contenidos.size(),1);
		contenidos = servidor.buscar("4", "");
		assertEquals(contenidos.size(),1);
		contenidos = servidor.buscar("4", "");
		assertEquals(contenidos.get(0).obtenerTitulo(),"PUBLICIDAD");
		assertEquals(contenidos.size(),2);
		
		
	}

	private Servidor crearServidor() {

		Servidor servidor = new ServidorImplRespaldo("Servidor");
		Contenido cancion1 = new Cancion("Cancion1", 1);
		Contenido cancion2 = new Cancion("Cancion2", 2);
		Contenido cancion3 = new Cancion("Cancion3", 3);
		Contenido cancion4 = new Cancion("Cancion4", 4);
		Contenido cancion5 = new Cancion("Cancion5", 3);
		Contenido cancion6 = new Cancion("Cancion6", 2);
		Contenido cancion7 = new Cancion("Cancion7", 1);
		Contenido cancion8 = new Cancion("Cancion8", 4);
		Contenido emisora1 = new Emisora("Emisora1");
		Contenido anuncio = new Anuncio();
		try {
			emisora1.agregar(cancion7, null);
			emisora1.agregar(cancion6, cancion7);
			emisora1.agregar(cancion8, cancion6);
			emisora1.agregar(cancion1, cancion8);
			emisora1.agregar(anuncio, cancion6);
		} catch (ContenidoInexistenteException e1) {
			e1.printStackTrace();
		}

		try {
			servidor.agregar(cancion1, tokenSpecial);
			servidor.agregar(cancion2, tokenSpecial);
			servidor.agregar(cancion2, tokenSpecial);
			servidor.agregar(cancion2, tokenSpecial);
			servidor.agregar(cancion2, tokenSpecial);
			servidor.agregar(cancion3, tokenSpecial);
			servidor.agregar(cancion4, tokenSpecial);
			servidor.agregar(cancion5, tokenSpecial);
			servidor.agregar(cancion6, tokenSpecial);
			servidor.agregar(cancion7, tokenSpecial);
			servidor.agregar(cancion8, tokenSpecial);
			servidor.agregar(emisora1, tokenSpecial);
		} catch (InvalidTokenException e) {
			e.printStackTrace();
		}

		return servidor;
		
	}	
	
	private Servidor crearServidorRespaldo() {

		Servidor servidor = new ServidorImplRespaldo("Servidor", crearServidor());
		Contenido cancion1 = new Cancion("Cancion1", 1);
		Contenido cancion2 = new Cancion("Cancion2", 2);
		Contenido cancion3 = new Cancion("Cancion3", 3);
		
		
		Contenido cancion6 = new Cancion("Cancion6", 2);
		Contenido cancion7 = new Cancion("Cancion7", 1);
		Contenido cancion8 = new Cancion("Cancion8", 4);
		Contenido emisora1 = new Emisora("Emisora1");
		Contenido anuncio = new Anuncio();
		try {
			emisora1.agregar(cancion7, null);
			emisora1.agregar(cancion6, cancion7);
			emisora1.agregar(cancion8, cancion6);
			emisora1.agregar(cancion1, cancion8);
			emisora1.agregar(anuncio, cancion6);
		} catch (ContenidoInexistenteException e1) {
			e1.printStackTrace();
		}

		try {
			servidor.agregar(cancion1, tokenSpecial);
			servidor.agregar(cancion2, tokenSpecial);
			servidor.agregar(cancion2, tokenSpecial);
			servidor.agregar(cancion2, tokenSpecial);
			servidor.agregar(cancion2, tokenSpecial);
			servidor.agregar(cancion3, tokenSpecial);
			servidor.agregar(cancion6, tokenSpecial);
			servidor.agregar(cancion7, tokenSpecial);
			servidor.agregar(cancion8, tokenSpecial);
			servidor.agregar(emisora1, tokenSpecial);
		} catch (InvalidTokenException e) {
			e.printStackTrace();
		}

		return servidor;
		
	}	
	
	
	
		@Test
		public void buscarContenidoRespaldoTest() throws InvalidTokenException {
		
			Servidor servidor = crearServidorRespaldo();
			 
			List<Contenido> busqueda = servidor.buscar("cancion", "");
			
			busqueda.remove(null);
			
			assertEquals(busqueda.size(),13);
			
			//cancion5 solo esta en el servidor de respaldo
			busqueda = servidor.buscar("cancion5", "");
		
			assertEquals(busqueda.size(),1);
		
			
			//prueba para el anuncio en el servidor de respaldo
			busqueda = servidor.buscar("cancion5", "");
		
			assertEquals(busqueda.size(),2);
		}
		
		
		@Test(expected=InvalidTokenException.class)
		public void tokenTimedOutTest() throws InvalidTokenException {
			
			Servidor servidor = crearServidor();
			
			String token = servidor.alta();
			
			List<Contenido> c = null;
			//hacer que caduque el token
			for(int i = 0; i<10; i++){
				c = servidor.buscar("cancion5", token);
			}
			
			servidor.buscar("cancion5", token);
			
			
		}
		
		
	

}
