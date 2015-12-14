package es.udc.fic.vvs.Practica1.Servidor;

/**
 * En esta clase se definen la estructura del Token,
 * que contendra una cadena de caracteres correspondiente con el 
 * token en si (similar a una password de usuario) y un numero,
 * que nos indicara el numero de contenidos disponibles para obtener que
 * le quedan.
 * 
 * 
 * @author elenamdf, mateof, Darktuse
 * @version 10/11/2015
 * 
 * 
 */

public class Token {

	private String token;
	private int numero;
	
	/**
	 * Constructor del Token
	 * @param token La cadena de caracteres correspondiente al token.
	 * @param numero El numero de contenidos disponibles para ese token.
	 */
	public Token(String token, int numero) {
		super();
		this.token = token;
		this.numero = numero;
	}

	
	/**
	 * Este metodo se encarga de devolver el token.
	 * @return La cadena de caracteres del token.
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Este metodo se encarga de settear el token.
	 * @param token La cadena de caracteres del token que se quiere settear.
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Este metodo se encarga de devolver el numero de contenidos disponibles del token.
	 * @return El numero de contenidos disponibles del token.
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * Este metodo se encarga de settear el numero de contenidos disponibles del token.
	 * @param numero El numero de contenidos disponibles del token que se quiere settear.
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	/**
	 * Este metodo se encarga de realizar la resta de un contenido al numero
	 * de los que le quedaban disponibles al token y de decirnos si se ha podido realizar
	 * esta diferencia o no. 
	 * @return True si se ha podido realizar la resta de manera correcta, False en el caso contrario.
	 */
	public boolean restarUno(){
		if (numero>0){
			numero--;
			return true;
		}
		return false;
	}
	
	/**
	 * Este metodo se encarga de realizar la resta de un numero num de contenidos
	 * al numero de los que le quedaban disponibles al token, y de decirnos si se ha podido realizar
	 * esta diferencia o no.
	 * @param num El numero de contenidos que se le quiere restar a los disponibles del token.
	 * @return True si se ha podido realizar la resta de manera correcta, False en el caso contrario.
	 */
	public boolean restarVarios(int num){
		if (numero>0 && numero>num) {
			numero -= num;
			return true;
		}
		return false;
	}
	
	
	
}
