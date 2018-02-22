package ar.com.tutuca.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ar.com.tutuca.dao.extras.GenericDAO;
import ar.com.tutuca.dao.extras.PersistenciaException;
import ar.com.tutuca.dao.extras.Util;
import ar.com.tutuca.model.Sucursal;

public class SucursalDAO implements GenericDAO<Sucursal, Integer> {

	@Override
	public List<Sucursal> list() throws PersistenciaException {
		List<Sucursal> r = new ArrayList<Sucursal>();
		try {
			ResultSet rs = Util.createStatement().executeQuery("SELECT * FROM Sucursal;");
			while (rs.next()) {
				Sucursal s = new Sucursal(rs.getInt("idSucursal"), rs.getString("Telefono"), rs.getString("Ubicacion"),
						rs.getString("IP"));
				r.add(s);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return r;
	}

	@Override
	public Sucursal insert(Sucursal entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement(
					"INSERT INTO `Sucursal`.`Sucursal` (`Telefono`, `Ubicacion`, `IP`) VALUES (?, ?, ?);");
			ps.setString(1, entidad.getTelefono());
			ps.setString(2, entidad.getUbicacion());
			ps.setString(3, entidad.getIp());
			ps.execute();
		} catch (Exception e) {
			e.getStackTrace();
		}
		return entidad;
	}

	@Override
	public Sucursal update(Sucursal entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement(
					"UPDATE `Sucursal`.`Sucursal` SET `Telefono`=?, `Ubicacion`=?, `IP`=? WHERE `idSucursal`=?;");
			ps.setString(1, entidad.getTelefono());
			ps.setString(2, entidad.getUbicacion());
			ps.setString(3, entidad.getIp());
			ps.setInt(4, entidad.getIdSucursal());
			ps.execute();
		} catch (Exception e) {
			e.getStackTrace();
		}
		return entidad;
	}

	@Override
	public void delete(Sucursal entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement("DELETE FROM `Sucursal`.`Sucursal` WHERE `idSucursal`=?;");
			ps.setInt(1, entidad.getIdSucursal());
			ps.execute();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	@Override
	public Sucursal load(Integer id) throws PersistenciaException {
		Sucursal r = null;
		try {
			PreparedStatement ps = Util.prepareStatement("SELECT * FROM Sucursal WHERE idSucursal=?;");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				r = new Sucursal(rs.getInt("idSucursal"), rs.getString("Telefono"), rs.getString("Ubicacion"),
						rs.getString("IP"));
				;
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return r;
	}

}
