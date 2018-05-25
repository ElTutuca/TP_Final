package ar.com.tutuca.model;

import ar.com.tutuca.extras.GenericModel;

public class CompraDetalle implements GenericModel {

	private Compra compra;
	private Producto prod;
	private int cantidad;

	@Override
	public Object[] getFieldsValues() {
		Object[] r = { getCompra(), getProd(), getCantidad() };
		return r;
	}

	@Override
	public String[] getFieldNames() {
		String[] r = { "Compra", "Producto", "Cantidad" };
		return r;
	}

	@Override
	public String toString() {
		return String.format("Compra: (%s) Producto: (%s), Cantidad: %s", getCompra().getIdCompra(),
				getProd().getIdProductos(), getCantidad());
	}

	// Constructores;
	public CompraDetalle() {
	}

	public CompraDetalle(int cantidad) {
		setCantidad(cantidad);
	}

	public CompraDetalle(Producto producto, int cantidad) {
		setProd(producto);
		setCantidad(cantidad);
	}

	public CompraDetalle(Compra compra, Producto producto, int cantidad) {
		setCompra(compra);
		setProd(producto);
		setCantidad(cantidad);
	}

	// Getters

	public int getCantidad() {
		return cantidad;
	}

	public Compra getCompra() {
		return compra;
	}

	public Producto getProd() {
		return prod;
	}

	// Setters
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public void setProd(Producto prod) {
		this.prod = prod;
	}

}
