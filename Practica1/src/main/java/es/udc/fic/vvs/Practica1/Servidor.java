package es.udc.fic.vvs.Practica1;

import java.util.List;
import es.udc.fic.vvs.Practica1.Contenido;

public interface Servidor  {
		
		public String obtenerNombre();
		
		public String alta();
		
		public void baja (String token) throws InvalidTokenException;
		
		public void agregar (Contenido contenido, String token) throws InvalidTokenException;
		
		public void eliminar (Contenido contenido, String token) throws InvalidTokenException;
		
		public List<Contenido> buscar (String subcadena, String token) throws InvalidTokenException;
		
		
}
