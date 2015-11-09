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

		List<Contenido> contenidos = new ArrayList<Contenido>();
		
		if ((titulo.toLowerCase()).indexOf((subcadena.toLowerCase())) > (-1)){
			contenidos.add(this);
		} else {
			System.out.println("No existe ningún contenido correspondiente a esa búsqueda.");
		}
		
		return contenidos;
	}

	
	
	
	// NO TIENEN EFECTO EN ESTA CLASE.
	
	
	public void agregar(Contenido contenido, Contenido predecesor) {
		// NO TIENE EFECTO
	}

	public void eliminar(Contenido contenido) {
		// NO TIENE EFECTO
		
	}

}
