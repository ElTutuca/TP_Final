package ar.com.tutuca.dao;

import java.sql.PreparedStatement;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.com.tutuca.extras.GenericDAO;
import ar.com.tutuca.extras.PersistenciaException;
import ar.com.tutuca.extras.Util;
import ar.com.tutuca.model.Marca;
import ar.com.tutuca.model.Producto;

public class ProductoDAO implements GenericDAO<Producto, Integer> {

	private MayoristaDAO mayoristaDAO;
	private SubcategoriaDAO subcategoriaDAO;
	private ProdArchivosDAO prodArchDAO;

	public ProductoDAO(MayoristaDAO mDAO, SubcategoriaDAO scDAO, ProdArchivosDAO paDAO) {
		mayoristaDAO = mDAO;
		subcategoriaDAO = scDAO;
		prodArchDAO = paDAO;
	}

	public List<Producto> listNoEliminados() throws PersistenciaException {
		List<Producto> r = new ArrayList<Producto>();
		try {
			ResultSet rs = Util.createStatement().executeQuery(
					"SELECT p.*, m.Nombre AS Marca FROM Productos p INNER JOIN Marca m ON p.idMarca=m.idMarca WHERE p.Eliminado=0;");
			while (rs.next()) {
				Marca marca = new Marca(rs.getInt("idMarca"), rs.getString("Marca"));
				Producto producto = new Producto(rs.getInt("idProductos"), rs.getString("Codigo"),
						rs.getDouble("Precio"), rs.getString("Nombre"), rs.getString("Ubicacion"),
						rs.getInt("StockMaximo"), rs.getInt("StockMinimo"), rs.getInt("StockIdeal"), rs.getInt("Stock"),
						rs.getBigDecimal("Descuento"), rs.getBoolean("Eliminado"), rs.getBigDecimal("porcentajeIva"), marca);

				producto.setMayoristas(mayoristaDAO.listPorProducto(producto.getIdProductos()));
				producto.setSubcategoria(subcategoriaDAO.listPorProducto(producto.getIdProductos()));
				producto.setProdArch(prodArchDAO.listPorProducto(producto.getIdProductos()));
				r.add(producto);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}

	@Override
	public List<Producto> list() throws PersistenciaException {
		List<Producto> r = new ArrayList<Producto>();
		try {
			ResultSet rs = Util.createStatement().executeQuery(
					"SELECT p.*, m.Nombre AS Marca FROM Productos p INNER JOIN Marca m ON p.idMarca=m.idMarca;");
			while (rs.next()) {
				Marca marca = new Marca(rs.getInt("idMarca"), rs.getString("Marca"));
				Producto producto = new Producto(rs.getInt("idProductos"), rs.getString("Codigo"),
						rs.getDouble("Precio"), rs.getString("Nombre"), rs.getString("Ubicacion"),
						rs.getInt("StockMaximo"), rs.getInt("StockMinimo"), rs.getInt("StockIdeal"), rs.getInt("Stock"),
						rs.getBigDecimal("Descuento"), rs.getBoolean("Eliminado"), rs.getBigDecimal("porcentajeIva"), marca);

				producto.setMayoristas(mayoristaDAO.listPorProducto(producto.getIdProductos()));
				producto.setSubcategoria(subcategoriaDAO.listPorProducto(producto.getIdProductos()));
				producto.setProdArch(prodArchDAO.listPorProducto(producto.getIdProductos()));
				r.add(producto);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}

	@Override
	public Producto insert(Producto entidad) throws PersistenciaException {
		Producto prod = entidad;
		try {
			PreparedStatement ps = Util.prepareStatement(
					"INSERT INTO `Sucursal`.`Productos` (`Codigo`, `Precio`, `Nombre`, `Ubicacion`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `Eliminado`, `PorcentajeIVA`, `idMarca`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
			ps.setString(1, prod.getCodigo());
			ps.setDouble(2, prod.getPrecio());
			ps.setString(3, prod.getNombre());
			ps.setString(4, prod.getUbicacion());
			ps.setInt(5, prod.getStockMaximo());
			ps.setInt(6, prod.getStockMinimo());
			ps.setInt(7, prod.getStockIdeal());
			ps.setInt(8, prod.getStock());
			ps.setBigDecimal(9, prod.getDescuento());
			ps.setBoolean(10, prod.isEliminado());
			ps.setBigDecimal(11, prod.getPorcentajeIva());
			ps.setInt(12, prod.getMarca().getIdMarca());
			ps.execute();

			prod.setIdProductos(Util.lastId());

			mayoristaDAO.insertEnMayProd(prod);
			subcategoriaDAO.insertEnSubProd(prod);
			prodArchDAO.insertPorProducto(prod);

		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return prod;
	}

	@Override
	public Producto update(Producto entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement(
					"UPDATE `Sucursal`.`Productos` SET `Codigo`=?, `Precio`=?', `Nombre`=?, `Ubicacion`=?, `StockMaximo`=?', `StockMinimo`=?, `StockIdeal`=?, `Stock`=?, `Descuento`=?, `Eliminado`=?, `PorcentajeIVA`=?, `idMarca`=? WHERE `idProductos`=?;");
			ps.setString(1, entidad.getCodigo());
			ps.setDouble(2, entidad.getPrecio());
			ps.setString(3, entidad.getNombre());
			ps.setString(4, entidad.getUbicacion());
			ps.setInt(5, entidad.getStockMaximo());
			ps.setInt(6, entidad.getStockMinimo());
			ps.setInt(7, entidad.getStockIdeal());
			ps.setInt(8, entidad.getStock());
			ps.setBigDecimal(9, entidad.getDescuento());
			ps.setBoolean(10, entidad.isEliminado());
			ps.setBigDecimal(11, entidad.getPorcentajeIva());
			ps.setInt(12, entidad.getMarca().getIdMarca());
			ps.setInt(13, entidad.getIdProductos());
			ps.execute();

			mayoristaDAO.deleteEnMayProd(entidad);
			mayoristaDAO.insertEnMayProd(entidad);

			subcategoriaDAO.deleteEnSubProd(entidad);
			subcategoriaDAO.insertEnSubProd(entidad);

			prodArchDAO.deletePorProducto(entidad);
			prodArchDAO.insertPorProducto(entidad);
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return entidad;
	}

	@Override
	public void delete(Producto entidad) throws PersistenciaException {
		try {
			// NO SE ELIMINA NADA, solo se cambia el atrubuto "elimindo" a true
			PreparedStatement ps = Util
					.prepareStatement("UPDATE `Sucursal`.`Productos` SET `Eliminado`='1' WHERE `idProductos`=?;");
			ps.setInt(1, entidad.getIdProductos());
			ps.execute();

		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
	}

	public void restore(Producto entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util
					.prepareStatement("UPDATE `Sucursal`.`Productos` SET `Eliminado`='0' WHERE `idProductos`=?;");
			ps.setInt(1, entidad.getIdProductos());
			ps.execute();

		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
	}

	@Override
	public Producto load(Integer id) throws PersistenciaException {
		Producto r = null;
		try {
			PreparedStatement ps = Util.prepareStatement(
					"SELECT p.*, m.Nombre AS Marca FROM Productos p INNER JOIN Marca m ON p.idMarca=m.idMarca WHERE p.idProductos=?;");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Marca marca = new Marca(rs.getInt("idMarca"), rs.getString("Marca"));
				Producto producto = new Producto(rs.getInt("idProductos"), rs.getString("Codigo"),
						rs.getDouble("Precio"), rs.getString("Nombre"), rs.getString("Ubicacion"),
						rs.getInt("StockMaximo"), rs.getInt("StockMinimo"), rs.getInt("StockIdeal"), rs.getInt("Stock"),
						rs.getBigDecimal("Descuento"), rs.getBoolean("Eliminado"), rs.getBigDecimal("porcentajeIva"), marca);

				producto.setMayoristas(mayoristaDAO.listPorProducto(producto.getIdProductos()));
				producto.setSubcategoria(subcategoriaDAO.listPorProducto(producto.getIdProductos()));
				producto.setProdArch(prodArchDAO.listPorProducto(producto.getIdProductos()));
				r = producto;

			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}
}
