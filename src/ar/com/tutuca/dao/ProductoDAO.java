package ar.com.tutuca.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.com.tutuca.dao.extras.GenericDAO;
import ar.com.tutuca.dao.extras.Util;
import ar.com.tutuca.dao.extras.Exceptions.PersistenciaException;
import ar.com.tutuca.model.Archivo;
import ar.com.tutuca.model.Marca;
import ar.com.tutuca.model.Producto;
import ar.com.tutuca.model.Sucursal;;

public class ProductoDAO implements GenericDAO<Producto, Integer> {

	private MayoristaDAO mayoristaDAO;
	private SubcategoriaDAO subcategoriaDAO;
	private ArchivoDAO archivoDAO;

	public ProductoDAO(MayoristaDAO mDAO, SubcategoriaDAO scDAO, ArchivoDAO aDAO) {
		mayoristaDAO = mDAO;
		subcategoriaDAO = scDAO;
		archivoDAO = aDAO;
	}

	// TODO Agregar porcentajeIva

	public List<Producto> listNoEliminados() throws PersistenciaException {
		List<Producto> r = new ArrayList<Producto>();
		try {
			ResultSet rs = Util.createStatement().executeQuery(
					"SELECT p.*,sc.idSubcategoria, suc.*, mc.Nombre AS Marca FROM Productos p INNER JOIN Subcategorias_Productos sc ON p.idProductos=sc.idProductos INNER JOIN Sucursal suc ON suc.idSucursal=p.idSucursal INNER JOIN Marca mc ON mc.idMarca=p.idMarca WHERE p.Eliminado=0;");
			while (rs.next()) {
				Sucursal sucursal = new Sucursal(rs.getInt("idSucursal"), rs.getString("Telefono"),
						rs.getString("Ubicacion"), rs.getString("IP"));
				Marca marca = new Marca(rs.getInt("idMarca"), rs.getString("Marca"));
				Producto producto = new Producto(rs.getInt("idProductos"), rs.getDouble("Precio"),
						rs.getString("Nombre"), rs.getBoolean("Deposito"), rs.getInt("StockMaximo"),
						rs.getInt("StockMinimo"), rs.getInt("StockIdeal"), rs.getInt("Stock"), rs.getInt("Descuento"),
						rs.getInt("PuntosReq"), rs.getBoolean("Eliminado"), sucursal, marca,
						rs.getFloat("PorcentajeIVA"));

				producto.setMayoristas(mayoristaDAO.listPorProducto(producto.getIdProductos()));
				producto.setSubcategoria(subcategoriaDAO.listPorProducto(producto.getIdProductos()));
				producto.setArchivos(archivoDAO.listPorProducto(producto.getIdProductos()));
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
					"SELECT p.*,sc.idSubcategoria, suc.*, mc.Nombre AS Marca FROM Productos p INNER JOIN Subcategorias_Productos sc ON p.idProductos=sc.idProductos INNER JOIN Sucursal suc ON suc.idSucursal=p.idSucursal INNER JOIN Marca mc ON mc.idMarca=p.idMarca;");
			while (rs.next()) {
				Sucursal sucursal = new Sucursal(rs.getInt("idSucursal"), rs.getString("Telefono"),
						rs.getString("Ubicacion"), rs.getString("IP"));
				Marca marca = new Marca(rs.getInt("idMarca"), rs.getString("Marca"));
				Producto producto = new Producto(rs.getInt("idProductos"), rs.getDouble("Precio"),
						rs.getString("Nombre"), rs.getBoolean("Deposito"), rs.getInt("StockMaximo"),
						rs.getInt("StockMinimo"), rs.getInt("StockIdeal"), rs.getInt("Stock"), rs.getInt("Descuento"),
						rs.getInt("PuntosReq"), rs.getBoolean("Eliminado"), sucursal, marca,
						rs.getFloat("PorcentajeIVA"));

				producto.setMayoristas(mayoristaDAO.listPorProducto(producto.getIdProductos()));
				producto.setSubcategoria(subcategoriaDAO.listPorProducto(producto.getIdProductos()));

				List<Archivo> arch = archivoDAO.listPorProducto(producto.getIdProductos());
				producto.setArchivos(arch);

				r.add(producto);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}

	@Override
	public Producto insert(Producto entidad) throws PersistenciaException {
		try {
			// Insert del producto
			PreparedStatement ps = Util.prepareStatement(
					"INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `Eliminado`, `idSucursal`, `idMarca`, `PorcentajeIVA`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);");
			ps.setDouble(1, entidad.getPrecio());
			ps.setString(2, entidad.getNombre());
			ps.setBoolean(3, entidad.isDeposito());
			ps.setInt(4, entidad.getStockMaximo());
			ps.setInt(5, entidad.getStockMinimo());
			ps.setInt(6, entidad.getStockIdeal());
			ps.setInt(7, entidad.getStock());
			ps.setInt(8, entidad.getDescuento());
			ps.setInt(9, entidad.getPuntosReq());
			ps.setBoolean(10, entidad.isEliminado());
			ps.setInt(11, entidad.getSucursal().getIdSucursal());
			ps.setInt(12, entidad.getMarca().getIdMarca());
			ps.setFloat(13, entidad.getPorcentajeIva());
			ps.execute();

			int lastId = Util.lastId();
			mayoristaDAO.insertEnMayProd(lastId, entidad.getMayoristas());
			subcategoriaDAO.insertEnSubProd(lastId, entidad.getSubcategoria());
			archivoDAO.insertEnProdArch(lastId, entidad.getArchivos());

		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return entidad;
	}

	@Override
	public Producto update(Producto entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement(
					"UPDATE `Sucursal`.`Productos` SET `Precio`=?, `Nombre`=?, `Deposito`=?, `StockMaximo`=?, `StockMinimo`=?, `StockIdeal`=?, `Stock`=?, `Descuento`=?, `PuntosReq`=?, `Eliminado`=?, `idMayorista`=?, `idSucursal`=?, `idMarca`=?, `PorcentajeIVA`=? WHERE `idProductos`=?;");
			ps.setDouble(1, entidad.getPrecio());
			ps.setString(2, entidad.getNombre());
			ps.setBoolean(3, entidad.isDeposito());
			ps.setInt(4, entidad.getStockMaximo());
			ps.setInt(5, entidad.getStockMinimo());
			ps.setInt(6, entidad.getStockIdeal());
			ps.setInt(7, entidad.getStock());
			ps.setInt(8, entidad.getDescuento());
			ps.setInt(9, entidad.getPuntosReq());
			ps.setBoolean(10, entidad.isEliminado());
			ps.setInt(11, entidad.getSucursal().getIdSucursal());
			ps.setInt(12, entidad.getMarca().getIdMarca());
			ps.setFloat(13, entidad.getPorcentajeIva());
			ps.setInt(14, entidad.getIdProductos());
			ps.execute();

			mayoristaDAO.deleteEnMayProd(entidad.getIdProductos(), entidad.getMayoristas());
			mayoristaDAO.insertEnMayProd(entidad.getIdProductos(), entidad.getMayoristas());

			subcategoriaDAO.deleteEnSubProd(entidad.getIdProductos(), entidad.getSubcategoria());
			subcategoriaDAO.insertEnSubProd(entidad.getIdProductos(), entidad.getSubcategoria());

			archivoDAO.deleteEnProdArch(entidad.getIdProductos(), entidad.getArchivos());
			archivoDAO.insertEnProdArch(entidad.getIdProductos(), entidad.getArchivos());

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
					"SELECT p.*,sc.idSubcategoria, suc.*, mc.Nombre AS Marca FROM Productos p INNER JOIN Subcategorias_Productos sc ON p.idProductos=sc.idProductos INNER JOIN Sucursal suc ON suc.idSucursal=p.idSucursal INNER JOIN Marca mc ON mc.idMarca=p.idMarca WHERE p.idProductos=?;");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Sucursal sucursal = new Sucursal(rs.getInt("idSucursal"), rs.getString("Telefono"),
						rs.getString("Ubicacion"), rs.getString("IP"));
				Marca marca = new Marca(rs.getInt("idMarca"), rs.getString("Marca"));
				Producto producto = new Producto(rs.getInt("idProductos"), rs.getDouble("Precio"),
						rs.getString("Nombre"), rs.getBoolean("Deposito"), rs.getInt("StockMaximo"),
						rs.getInt("StockMinimo"), rs.getInt("StockIdeal"), rs.getInt("Stock"), rs.getInt("Descuento"),
						rs.getInt("PuntosReq"), rs.getBoolean("Eliminado"), sucursal, marca,
						rs.getFloat("PorcentajeIVA"));

				producto.setArchivos(archivoDAO.listPorProducto(producto.getIdProductos()));
				producto.setMayoristas(mayoristaDAO.listPorProducto(producto.getIdProductos()));
				producto.setSubcategoria(subcategoriaDAO.listPorProducto(producto.getIdProductos()));
				r = producto;

			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}

}
