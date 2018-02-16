package ar.com.tutuca.dao;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ar.com.tutuca.model.Mayorista;

public class MayoristaDAO implements GenericDAO<Mayorista, Integer> {

	@Override
	public List<Mayorista> list() throws PersistenciaException {
		List<Mayorista> r = new ArrayList<Mayorista>();
		try {
			ResultSet rs = Util.createStatement().executeQuery("SELECT * FROM Mayorista ORDER BY Nombre");
			while (rs.next()) {
				r.add(new Mayorista(rs.getInt("idMayorista"), rs.getString("Nombre")));
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}

	@Override
	public Mayorista insert(Mayorista entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement("INSERT INTO `Sucursal`.`Mayorista` (`Nombre`) VALUES (?);");

			ps.setString(1, entidad.getNombre());
			ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return entidad;
	}

	@Override
	public Mayorista update(Mayorista entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util
					.prepareStatement("UPDATE `Sucursal`.`Mayorista` SET `Nombre`=? WHERE `idMayorista`=?;");

			ps.setString(1, entidad.getNombre());
			ps.setInt(2, entidad.getIdMayorista());
			ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return entidad;
	}

	@Override
	public void delete(Mayorista entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement("DELETE FROM `Sucursal`.`Mayorista` WHERE `idMayorista`=?;");
			ps.setInt(1, entidad.getIdMayorista());
			ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
	}

	@Override
	public Mayorista load(Integer id) throws PersistenciaException {
		Mayorista r = null;
		try {
			PreparedStatement ps = Util.prepareStatement("SELECT * FROM Mayorista WHERE idMayorista=? ORDER BY Nombre");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				 r = new Mayorista(rs.getInt("idMayorista"), rs.getString("Mayorista"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}

}
