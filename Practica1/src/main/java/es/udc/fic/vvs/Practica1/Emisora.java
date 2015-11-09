package es.udc.fic.vvs.Practica1;

import java.util.ArrayList;
import java.util.List;

public class Emisora implements Contenido {
	
	// Atributos de Emisora.
	
	private String titulo;
	private int duracion;
	private List<Contenido> contenidos = new ArrayList<Contenido>();
	
	// Constructores.
	
	public Emisora(){
		
	}
	
	public Emisora(String titulo){
		super();
		this.titulo = titulo;
		this.duracion = 0;
	}

	// Métodos de la interfaz.
	
	public String obtenerTitulo() {
		return this.titulo;
	}

	public int obtenerDuracion() {
		
		return this.duracion;
	}

	public List<Contenido> obtenerListaReproduccion() {
		
		return contenidos;
	}

	public List<Contenido> buscar(String subcadena) {
		
		List<Contenido> listaContenidos = new ArrayList<Contenido>();
		
		for (int i = 0; i < contenidos.size(); i++){
			if ((contenidos.get(i).obtenerTitulo().toLowerCase()).contains(subcadena.toLowerCase())) {
				listaContenidos.add(contenidos.get(i));
			}
		}
			
		
		return listaContenidos;
	}

	public void agregar(Contenido contenido, Contenido predecesor) {
		// primero busca el contenido predecesor, mira la posicion en la que está, y hace un add
		// del nuevo contenido en la siguiente posicion
		if (contenidos.isEmpty() && predecesor==null){
			contenidos.add(contenido);
			duracion += contenido.obtenerDuracion();
		} else {
			if (predecesor == null) return;
			int posicion = buscarContenido(predecesor);
			contenidos.add(posicion+1, contenido);
			duracion += contenido.obtenerDuracion();
		}
		
		
	
	}

	
	public void eliminar(Contenido contenido) {
		// TODO Auto-generated method stub
		int posicion = buscarContenido(contenido);
		duracion -= contenido.obtenerDuracion();
		contenidos.remove(posicion);
		
	}
	
	
	// FUNCIONES AUXILIARES
	
	private int buscarContenido(Contenido contenido){
		Integer posicion = null;
		
		for (int i = 0; i < contenidos.size(); i++){
			if (contenidos.get(i).equals(contenido)){
				posicion = i;
			}
		}
		
		if (posicion == null){
//			throw EXCEPTION;
			System.out.println ("EH, QUE NO EXISTE ESE CONTENIDO");
		}
		
		return posicion;
	}

}
