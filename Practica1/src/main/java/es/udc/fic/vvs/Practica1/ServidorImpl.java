package es.udc.fic.vvs.Practica1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ServidorImpl implements Servidor {

	// Atributos del servidor
	
	private String nombre;
	private List<Contenido> contenidos = new ArrayList<Contenido>();
	private List<String> tokensAdmitidos = new ArrayList<String>();
	
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
		Token token = new Token(10);
		boolean exist = true;
		
		while (exist) {
			token.setToken(token.generarToken());
			if (!tokensAdmitidos.isEmpty()){
				exist = token.findToken(tokensAdmitidos, token.getToken());
			} else {
				exist = false;
			}
		}
		
		tokensAdmitidos.add(token.getToken());
		return token.getToken();
	}

	public void baja(String token) {
		// Dase de baixa o token, polo que non se recoñecerá
		// como válido nunca máis.
		// IMPLICITAMENTE cando buscas e superas os 10 contidos.
		Token tok = new Token();
		
		boolean exist = tok.findToken(tokensAdmitidos, token);
		
		if (!exist){
			// LANZAR EXCEPCION?
		} else {
			
			for (int i = 0; i < tokensAdmitidos.size(); i++){
				if (tokensAdmitidos.get(i) == token) {
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
	



}
