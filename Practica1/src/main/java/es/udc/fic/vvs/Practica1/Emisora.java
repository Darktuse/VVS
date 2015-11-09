package es.udc.fic.vvs.Practica1;

import java.util.List;

public class Emisora implements Contenido {
	
	// Atributos de Emisora.
	
	private String titulo;
	private int duracion;
	
	// Constructores.
	
	public Emisora(){
		
	}
	
	public Emisora(String titulo, int duracion){
		
	}

	// Métodos de la interfaz.
	
	public String obtenerTitulo() {
		return this.titulo;
	}

	public int obtenerDuracion() {
		return this.duracion;
	}

	public List<Contenido> obtenerListaReproduccion() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Contenido> buscar(String subcadena) {
		// TODO Auto-generated method stub
		return null;
	}

	public void agregar(Contenido contenido, Contenido predecesor) {
		// TODO Auto-generated method stub
		
	}

	public void eliminar(Contenido contenido) {
		// TODO Auto-generated method stub
		
	}

}
