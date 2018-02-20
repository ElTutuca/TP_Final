package ar.com.tutuca.dao;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.com.tutuca.dao.extras.GenericDAO;
import ar.com.tutuca.dao.extras.PersistenciaException;
import ar.com.tutuca.dao.extras.Util;
import ar.com.tutuca.model.Categoria;
import ar.com.tutuca.model.Subcategoria;

public class SubcategoriaDAO implements GenericDAO<Subcategoria, Integer> {

	public void deleteEnSubProd(int id, List<Subcategoria> subcategoria) {
		for (Subcategoria sc : subcategoria) {
			try {
				PreparedStatement ps = Util.prepareStatement(
						"DELETE FROM `Sucursal`.`Subcategorias_Productos` WHERE `idProductos`=? and`idSubcategoria`=?;");
				ps.setInt(1, id);
				ps.setInt(2, sc.getIdSubcategoria());
				ps.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void insertEnSubProd(int id, List<Subcategoria> subcategoria) {
		for (Subcategoria sc : subcategoria) {
			try {
				PreparedStatement ps = Util.prepareStatement(
						"INSERT INTO `Sucursal`.`Subcategorias_Productos` (`idProductos`, `idSubcategoria`) VALUES (?, ?);");
				ps.setInt(1, id);
				ps.setInt(2, sc.getIdSubcategoria());
				ps.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public List<Subcategoria> listPorProducto(int id) {
		List<Subcategoria> r = new ArrayList<Subcategoria>();
		try {
			PreparedStatement ps = Util.prepareStatement(
					"SELECT s.*, c.Categoria FROM Categoria c INNER JOIN Subcategoria s ON c.idCategoria=s.idCategoria INNER JOIN Subcategorias_Productos sp ON sp.idSubcategoria=s.idSubcategoria WHERE sp.idProductos=?;");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Categoria c = new Categoria(rs.getInt("idCategoria"), rs.getString("Categoria"));
				Subcategoria s = new Subcategoria(rs.getInt("idSubcategoria"), c, rs.getString("Subcategoria"));
				r.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public List<Subcategoria> list() throws PersistenciaException {
		List<Subcategoria> r = new ArrayList<Subcategoria>();
		try {
			ResultSet rs = Util.createStatement().executeQuery(
					"SELECT s.*, c.Categoria FROM Subcategoria s INNER JOIN Categoria c ON s.idCategoria=c.idCategoria;");
			while (rs.next()) {
				Categoria c = new Categoria(rs.getInt("idCategoria"), rs.getString("Categoria"));
				Subcategoria s = new Subcategoria(rs.getInt("idSubcategoria"), c, rs.getString("Subcategoria"));
				r.add(s);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}

	@Override
	public Subcategoria insert(Subcategoria entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement(
					"INSERT INTO `Sucursal`.`Subcategoria` (`Subcategoria`, `idCategoria`) VALUES (?, ?);");

			ps.setString(1, entidad.getSubcategoria());
			ps.setInt(2, entidad.getCategoria().getIdCategoria());
			ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return entidad;
	}

	@Override
	public Subcategoria update(Subcategoria entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement(
					"UPDATE `Sucursal`.`Subcategoria` SET `Subcategoria`=?, `idCategoria`=? WHERE `idSubcategoria`=?;");
			ps.setString(1, entidad.getSubcategoria());
			ps.setInt(2, entidad.getCategoria().getIdCategoria());
			ps.setInt(3, entidad.getIdSubcategoria());
			ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return entidad;
	}

	@Override
	public void delete(Subcategoria entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util
					.prepareStatement("DELETE FROM `Sucursal`.`Subcategoria` WHERE `idSubcategoria`=?;");
			ps.setInt(1, entidad.getIdSubcategoria());
			ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}

	}

	@Override
	public Subcategoria load(Integer id) throws PersistenciaException {
		Subcategoria r = null;
		try {
			PreparedStatement ps = Util.prepareStatement(
					"SELECT s.*, c.Categoria FROM Subcategoria s INNER JOIN Categoria c ON s.idCategoria=c.idCategoria WHERE s.idSubcategoria=?;");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Categoria c = new Categoria(rs.getInt("idCategoria"), rs.getString("Categoria"));
				r = new Subcategoria(rs.getInt("idSubcategoria"), c, rs.getString("Subcategoria"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}

}
