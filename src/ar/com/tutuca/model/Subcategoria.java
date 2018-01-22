package ar.com.tutuca.model;

public class Subcategoria {
	private int idSubcategoria;
	private int idCategoria;
	private String subcategoria;
	
	public int getIdSubcategoria() {
		return idSubcategoria;
	}
	public void setIdSubcategoria(int idSubcategoria) {
		this.idSubcategoria = idSubcategoria;
	}
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
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

	public Subcategoria(int idSubcategoria, int idCategoria, String subcategoria) {
		setIdCategoria(idCategoria);
		setIdSubcategoria(idSubcategoria);
		setSubcategoria(subcategoria);
	}
}
