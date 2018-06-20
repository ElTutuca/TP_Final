package ar.com.tutuca.model;

import ar.com.tutuca.extras.GenericModel;

public class Marca implements GenericModel {

	private int idMarca;
	private String nombre;

	@Override
	public Object[] getFieldsValues() {
		Object[] r = { getNombre() };
		return r;
	}

	@Override
	public String[] getFieldNames() {
		String[] r = { "Nombre" };
		return r;
	}

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
