package ar.com.tutuca.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ar.com.tutuca.dao.extras.GenericDAO;
import ar.com.tutuca.dao.extras.Util;
import ar.com.tutuca.dao.extras.Exceptions.PersistenciaException;
import ar.com.tutuca.model.Cuenta;

public class CuentaDAO implements GenericDAO<Cuenta, Integer> {

	@Override
	public List<Cuenta> list() throws PersistenciaException {
		List<Cuenta> r = new ArrayList<Cuenta>();
		try {
			ResultSet rs = Util.createStatement().executeQuery("SELECT * FROM Cuentas;");
			while (rs.next()) {
				Cuenta c = new Cuenta(rs.getString("email"), rs.getString("Usuario"), rs.getString("Password"),
						rs.getTimestamp("create_time"), rs.getInt("Puntos"), rs.getInt("idCuenta"));
				r.add(c);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return r;
	}

	@Override
	public Cuenta insert(Cuenta entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement(
					"INSERT INTO `Sucursal`.`Cuentas` (`email`, `Usuario`, `Password`, `create_time`, `Puntos`) VALUES (?, ?, ?, ?, ?);");
			ps.setString(1, entidad.getEmail());
			ps.setString(2, entidad.getUsuario());
			ps.setString(3, entidad.getPassword());
			ps.setTimestamp(4, entidad.getCreateTime());
			ps.setInt(5, entidad.getPuntos());
			ps.execute();
		} catch (Exception e) {
			e.getStackTrace();
		}
		return entidad;
	}

	@Override
	public Cuenta update(Cuenta entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement(
					"UPDATE `Sucursal`.`Cuentas` SET `email`=?, `Usuario`=?, `Password`=?, `create_time`=?, `Puntos`=? WHERE `idCuenta`=?;");
			ps.setString(1, entidad.getEmail());
			ps.setString(2, entidad.getUsuario());
			ps.setString(3, entidad.getPassword());
			ps.setTimestamp(4, entidad.getCreateTime());
			ps.setInt(5, entidad.getPuntos());
			ps.setInt(6, entidad.getIdCuenta());
			ps.execute();
		} catch (Exception e) {
			e.getStackTrace();
		}
		return entidad;
	}

	@Override
	public void delete(Cuenta entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement("DELETE FROM `Sucursal`.`Cuentas` WHERE `idCuenta`=?;");
			ps.setInt(1, entidad.getIdCuenta());
			ps.execute();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	@Override
	public Cuenta load(Integer id) throws PersistenciaException {
		Cuenta r = null;
		try {
			PreparedStatement ps = Util.prepareStatement("SELECT * FROM Cuentas WHERE idCuenta=?;");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				r = new Cuenta(rs.getString("email"), rs.getString("Usuario"), rs.getString("Password"),
						rs.getTimestamp("create_time"), rs.getInt("Puntos"), rs.getInt("idCuenta"));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return r;
	}

}
