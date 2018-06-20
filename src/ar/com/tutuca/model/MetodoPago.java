package ar.com.tutuca.model;

import ar.com.tutuca.extras.GenericModel;

public class MetodoPago implements GenericModel {

	private int idMetodo;
	private String descripcion;

	@Override
	public String toString() {
		return String.format("(%s) Metodo: %s", getIdMetodo(), getDescripcion());
	}

	// Constructores
	public MetodoPago() {
	}

	public MetodoPago(String descripcion) {
		setDescripcion(descripcion);
	}

	public MetodoPago(int idMetodo, String descripcion) {
		setIdMetodo(idMetodo);
		setDescripcion(descripcion);
	}

	// Getters
	public String getDescripcion() {
		return descripcion;
	}

	public int getIdMetodo() {
		return idMetodo;
	}

	// Setters
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setIdMetodo(int idMetodo) {
		this.idMetodo = idMetodo;
	}

	@Override
	public Object[] getFieldsValues() {
		Object[] r = { getDescripcion() };
		return r;
	}

	@Override
	public String[] getFieldNames() {
		String[] r = { "Descripcion" };
		return r;
	}

}
