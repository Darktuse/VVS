package es.udc.fic.vvs.Practica1;

import java.util.List;

public class Anuncio implements Contenido {

	public String obtenerTitulo() {
		// Los anuncios siempre tienen como título "PUBLICIDAD".
		return "PUBLICIDAD";
	}

	public int obtenerDuracion() {
		// Los anuncios siempre duran 5 segundos.
		return 5;
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
		// NO TIENE EFECTO
		
	}

	public void eliminar(Contenido contenido) {
		// NO TIENE EFECTO
		
	}

}
