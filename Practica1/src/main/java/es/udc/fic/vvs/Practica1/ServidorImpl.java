package es.udc.fic.vvs.Practica1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ServidorImpl implements Servidor {

	// Atributos del servidor
	
	private String nombre;
	private List<Contenido> contenidos = new ArrayList<Contenido>();
	private List<Token> tokensAdmitidos = new ArrayList<Token>();
	
	// Constructores
	
	public ServidorImpl(){
		
	}
	
	
	public ServidorImpl(String nombre) {
		
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
		// TODO Auto-generated method stub
		
	}

	public void eliminar(Contenido contenido, String token) {
		// TODO Auto-generated method stub
		
	}

	public List<Contenido> buscar(String subcadena, String token) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	// FUNCIONES AUXILIARES
	
	private String generarToken(){
			
		char[] chars = "abcdefghijklmnopqrstuvwxyz123456789".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		
		String output = sb.toString();
		return output;
		
	}
			
	private boolean findToken(List<Token> tokensAdmitidos, String token){
		boolean exist = false;
			
		for (int i=0; i<(tokensAdmitidos.size()); i++){
			exist = tokensAdmitidos.get(i).getToken().equalsIgnoreCase(token);
			if (exist == true){
				break;
			}
		}
		
		
		return exist;
	}



}
