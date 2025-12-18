
import java.io.Serializable;

public class Fecha implements Serializable {
	
	private int dia;
	private int mes;
	private int anio;
	
	public Fecha(int dia, int mes, int anio) {
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
	}
	
	public Fecha(String cadena) {
		String[] datos = cadena.split("/");
		if (datos.length != 3) {
			throw new IllegalArgumentException("La fecha debe tener 3 datos: día, mes y año.");
		}
		this.dia = Integer.parseInt(datos[0]);
		this.mes = Integer.parseInt(datos[1]);
		this.anio = Integer.parseInt(datos[2]);
	}
	
    private static boolean esBisiesto(int anio) {
    	boolean bisiesto = false;
    	if (anio >= 1583) {
    		if ((anio % 4 == 0) && (anio % 100 != 0 || anio % 400 == 0)) {
    			bisiesto = true;
    		}
    	}
    	return bisiesto;
    }
    
    private static int obtenerNumeroDiasDeMes(int mes, int anio) {
    	int numeroDiasMes = 31;
    	if (mes == 2) {
    		if (esBisiesto(anio)) {
    			numeroDiasMes = 29;
    		} 
    		else {
    			numeroDiasMes = 28;
    		}
    	}
    	else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
    		numeroDiasMes = 30;
    	}
    	return numeroDiasMes;
    }
    
    public static boolean esValida(int dia, int mes, int anio) {
    	boolean valida = true;
    	if (anio < 1583) {
    		valida = false;
    	}
    	else if (mes < 1 || mes > 12) {
    		valida = false;
    	}
    	else if (dia < 1 || dia > obtenerNumeroDiasDeMes(mes, anio)) {
    		valida = false;
    	}
    	return valida;
    }
	
	@Override
	public String toString() {
		String fecha = String.format("%02d/%02d/%04d", dia, mes, anio);
		return fecha;
	}

	public int getDia() {
		return dia;
	}

	public int getMes() {
		return mes;
	}

	public int getAnio() {
		return anio;
	}
	
}
