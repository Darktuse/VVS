package es.udc.fic.vvs.Practica1;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ServidorImpl implements Servidor {

	// Atributos del servidor
	
	private String nombre;
	private List<Contenido> contenidos = new ArrayList<Contenido>();
	private List<Token> tokensAdmitidos = new ArrayList<Token>();
	private static final String tokenSpecial = "tokenspecial";
	
	// Constructores
	
	public ServidorImpl(){
		
	}
	
	
	public ServidorImpl(String nombre) {
		super();
		this.nombre=nombre;
	}
	
	
	// Métodos de la interfaz
	
	public String obtenerNombre() {
		return nombre;
	}

	public String alta() {
		// Ten que devolver o token. Serve para dar
		// de alta un usuario no servidor.
		String newToken = null;
		boolean exist = true;
		
		while (exist) {
			newToken = generarToken();
			if (!tokensAdmitidos.isEmpty()){
				exist = findToken(tokensAdmitidos, newToken);
			} else {
				exist = false;
			}
		}
		
		Token token = new Token(newToken, 10);
		tokensAdmitidos.add(token);
		
		return token.getToken();
	}

	public void baja(String token) {
		// Dase de baixa o token, polo que non se recoñecerá
		// como válido nunca máis.
		// IMPLICITAMENTE cando buscas e superas os 10 contidos.
		
		boolean exist = findToken(tokensAdmitidos, token);
		
		if (!exist){
			// LANZAR EXCEPCION?
		} else {
			
			for (int i = 0; i < tokensAdmitidos.size(); i++){
				if (tokensAdmitidos.get(i).getToken() == token) {
					tokensAdmitidos.remove(i);
					break;
				}
			
			}
		}
		
	}

	public void agregar(Contenido contenido, String token) {
		// TODO FALTA EXCEPCION DE TOKEN NO VALIDO
		
		if (findToken(tokensAdmitidos, token)){
			this.contenidos.add(contenido);
		} else {
			// TOKEN NO VALIDO!
		}
		
	}

	
	public void eliminar(Contenido contenido, String token) {
		// TODO Auto-generated method stub
		
		if (findToken(tokensAdmitidos, token)){
			
			for (int i = 0; i < contenidos.size(); i++){
				if (contenidos.get(i) == contenido) {
					contenidos.remove(i);
					break;
				}
			
			}
			
		} else {
			// TOKEN NO VALIDO!
		}
		
	}

	
	public List<Contenido> buscar(String subcadena, String token) {
		List<Contenido> c = new ArrayList<Contenido>();
		if (token.isEmpty()){
			c = buscarNome(subcadena);
			if (c.isEmpty()) {
				// se non atopou nada, chamase ao outro servidor para mirar o seu contido
				ServidorImpl2 serv = new ServidorImpl2();
				c = serv.buscaInterna(subcadena);
			}
			c = insertaAnuncios(c);
			return c;
		}
		if (findToken(tokensAdmitidos, token)) {
			c = buscarNome(subcadena);
			Token t = buscaToken(token);
			if (c.isEmpty()) {
				// se non atopou nada, chamase ao outro servidor para mirar o seu contido
				ServidorImpl2 serv = new ServidorImpl2();
				c = serv.buscaInterna(subcadena);
				c = restarToken(t,c);
			} else 
				c = restarToken(t,c);
		}
		return c;
	}
	
	
	
	
	
	
	// FUNCIONES AUXILIARES
	
	public List<Contenido> buscaInterna(String subcadena) {
		return buscarNome(subcadena);
	}
	
	private List<Contenido> insertaAnuncios(List<Contenido> l){
		int i = 0;
		Anuncio a = new Anuncio();
		List<Contenido> cont = new ArrayList<Contenido>();
		for(Contenido c:l){
			if (i%3==0) {
				cont.add(a);
				cont.add(c);
			} else {
				cont.add(c);
			}
			i++;
		}
		return cont;
	}
	
	private List<Contenido> restarToken(Token t, List<Contenido> c ){
		/*Funcion na cal se resta o numero ao token
		 * 
		 * O token pode darse de baixa (operación baja()), co cal o servidor xa non o recoñecerá como válido nunca mais, 
		 * ou será "dado de baixa" implícitamente cando se utilice en operacións buscar até superar os 10 contidos devoltos. 
		 * A especificación non indica con mais detalle como se "contan" eses 10 contidos... cousas que pasan ;-)
		 * */
		// Se é menor restase o tamaño dos contidos
		if (c.size() < t.getNumero())
			t.restarVarios(c.size());
		else {
			// se e maior ou igual reduciriase a lista ao tamaño do token que queda
			c = c.subList(0, t.getNumero() - 1);
			// como o toquen quedaria a cero xa se borra e listo
			baja(t.getToken());
		}
		return c;
	}
	
	private List<Contenido> buscarNome(String nome){
		List<Contenido> cont = new ArrayList<Contenido>();
		for(Contenido c:contenidos){
			cont.addAll(c.buscar(nome));
		}
		return cont;
	}
	
	private String generarToken(){
		String output = new String();
		while(true){
		output = UUID.randomUUID().toString().substring(0, 10);
		if(!findToken(tokensAdmitidos,output)) return output;
		}
		
	}
			
	private boolean findToken(List<Token> tokensAdmitidos, String token){
		for (int i=0; i<(tokensAdmitidos.size()); i++)
			if (tokensAdmitidos.get(i).getToken().equalsIgnoreCase(token))
				return true;
		return false;
	}
	
	private Token buscaToken(String token){
		for (int i=0; i<(tokensAdmitidos.size()); i++)
			if (tokensAdmitidos.get(i).getToken().equalsIgnoreCase(token))
				return tokensAdmitidos.get(i);
		return null;
	}



}
