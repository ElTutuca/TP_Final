package ar.com.tutuca.model;

import ar.com.tutuca.extras.GenericModel;

public class ProdArchivos implements GenericModel {

	private int idProducto;
	private Archivo arch;
	private int orden;
	
	@Override
	public String toString() {
		return String.format("idProducto: (%s), idArchivo: (%s), Orden: %s", getIdProducto(), getArch().getIdArchivo(),
				getOrden());
	}
	
	// Constructores
	public ProdArchivos() {
	}
	
	public ProdArchivos(Archivo arch, int orden) {
		setArch(arch);
		setOrden(orden);
	}

	public ProdArchivos(int idProducto, Archivo arch, int orden) {
		setIdProducto(idProducto);
		setArch(arch);
		setOrden(orden);
	}

	// Getters
	public int getIdProducto() {
		return idProducto;
	}

	public Archivo getArch() {
		return arch;
	}

	public int getOrden() {
		return orden;
	}

	// Setters
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public void setArch(Archivo arch) {
		this.arch = arch;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

}
