
import java.io.Serializable;

public class Socio implements Serializable {
	
	private int codigo;      // identificador
	private String nombre;
	private String correo;
	private Fecha fechaAlta;
	private double cuota;    // en euros
	
	public Socio(int codigo, String nombre, String correo, 
	             Fecha fechaAlta, double cuota) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.correo = correo;
		this.fechaAlta = fechaAlta;
		this.cuota = cuota;
	}
	
	@Override
	public String toString() {
		return "Socio {Código = " + codigo + 
		       ", Nombre = " + nombre + 
		       ", Correo = " + correo + 
		       ", FechaAlta = " + fechaAlta.toString() + 
		       ", Cuota = " + String.format("%.2f", cuota) + "}";
	}

	public int getCodigo() {
		return codigo;
	}
	
	public Fecha getFechaAlta() {
		return fechaAlta;
	}
	
	public double getCuota() {
		return cuota;
	}
	
	public void setCuota(double cuota) {
		this.cuota = cuota;
	}
	
}
