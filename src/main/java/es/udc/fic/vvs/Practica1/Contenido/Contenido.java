package es.udc.fic.vvs.Practica1.Contenido;

import java.util.List;

/**
 * Esta es la interfaz correspondiente con los diferentes contenidos.
 * La implementan los Anuncios, las Canciones y las Emisoras.
 * 
 * 
 * @author elenamdf, mateof, Darktuse
 * @version 10/11/2015
 * 
 * 
 */

public interface Contenido {
	
	/**
	 * Este metodo se encarga de devolver el titulo del contenido.
	 * @return El titulo del contenido.
	 */
	public String obtenerTitulo();
	
	/**
	 * Este metodo se encarga de devolver la duracion del contenido.
	 * @return La duracion del contenido.
	 */
	public int obtenerDuracion();
	
	/**
	 * Este metodo se encarga de devolver la lista de reproduccion del contenido,
	 * que en caso del Anuncio y la Cancion sera una lista de un solo elemento,
	 * y que en caso de la Emisora puede ser de tamaño variable.
	 * @return La lista de reproduccion del contenido.
	 */
	public List<Contenido> obtenerListaReproduccion();
	
	/**
	 * Este metodo se encarga de realizar una busqueda por el titulo de los contenidos,
	 * mirando si este contiene la subcadena que se le pase como parametro.
	 * @param subcadena La subcadena por la que se quiere buscar. 
	 * @return La lista de los contenidos cuyo titulo contiene la subcadena que se le ha pasado como parametro.
	 */
	public List<Contenido> buscar (String subcadena);
	
	/**
	 * Este metodo se encarga de agregar un contenido a la lista de reproduccion.
	 * Este metodo solo es valido para el contenido de tipo Emisora.
	 * @param contenido El contenido que se quiere insertar en la lista de reproduccion.
	 * @param predecesor El contenido tras el que se quiere insertar el nuevo contenido.
	 * @throws ContenidoInexistenteException La excepcion que salta en el momento en el que se busque como predecesor un
	 * contenido que no exista en la lista de reproduccion con anterioridad.
	 */
	public void agregar (Contenido contenido, Contenido predecesor) throws ContenidoInexistenteException;

	/**
	 * Este metodo se encarga de eliminar un contenido de la lista de reproduccion.
	 * Este metodo solo es valido para el contenido de tipo Emisora.
	 * @param contenido El contenido que se quiere eliminar de la lista de reproduccion.
	 * @throws ContenidoInexistenteException La excepcion que salta en el momento en el que se intente eliminar un
	 * contenido que no exista en la lista de reproduccion.
	 */
	public void eliminar (Contenido contenido) throws ContenidoInexistenteException;
	
	
}
