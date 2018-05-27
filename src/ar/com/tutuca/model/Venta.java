package ar.com.tutuca.model;

import java.sql.Timestamp;
import java.util.List;

import ar.com.tutuca.extras.GenericModel;

public class Venta implements GenericModel {

	private Cliente cliente;
	private TipoDeComprobante tipoDeComprob;
	private Timestamp fecha;
	private String nmroDeComprobante;
	private String puntoDeVenta;
	private double neto1050;
	private double neto2100;
	private double neto2700;
	private double iva1050;
	private double iva2100;
	private double iva2700;
	private double total;
	private int idVenta;
	private List<MetodoPago> metodosPago;
	private List<VentaDetalle> ventasDetalle;

	public Venta() {
	}

	public Venta(Cliente cliente, TipoDeComprobante tipoDeComprobante, Timestamp fecha, String nmroDeComprobante,
			String puntoDeVenta, double neto1050, double neto2100, double neto2700, double iva1050, double iva2100,
			double iva2700, double total) {

		setCliente(cliente);
		setTipoDeComprob(tipoDeComprobante);
		setFecha(fecha);
		setNmroDeComprobante(nmroDeComprobante);
		setPuntoDeVenta(puntoDeVenta);
		setNeto1050(neto1050);
		setNeto2100(neto2100);
		setNeto2700(neto2700);
		setIva1050(iva1050);
		setIva2100(iva2100);
		setIva2700(iva2700);
		setTotal(total);

	}

	public Venta(int idVenta, Cliente cliente, TipoDeComprobante tipoDeComprobante, Timestamp fecha,
			String nmroDeComprobante, String puntoDeVenta, double neto1050, double neto2100, double neto2700,
			double iva1050, double iva2100, double iva2700, double total) {

		setIdVenta(idVenta);
		setCliente(cliente);
		setTipoDeComprob(tipoDeComprobante);
		setFecha(fecha);
		setNmroDeComprobante(nmroDeComprobante);
		setPuntoDeVenta(puntoDeVenta);
		setNeto1050(neto1050);
		setNeto2100(neto2100);
		setNeto2700(neto2700);
		setIva1050(iva1050);
		setIva2100(iva2100);
		setIva2700(iva2700);
		setTotal(total);

	}

	@Override
	public Object[] getFieldsValues() {
		Object[] r = { getCliente().getNombre(), getTipoDeComprob().getAbreviatura(), getFecha(),
				getNmroDeComprobante(), getPuntoDeVenta(), getNeto1050(), getNeto2100(), getNeto2700(), getIva1050(),
				getIva2100(), getIva2700(), getTotal() };
		return r;
	}

	@Override
	public String[] getFieldNames() {
		String[] r = { "Cliente", "Tipo de Comprobante", "Fecha", "Nmro de Comprobante", "Punto de Venta", "Neto 10,50",
				"Neto 21,00", "Neto 27,00", "Iva 10,50", "Iva 21,00", "Iva 2700", "Total" };
		return r;
	}

	// Getter
	public Cliente getCliente() {
		return cliente;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public int getIdVenta() {
		return idVenta;
	}

	public double getIva1050() {
		return iva1050;
	}

	public double getIva2100() {
		return iva2100;
	}

	public double getIva2700() {
		return iva2700;
	}

	public List<MetodoPago> getMetodosPago() {
		return metodosPago;
	}

	public double getNeto1050() {
		return neto1050;
	}

	public double getNeto2100() {
		return neto2100;
	}

	public double getNeto2700() {
		return neto2700;
	}

	public String getNmroDeComprobante() {
		return nmroDeComprobante;
	}

	public String getPuntoDeVenta() {
		return puntoDeVenta;
	}

	public TipoDeComprobante getTipoDeComprob() {
		return tipoDeComprob;
	}

	public double getTotal() {
		return total;
	}

	public List<VentaDetalle> getVentasDetalle() {
		return ventasDetalle;
	}

	// Setters
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public void setIva1050(double iva1050) {
		this.iva1050 = iva1050;
	}

	public void setIva2100(double iva2100) {
		this.iva2100 = iva2100;
	}

	public void setIva2700(double iva2700) {
		this.iva2700 = iva2700;
	}

	public void setMetodosPago(List<MetodoPago> metodosPago) {
		this.metodosPago = metodosPago;
	}

	public void setNeto1050(double neto1050) {
		this.neto1050 = neto1050;
	}

	public void setNeto2100(double neto2100) {
		this.neto2100 = neto2100;
	}

	public void setNeto2700(double neto2700) {
		this.neto2700 = neto2700;
	}

	public void setNmroDeComprobante(String nmroDeComprobante) {
		this.nmroDeComprobante = nmroDeComprobante;
	}

	public void setPuntoDeVenta(String puntoDeVenta) {
		this.puntoDeVenta = puntoDeVenta;
	}

	public void setTipoDeComprob(TipoDeComprobante tipoDeComprob) {
		this.tipoDeComprob = tipoDeComprob;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public void setVentasDetalle(List<VentaDetalle> ventasDetalle) {
		this.ventasDetalle = ventasDetalle;
	}

}
