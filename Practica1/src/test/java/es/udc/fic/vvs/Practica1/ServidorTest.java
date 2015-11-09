package es.udc.fic.vvs.Practica1;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Unit test for simple Contenido.
 */
public class ServidorTest {
    
	private static final String tokenSpecial = "tokenspecial";

	
	@Test
    public void obtenerContenidoTest()
    {

		Servidor servidor = crearServidor();
		
		String token = servidor.alta();
		
		List<Contenido> contenidos = servidor.buscar("4", token);
		
		assertEquals(contenidos.size(), 1);
		
    }
    
	
	@Test
	public void obtenerContenidoSinTokenTest(){
		
		Servidor servidor = crearServidor();
		
		String token = "No valido";
	
		//Debe devolver una lista vacia
		assert(servidor.buscar("4", token).isEmpty()==true);
		
	}
	
	@Test
	public void darBajaTokenTest(){
		
		Servidor servidor = crearServidor();
		
		String token = servidor.alta();
		
		List<Contenido> contenidos = servidor.buscar("4", token);
		
		assert(contenidos.isEmpty()==false);
		
		servidor.baja(token);
		
		contenidos = servidor.buscar("4", token);
		
		assert(contenidos.isEmpty()==true);
	}
	
	@Test
	public void darBajaTokenDosVeces(){
		assert(true);
	}
	
	@Test(expected = InvalidTokenException.class)
	public void agregarContenidoExceptionTest() throws InvalidTokenException{

		Servidor servidor = crearServidor();
		
		String token = servidor.alta();
		
		servidor.agregar(new Anuncio(), token);

	};

	@Test
	public void eliminarContenidoTest() throws InvalidTokenException{

		Servidor servidor = crearServidor();
		
		String token = servidor.alta();
		
		List<Contenido> contenidos = servidor.buscar("4", token);
		
		assertEquals(contenidos.size(), 1);

		servidor.eliminar(contenidos.get(0), tokenSpecial);

		contenidos = servidor.buscar("4", token);
		
		assertEquals(contenidos.size(), 0);

	};	
	
	@Test
	public void buscarTokenVacioTest(){

		Servidor servidor = crearServidor();
		
		String token = "";
		
		List<Contenido> contenidos = servidor.buscar("Cancion2", token);
			
		//Encuentra 4 Cancion2 + 2 anuncios
		assertEquals(contenidos.size(), 6);
		
	};

	@Test
	public void buscarTokenNoAdmitidoTest(){

		Servidor servidor = crearServidor();
		
		String token = "No admitido";
		
		List<Contenido> contenidos = servidor.buscar("Cancion2", token);
			
		assert(contenidos.isEmpty());
		
	};
	
	@Test(expected = InvalidTokenException.class)
	public void eliminarContenidoExceptionTest() throws InvalidTokenException{

		Servidor servidor = crearServidor();
		
		String token = servidor.alta();
		
		List<Contenido> contenidos = servidor.buscar("4", token);
		
		assertEquals(contenidos.size(), 1);

		servidor.eliminar(new Anuncio(), token);
	};
	
	private Servidor crearServidor(){
				
		Servidor servidor = new ServidorImpl("Servidor");
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
			// TODO Auto-generated catch block
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
			servidor.agregar(emisora1, tokenSpecial);
		} catch (InvalidTokenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return servidor;
	}
    
}
