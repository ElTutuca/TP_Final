package ar.com.tutuca.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.com.tutuca.dao.extras.GenericDAO;
import ar.com.tutuca.dao.extras.Util;
import ar.com.tutuca.dao.extras.Exceptions.PersistenciaException;
import ar.com.tutuca.model.Marca;

public class MarcaDAO implements GenericDAO<Marca, Integer> {

	@Override
	public List<Marca> list() throws PersistenciaException {
		List<Marca> r = new ArrayList<Marca>();
		try {
			ResultSet rs = Util.createStatement().executeQuery("SELECT * FROM Marca ORDER BY Nombre");
			while (rs.next()) {
				r.add(new Marca(rs.getInt("idMarca"), rs.getString("Nombre")));
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}

	@Override
	public Marca insert(Marca entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement(
					"INSERT INTO `Sucursal`.`Marca` (`Nombre`) VALUES (?);");
			ps.setString(1, entidad.getNombre());
			ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return entidad;
	}

	@Override
	public Marca update(Marca entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util
					.prepareStatement("UPDATE `Sucursal`.`Marca` SET `Nombre`=? WHERE `idMarca`=?;");
			ps.setString(1, entidad.getNombre());
			ps.setInt(2, entidad.getIdMarca());
			ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return entidad;
	}

	@Override
	public void delete(Marca entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement("DELETE FROM `Sucursal`.`Marca` WHERE `idMarca`=?;");
			ps.setInt(1, entidad.getIdMarca());
			ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
	}

	@Override
	public Marca load(Integer id) throws PersistenciaException {
		Marca r = null;
		try {
			PreparedStatement ps = Util.prepareStatement("SELECT * FROM Marca WHERE idMarca=?;");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				 r = new Marca(rs.getInt("idMarca"), rs.getString("Nombre"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}
	
}
