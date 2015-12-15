package es.udc.fic.vvs.Practica1.Contenido;

/**
 * Esta es la clase correspondiente con la Excepcion que nos
 * indica cuando se ha intentado acceder (ya sea para eliminar, agregar...)
 * a un contenido no existente. * 
 * 
 * @author elenamdf, mateof, Darktuse
 * @version 10/11/2015
 * 
 * 
 */

public class ContenidoInexistenteException extends Exception{
	
	/**
	 * Constructor de la excepcion.
	 */
	public ContenidoInexistenteException(){
		System.out.println("Contenido inexistente");
	}

}
