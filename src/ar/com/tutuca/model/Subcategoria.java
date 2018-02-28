package ar.com.tutuca.model;

public class Subcategoria {

	private int idSubcategoria;
	private Categoria categoria;
	private String subcategoria;

	@Override
	public String toString() {
		return String.format("(%s) Subcat: %s, idCat: %s", getIdSubcategoria(), getSubcategoria(),
				getCategoria().getIdCategoria());
	}

	// Constructores
	public Subcategoria() {
	}

	public Subcategoria(Categoria categoria, String subcategoria) {
		setCategoria(categoria);
		setSubcategoria(subcategoria);
	}

	public Subcategoria(int idSubcategoria, Categoria categoria, String subcategoria) {
		setIdSubcategoria(idSubcategoria);
		setCategoria(categoria);
		setSubcategoria(subcategoria);
	}
	
	// Getters
	public Categoria getCategoria() {
		return categoria;
	}

	public int getIdSubcategoria() {
		return idSubcategoria;
	}

	public String getSubcategoria() {
		return subcategoria;
	}
	
	// Setters
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void setIdSubcategoria(int idSubcategoria) {
		this.idSubcategoria = idSubcategoria;
	}

	public void setSubcategoria(String subcategoria) {
		this.subcategoria = subcategoria;
	}

}
