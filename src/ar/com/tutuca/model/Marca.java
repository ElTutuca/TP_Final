package ar.com.tutuca.model;

public class Marca {

	private int idMarca;
	private String nombre;

	@Override
	public String toString() {
		return String.format("(%s) Marca: %s", getIdMarca(), getNombre());
	}

	// Constructores
	public Marca() {
	}

	public Marca(String nombre) {
		setNombre(nombre);
	}

	public Marca(int id, String nombre) {
		setIdMarca(id);
		setNombre(nombre);
	}

	// Getters
	public int getIdMarca() {
		return idMarca;
	}

	public String getNombre() {
		return nombre;
	}

	// Setters
	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
