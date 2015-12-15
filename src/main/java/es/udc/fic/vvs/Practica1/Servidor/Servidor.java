package es.udc.fic.vvs.Practica1.Servidor;

import java.util.List;

import org.junit.contrib.theories.Theories;
import org.junit.runner.RunWith;
import com.pholser.junit.quickcheck.runner.*;

import es.udc.fic.vvs.Practica1.Contenido.Contenido;

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
@RunWith(JUnitQuickcheck.class)
public interface Servidor  {
		
		/**
		 * Este metodo se encarga de obtener el nombre del servidor.
		 * @return El nombre del servidor.
		 */
		public String obtenerNombre();
		
		/**
		 * Este metodo sirve para dar de alta a un usuario en el servidor.
		 * @return El token correspondiente al usuario (similar a una password) con posibilidad de
		 * obtener hasta 10 contenidos posteriormente.
		 */
		public String alta();
		
		/**
		 * Este metodo sirve para dar de baja a un usuario invalidando su token.
		 * @param token El token del usuario que se quiere dar de baja.
		 * @throws InvalidTokenException Excepcion que salta en el momento en el que se quiera dar
		 * de baja un token que ya no sea valido.
		 */
		public void baja (String token) throws InvalidTokenException;
		
		/**
		 * Este metodo sirve para agregar cualquier tipo de contenido al servidor.
		 * Simplemente se podra agregar en caso de que se le pase el token especial de administrador.
		 * @param contenido El contenido que se quiere agregar al servidor.
		 * @param token El token que se le pasa para ver si puede agregar o no contenido.
		 * @throws InvalidTokenException Excepcion que salta en el momento en el que no se corresponda
		 * el token con el especial de administrador.
		 */
		public void agregar (Contenido contenido, String token) throws InvalidTokenException;
		
		/**
		 * Este metodo sirve para eliminar cualquier tipo de contenido del servidor.
		 * Simplemente se podra eliminar en caso de que se le pase el token especial de administrador.
		 * @param contenido El contenido que se quiere eliminar del servidor.
		 * @param token El token que se le pasa para ver si puede eliminar o no el contenido.
		 * @throws InvalidTokenException Excepcion que salta en el momento en el que no se corresponda
		 * el token con el especial de administrador.
		 */
		public void eliminar (Contenido contenido, String token) throws InvalidTokenException;
		
		/**
		 * Este metodo sirve para buscar en los contenidos que contenga el servidor, aquellos cuyo titulo
		 * contenga la subcadena que se le pasa como parametro.
		 * En caso de que se le pase token de usuario valido, se devolveran hasta un maximo de 10 contenidos
		 * (si el token es nuevo). Si se le pasa un token vacio, se le devolveran todos los contenidos que se 
		 * correspondan con la busqueda, pero con un anuncio cada tres de ellos.
		 * @param subcadena La subcadena por la que queremos buscar si se encuentra en los titulos
		 * de los contenidos.
		 * @param token El token de usuario para verificar que puede obtener contenido o si hay que
		 * introducirle anuncios en la lista que se devuelve.
		 * @return La lista de contenidos (con o sin anuncios) que se corresponden con los resultados
		 * de la busqueda por la subcadena en los titulos.
		 * @throws InvalidTokenException Excepcion que salta en el momento en el que se le introduzca
		 * un token invalido (que ya ha sido dado de baja o que directamente no existe).
		 */
		public List<Contenido> buscar (String subcadena, String token) throws InvalidTokenException;
		
		
}
