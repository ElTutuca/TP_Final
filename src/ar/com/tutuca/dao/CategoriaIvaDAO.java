package ar.com.tutuca.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.com.tutuca.dao.extras.GenericDAO;
import ar.com.tutuca.dao.extras.Util;
import ar.com.tutuca.dao.extras.Exceptions.PersistenciaException;
import ar.com.tutuca.model.CategoriaIva;

public class CategoriaIvaDAO implements GenericDAO<CategoriaIva, Integer> {

	@Override
	public List<CategoriaIva> list() throws PersistenciaException {
		List<CategoriaIva> r = new ArrayList<CategoriaIva>();
		try {
			ResultSet rs = Util.createStatement().executeQuery("SELECT * FROM CategoriasIVA;");
			while (rs.next()) {
				r.add(new CategoriaIva(rs.getInt("idCategoriasIVA"), rs.getString("Nombre"), rs.getBigDecimal("Tasa"),
						rs.getBoolean("Discrimina")));
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}

	@Override
	public CategoriaIva insert(CategoriaIva entidad) throws PersistenciaException {
		CategoriaIva catIva = entidad;
		try {
			PreparedStatement ps = Util.prepareStatement(
					"INSERT INTO `Sucursal`.`CategoriasIVA` (`Nombre`, `Tasa`, `Discrimina`) VALUES (?, ?, ?);");
			ps.setString(1, catIva.getNombre());
			ps.setBigDecimal(2, catIva.getTasa());
			ps.setBoolean(3, catIva.isDiscrimina());
			ps.execute();
			catIva.setIdCategoriasIVA(Util.lastId());
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return catIva;
	}

	@Override
	public CategoriaIva update(CategoriaIva entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement(
					"UPDATE `Sucursal`.`CategoriasIVA` SET `Nombre`=?, `Tasa`=?, `Discrimina`=? WHERE `idCategoriasIVA`=?;");
			ps.setString(1, entidad.getNombre());
			ps.setBigDecimal(2, entidad.getTasa());
			ps.setBoolean(3, entidad.isDiscrimina());
			ps.setInt(4, entidad.getIdCategoriasIVA());
			ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return entidad;
	}

	@Override
	public void delete(CategoriaIva entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util
					.prepareStatement("DELETE FROM `Sucursal`.`CategoriasIVA` WHERE `idCategoriasIVA`=?;");
			ps.setInt(1, entidad.getIdCategoriasIVA());
			ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
	}

	@Override
	public CategoriaIva load(Integer id) throws PersistenciaException {
		CategoriaIva r = null;
		try {
			PreparedStatement ps = Util.prepareStatement("SELECT * FROM CategoriasIVA WHERE idCategoriasIVA=?;");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				r = new CategoriaIva(rs.getInt("idCategoriasIVA"), rs.getString("Nombre"), rs.getBigDecimal("Tasa"),
						rs.getBoolean("Discrimina"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}
}
