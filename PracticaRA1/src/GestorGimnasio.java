
import java.util.List;
import entrada.Teclado;

public class GestorGimnasio {

	public static void escribirMenuOpciones() {
		System.out.println();
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("(0) Salir del programa.");
		System.out.println("(1) Insertar un socio en el fichero.");
		System.out.println("(2) Consultar todos los socios del fichero.");
		System.out.println("(3) Actualizar varios socios del fichero por año de alta con un aumento.");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println();
	}
	
	public static void main(String[] args) {
		int opcion, codigo, anioAlta, numeroSocios;
		double cuota, aumento;
		String nombre, correo;
		Fecha fechaAlta;
		Socio socio;
		List<Socio> listaSocios;
		do {
			escribirMenuOpciones();
			opcion = Teclado.leerEntero("Opción: ");
			try {
				switch (opcion) {
				// Salir del programa.
				case 0:
					break;
					
				// Insertar un socio en el fichero.
				case 1:
					codigo = Teclado.leerEntero("Código: ");
					socio = AccesoGimnasio.consultarPorCodigo(codigo);
					if (socio != null) {
						System.out.println("Se ha encontrado un socio con el código en el fichero.");
					}
					else {
						nombre = Teclado.leerCadena("Nombre: ");
						correo = Teclado.leerCadena("Correo: ");
						fechaAlta = Consola.leerFecha("Fecha de Alta: ");
						cuota = Teclado.leerReal("Cuota: ");
						socio = new Socio(codigo, nombre, correo, fechaAlta, cuota);
						AccesoGimnasio.insertar(socio);
						System.out.println("Se ha insertado un socio en el fichero.");
					}
					break;
					
				// Consultar todos los socios del fichero.
				case 2:
					listaSocios = AccesoGimnasio.consultarTodos();
					if (listaSocios.isEmpty()) {
						System.out.println("No se ha encontrado ningún socio en el fichero.");
					}
					else {
						Consola.escribirLista(listaSocios);
					}
					break;
					
				// Actualizar varios socios del fichero por año de alta con un aumento.
				case 3:
					anioAlta = Teclado.leerEntero("Año de Alta: ");
					aumento = Teclado.leerReal("Aumento: ");
					numeroSocios = AccesoGimnasio.actualizarPorAnioAlta(anioAlta, aumento);
					if (numeroSocios > 0) {
						System.out.println("Se han actualizado " + numeroSocios + " socios del fichero.");
					}
					else {
						System.out.println("No se ha encontrado ningún socio con el año de alta en el fichero.");
					}
					break;
				}
			}
			catch (Exception excepcion) {
				excepcion.printStackTrace();
			}
		}
		while (opcion != 0);
		System.out.println("Programa finalizado.");
	}

}
