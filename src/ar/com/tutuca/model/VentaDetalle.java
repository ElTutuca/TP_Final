package ar.com.tutuca.model;

import java.math.BigDecimal;

import ar.com.tutuca.extras.GenericModel;

public class VentaDetalle implements GenericModel {

	private int idVenta;
	private Producto producto;
	private int cantidad;
	private Double precioUnitario;
	private int descuento;
	private Double neto;
	private BigDecimal porcentajeIva;

	public VentaDetalle() {
	}

	public VentaDetalle(int cantidad, double precioUnitario, int descuento, double neto, BigDecimal porcentajeIva) {
		setCantidad(cantidad);
		setPrecioUnitario(precioUnitario);
		setDescuento(descuento);
		setNeto(neto);
		setPorcentajeIva(porcentajeIva);
	}

	public VentaDetalle(Producto producto, int cantidad, double precioUnitario, int descuento, double neto,
			BigDecimal porcentajeIva) {
		setProducto(producto);
		setCantidad(cantidad);
		setPrecioUnitario(precioUnitario);
		setDescuento(descuento);
		setNeto(neto);
		setPorcentajeIva(porcentajeIva);
	}

	public VentaDetalle(int idVenta, int cantidad, double precioUnitario, int descuento, double neto,
			BigDecimal porcentajeIva) {
		setIdVenta(idVenta);
		setCantidad(cantidad);
		setPrecioUnitario(precioUnitario);
		setDescuento(descuento);
		setNeto(neto);
		setPorcentajeIva(porcentajeIva);
	}

	public VentaDetalle(int idVenta, Producto producto, int cantidad, double precioUnitario, int descuento, double neto,
			BigDecimal porcentajeIva) {
		setIdVenta(idVenta);
		setProducto(producto);
		setCantidad(cantidad);
		setPrecioUnitario(precioUnitario);
		setDescuento(descuento);
		setNeto(neto);
		setPorcentajeIva(porcentajeIva);
	}

	@Override
	public String[] getFieldNames() {
		String[] r = { "Producto", "Cantidad", "Precio Unitario", "Decuento", "Neto", "Porcentaje Iva" };
		return r;
	}

	@Override
	public Object[] getFieldsValues() {
		Object[] r = { getProducto(), getCantidad(), getPrecioUnitario(), getDescuento(), getNeto(),
				getPorcentajeIva() };
		return r;
	}

	// Getter
	public int getCantidad() {
		return cantidad;
	}

	public int getDescuento() {
		return descuento;
	}

	public Double getNeto() {
		return neto;
	}

	public BigDecimal getPorcentajeIva() {
		return porcentajeIva;
	}

	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public Producto getProducto() {
		return producto;
	}

	public int getIdVenta() {
		return idVenta;
	}

	// Setters
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

	public void setNeto(Double neto) {
		this.neto = neto;
	}

	public void setPorcentajeIva(BigDecimal porcentajeIva) {
		this.porcentajeIva = porcentajeIva;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

}
