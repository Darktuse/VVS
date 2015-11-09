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
	
    
}
