package es.udc.fic.vvs.Practica1.JCheck;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.jcheck.annotations.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;

import es.udc.fic.vvs.Practica1.Contenido.Anuncio;
import es.udc.fic.vvs.Practica1.Contenido.Cancion;
import es.udc.fic.vvs.Practica1.Contenido.Contenido;
import es.udc.fic.vvs.Practica1.Contenido.ContenidoInexistenteException;
import es.udc.fic.vvs.Practica1.Contenido.Emisora;
import es.udc.fic.vvs.Practica1.Servidor.InvalidTokenException;
import es.udc.fic.vvs.Practica1.Servidor.Servidor;
import es.udc.fic.vvs.Practica1.Servidor.ServidorImplSimple;

/**
 * Unit test for simple ServidorSimple.
 */
@RunWith(org.jcheck.runners.JCheckRunner.class)
@Configuration(tests=100)
public class ServidorTest {
	private static final String tokenSpecial = "tokenspecial";


	// Hay mas tokens al intentar dar de baja por segunda vez
	@Test(expected = InvalidTokenException.class)
	@Configuration(tests=100)
	public void darBajaTokenDosVecesVariosAdmitidos(int i) throws InvalidTokenException{

		Servidor servidor = crearServidor();
		
		String token = servidor.alta();
		for (int n=0;i>n;n++)
			servidor.alta();
		
		servidor.baja(token);
		
		servidor.baja(token);
		
	}
	
	@Test
	@Configuration(tests=100)
	public void buscarListaCancionesVaciaTest(String s) throws InvalidTokenException {

		Servidor servidor = new ServidorImplSimple(s);

		String token = "";

		List<Contenido> contenidos = servidor.buscar("Cancion2", token);

		assertTrue(contenidos.isEmpty());
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
