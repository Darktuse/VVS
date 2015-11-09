package es.udc.fic.vvs.Practica1;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for simple Contenido.
 */
public class ContenidoTest {
    
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
    
}
