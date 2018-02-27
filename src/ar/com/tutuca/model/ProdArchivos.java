package ar.com.tutuca.model;

public class ProdArchivos {

	private int idProducto;
	private Archivo arch;
	private int orden;

	public ProdArchivos() {
	}

	public ProdArchivos(int idProducto, Archivo arch, int orden) {
		setArch(arch);
		setOrden(orden);
		setIdProducto(idProducto);
	}

	public int getIdProducto() {
		return idProducto;
	}

	public Archivo getArch() {
		return arch;
	}

	public int getOrden() {
		return orden;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public void setArch(Archivo arch) {
		this.arch = arch;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	@Override
	public String toString() {
		return String.format("idProducto: (%s), idArchivo: (%s), Orden: %s", getIdProducto(), getArch().getIdArchivo(),
				getOrden());
	}

}
