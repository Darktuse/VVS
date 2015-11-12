package es.udc.fic.vvs.Practica1;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * En esta clase se definen las emisoras, que son un tipo de Contenido 
 * y que, por lo tanto, consiste en una implementacion de dicha interfaz.
 * 
 * 
 * @author elenamdf, mateof, Darktuse
 * @version 10/11/2015
 * 
 * 
 */
public class Emisora implements Contenido {
	
	// Atributos de Emisora.
	
	private String titulo;
	private int duracion;
	private List<Contenido> contenidos = new ArrayList<Contenido>();
	
	// Constructores.
	
	/** 
	 * Constructor de la emisora.
	 * @param titulo El titulo que se le quiere poner a la emisora.
	 */
	public Emisora(String titulo){
		super();
		this.titulo = titulo;
		this.duracion = 0;
	}

	// Métodos de la interfaz.
	
	/**
	 * Metodo que devuelve una cadena de caracteres correspondiente
	 * con el titulo de la emisora.
	 * @return El titulo de la emisora.
	 */
	public String obtenerTitulo() {
		return this.titulo;
	}

	/**
	 * Metodo que devuelve la duracion total de la emisora.
	 * Esta duracion se corresponde con la suma de la duracion de todos los 
	 * contenido que tiene en la lista de reproduccion la emisora.
	 * @return La duracion total de la emisora.
	 */
	public int obtenerDuracion() {
		int duracion = 0;
		for (Contenido c: contenidos){
			duracion += c.obtenerDuracion();
		}
		return duracion;
	}

	
	/** 
	 * Metodo que devuelve la lista de reproduccion correspondiente con el
	 * contenido, que es una lista de contenidos que puede estar formada por Canciones,
	 * Anuncios o incluso otra emisora.
	 * @return Una lista con los contenidos existentes en la lista de reproduccion.
	 */
	public List<Contenido> obtenerListaReproduccion() {
		
		return contenidos;
	}

	
	/**
	 * Metodo que devuelve la lista de contenidos que contienen la subcadena que
	 * se pasa como parametro en su titulo.
	 * @param subcadena La subcadena que se quiere buscar en el titulo de los contenidos
	 * @return Una lista de contenidos que incluyan aquellos cuyo titulo contenga la subcadena. Si no existen,
	 * devolvera una lista vacia.
	 */
	public List<Contenido> buscar(String subcadena) {
		
		List<Contenido> listaContenidos = new ArrayList<Contenido>();
		
		for (int i = 0; i < contenidos.size(); i++){
			if ((contenidos.get(i).obtenerTitulo().toLowerCase()).contains(subcadena.toLowerCase())) {
				listaContenidos.add(contenidos.get(i));
			}
		}
			
		
		return listaContenidos;
	}

	
	/**
	 * Este metodo se encarga de agregar contenidos a la lista de reproduccion de la emisora.
	 * Se le pasara el contenido que se quiere agregar y tras el cual se quiere insertar.
	 * Tambien se incrementa la duracion de la emisora sumando la duracion del contenido que se inserta. 
	 * @param contenido El contenido que se quiere agregar a la emisora.
	 * @param predecesor El contenido tras el cual se quiere insertar el nuevo.
	 * @throws ContenidoInexistenteException La excepcion que salta en el momento en el que se busque como predecesor un
	 * contenido que no exista en la lista de reproduccion con anterioridad.
	 */
	public void agregar(Contenido contenido, Contenido predecesor) throws ContenidoInexistenteException {
		// primero busca el contenido predecesor, mira la posicion en la que está, y hace un add
		// del nuevo contenido en la siguiente posicion
		if (contenidos.isEmpty() && predecesor==null){
			contenidos.add(contenido);
			duracion += contenido.obtenerDuracion();
		} else {
			if (predecesor == null) throw new ContenidoInexistenteException();
			int posicion = buscarContenido(predecesor);
			contenidos.add(posicion+1, contenido);
			duracion += contenido.obtenerDuracion();
		}
		
		
	
	}

	
	/**
	 * Este metodo se encarga de eliminar un contenido de la lista de reproduccion de la emisora.
	 * @param contenido El contenido que se quiere eliminar de la lista de reproduccion.
	 * @throws ContenidoInexistenteException La excepcion que salta en el momento en el que se intente eliminar un
	 * contenido que no exista en la lista de reproduccion.
	 */
	public void eliminar(Contenido contenido) throws ContenidoInexistenteException {

		int posicion = buscarContenido(contenido);
		duracion -= contenido.obtenerDuracion();
		contenidos.remove(posicion);
		
	}
	
	
	// FUNCIONES AUXILIARES
	/**
	 * FUNCION AUXILIAR
	 * 
	 * Funcion auxiliar que nos sirve para buscar si un contenido existe en la lista
	 * de reproduccion y en que posicion se encuentra dicho contenido.
	 * @param contenido El contenido del que queremos saber la posicion
	 * @return La posicion en la que se encuentra el contenido que hemos buscado.
	 * @throws ContenidoInexistenteException Excepcion que salta en el momento en el que no se encuentra
	 * el contenido en la lista de reproduccion.
	 */
	private int buscarContenido(Contenido contenido) throws ContenidoInexistenteException{
		Integer posicion = null;
		
		for (int i = 0; i < contenidos.size(); i++){
			if (contenidos.get(i).equals(contenido)){
				posicion = i;
			}
		}
		
		if (posicion == null){
			throw new ContenidoInexistenteException();
		}
		
		return posicion;
	}

}
