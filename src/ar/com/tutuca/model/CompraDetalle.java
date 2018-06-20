package ar.com.tutuca.model;

import ar.com.tutuca.extras.GenericModel;

public class CompraDetalle implements GenericModel {

	private int idCompra;
	private Producto prod;
	private int cantidad;

	@Override
	public Object[] getFieldsValues() {
		Object[] r = { getProd(), getCantidad() };
		return r;
	}

	@Override
	public String[] getFieldNames() {
		String[] r = { "Producto", "Cantidad" };
		return r;
	}

	@Override
	public String toString() {
		return String.format("Compra: (%s) Producto: (%s), Cantidad: %s", getIdCompra(),
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

	public CompraDetalle(int idCompra, Producto producto, int cantidad) {
		setIdCompra(idCompra);
		setProd(producto);
		setCantidad(cantidad);
	}

	// Getters

	public int getCantidad() {
		return cantidad;
	}

	public int getIdCompra() {
		return idCompra;
	}

	public Producto getProd() {
		return prod;
	}

	// Setters
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}

	public void setProd(Producto prod) {
		this.prod = prod;
	}

}
