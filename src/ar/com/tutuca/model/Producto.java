package ar.com.tutuca.model;

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
	private Mayorista mayorista;
	private Sucursal sucursal;
	private Marca marca;

	public Mayorista getMayorista() {
		return mayorista;
	}

	public void setMayorista(Mayorista mayorista) {
		this.mayorista = mayorista;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public int getIdProductos() {
		return idProductos;
	}

	public void setIdProductos(int idProductos) {
		this.idProductos = idProductos;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isDeposito() {
		return deposito;
	}

	public void setDeposito(boolean deposito) {
		this.deposito = deposito;
	}

	public int getStockMaximo() {
		return stockMaximo;
	}

	public void setStockMaximo(int stockMaximo) {
		this.stockMaximo = stockMaximo;
	}

	public int getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(int stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	public int getStockIdeal() {
		return stockIdeal;
	}

	public void setStockIdeal(int stockIdeal) {
		this.stockIdeal = stockIdeal;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

	public int getPuntosReq() {
		return puntosReq;
	}

	public void setPuntosReq(int puntosReq) {
		this.puntosReq = puntosReq;
	}


	// Constructores
	public Producto(int idProductos, double precio, String nombre, boolean deposito, int stockMaximo, int stockMinimo,
			int stockIdeal, int stock, int descuento, int puntosReq, Mayorista mayorista, Sucursal sucursal, Marca marca) {
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
		setMayorista(mayorista);
		setSucursal(sucursal);
		setMarca(marca);
	}

	public Producto() {
	}

	@Override
	public String toString() {
		return String.format("(%s) %s - $%s ; %s ; %s - %s - %s - [%s] ; [%s] ; %s p ; [%s] | [%s] | [%s]",
				getIdProductos(), getNombre(), getPrecio(), isDeposito(), getStockMaximo(), getStockMinimo(),
				getStockIdeal(), getStock(), getDescuento(), getPuntosReq(), getMayorista().getIdMayorista(), getSucursal().getIdSucursal(),
				getMarca().getIdMarca());
	}

}
