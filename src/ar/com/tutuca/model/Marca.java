package ar.com.tutuca.model;

public class Marca {
	
	private int idMarca;
	private String nombre;

	public int getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// Constructores
	public Marca() {
	}

	public Marca(int id, String nombre) {
		setIdMarca(id);
		setNombre(nombre);
	}

	@Override
	public String toString() {
		return String.format("(%s) %s", getIdMarca(), getNombre());
	}

}
