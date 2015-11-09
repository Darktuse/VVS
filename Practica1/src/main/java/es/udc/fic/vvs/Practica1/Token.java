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
	
	
	
}
