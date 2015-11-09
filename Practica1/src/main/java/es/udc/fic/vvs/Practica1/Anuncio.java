package es.udc.fic.vvs.Practica1;

import java.util.ArrayList;
import java.util.List;

public class Anuncio implements Contenido {
	

	// Constructores.
	
	public Anuncio(){
		
	}
	
		
	// Métodos de la interfaz.

	public String obtenerTitulo() {
		// Los anuncios siempre tienen como título "PUBLICIDAD".
		return "PUBLICIDAD";
	}

	
	public int obtenerDuracion() {
		// Los anuncios siempre duran 5 segundos.
		return 5;
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
