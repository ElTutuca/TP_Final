package ar.com.tutuca.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ar.com.tutuca.model.Marca;
import ar.com.tutuca.model.Mayorista;
import ar.com.tutuca.model.Producto;
import ar.com.tutuca.model.Sucursal;;

public class ProductoDAO implements GenericDAO<Producto, Integer> {

	// Select que devuelve toda la info sobre un producto
	@Override
	public List<Producto> list() throws PersistenciaException {
		List<Producto> r = new ArrayList<Producto>();
		try {
			ResultSet rs = Util.createStatement().executeQuery(
					"SELECT p.*,sc.idSubcategoria, may.Nombre AS Mayorista, suc.*, mc.Nombre AS Marca FROM Productos p INNER JOIN Subcategorias_Productos sc ON p.idProductos=sc.idProductos INNER JOIN Mayorista may ON may.idMayorista=p.idMayorista INNER JOIN Sucursal suc ON suc.idSucursal=p.idSucursal INNER JOIN Marca mc ON mc.idMarca=p.idMarca ORDER BY p.Nombre");
			while (rs.next()) {
				Mayorista mayorista = new Mayorista(rs.getInt("idMayorista"), rs.getString("Mayorista"));
				Sucursal sucursal = new Sucursal(rs.getInt("idSucursal"), rs.getString("Telefono"),
						rs.getString("Ubicacion"), rs.getString("IP"));
				Marca marca = new Marca(rs.getInt("idMarca"), rs.getString("Marca"));
				r.add(new Producto(rs.getInt("idProductos"), rs.getDouble("Precio"), rs.getString("Nombre"),
						rs.getBoolean("Deposito"), rs.getInt("StockMaximo"), rs.getInt("StockMinimo"),
						rs.getInt("StockIdeal"), rs.getInt("Stock"), rs.getInt("Descuento"), rs.getInt("PuntosReq"),
						mayorista, sucursal, marca));
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}

	@Override
	public Producto insert(Producto entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement(
					"INSERT INTO `Sucursal`.`Productos` (`Precio`, `Nombre`, `Deposito`, `StockMaximo`, `StockMinimo`, `StockIdeal`, `Stock`, `Descuento`, `PuntosReq`, `idMayorista`, `idSucursal`, `idMarca`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);");
			ps.setDouble(1, entidad.getPrecio());
			ps.setString(2, entidad.getNombre());
			ps.setBoolean(3, entidad.isDeposito());
			ps.setInt(4, entidad.getStockMaximo());
			ps.setInt(5, entidad.getStockMinimo());
			ps.setInt(6, entidad.getStockIdeal());
			ps.setInt(7, entidad.getStock());
			ps.setInt(8, entidad.getDescuento());
			ps.setInt(9, entidad.getPuntosReq());
			ps.setInt(10, entidad.getMayorista().getIdMayorista());
			ps.setInt(11, entidad.getSucursal().getIdSucursal());
			ps.setInt(12, entidad.getMarca().getIdMarca());
			ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return entidad;
	}

	@Override
	public Producto update(Producto entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement(
					"UPDATE `Sucursal`.`Productos` SET `Precio`=?, `Nombre`=?, `Deposito`=?, `StockMaximo`=?, `StockMinimo`=?, `StockIdeal`=?, `Stock`=?, `Descuento`=?, `PuntosReq`=?, `idMayorista`=?, `idSucursal`=?, `idMarca`=? WHERE `idProductos`=?;");
			ps.setDouble(1, entidad.getPrecio());
			ps.setString(2, entidad.getNombre());
			ps.setBoolean(3, entidad.isDeposito());
			ps.setInt(4, entidad.getStockMaximo());
			ps.setInt(5, entidad.getStockMinimo());
			ps.setInt(6, entidad.getStockIdeal());
			ps.setInt(7, entidad.getStock());
			ps.setInt(8, entidad.getDescuento());
			ps.setInt(9, entidad.getPuntosReq());
			ps.setInt(10, entidad.getMayorista().getIdMayorista());
			ps.setInt(11, entidad.getSucursal().getIdSucursal());
			ps.setInt(12, entidad.getMarca().getIdMarca());
			ps.setInt(13, entidad.getIdProductos());
			ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return entidad;
	}

	@Override
	public void delete(Producto entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement("DELETE FROM `Sucursal`.`Productos` WHERE `idProductos`=?;");
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
					"SELECT p.*,sc.idSubcategoria, may.Nombre AS Mayorista, suc.*, mc.Nombre AS Marca FROM Productos p INNER JOIN Subcategorias_Productos sc ON p.idProductos=sc.idProductos INNER JOIN Mayorista may ON may.idMayorista=p.idMayorista INNER JOIN Sucursal suc ON suc.idSucursal=p.idSucursal INNER JOIN Marca mc ON mc.idMarca=p.idMarca WHERE p.idProductos=? ORDER BY p.Nombre");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Mayorista mayorista = new Mayorista(rs.getInt("idMayorista"), rs.getString("Mayorista"));

				Sucursal sucursal = new Sucursal(rs.getInt("idSucursal"), rs.getString("Telefono"),
						rs.getString("Ubicacion"), rs.getString("IP"));

				Marca marca = new Marca(rs.getInt("idMarca"), rs.getString("Marca"));
				r = new Producto(rs.getInt("idProductos"), rs.getDouble("Precio"), rs.getString("Nombre"),
						rs.getBoolean("Deposito"), rs.getInt("StockMaximo"), rs.getInt("StockMinimo"),
						rs.getInt("StockIdeal"), rs.getInt("Stock"), rs.getInt("Descuento"), rs.getInt("PuntosReq"),
						mayorista, sucursal, marca);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}

}
