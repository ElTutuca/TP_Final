package ar.com.tutuca.extras;

import ar.com.tutuca.dao.ArchivoDAO;

import ar.com.tutuca.dao.CategoriaDAO;
import ar.com.tutuca.dao.CategoriaIvaDAO;
import ar.com.tutuca.dao.ClienteDAO;
import ar.com.tutuca.dao.MarcaDAO;
import ar.com.tutuca.dao.MayoristaDAO;
import ar.com.tutuca.dao.MetodoPagoDAO;
import ar.com.tutuca.dao.ProdArchivosDAO;
import ar.com.tutuca.dao.ProductoDAO;
import ar.com.tutuca.dao.SubcategoriaDAO;
import ar.com.tutuca.model.Categoria;
import ar.com.tutuca.model.Cliente;
import ar.com.tutuca.model.Marca;
import ar.com.tutuca.model.MetodoPago;
import ar.com.tutuca.model.Producto;
import ar.com.tutuca.model.Subcategoria;

public class TestDAO {

	public static void main(String[] args) throws PersistenciaException {

		ArchivoDAO arDAO = new ArchivoDAO();
		ProdArchivosDAO prodArchDAO = new ProdArchivosDAO(arDAO);
		CategoriaIvaDAO catIvaDAO = new CategoriaIvaDAO();
		MayoristaDAO mayDAO = new MayoristaDAO(catIvaDAO);
		MarcaDAO marDAO = new MarcaDAO();
		SubcategoriaDAO subDAO = new SubcategoriaDAO();
		CategoriaDAO catDAO = new CategoriaDAO();
		ProductoDAO proDAO = new ProductoDAO(mayDAO, subDAO, prodArchDAO);
		ClienteDAO clienteDAO = new ClienteDAO(catIvaDAO);
		MetodoPagoDAO mpDAO = new MetodoPagoDAO();

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

		marco("cliente");
		for (Cliente c : clienteDAO.list()) {
			System.out.println(c);
		}

		marco("metodos de pago");
		for (MetodoPago mp : mpDAO.list()) {
			System.out.println(mp);
		}

	}

	public static void marco(String titulo) {
		System.out.println("-------------------");
		System.out.println(titulo.toUpperCase());
		System.out.println("-------------------");
	}
}
