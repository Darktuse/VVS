package es.udc.fic.vvs.Practica1;

import java.util.List;
import es.udc.fic.vvs.Practica1.Contenido;

public interface Servidor  {
		
		public String obtenerNombre();
		
		public String alta();
		
		public void baja (String token);
		
		public void agregar (Contenido contenido, String token);
		
		public void eliminar (Contenido contenido, String token);
		
		public List<Contenido> buscar (String subcadena, String token);
		
		
}
