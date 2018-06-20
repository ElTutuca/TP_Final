package ar.com.tutuca.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.com.tutuca.extras.GenericDAO;
import ar.com.tutuca.extras.PersistenciaException;
import ar.com.tutuca.extras.Util;
import ar.com.tutuca.model.CompraDetalle;
import ar.com.tutuca.model.Producto;

public class CompraDetalleDAO implements GenericDAO<CompraDetalle, Integer> {

	private ProductoDAO proDAO;

	public CompraDetalleDAO(ProductoDAO productoDAO) {
		this.proDAO = productoDAO;
	}

	public List<CompraDetalle> listPorCompra(int idCompra) throws PersistenciaException {
		List<CompraDetalle> r = new ArrayList<CompraDetalle>();
		try {
			PreparedStatement ps = Util.prepareStatement("SELECT * FROM Compras_Detalle WHERE idCompra=?;");
			ps.setInt(1, idCompra);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				Producto prod = proDAO.load(rs.getInt("idProductos"));
				int cant = rs.getInt("Cantidad");

				r.add(new CompraDetalle(idCompra, prod, cant));
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}

	@Override
	public List<CompraDetalle> list() throws PersistenciaException {
		List<CompraDetalle> r = new ArrayList<CompraDetalle>();
		try {
			ResultSet rs = Util.createStatement().executeQuery("SELECT * FROM Compras_Detalle;");
			while (rs.next()) {
				
				int idCompra = rs.getInt("idCompra");
				Producto prod = proDAO.load(rs.getInt("idProductos"));
				int cant = rs.getInt("Cantidad");

				r.add(new CompraDetalle(idCompra, prod, cant));			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}

	@Override
	public CompraDetalle insert(CompraDetalle entidad) throws PersistenciaException {
		/*
		 * Antes de llamar a este metodo se deberia insertar una 'Compra', y a
		 * continuacion se debera crear un 'CompraDetalle' con el id de la compra
		 * insertada para ser insertado.
		 */
		
		try {
			PreparedStatement ps = Util.prepareStatement(
					"INSERT INTO `Sucursal`.`Compras_Detalle` (`idCompra`, `idProductos`, `Cantidad`) VALUES (?, ?, ?);");
			ps.setInt(1, entidad.getIdCompra());
			ps.setInt(2, entidad.getProd().getIdProductos());
			ps.setInt(3, entidad.getCantidad());
			ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return entidad;
	}

	@Override
	public CompraDetalle update(CompraDetalle entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement(
					"UPDATE `Sucursal`.`Compras_Detalle` SET `idCompra`=?, `idProductos`=?, `Cantidad`=? WHERE `idCompra`=? and`idProductos`=?;");
			ps.setInt(1, entidad.getIdCompra());
			ps.setInt(2, entidad.getProd().getIdProductos());
			ps.setInt(3, entidad.getCantidad());
			ps.setInt(4, entidad.getIdCompra());
			ps.setInt(5, entidad.getProd().getIdProductos());
			ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return entidad;
	}

	@Override
	public void delete(CompraDetalle entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement(
					"DELETE FROM `Sucursal`.`Compras_Detalle` WHERE `idCompra`=? and`idProductos`=?;");
			ps.setInt(1, entidad.getIdCompra());
			ps.setInt(2, entidad.getProd().getIdProductos());
			ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
	}

	@Override
	public CompraDetalle load(Integer id) throws PersistenciaException {
		/**
		 * Useless method, use listPorCompra() instead.
		 */
		return null;
	}

}
