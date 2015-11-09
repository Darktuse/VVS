package es.udc.fic.vvs.Practica1;

import java.util.List;

public interface Contenido {
	
	public String obtenerTitulo();
	
	public int obtenerDuracion();
	
	public List<Contenido> obtenerListaReproduccion();
	
	public List<Contenido> buscar (String subcadena);
	
	public void agregar (Contenido contenido, Contenido predecesor) throws ContenidoInexistenteException;

	public void eliminar (Contenido contenido) throws ContenidoInexistenteException;
	
	
}
