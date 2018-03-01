package ar.com.tutuca.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.com.tutuca.extras.GenericDAO;
import ar.com.tutuca.extras.PersistenciaException;
import ar.com.tutuca.extras.Util;
import ar.com.tutuca.model.Categoria;

public class CategoriaDAO implements GenericDAO<Categoria, Integer> {

	@Override
	public List<Categoria> list() throws PersistenciaException {
		List<Categoria> r = new ArrayList<Categoria>();
		try {
			ResultSet rs = Util.createStatement().executeQuery("SELECT * FROM Categoria;");
			while (rs.next()) {
				r.add(new Categoria(rs.getInt("idCategoria"), rs.getString("Categoria")));
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}

	@Override
	public Categoria insert(Categoria entidad) throws PersistenciaException {
		Categoria cat = entidad;
		try {
			PreparedStatement ps = Util.prepareStatement("INSERT INTO `Sucursal`.`Categoria` (`Categoria`) VALUES (?);");
			ps.setString(1, cat.getCategoria());
			ps.execute();
			cat.setIdCategoria(Util.lastId());
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return cat;
	}

	@Override
	public Categoria update(Categoria entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util
					.prepareStatement("UPDATE `Sucursal`.`Categoria` SET `Categoria`=? WHERE `idCategoria`=?;");
			ps.setString(1, entidad.getCategoria());
			ps.setInt(2, entidad.getIdCategoria());
			ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return entidad;
	}

	@Override
	public void delete(Categoria entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement("DELETE FROM `Sucursal`.`Categoria` WHERE `idCategoria`=?;");
			ps.setInt(1, entidad.getIdCategoria());
			ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
	}

	@Override
	public Categoria load(Integer id) throws PersistenciaException {
		Categoria r = null;
		try {
			PreparedStatement ps = Util.prepareStatement("SELECT * FROM Categoria WHERE idCategoria=?;");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				 r = new Categoria(rs.getInt("idCategoria"), rs.getString("Categoria"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}
}
