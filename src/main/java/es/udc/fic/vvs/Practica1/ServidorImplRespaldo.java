package es.udc.fic.vvs.Practica1;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 
 * En esta clase se define la implementacion del Servidor con uno de respaldo, que es un tipo de Servidor
 * y que, por lo tanto, consiste en una implementación de dicha interfaz.
 * 
 * 
 * @author elenamdf, mateof, Darktuse
 * @version 10/11/2015
 * 
 * 
 */
public class ServidorImplRespaldo implements Servidor {

	// Atributos del servidor

	private String nombre;
	private List<Contenido> contenidos = new ArrayList<Contenido>();
	private List<Token> tokensAdmitidos = new ArrayList<Token>();
	private static final String tokenSpecial = "tokenspecial";
	private ServidorImplRespaldo servidorRespaldo = null;

	// Constructores

	/**
	 * El constructor del servidor que tendrá un servidor de respaldo.
	 * @param nombre El nombre del servidor principal.
	 * @param servidorRespaldo El servidor de respaldo.
	 */
	public ServidorImplRespaldo(String nombre, Servidor servidorRespaldo) {
		super();
		this.nombre = nombre;
		this.servidorRespaldo = (ServidorImplRespaldo) servidorRespaldo;
	}

	/**
	 * El constructor del servidor de respaldo.
	 * @param nombre El nombre del servidor de respaldo.
	 */
	public ServidorImplRespaldo(String nombre) {
		super();
		this.nombre = nombre;
	}

	// Métodos de la interfaz

	/**
	 * Este metodo se encarga de devolver el nombre del servidor.
	 * @return El nombre del servidor.
	 */
	public String obtenerNombre() {
		return nombre;
	}

	/**
	 * Este metodo se encarga de dar de alta un usuario en el servidor.
	 * En caso de que sea el servidor que tiene uno de respaldo, tambien lo da
	 * de alta en ese.
	 * @return El token que se le devuelve al usuario para poder manejar el resto de los
	 * metodos (similar a una password).
	 */
	public String alta() {
		// Ten que devolver o token. Serve para dar
		// de alta un usuario no servidor.
		String newToken = null;
		boolean exist = true;

		while (exist) {
			newToken = generarToken();
			if (!tokensAdmitidos.isEmpty()) {
				exist = findToken(tokensAdmitidos, newToken);
			} else {
				exist = false;
			}
		}

		Token token = new Token(newToken, 10);
		tokensAdmitidos.add(token);
		if (servidorRespaldo != null)
			servidorRespaldo.setToken(token);

		return token.getToken();
	}

	/**
	 * Este metodo se encarga de dar de baja del servidor a un usuario.
	 * Esto se realiza eliminando el token de la lista de tokens (tanto del servidor
	 * principal como del servidor de respaldo).
	 * @param token El token que se quiere dar de baja del servidor.
	 * @throws InvalidTokenException Excepcion que salta en el momento en el que se quiera dar
	 * de baja un token que ya no sea valido.
	 */
	public void baja(String token) throws InvalidTokenException {
		// Dase de baixa o token, polo que non se recoñecerá
		// como válido nunca máis.
		// IMPLICITAMENTE cando buscas e superas os 10 contidos.

		boolean exist = findToken(tokensAdmitidos, token);

		if (!exist) {
			throw new InvalidTokenException();
		} else {
			if (servidorRespaldo != null)
				servidorRespaldo.baja(token);
			for (int i = 0; i < tokensAdmitidos.size(); i++) {
				if (tokensAdmitidos.get(i).getToken() == token) {
					tokensAdmitidos.remove(i);
					break;
				}

			}
		}

	}

	/**
	 * Este metodo sirve para agregar cualquier tipo de contenido al servidor.
	 * Simplemente se podra agregar en caso de que se le pase el token especial de administrador.
	 * @param contenido El contenido que se quiere agregar al servidor.
	 * @param token El token que se le pasa para ver si puede agregar o no contenido.
	 * @throws InvalidTokenException Excepcion que salta en el momento en el que no se corresponda
	 * el token con el especial de administrador.
	 */
	public void agregar(Contenido contenido, String token) throws InvalidTokenException {

		if (tokenSpecial.equals(token)) {
			this.contenidos.add(contenido);
		} else {
			throw new InvalidTokenException();
		}

	}

