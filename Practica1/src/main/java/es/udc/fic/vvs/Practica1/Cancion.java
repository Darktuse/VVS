package es.udc.fic.vvs.Practica1;

import java.util.ArrayList;
import java.util.List;

public class Cancion implements Contenido {
	
	// Atributos de Cancion.

	private String titulo;
	private int duracion;
	
	// Constructores.
	
	public Cancion(){
		
	}
	
	public Cancion(String titulo, int duracion){
		
	}
	
	
	// Métodos de la interfaz.

	public String obtenerTitulo() {
		return this.titulo;
	}

	public int obtenerDuracion() {
		return this.duracion;
	}

	public List<Contenido> obtenerListaReproduccion() {
		List<Contenido> listaReproduccion = new ArrayList<Contenido>();
		listaReproduccion.add(this);
		
		return listaReproduccion;
	}

	
	public List<Contenido> buscar(String subcadena) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	// NO TIENEN EFECTO EN ESTA CLASE.
	
	
	public void agregar(Contenido contenido, Contenido predecesor) {
		// NO TIENE EFECTO
	}

	public void eliminar(Contenido contenido) {
		// NO TIENE EFECTO
		
	}

}
