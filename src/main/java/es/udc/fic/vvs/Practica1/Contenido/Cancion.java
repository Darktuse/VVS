package es.udc.fic.vvs.Practica1.Contenido;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * En esta clase se definen las canciones, que son un tipo de Contenido 
 * y que, por lo tanto, consiste en una implementacion de dicha interfaz.
 * 
 * 
 * @author elenamdf, mateof, Darktuse
 * @version 10/11/2015
 * 
 *
 */

public class Cancion implements Contenido {
	
	// Atributos de Cancion.

	private String titulo;
	private int duracion;
	
	/**
	 * Constructor para la cancion.
	 * @param titulo El titulo de la cancion.
	 * @param duracion La duracion de la cancion.
	 */
	public Cancion(String titulo, int duracion){
		this.titulo = titulo;
		this.duracion = duracion;
	}
	
	
	// Métodos de la interfaz.

	/**
	 * Metodo que devuelve una cadena de caracteres correspondiente
	 * con el titulo de la cancion.
	 * @return El titulo de la cancion.
	 */
	public String obtenerTitulo() {
		return this.titulo;
	}

	/**
	 * Metodo que devuelve la duracion de la cancion.
	 * @return La duracion de la cancion.
	 */
	public int obtenerDuracion() {
		return this.duracion;
	}

	/** 
	 * Metodo que devuelve la lista de reproduccion correspondiente con el
	 * contenido, que en este caso, es la propia cancion.
	 * @return Una lista con los contenidos existentes en la lista de reproduccion.
	 */
	public List<Contenido> obtenerListaReproduccion() {
		List<Contenido> listaReproduccion = new ArrayList<Contenido>();
		listaReproduccion.add(this);
		
		return listaReproduccion;
	}

	
	/**
	 * Metodo que devuelve la lista de contenidos que contienen la subcadena que
	 * se pasa como parametro en su titulo.
	 * @param subcadena La subcadena que se quiere buscar en el titulo de las canciones
	 * @return Una lista de contenido que incluya la cancion en caso de que su titulo contenga la subcadena. Si no,
	 * devolvera una lista vacia.
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
	 * El metodo de agregar no tiene efecto en la clase Cancion.
	 */
	public void agregar(Contenido contenido, Contenido predecesor) {
		// NO TIENE EFECTO
	}

	/**
	 * El metodo de eliminar no tiene efecto en la clase Cancion.
	 */
	public void eliminar(Contenido contenido) {
		// NO TIENE EFECTO
		
	}

}
