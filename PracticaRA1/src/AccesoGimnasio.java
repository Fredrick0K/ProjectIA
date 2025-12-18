
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class AccesoGimnasio {

	private static final String FICHERO_SOCIOS = "data/socios.dat";
	private static final String FICHERO_SOCIOS_TEMP = "data/sociosTemp.dat";

	public static void insertar(Socio socio) throws IOException {
		ObjectOutputStream flujoSalida1 = null;
		MyObjectOutputStream flujoSalida2 = null;
		try {
			File fichero = new File(FICHERO_SOCIOS);
			if (fichero.exists()) {
				flujoSalida2 = new MyObjectOutputStream(new FileOutputStream(fichero, true));
				flujoSalida2.writeObject(socio);
			} else {
				flujoSalida1 = new ObjectOutputStream(new FileOutputStream(fichero));
				flujoSalida1.writeObject(socio);
			}
		} finally {
			if (flujoSalida1 != null) {
				flujoSalida1.close();
			}
			if (flujoSalida2 != null) {
				flujoSalida2.close();
			}
		}
	}

	public static Socio consultarPorCodigo(int codigo) throws ClassNotFoundException, IOException {
		ObjectInputStream flujoEntrada = null;
		Socio socioEncontrado = null;
		try {
			File fichero = new File(FICHERO_SOCIOS);
			if (!fichero.exists()) {
				return null;
			}
			flujoEntrada = new ObjectInputStream(new FileInputStream(FICHERO_SOCIOS));
			boolean finFichero = false;
			boolean encontrado = false;
			while (!finFichero && !encontrado) {
				try {
					Socio socio = (Socio) flujoEntrada.readObject();
					if (socio.getCodigo() == codigo) {
						socioEncontrado = socio;
						encontrado = true;
					}
				} catch (EOFException eofe) {
					finFichero = true;
				}
			}
		} finally {
			if (flujoEntrada != null) {
				flujoEntrada.close();
			}
		}
		return socioEncontrado;
	}

	public static List<Socio> consultarTodos() throws ClassNotFoundException, IOException {
		ObjectInputStream flujoEntrada = null;
		List<Socio> listaSocios = new ArrayList<Socio>();
		try {
			File fichero = new File(FICHERO_SOCIOS);
			if (!fichero.exists()) {
				return listaSocios;
			}
			flujoEntrada = new ObjectInputStream(new FileInputStream(FICHERO_SOCIOS));
			boolean finFichero = false;
			while (!finFichero) {
				try {
					Socio socio = (Socio) flujoEntrada.readObject();
					listaSocios.add(socio);
				} catch (EOFException eofe) {
					finFichero = true;
				}
			}
		} finally {
			if (flujoEntrada != null) {
				flujoEntrada.close();
			}
		}
		return listaSocios;
	}

	// TODO: indicar nombre/s de alumno/s
	// Alumno 1:
	// Alumno 2:
	public static int actualizarPorAnioAlta(int anioAlta, double aumento) throws ClassNotFoundException, IOException {
		// TODO: implementar operación

		ObjectInputStream flujoLectura = null;
		ObjectOutputStream flujoEscritura = null;
		Socio socio = null;
		int actualizado = 0;

		try {
			File fichero = new File(FICHERO_SOCIOS);
			flujoLectura = new ObjectInputStream(new FileInputStream(fichero));
			File ficheroTemp = new File(FICHERO_SOCIOS_TEMP);
			flujoEscritura = new ObjectOutputStream(new FileOutputStream(ficheroTemp));
			boolean finalFichero = false;
			while(!finalFichero) {
				try {
					socio = (Socio) flujoLectura.readObject();
					int fechaAltaSocio = socio.getFechaAlta().getAnio();
					if (fechaAltaSocio == anioAlta) {
						double cuotaAumentada = (socio.getCuota() * aumento / 100) + socio.getCuota();
						socio.setCuota(cuotaAumentada);
						
					}
					flujoEscritura.writeObject(socio);
				}catch (EOFException eof) {
					// TODO: handle exception
					finalFichero = true;
				}
			}

		} finally {
			if (flujoEscritura != null) {
				flujoEscritura.close();
			}
			if (flujoLectura != null) {
				flujoLectura.close();
			}
		}

		return 0;
	}

}
