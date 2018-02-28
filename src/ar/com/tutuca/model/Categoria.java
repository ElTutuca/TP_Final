package ar.com.tutuca.model;

public class Categoria {

	private int idCategoria;
	private String categoria;

	@Override
	public String toString() {
		return String.format("(%s) Cat: %s", getIdCategoria(), getCategoria());
	}

	// Constructores
	public Categoria() {
	}
	
	public Categoria(String categoria) {
		setCategoria(categoria);
	}
	
	public Categoria(int id, String categoria) {
		setIdCategoria(id);
		setCategoria(categoria);
	}

	// Getters
	public String getCategoria() {
		return categoria;
	}

	public int getIdCategoria() {
		return idCategoria;
	}
	
	// Setters
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
}
