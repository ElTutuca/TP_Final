package ar.com.tutuca.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.com.tutuca.extras.GenericDAO;
import ar.com.tutuca.extras.PersistenciaException;
import ar.com.tutuca.extras.Util;
import ar.com.tutuca.model.ProdArchivos;
import ar.com.tutuca.model.Producto;

public class ProdArchivosDAO implements GenericDAO<ProdArchivos, Integer> {

	private ArchivoDAO archDAO;

	public ProdArchivosDAO(ArchivoDAO archDAO) {
		this.archDAO = archDAO;
	}

	public void deletePorProducto(Producto entidad) throws PersistenciaException {
		for (ProdArchivos archs : entidad.getProdArch()) {
			try {
				delete(archs);
			} catch (PersistenciaException e) {
				throw new PersistenciaException(e.getMessage(), e);
			}
		}
	}

	public void insertPorProducto(Producto entidad) throws PersistenciaException {
		for (ProdArchivos archs : entidad.getProdArch()) {
			try {
				insert(archs);
			} catch (PersistenciaException e) {
				throw new PersistenciaException(e.getMessage(), e);
			}
		}
	}

	public List<ProdArchivos> listPorProducto(int id) throws PersistenciaException {
		List<ProdArchivos> r = new ArrayList<ProdArchivos>();
		try {
			PreparedStatement ps = Util.prepareStatement(
					"SELECT a.* FROM Productos_Archivos a INNER JOIN Productos p ON p.idProductos=a.idProductos WHERE a.idProductos=?;");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				r.add(new ProdArchivos(rs.getInt("idProductos"), archDAO.load(rs.getInt("idArchivos")),
						rs.getInt("Orden")));
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}

	@Override
	public List<ProdArchivos> list() throws PersistenciaException {
		List<ProdArchivos> r = new ArrayList<ProdArchivos>();
		try {
			ResultSet rs = Util.createStatement().executeQuery("SELECT * FROM Productos_Archivos;");
			while (rs.next()) {
				r.add(new ProdArchivos(rs.getInt("idProductos"), archDAO.load(rs.getInt("idArchivos")),
						rs.getInt("Orden")));
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}

	@Override
	public ProdArchivos insert(ProdArchivos entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement(
					"INSERT INTO `Sucursal`.`Productos_Archivos` (`idProductos`, `idArchivos`, `Orden`) VALUES (?, ?, ?);");
			ps.setInt(1, entidad.getIdProducto());
			ps.setInt(2, entidad.getArch().getIdArchivo());
			ps.setInt(3, entidad.getOrden());
			ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return entidad;
	}

	@Override
	public ProdArchivos update(ProdArchivos entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement(
					"UPDATE `Sucursal`.`Productos_Archivos` SET `idProductos`=?, `idArchivos`=?, `Orden`=? WHERE `idProductos`=? and`idArchivos`=?;");
			ps.setInt(1, entidad.getIdProducto());
			ps.setInt(2, entidad.getArch().getIdArchivo());
			ps.setInt(3, entidad.getOrden());
			ps.setInt(4, entidad.getIdProducto());
			ps.setInt(5, entidad.getArch().getIdArchivo());
			ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return entidad;
	}

	@Override
	public void delete(ProdArchivos entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement(
					"DELETE FROM `Sucursal`.`Productos_Archivos` WHERE `idProductos`=? and`idArchivos`=?;");
			ps.setInt(1, entidad.getIdProducto());
			ps.setInt(2, entidad.getArch().getIdArchivo());
			ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
	}

	@Override
	public ProdArchivos load(Integer id) throws PersistenciaException {
		/**
		 * Useless method, use listPorProducto() instead.
		 */
		return null;
	}

}
