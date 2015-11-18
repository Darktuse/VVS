package es.udc.fic.vvs.Practica1.Servidor;

/**
 * Esta es la clase correspondiente con la Excepcion que nos
 * indica cuando se ha intentado utilizar un token que no es valido, ya haya
 * caducado o es que nunca ha sido creado. * 
 * 
 * @author elenamdf, mateof, Darktuse
 * @version 10/11/2015
 * 
 * 
 */

public class InvalidTokenException extends Exception{
	
	/**
	 * Constructor de la excepcion.
	 */
	InvalidTokenException(){
		System.out.println("Token no valido");
	}

}
