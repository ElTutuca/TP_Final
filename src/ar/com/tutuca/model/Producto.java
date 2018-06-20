package ar.com.tutuca.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ar.com.tutuca.extras.GenericModel;

public class Producto implements GenericModel {

	// Atributos
	private int idProductos;
	private String codigo;
	private double precio;
	private String nombre;
	private String ubicacion;
	private int stockMaximo;
	private int stockMinimo;
	private int stockIdeal;
	private int stock;
	private BigDecimal descuento;
	private boolean eliminado;
	private BigDecimal porcentajeIva;
	private Marca marca;
	private List<Mayorista> mayoristas = new ArrayList<Mayorista>();
	private List<Subcategoria> subcategoria = new ArrayList<Subcategoria>();
	private List<ProdArchivos> prodArch = new ArrayList<ProdArchivos>();

	@Override
	public String toString() {
		return String.format(
				"(%s) Cod: %s, Precio: %s, Name: %s, Ubic: %s, StckMax: %s, StckMin: %s, StckIdeal: %s, Stck: %s, Descto: %s, Elim: %s, PorcIVA: %s, Marca: %s",
				getIdProductos(), getCodigo(), getPrecio(), getNombre(), getUbicacion(), getStockMaximo(),
				getStockMinimo(), getStockIdeal(), getStock(), getDescuento(), isEliminado(), getPorcentajeIva(),
				getMarca().getIdMarca());
	}

	public void mostrarMayoristas() {
		for (Mayorista mayorista : getMayoristas()) {
			System.out.printf("May: (%s) ----> %s\n", idProductos, mayorista.getIdMayorista());
		}
	}

	public void mostrarSubcategorias() {
		for (Subcategoria sc : getSubcategoria()) {
			System.out.printf("Sub: (%s) ----> %s\n", idProductos, sc.getIdSubcategoria());
		}
	}

	public void mostrarArchivos() {
		for (ProdArchivos ar : getProdArch()) {
			System.out.println(ar);
		}
	}

	// Constructores
	public Producto() {
	}

	public Producto(String codigo, double precio, String nombre, String ubicacion, int stockMaximo, int stockMinimo,
			int stockIdeal, int stock, BigDecimal descuento, boolean eliminado, BigDecimal porcentajeIva, Marca marca) {
		setCodigo(codigo);
		setPrecio(precio);
		setNombre(nombre);
		setUbicacion(ubicacion);
		setStockMaximo(stockMaximo);
		setStockMinimo(stockMinimo);
		setStockIdeal(stockIdeal);
		setStock(stock);
		setDescuento(descuento);
		setEliminado(eliminado);
		setPorcentajeIva(porcentajeIva);
		setMarca(marca);
	}

	public Producto(int idProductos, String codigo, double precio, String nombre, String ubicacion, int stockMaximo,
			int stockMinimo, int stockIdeal, int stock, BigDecimal descuento, boolean eliminado,
			BigDecimal porcentajeIva, Marca marca) {
		setIdProductos(idProductos);
		setCodigo(codigo);
		setPrecio(precio);
		setNombre(nombre);
		setUbicacion(ubicacion);
		setStockMaximo(stockMaximo);
		setStockMinimo(stockMinimo);
		setStockIdeal(stockIdeal);
		setStock(stock);
		setDescuento(descuento);
		setEliminado(eliminado);
		setPorcentajeIva(porcentajeIva);
		setMarca(marca);
	}

	// Getters
	public List<ProdArchivos> getProdArch() {
		return prodArch;
	}

	public String getCodigo() {
		return codigo;
	}

	public BigDecimal getDescuento() {
		return descuento;
	}

	public int getIdProductos() {
		return idProductos;
	}

	public Marca getMarca() {
		return marca;
	}

	public List<Mayorista> getMayoristas() {
		return mayoristas;
	}

	public String getNombre() {
		return nombre;
	}

	public BigDecimal getPorcentajeIva() {
		return porcentajeIva;
	}

	public double getPrecio() {
		return precio;
	}

	public int getStock() {
		return stock;
	}

	public int getStockIdeal() {
		return stockIdeal;
	}

	public int getStockMaximo() {
		return stockMaximo;
	}

	public int getStockMinimo() {
		return stockMinimo;
	}

	public List<Subcategoria> getSubcategoria() {
		return subcategoria;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public boolean isEliminado() {
		return eliminado;
	}

	// Setters
	public void setProdArch(List<ProdArchivos> prodArch) {
		this.prodArch = prodArch;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}

	public void setIdProductos(int idProductos) {
		this.idProductos = idProductos;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public void setMayoristas(List<Mayorista> mayoristas) {
		this.mayoristas = mayoristas;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPorcentajeIva(BigDecimal porcentajeIva) {
		this.porcentajeIva = porcentajeIva;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public void setStockIdeal(int stockIdeal) {
		this.stockIdeal = stockIdeal;
	}

	public void setStockMaximo(int stockMaximo) {
		this.stockMaximo = stockMaximo;
	}

	public void setStockMinimo(int stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	public void setSubcategoria(List<Subcategoria> subcategoria) {
		this.subcategoria = subcategoria;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	@Override
	public Object[] getFieldsValues() {
		String elim = isEliminado() ? "Eliminado" : null;
		Object[] r = { getCodigo(), getPrecio(), getNombre(), getUbicacion(), getStockMaximo(), getStockMinimo(),
				getStockIdeal(), getStock(), getDescuento(), elim, getPorcentajeIva(), getMarca().getNombre() };
		return r;
	}

	@Override
	public String[] getFieldNames() {
		String[] r = { "Codigo", "Precio", "Nombre", "Ubicacion", "Stock Max", "Stock Min", "Stock Ideal", "Stock",
				"Descuento", "Eliminado", "Porcentaje Iva", "Marca" };
		return r;
	}

}
