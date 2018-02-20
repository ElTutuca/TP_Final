package ar.com.tutuca.dao.extras;

import ar.com.tutuca.dao.ArchivoDAO;
import ar.com.tutuca.dao.CategoriaDAO;
import ar.com.tutuca.dao.MarcaDAO;
import ar.com.tutuca.dao.MayoristaDAO;
import ar.com.tutuca.dao.ProductoDAO;
import ar.com.tutuca.dao.SubcategoriaDAO;
import ar.com.tutuca.dao.SucursalDAO;
import ar.com.tutuca.model.Categoria;
import ar.com.tutuca.model.Marca;
import ar.com.tutuca.model.Mayorista;
import ar.com.tutuca.model.Producto;
import ar.com.tutuca.model.Subcategoria;
import ar.com.tutuca.model.Sucursal;

public class TestDAO {

	public static void main(String[] args) throws PersistenciaException {
		MayoristaDAO mayDAO = new MayoristaDAO();
		MarcaDAO marDAO = new MarcaDAO();
		SubcategoriaDAO subDAO = new SubcategoriaDAO();
		CategoriaDAO catDAO = new CategoriaDAO();
		ArchivoDAO arDAO = new ArchivoDAO();
		ProductoDAO proDAO = new ProductoDAO(mayDAO, subDAO, arDAO);
		SucursalDAO sucDAO = new SucursalDAO();
		
		
		marco("mayorista");
		for (Mayorista m : mayDAO.list()) {
			System.out.println(m);
		}
		
		marco("producto");
		for (Producto p : proDAO.list()) {
			System.out.println(p);
			p.mostrarMayoristas();
			p.mostrarSubcategorias();
			p.mostrarArchivos();
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
		
		marco("sucusal");
		for (Sucursal s : sucDAO.list()) {
			System.out.println(s);
		}
		
	}
	
	public static void marco(String titulo) {
		System.out.println("-------------------");
		System.out.println(titulo.toUpperCase());
		System.out.println("-------------------");
	}
}