	/**
	 * Este metodo sirve para eliminar cualquier tipo de contenido del servidor.
	 * Simplemente se podra eliminar en caso de que se le pase el token especial de administrador.
	 * @param contenido El contenido que se quiere eliminar del servidor.
	 * @param token El token que se le pasa para ver si puede eliminar o no el contenido.
	 * @throws InvalidTokenException Excepcion que salta en el momento en el que no se corresponda
	 * el token con el especial de administrador.
	 */
	public void eliminar(Contenido contenido, String token) throws InvalidTokenException {

		if (tokenSpecial.equals(token)) {

			for (int i = 0; i < contenidos.size(); i++) {
				if (contenidos.get(i) == contenido) {
					contenidos.remove(i);
					break;
				}

			}

		} else {
			throw new InvalidTokenException();
		}

	}

	/**
	 * Este metodo sirve para buscar en los contenidos que contenga el servidor, aquellos cuyo titulo
	 * contenga la subcadena que se le pasa como parametro.
	 * En caso de que se le pase token de usuario valido, se devolveran hasta un maximo de 10 contenidos
	 * (si el token es nuevo). Si se le pasa un token vacio, se le devolveran todos los contenidos que se 
	 * correspondan con la busqueda, pero con un anuncio cada tres de ellos.
	 * En caso de que no encuentre nada en el servidor principal, buscara en el de respaldo.
	 * @param subcadena La subcadena por la que queremos buscar si se encuentra en los titulos
	 * de los contenidos.
	 * @param token El token de usuario para verificar que puede obtener contenido o si hay que
	 * introducirle anuncios en la lista que se devuelve.
	 * @return La lista de contenidos (con o sin anuncios) que se corresponden con los resultados
	 * de la busqueda por la subcadena en los titulos.
	 * @throws InvalidTokenException Excepcion que salta en el momento en el que se le introduzca
	 * un token invalido (que ya ha sido dado de baja o que directamente no existe).
	 */
	public List<Contenido> buscar(String subcadena, String token) throws InvalidTokenException {
		List<Contenido> c = new ArrayList<Contenido>();
		if (token.isEmpty()) {
			c = buscarNome(subcadena);
			if (c.isEmpty()) {
				// Se a lista esta vacia enton vamos a mirar o servidor de
				// respaldo en caso de ter un
				if (servidorRespaldo != null)
					// se non atopou nada, chamase ao outro servidor para mirar
					// o seu contido
					c = servidorRespaldo.buscar(subcadena, token);
			} else {
				// en caso de que non estea vacio
				c = insertaAnuncios(c);
				return c;

			}
		}
		if (findToken(tokensAdmitidos, token)) {
			c = buscarNome(subcadena);
			Token t = buscaToken(token);
			if (c.isEmpty()) {
				// se non atopou nada, chamase ao outro servidor para mirar o
				// seu contido
				if (servidorRespaldo != null) {
					c = servidorRespaldo.buscar(subcadena, token);
				}
			} else {
				c = restarToken(t, c);
			}

		}
		return c;
	}

	// FUNCIONES AUXILIARES

	/**
	 * FUNCION AUXILIAR
	 * 
	 * Este metodo se encarga de insertar anuncios en la lista de contenidos en el momento en 
	 * el que se realiza una busqueda con un token vacio. Inserta siempre un anuncio al principio
	 * y cada tres contenidos.
	 * @param l La lista de contenidos a la que se le quiere añadir la publicidad
	 * @return La lista de contenidos con la publicidad ya añadida.
	 */
	private List<Contenido> insertaAnuncios(List<Contenido> l) {
		int i = 0;
		Anuncio a = new Anuncio();
		List<Contenido> cont = new ArrayList<Contenido>();
		for (Contenido c : l) {
			if (i % 3 == 0) {
				cont.add(a);
				cont.add(c);
			} else {
				cont.add(c);
			}
			i++;
		}
		return cont;
	}

