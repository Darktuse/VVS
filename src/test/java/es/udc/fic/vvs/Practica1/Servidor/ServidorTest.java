package es.udc.fic.vvs.Practica1.Servidor;

import java.util.Enumeration;
import java.util.List;

import org.junit.Test;
import org.junit.contrib.theories.Theory;
import org.junit.runner.RunWith;
import org.mockito.internal.util.collections.Iterables;

import com.pholser.junit.quickcheck.ForAll;
import com.pholser.junit.quickcheck.generator.*;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

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
	public void obtenerContenidoTest() throws InvalidTokenException {

		Servidor servidor = crearServidor();

		String token = servidor.alta();

		List<Contenido> contenidos = servidor.buscar("4", token);

		assertEquals(contenidos.size(), 1);

	}

	@Test
	public void obtenerContenidoSinTokenTest() throws InvalidTokenException {

		Servidor servidor = crearServidor();

		String token = "No valido";

		// Debe devolver una lista vacia
		assert (servidor.buscar("4", token).isEmpty() == true);

	}

	@Test
	public void darBajaTokenTest() throws InvalidTokenException {

		Servidor servidor = crearServidor();

		String token = servidor.alta();

		List<Contenido> contenidos = servidor.buscar("4", token);

		assert (contenidos.isEmpty() == false);

		servidor.baja(token);

		contenidos = servidor.buscar("4", token);

		assert (contenidos.isEmpty() == true);
	}

	@Test(expected = InvalidTokenException.class)
	public void darBajaTokenDosVeces() throws InvalidTokenException {

		Servidor servidor = crearServidor();

		String token = servidor.alta();

		servidor.baja(token);

		servidor.baja(token);

	}

	// Hay mas tokens al intentar dar de baja por segunda vez
	@Test(expected = InvalidTokenException.class)
	public void darBajaTokenDosVecesVariosAdmitidos() throws InvalidTokenException{

		Servidor servidor = crearServidor();
		
		String token = servidor.alta();
		for (Integer any : someIntegers())
			servidor.alta();
		
		servidor.baja(token);
		
		servidor.baja(token);
		
	}

	@Test(expected = InvalidTokenException.class)
	public void agregarContenidoExceptionTest() throws InvalidTokenException {

		Servidor servidor = crearServidor();

		String token = servidor.alta();

		servidor.agregar(new Anuncio(), token);

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
	
	@Test
	public void eliminarTodoContenidoTest() throws InvalidTokenException {
		
		Servidor servidor = new ServidorImplSimple("Servidor");
		
		Anuncio a = new Anuncio();
		servidor.agregar(a, tokenSpecial);
		
		String token = servidor.alta();
		
		assert(servidor.buscar("Publi", token).size() == 1);
		
		servidor.eliminar(a, tokenSpecial);
		
	}

	@Test
	public void bajaTokenTest() throws InvalidTokenException {
		
		Servidor servidor = new ServidorImplSimple("Servidor");
		
		Anuncio a = new Anuncio();
		Anuncio a2 = new Anuncio();
		servidor.agregar(a, tokenSpecial);
		
		String token = servidor.alta();
		
		assert(servidor.buscar("Publi", token).size() == 1);
		
		servidor.eliminar(a2, tokenSpecial);

		assert(servidor.buscar("Publi", token).size() == 1);

	}
	
	
	
	@Test
	public void buscarTokenVacioTest() throws InvalidTokenException {
		EtmPoint point = etmMonitor.createPoint("BusinessService:buscarTokenVacioTest");
		Servidor servidor = crearServidor();

		String token = "";

		List<Contenido> contenidos = servidor.buscar("Cancion2", token);

		// Encuentra 4 Cancion2 + 2 anuncios
		assertEquals(contenidos.size(), 6);

	};

	@Test
	public void buscarListaCancionesVaciaTest() throws InvalidTokenException {

		Servidor servidor = new ServidorImplSimple("Servidor");

		String token = "";

		List<Contenido> contenidos = servidor.buscar("Cancion2", token);

		assertTrue(contenidos.isEmpty());
	};

	
	// Lista de canciones mayor que elnumero del token
	@Test
	public void buscarListaCancionesTest() throws InvalidTokenException {

		Servidor servidor = crearServidor();

		String token = servidor.alta();

		List<Contenido> contenidos = servidor.buscar("Cancion", token);

		// Reduce la lista a 10, la catidad del numero del token
		assertEquals(contenidos.size(),10);
	};

	
	@Test
	public void buscarTokenNoAdmitidoTest() throws InvalidTokenException {

		Servidor servidor = crearServidor();

		String token = "No admitido";

		List<Contenido> contenidos = servidor.buscar("Cancion2", token);

		assert (contenidos.isEmpty());

	};

	@Test(expected = InvalidTokenException.class)
	public void eliminarContenidoExceptionTest() throws InvalidTokenException {

		Servidor servidor = crearServidor();

		String token = servidor.alta();

		List<Contenido> contenidos = servidor.buscar("4", token);

		assertEquals(contenidos.size(), 1);

		servidor.eliminar(new Anuncio(), token);
	};

	private Servidor crearServidor() {

		Servidor servidor = new ServidorImplSimple("Servidor");
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

}
