package es.udc.fic.vvs.Practica1.Contenido;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import es.udc.fic.vvs.Practica1.Contenido.Anuncio;
import es.udc.fic.vvs.Practica1.Contenido.Cancion;
import es.udc.fic.vvs.Practica1.Contenido.Contenido;
import es.udc.fic.vvs.Practica1.Contenido.ContenidoInexistenteException;
import es.udc.fic.vvs.Practica1.Contenido.Emisora;
import static org.junit.Assert.*;

public class ContenidoTest {

	@Test
	public void testAgregarContenidoEmisora() throws ContenidoInexistenteException {

		Contenido emisora = new Emisora("RockFm");
		List<Contenido> lista = new ArrayList<Contenido>();

		assertEquals(0, emisora.obtenerListaReproduccion().size());
		assertEquals(lista, emisora.obtenerListaReproduccion());

		Contenido anuncio = new Anuncio();
		lista.add(anuncio);

		emisora.agregar(anuncio, null);

		assertEquals(1, emisora.obtenerListaReproduccion().size());
		assertEquals(lista, emisora.obtenerListaReproduccion());
	}

	@Test(expected = ContenidoInexistenteException.class)
	public void testAgregarContenidoEmisoraNoVacia() throws ContenidoInexistenteException {

		Contenido emisora = new Emisora("RockFm");
		List<Contenido> lista = new ArrayList<Contenido>();

		assertEquals(0, emisora.obtenerListaReproduccion().size());
		assertEquals(lista, emisora.obtenerListaReproduccion());

		Contenido anuncio = new Anuncio();
		lista.add(anuncio);

		emisora.agregar(anuncio, null);
		emisora.agregar(anuncio, null);

		assertEquals(1, emisora.obtenerListaReproduccion().size());
		assertEquals(lista, emisora.obtenerListaReproduccion());
	}

	@Test
	public void testObtenerDuracionEmisoraNoVacia() throws ContenidoInexistenteException {

		Contenido emisora = new Emisora("RockFm");
		int duracion = 0;

		assertEquals(duracion, emisora.obtenerDuracion());

		Contenido cancion = new Cancion("Cancion1", 133);
		duracion += cancion.obtenerDuracion();
		emisora.agregar(cancion, null);

		assertEquals(duracion, emisora.obtenerDuracion());

		Contenido anuncio = new Anuncio();
		duracion += anuncio.obtenerDuracion();
		emisora.agregar(anuncio, cancion);

		assertEquals(duracion, emisora.obtenerDuracion());
	}

	@Test
	public void testAgregarContenidoAnuncioIncorrecto() throws ContenidoInexistenteException {

		Contenido anuncio = new Anuncio();
		List<Contenido> c = anuncio.obtenerListaReproduccion();
		anuncio.agregar(anuncio, null);
		List<Contenido> c2 = anuncio.obtenerListaReproduccion();
		assertEquals(c, c2);

	}

	@Test
	public void testAgregarContenidoCancionIncorrecto() throws ContenidoInexistenteException {

		Contenido cancion = new Cancion("cancion1", 100);
		List<Contenido> c = cancion.obtenerListaReproduccion();
		cancion.agregar(cancion, null);
		List<Contenido> c2 = cancion.obtenerListaReproduccion();
		assertEquals(c, c2);

	}

	@Test
	public void testBuscarContenidoEmisora() throws ContenidoInexistenteException {

		Contenido emisora = new Emisora("RockFm");

		Contenido anuncio = new Anuncio();
		Contenido cancion = new Cancion("canción1",100);
		Contenido cancion5 = new Cancion("canción5",100);

		emisora.agregar(anuncio, null);
		emisora.agregar(cancion, anuncio);
		emisora.agregar(cancion5, cancion);
		
		
		assertEquals(emisora.buscar("5").size(),1);
		assertEquals(emisora.buscar("5").get(0),cancion5);

		assertEquals(emisora.buscar("canción").size(),2);
		assertEquals(emisora.buscar("canción").get(0),cancion);
		assertEquals(emisora.buscar("canción").get(1),cancion5);

		assertEquals(emisora.buscar("Publi").size(),1);
		assertEquals(emisora.buscar("Publi").get(0),anuncio);

	}

}
