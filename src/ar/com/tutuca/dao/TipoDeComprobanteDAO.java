package ar.com.tutuca.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ar.com.tutuca.extras.GenericDAO;
import ar.com.tutuca.extras.PersistenciaException;
import ar.com.tutuca.extras.Util;
import ar.com.tutuca.model.TipoDeComprobante;

public class TipoDeComprobanteDAO implements GenericDAO<TipoDeComprobante, Integer> {

	@Override
	public List<TipoDeComprobante> list() throws PersistenciaException {
		List<TipoDeComprobante> r = new ArrayList<TipoDeComprobante>();
		try {
			ResultSet rs = Util.createStatement().executeQuery("SELECT * FROM TiposDeComprobantes;");
			while (rs.next()) {
				r.add(new TipoDeComprobante(rs.getInt("idTiposDeComprob"), rs.getString("Nombre"), rs.getString("Letra"),
						rs.getString("Abreviatura")));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return r;
	}

	@Override
	public TipoDeComprobante insert(TipoDeComprobante entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement(
					"INSERT INTO `Sucursal`.`TiposDeComprobantes` (`Nombre`, `Letra`, `Abreviatura`) VALUES (?, ?, ?);");
			ps.setString(1, entidad.getNombre());
			ps.setString(2, entidad.getLetra());
			ps.setString(3, entidad.getAbreviatura());
			ps.execute();
		} catch (Exception e) {
			e.getStackTrace();
		}
		return entidad;
	}

	@Override
	public TipoDeComprobante update(TipoDeComprobante entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement(
					"UPDATE `Sucursal`.`TiposDeComprobantes` SET `Nombre`=?, `Letra`=?, `Abreviatura`=? WHERE `idTiposDeComprob`=?;");
			ps.setString(1, entidad.getNombre());
			ps.setString(2, entidad.getLetra());
			ps.setString(3, entidad.getAbreviatura());
			ps.setInt(4, entidad.getIdTipoDeComprob());
			ps.execute();
		} catch (Exception e) {
			e.getStackTrace();
		}
		return entidad;
	}

	@Override
	public void delete(TipoDeComprobante entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util
					.prepareStatement("DELETE FROM `Sucursal`.`TiposDeComprobantes` WHERE `idTiposDeComprob`=?;");
			ps.setInt(1, entidad.getIdTipoDeComprob());
			ps.execute();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	@Override
	public TipoDeComprobante load(Integer id) throws PersistenciaException {
		TipoDeComprobante r = null;
		try {
			PreparedStatement ps = Util.prepareStatement("SELECT * FROM TiposDeComprobantes WHERE idTiposDeComprob=?;");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				r = (new TipoDeComprobante(rs.getInt("idTipoDeComprov"), rs.getString("Nombre"), rs.getString("Letra"),
						rs.getString("Abreviatura")));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return r;
	}

}
