package ar.com.tutuca.model;

import java.sql.Timestamp;
import java.util.List;

public class Compra {

	private int idCompra;
	private Mayorista mayorista;
	private TipoDeComprobante tipoDeComprob;
	private Timestamp fecha;
	private String nmroDeComprobante;
	private double neto1050;
	private double neto2100;
	private double neto2700;
	private double iva1050;
	private double iva2100;
	private double iva2700;
	private double total;
	private List<MetodoPago> metodosPago;
	private List<CompraDetalle> comprasDetalle;

	// Constructores
	public Compra() {
	}

	public Compra(int idCompra, Mayorista mayorista, TipoDeComprobante tipoDeComprob, Timestamp fecha,
			String nmroDeComprobante, double neto1050, double neto2100, double neto2700, double iva1050, double iva2100,
			double iva2700, double total) {
		setIdCompra(idCompra);
		setMayorista(mayorista);
		setTipoDeComprob(tipoDeComprob);
		setFecha(fecha);
		setNmroDeComprobante(nmroDeComprobante);
		setNeto1050(neto1050);
		setNeto2100(neto2100);
		setNeto2700(neto2700);
		setIva1050(iva1050);
		setIva2100(iva2100);
		setIva2700(iva2700);
		setTotal(total);
	}

	// Getters
	public List<CompraDetalle> getComprasDetalle() {
		return comprasDetalle;
	}
	
	public List<MetodoPago> getMetodosPago() {
		return metodosPago;
	}
	
	public Timestamp getFecha() {
		return fecha;
	}

	public int getIdCompra() {
		return idCompra;
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

	public Mayorista getMayorista() {
		return mayorista;
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

	public TipoDeComprobante getTipoDeComprob() {
		return tipoDeComprob;
	}

	public double getTotal() {
		return total;
	}

	// Setters
	public void setComprasDetalle(List<CompraDetalle> comprasDetalle) {
		this.comprasDetalle = comprasDetalle;
	}
	
	public void setMetodosPago(List<MetodoPago> metodosPago) {
		this.metodosPago = metodosPago;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
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

	public void setMayorista(Mayorista mayorista) {
		this.mayorista = mayorista;
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

	public void setTipoDeComprob(TipoDeComprobante tipoDeComprob) {
		this.tipoDeComprob = tipoDeComprob;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return String.format(
				"(%s) Mayor: %s, TipoDeComp: %s, Fecha: %s, NmroDeComp: %s, Neto1050: %s, Neto2100: %s, Neto2700: %s, Iva1050: %s, Iva2100: %s, Iva2700: %s, Total: %s",
				getIdCompra(), getMayorista().getIdMayorista(), getTipoDeComprob().getIdTipoDeComprov(), getFecha(),
				getNmroDeComprobante(), getNeto1050(), getNeto2100(), getNeto2700(), getIva1050(), getIva2100(),
				getIva2700(), getTotal());
	}

}
