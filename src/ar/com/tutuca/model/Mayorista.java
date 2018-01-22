package ar.com.tutuca.model;

public class Mayorista {

	private int idMayorista;
	private String nombre;

	public int getIdMayorista() {
		return idMayorista;
	}

	public void setIdMayorista(int idMayorista) {
		this.idMayorista = idMayorista;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// Constructores
	public Mayorista() {
	}

	public Mayorista(int id, String nombre) {
		setIdMayorista(id);
		setNombre(nombre);
	}

	@Override
	public String toString() {
		return String.format("ID: %s / May: %s", getIdMayorista(), getNombre());
	}
}
