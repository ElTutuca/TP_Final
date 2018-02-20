package ar.com.tutuca.model;

import java.util.ArrayList;
import java.util.List;

public class Producto {

	// Atributos
	private int idProductos;
	private double precio;
	private String nombre;
	private boolean deposito;
	private int stockMaximo;
	private int stockMinimo;
	private int stockIdeal;
	private int stock;
	private int descuento;
	private int puntosReq;
	private boolean eliminado;
	private List<Mayorista> mayoristas = new ArrayList<Mayorista>();
	private List<Subcategoria> subcategoria = new ArrayList<Subcategoria>();
	private List<Archivo> archivos = new ArrayList<Archivo>();
	private Sucursal sucursal;
	private Marca marca;
	
	// Constructores
	public Producto(int idProductos, double precio, String nombre, boolean deposito, int stockMaximo, int stockMinimo,
			int stockIdeal, int stock, int descuento, int puntosReq, boolean eliminado, Sucursal sucursal, Marca marca) {
		setIdProductos(idProductos);
		setPrecio(precio);
		setNombre(nombre);
		setDeposito(deposito);
		setStockMaximo(stockMaximo);
		setStockMinimo(stockMinimo);
		setStockIdeal(stockIdeal);
		setStock(stock);
		setDescuento(descuento);
		setPuntosReq(puntosReq);
		setEliminado(eliminado);
		setSucursal(sucursal);
		setMarca(marca);
	}

	public Producto() {
	}

	// Getters
	public boolean isEliminado() {
		return eliminado;
	}
	
	public List<Subcategoria> getSubcategoria() {
		return subcategoria;
	}
	
	public List<Archivo> getArchivos() {
		return archivos;
	}
	
	public int getDescuento() {
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

	public double getPrecio() {
		return precio;
	}

	public int getPuntosReq() {
		return puntosReq;
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

	public Sucursal getSucursal() {
		return sucursal;
	}

	public boolean isDeposito() {
		return deposito;
	}

	// Setters
	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}
	
	public void setArchivos(List<Archivo> archivos) {
		this.archivos = archivos;
	}

	public void setSubcategoria(List<Subcategoria> subcategoria) {
		this.subcategoria = subcategoria;
	}
	
	public void setDeposito(boolean deposito) {
		this.deposito = deposito;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
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

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public void setPuntosReq(int puntosReq) {
		this.puntosReq = puntosReq;
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

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	@Override
	public String toString() {
		return String.format("(%s) %s - $%s ; %s ; %s - %s - %s - [%s] ; [%s] ; %s p ; [%s] | [%s] -----%s",
				getIdProductos(), getNombre(), getPrecio(), isDeposito(), getStockMaximo(), getStockMinimo(),
				getStockIdeal(), getStock(), getDescuento(), getPuntosReq(), getSucursal().getIdSucursal(),
				getMarca().getIdMarca(), (isEliminado()?"Eliminado":"OKEY"));
	}
	
	public void mostrarMayoristas() {
		for (Mayorista mayorista : getMayoristas()) {
			System.out.printf("May: (%s) ----> %s\n",idProductos,mayorista.getIdMayorista());
		}
	}
	
	public void mostrarSubcategorias() {
		for (Subcategoria sc : getSubcategoria()) {
			System.out.printf("Sub: (%s) ----> %s\n",idProductos,sc.getIdSubcategoria());
		}
	}
	
	public void mostrarArchivos() {
		for (Archivo ar : getArchivos()) {
			System.out.printf("Arch: (%s) ----> %s\n",idProductos,ar.getIdArchivo());
		}
	}

}