	/**
	 * FUNCION AUXILIAR
	 * 
	 * Este metodo se encarga de restar a la variable numero del Token el numero de contenidos
	 * que ha obtenido en la busqueda. Si el numero de resultados que obtiene es menor que el numero
	 * que le queda disponible, simplemente se restan los valores y se devuelven todos los contenidos.
	 * En cambio, si la busqueda devuelve mas valores que los que le quedan disponibles, nos vemos obligados a
	 * reducir el tamaño de la lista y dar de baja el token (caduca).
	 * @param t El token al que se le deben restar el numero de contenidos que ha obtenido en la busqueda.
	 * @param c La lista de contenidos que se ha obtenido como resultado de la busqueda.
	 * @return La lista de contenidos final (tal y como habia llegado o reducida al numero de contenidos
	 * que le quedasen disponibles al usuario).
	 * @throws InvalidTokenException Excepcion que salta en el momento en el que se intenta dar de baja a un token
	 * ya invalido.
	 */
	private List<Contenido> restarToken(Token t, List<Contenido> c) throws InvalidTokenException {
		/*
		 * Funcion na cal se resta o numero ao token
		 * 
		 * O token pode darse de baixa (operación baja()), co cal o servidor xa
		 * non o recoñecerá como válido nunca mais, ou será "dado de baixa"
		 * implícitamente cando se utilice en operacións buscar até superar os
		 * 10 contidos devoltos. A especificación non indica con mais detalle
		 * como se "contan" eses 10 contidos... cousas que pasan ;-)
		 */
		// Se é menor restase o tamaño dos contidos
		if (c.size() < t.getNumero()) {
			// if (servidorRespaldo!=null)
			t.restarVarios(c.size());
		} else {
			// se e maior ou igual reduciriase a lista ao tamaño do token que
			// queda
			System.out.println("tamaño de c antes: "+c.size());
			c = c.subList(0, t.getNumero());
			System.out.println("tamaño de c despois :"+c.size());
			// como o toquen quedaria a cero xa se borra e listo
			String token = t.getToken();
			baja(token);
		}
		return c;
	}

	/**
	 * FUNCION AUXILIAR
	 * 
	 * Este metodo se encarga de buscar, para cada contenido, si sus titulos
	 * contienen una subcadena que se le pasa como parametro.
	 * @param nome La subcadena que se quiere mirar si esta en los titulos de los contenidos.
	 * @return La lista de contenidos que se corresponde con el resultado de la busqueda.
	 */
	private List<Contenido> buscarNome(String nome) {
		List<Contenido> cont = new ArrayList<Contenido>();
		for (Contenido c : contenidos) {
			cont.addAll(c.buscar(nome));
		}
		return cont;
	}

	/**
	 * FUNCION AUXILIAR
	 * 
	 * Este metodo se encarga de generar el token aleatorio que se le devolvera al usuario
	 * en el momento en el que se de de alta.
	 * @return El token que se le devolvera al usuario.
	 */
	private String generarToken() {
		String output = new String();
		while (true) {
			output = UUID.randomUUID().toString().substring(0, 10);
			if (!findToken(tokensAdmitidos, output))
				return output;
		}

	}

	/**
	 * FUNCION AUXILIAR
	 * 
	 * Este metodo se encarga de comprobar si existe un token en la lista de los admitidos
	 * por el servidor.
	 * @param tokensAdmitidos La lista de los tokens que han sido admitidos por el servidor.
	 * @param token El token que queremos comprobar si existe.
	 * @return True en caso de que el token exista como uno disponible, y False en caso de que no.
	 */
	private boolean findToken(List<Token> tokensAdmitidos, String token) {
		for (int i = 0; i < (tokensAdmitidos.size()); i++)
			if (tokensAdmitidos.get(i).getToken().equalsIgnoreCase(token))
				return true;
		return false;
	}

	/**
	 * FUNCION AUXILIAR
	 * 
	 * Este metodo se encarga de buscar si un token existe o no en la lista de los admitidos
	 * por el servidor.
	 * @param token El token que se quiere buscar si esta en la lista de los admitidos por el servidor.
	 * @return El Token.
	 */
	private Token buscaToken(String token) {
		for (int i = 0; i < (tokensAdmitidos.size()); i++)
			if (tokensAdmitidos.get(i).getToken().equalsIgnoreCase(token))
				return tokensAdmitidos.get(i);
		return null;
	}

	/**
	 * FUNCION AUXILIAR
	 * 
	 * Este metodo se encarga de obtener un token de la lista de los admitidos (para saber cuantos
	 * contenidos le quedan disponibles, por ejemplo).
	 * @param token El token que queremos obtener.
	 * @return El Token.
	 */
	public Token getToken(String token) {
		return buscaToken(token);
	}

	/**
	 * FUNCION AUXILIAR
	 * 
	 * Este metodo se encarga de añadir un token a la lista de admitidos.
	 * @param token El token que se quiere admitir.
	 */
	public void setToken(Token token) {
		if (buscaToken(token.getToken()) == null)
			tokensAdmitidos.add(token);
	}

}
