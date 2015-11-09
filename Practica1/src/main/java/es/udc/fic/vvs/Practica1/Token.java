package es.udc.fic.vvs.Practica1;

import java.util.List;
import java.util.Random;

public class Token {

	String token;
	int numero;
	
	public Token(){
		
	}
	
	public Token (int numero){
		super();
		this.numero = numero;
	}
	
	
	public Token(String token, int numero) {
		super();
		this.token = token;
		this.numero = numero;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public boolean restarUno(){
		if (numero>0){
			numero--;
			return true;
		}
		return false;
	}
	
	
	// FUNCIONES AUXILIARES
	
	public String generarToken(){
			
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
		
	public boolean findToken(List<String> tokensAdmitidos, String token){
		boolean exist = false;
			
		for (int i=0; i<(tokensAdmitidos.size()); i++){
			exist = tokensAdmitidos.get(i).equalsIgnoreCase(token);
			if (exist == true){
				break;
			}
		}
		
		
		return exist;
	}
	
}
