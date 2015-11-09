package es.udc.fic.vvs.Practica1;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for simple Contenido.
 */
public class ContenidoTest {
    
	/* -------------- TESTS DE ANUNCIO ----------------- */
	
	@Test
    public void testCrearAnuncioYObtenerDatos()
    {
        Contenido anuncio = new Anuncio();
        
        assertEquals("PUBLICIDAD", anuncio.obtenerTitulo());
        assertEquals(5, anuncio.obtenerDuracion());
        
        List<Contenido> lista = new ArrayList<Contenido>();
        lista.add(anuncio);
        
        assertEquals(1, anuncio.obtenerListaReproduccion().size());
        assertEquals(lista, anuncio.obtenerListaReproduccion());
        
    }
    
	
	@Test
	public void testBuscarEnAnuncioCorrecto(){
		
		Contenido anuncio = new Anuncio();
		List<Contenido> lista = new ArrayList<Contenido>();
		lista.add(anuncio);
		
		assertEquals(1, anuncio.buscar("PUBLI").size());
		assertEquals(lista, anuncio.buscar("PUBLI"));
		
	}
	
	@Test
	public void testBuscarEnAnuncioIncorrecto(){
		
		Contenido anuncio = new Anuncio();
		List<Contenido> listaVacia = new ArrayList<Contenido>();
		
		
		assertEquals(0, anuncio.buscar("CANCI").size());
		assertEquals(listaVacia, anuncio.buscar("CANCI"));
		
	}
	
	
	/* -------------- TESTS DE CANCION ----------------- */
	
	@Test
	public void testCrearCancionYObtenerDatos(){
	    Contenido cancion = new Cancion("Cancion1", 127);
	    
	    assertEquals("Cancion1", cancion.obtenerTitulo());
	    assertEquals(127, cancion.obtenerDuracion());
	    
	    List<Contenido> lista = new ArrayList<Contenido>();
	    lista.add(cancion);
	       
	    assertEquals(1, cancion.obtenerListaReproduccion().size());
	    assertEquals(lista, cancion.obtenerListaReproduccion());
	       
	}
	    
		
	@Test
	public void testBuscarEnCancionCorrecto(){
			
		Contenido cancion = new Cancion("Cancion1", 127);
		List<Contenido> lista = new ArrayList<Contenido>();
		lista.add(cancion);
			
		assertEquals(1, cancion.buscar("1").size());
		assertEquals(lista, cancion.buscar("1"));
			
	}
		
	@Test
	public void testBuscarEnCancionIncorrecto(){
		
		Contenido cancion = new Cancion("Cancioncita", 93);
		List<Contenido> listaVacia = new ArrayList<Contenido>();
		
		
		assertEquals(0, cancion.buscar("alo").size());
		assertEquals(listaVacia, cancion.buscar("alo"));
		
	}
	
	
	/* -------------- TESTS DE EMISORA ----------------- */

	@Test
	public void testCrearEmisoraYObtenerDatos(){
		
		Contenido emisora = new Emisora("RockFm");
		List<Contenido> lista = new ArrayList<Contenido>();
		
		assertEquals("RockFm", emisora.obtenerTitulo());
		assertEquals(0, emisora.obtenerDuracion());
		assertEquals(lista, emisora.obtenerListaReproduccion());
	}
	
	
	@Test
	public void testAgregarContenidoEmisora() throws ContenidoInexistenteException{
		
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
	
	
	@Test
	public void testObtenerDuracionEmisoraNoVacia() throws ContenidoInexistenteException{
		
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
	public void testEliminarContenidoEmisora() throws ContenidoInexistenteException{
		
		Contenido emisora = new Emisora("RockFm");
		
		Contenido cancion = new Cancion("Cancion1", 94);
		Contenido anuncio = new Anuncio();
		
		List<Contenido> lista = new ArrayList<Contenido>();
		
		emisora.agregar(anuncio, null);
		lista.add(anuncio);
		emisora.agregar(cancion, anuncio);
		lista.add(cancion);
		emisora.agregar(anuncio, cancion);
		lista.add(anuncio);
		
		assertEquals(3, emisora.obtenerListaReproduccion().size());
		assertEquals(lista, emisora.obtenerListaReproduccion());
		
		emisora.eliminar(cancion);
		lista.remove(1);
		
		assertEquals(2, emisora.obtenerListaReproduccion().size());
		assertEquals(lista, emisora.obtenerListaReproduccion());
		
	}
    
}
