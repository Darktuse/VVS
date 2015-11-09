package es.udc.fic.vvs.Practica1;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple Contenido.
 */
public class ContenidoTest extends TestCase {
    
	
    public void testCrearAnuncioYObtenerDatos()
    {
        Contenido anuncio = new Anuncio();
        
        assertEquals("PUBLICIDAD", anuncio.obtenerTitulo());
        assertEquals(5, anuncio.obtenerDuracion());
    }
    
    
}
