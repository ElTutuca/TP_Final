package ar.com.tutuca.model;

public class Subcategoria {
	private int idSubcategoria;
	private Categoria categoria;
	private String subcategoria;
	
	public int getIdSubcategoria() {
		return idSubcategoria;
	}
	public void setIdSubcategoria(int idSubcategoria) {
		this.idSubcategoria = idSubcategoria;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public String getSubcategoria() {
		return subcategoria;
	}
	public void setSubcategoria(String subcategoria) {
		this.subcategoria = subcategoria;
	}
	
	//Constructores
	public Subcategoria() {
	}

	public Subcategoria(int idSubcategoria, Categoria categoria, String subcategoria) {
		setCategoria(categoria);
		setIdSubcategoria(idSubcategoria);
		setSubcategoria(subcategoria);
	}
	
	@Override
	public String toString() {
		return String.format("(%s) %s ; [%s]", getIdSubcategoria(),getSubcategoria(),getCategoria().getIdCategoria());
	}
}
