
import java.util.List;
import entrada.Teclado;

public class Consola {
	
	private static String generarPlural(String singular) {
		if (singular.endsWith("a") || singular.endsWith("e") || singular.endsWith("i") || singular.endsWith("o") || singular.endsWith("u")) {
			return singular + "s";
		}
		else {
			return singular + "es";
		}
	}
	
	public static <E> void escribirLista(List<E> lista) {	
		String nombreClase = lista.getFirst().getClass().getSimpleName();
		String nombreSingular = nombreClase.toLowerCase();
		String nombrePlural = generarPlural(nombreSingular);
		for (E elemento : lista) {
			System.out.println(elemento.toString());
		}
		if (lista.size() == 1) {
			System.out.println("Se ha consultado 1 " + nombreSingular + " del fichero.");
		}
		else {
			System.out.println("Se han consultado " + lista.size() + " " + nombrePlural + " del fichero.");
		}
	}
	
	public static Fecha leerFecha(String mensaje) {
		boolean valida = false;
		String cadena = null;
		Fecha fecha = null;
		while (!valida) {
			try {
				cadena = Teclado.leerCadena(mensaje);
				fecha = new Fecha(cadena);
				valida = true;
			} 
			catch (IllegalArgumentException excepcion) {
				System.out.println("La fecha debe tener formato español DD/MM/AAAA.");
			}
		}        
		return fecha;
	}
	
}
