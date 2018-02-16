package ar.com.tutuca.model;

public class Categoria {

	private int idCategoria;
	private String categoria;

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	// Constructores
	public Categoria() {
	}

	public Categoria(int id, String categoria) {
		setIdCategoria(id);
		setCategoria(categoria);
	}

	@Override
	public String toString() {
		return String.format("(%s) %s", getIdCategoria(), getCategoria());
	}
}
