package ar.com.tutuca.dao;

import ar.com.tutuca.model.Categoria;
import ar.com.tutuca.model.Marca;
import ar.com.tutuca.model.Mayorista;
import ar.com.tutuca.model.Producto;
import ar.com.tutuca.model.Subcategoria;

public class TestDAO {

	public static void main(String[] args) throws PersistenciaException {
		MayoristaDAO mayDAO = new MayoristaDAO();
		ProductoDAO proDAO = new ProductoDAO();
		MarcaDAO marDAO = new MarcaDAO();
		SubcategoriaDAO subDAO = new SubcategoriaDAO();
		CategoriaDAO catDAO = new CategoriaDAO();
		
		
		marco("mayorista");
		for (Mayorista m : mayDAO.list()) {
			System.out.println(m);
		}
		
		marco("producto");
		for (Producto p : proDAO.list()) {
			System.out.println(p);
		}
		
		marco("marca");
		for (Marca m : marDAO.list()) {
			System.out.println(m);
		}
		
		marco("subcategoria");
		for (Subcategoria s : subDAO.list()) {
			System.out.println(s);
		}
		
		marco("categoria");
		for (Categoria c : catDAO.list()) {
			System.out.println(c);
		}
		
	}
	
	public static void marco(String titulo) {
		System.out.println("-------------------");
		System.out.println(titulo.toUpperCase());
		System.out.println("-------------------");
	}
}
