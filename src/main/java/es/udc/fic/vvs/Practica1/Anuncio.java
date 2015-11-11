package es.udc.fic.vvs.Practica1;

import java.util.ArrayList;
import java.util.List;

/**
 * En esta clase se definen los anuncios, que son un tipo de Contenido 
 * y que, por lo tanto, consiste en una implementacion de dicha interfaz.
 * 
 * 
 * @author elenamdf, mateof, Darktuse
 * @version 10/11/2015
 * 
 * 
 */

public class Anuncio implements Contenido {
	
	// Atributos de la clase.
	
	private String titulo;
	private int duracion;


	/**
	 * Constructor para el Anuncio.
	 */
	public Anuncio(){
		this.titulo = "PUBLICIDAD";
		this.duracion = 5;
	}
	
		
	// Métodos de la interfaz.

	/** 
	 * Metodo que devuelve una cadena de caracteres correspondiente
	 * con el titulo del anuncio.
	 * @return El titulo del anuncio.
	 */
	public String obtenerTitulo() {
		// Los anuncios siempre tienen como título "PUBLICIDAD".
		return titulo;
	}

	
	/** 
	 * Metodo que devuelve el numero de segundos que dura un anuncio.
	 * @return La duracion del anuncio.
	 */
	public int obtenerDuracion() {
		// Los anuncios siempre duran 5 segundos.
		return duracion;
	}

	
	/** 
	 * Metodo que devuelve la lista de reproduccion correspondiente con el
	 * contenido, que en este caso, es el propio anuncio.
	 * @return Una lista con los contenidos existentes en la lista de reproduccion.
	 */
	public List<Contenido> obtenerListaReproduccion() {
		List<Contenido> listaReproduccion = new ArrayList<Contenido>();
		listaReproduccion.add(this);
		
		return listaReproduccion;
	}

	/**
	 * Metodo que devuelve una lista de contenidos en los que el titulo contenga
	 * la subcadena que se le pasa como parametro. En caso del Anuncio solo
	 * devolvera el propio anuncio si contiene la subcadena.
	 * @param subcadena La cadena de caracteres que se quiere buscar en el titulo.
	 * @return Una lista de contenidos existentes que contengan la subcadena en su titulo.
	 */
	public List<Contenido> buscar(String subcadena) {

		List<Contenido> contenidos = new ArrayList<Contenido>();
		
		if ((titulo.toLowerCase()).contains(subcadena.toLowerCase())){
			contenidos.add(this);
		} else {
			System.out.println("No existe ningún contenido correspondiente a esa búsqueda.");
		}
		
		return contenidos;
	}
	
	
	
	// NO TIENEN EFECTO EN ESTA CLASE.

	/**
	 * El metodo de agregar no tiene efecto en la clase Anuncio.
	 */
	public void agregar(Contenido contenido, Contenido predecesor) {
		// NO TIENE EFECTO
		
	}

	/**
	 * El metodo de eliminar no tiene efecto en la clase Anuncio.
	 */
	public void eliminar(Contenido contenido) {
		// NO TIENE EFECTO
		
	}

}
