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
    
	
	@Test(expected = ContenidoInexistenteException.class)
	public void testAgregarContenidoEmisoraPredecesorInvalido() throws ContenidoInexistenteException{
		
		Contenido emisora = new Emisora("RockFm");
		
		Contenido cancion1= new Cancion("Cancion1", 93);
		Contenido cancion2= new Cancion("Cancion2", 124);
		Contenido anuncio = new Anuncio();
		
		emisora.agregar(cancion1, null);
		emisora.agregar(anuncio, cancion2);
		
	}
	
	@Test(expected = ContenidoInexistenteException.class)
	public void testEliminarContenidoNoExistenteEmisora() throws ContenidoInexistenteException{
		
		Contenido emisora = new Emisora("RockFm");
		
		Contenido cancion1 = new Cancion("Cancion1", 123);
		Contenido cancion2 = new Cancion("Cancion2", 87);
		Contenido anuncio = new Anuncio();
		
		emisora.agregar(cancion1, null);
		emisora.agregar(cancion2, cancion1);
		
		emisora.eliminar(anuncio);
	}
	
	
	@Test
	public void testBuscarEnEmisoraCorrecto() throws ContenidoInexistenteException{
		
		Contenido emisora = new Emisora("RockFm");
		List<Contenido> lista = new ArrayList<Contenido>();
		
		Contenido cancion1 = new Cancion("Cancion1", 132);
		Contenido cancion2 = new Cancion("Cancion2", 98);
		Contenido cancion3 = new Cancion("Little Song", 52);
		Contenido anuncio = new Anuncio();
		
	
		emisora.agregar(cancion1, null);
		emisora.agregar(anuncio, cancion1);
		emisora.agregar(cancion2, cancion1);
		emisora.agregar(cancion3, anuncio);
		
		lista.add(cancion1);
		lista.add(cancion2);
		lista.add(anuncio);
		lista.add(cancion3);
		
		
		
		assertEquals(lista, emisora.obtenerListaReproduccion());
		
		assertEquals(1, emisora.buscar("ttle").size());
		assertEquals(cancion3, emisora.buscar("ttle").get(0));
		
		assertEquals(2, emisora.buscar("cion").size());
		assertEquals(cancion1, emisora.buscar("cion").get(0));
		assertEquals(cancion2, emisora.buscar("cion").get(1));
	
		assertEquals(1, emisora.buscar("ici").size());
		assertEquals(anuncio, emisora.buscar("ici").get(0));
	}
	
	
	@Test
	public void testBuscarEnEmisoraIncorrecto() throws ContenidoInexistenteException{
		
		Contenido emisora = new Emisora("RockFm");
		List<Contenido> lista = new ArrayList<Contenido>();
		List<Contenido> listaVacia= new ArrayList<Contenido>();
		
		Contenido anuncio = new Anuncio();
		Contenido cancion1 = new Cancion("Cancion1", 93);
		
		emisora.agregar(anuncio, null);
		emisora.agregar(cancion1, anuncio);
		emisora.agregar(anuncio, cancion1);
		
		lista.add(anuncio);
		lista.add(cancion1);
		lista.add(anuncio);
		
		
		assertEquals(3, emisora.obtenerListaReproduccion().size());
		assertEquals(lista, emisora.obtenerListaReproduccion());
		
		assertEquals(0, emisora.buscar("ama").size());
		assertEquals(listaVacia, emisora.buscar("ama"));
		
		
		
	}
}
