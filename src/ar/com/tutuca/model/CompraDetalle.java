package ar.com.tutuca.model;

public class CompraDetalle {

	private int idCompra;
	private Producto prod;
	private int cantidad;
	
	@Override
	public String toString() {
		return String.format("(%s) Producto: (%s), Cantidad: %s", getIdCompra(), getProd().getIdProductos(),
				getCantidad());
	}

	// Constructores;
	public CompraDetalle() {
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
	public int getIdCompra() {
		return idCompra;
	}

	public int getCantidad() {
		return cantidad;
	}

	public Producto getProd() {
		return prod;
	}
	
	// Setters
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
