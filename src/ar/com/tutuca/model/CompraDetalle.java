package ar.com.tutuca.model;

public class CompraDetalle {

	private Compra compra;
	private Producto prod;
	private int cantidad;

	public CompraDetalle() {
	}

	public CompraDetalle(Compra comp, Producto prod, int cantidad) {
		setCompra(comp);
		setProd(prod);
		setCantidad(cantidad);
	}

	public int getIdCompra() {
		return idCompra;
	}

	public int getCantidad() {
		return cantidad;
	}

	public Producto getProd() {
		return prod;
	}

	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public void setProd(Producto prod) {
		this.prod = prod;
	}

}
