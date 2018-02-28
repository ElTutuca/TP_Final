package ar.com.tutuca.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.com.tutuca.dao.extras.GenericDAO;
import ar.com.tutuca.dao.extras.Util;
import ar.com.tutuca.dao.extras.Exceptions.PersistenciaException;
import ar.com.tutuca.model.Compra;
import ar.com.tutuca.model.MetodoPago;

public class MetodoPagoDAO implements GenericDAO<MetodoPago, Integer> {
	
	public void inserEnMetodoCompra(Compra c) throws PersistenciaException {
		for (MetodoPago mp : c.getMetodosPago()) {
			try {
				PreparedStatement ps = Util.prepareStatement("INSERT INTO `Sucursal`.`Metodo_Pago_De_Compras` (`idMetodo`, `idCompra`) VALUES (?, ?);");
				ps.setInt(1, mp.getIdMetodo());
				ps.setInt(2, c.getIdCompra());
				ps.execute();
			} catch (ClassNotFoundException | SQLException e) {
				throw new PersistenciaException(e.getMessage(), e);
			}
		}
	}

	public List<MetodoPago> listPorCompra(Compra c) throws PersistenciaException {
		List<MetodoPago> r = new ArrayList<MetodoPago>();
		try {
			PreparedStatement ps = Util.prepareStatement("SELECT mp.* FROM Metodo_Pago mp INNER JOIN Metodo_Pago_De_Compras mpc ON mpc.idMetodo=mp.idMetodo WHERE mpc.idCompra=?;");
			ps.setInt(1, c.getIdCompra());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				MetodoPago mp = new MetodoPago(rs.getInt("idMetodo"), rs.getString("Descripcion"));
				r.add(mp);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}

	@Override
	public List<MetodoPago> list() throws PersistenciaException {
		List<MetodoPago> r = new ArrayList<MetodoPago>();
		try {
			ResultSet rs = Util.createStatement().executeQuery("SELECT * FROM Metodo_Pago;");
			while (rs.next()) {
				MetodoPago mp = new MetodoPago(rs.getInt("idMetodo"), rs.getString("Descripcion"));
				r.add(mp);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}

	@Override
	public MetodoPago insert(MetodoPago entidad) throws PersistenciaException {
		MetodoPago mp = entidad;
		try {
			PreparedStatement ps = Util
					.prepareStatement("INSERT INTO `Sucursal`.`Metodo_Pago` (`Descripcion`) VALUES (?);");
			ps.setString(1, mp.getDescripcion());
			ps.execute();
			mp.setIdMetodo(Util.lastId());
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return mp;
	}

	@Override
	public MetodoPago update(MetodoPago entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util
					.prepareStatement("UPDATE `Sucursal`.`Metodo_Pago` SET `Descripcion`=? WHERE `idMetodo`=?;");
			ps.setString(1, entidad.getDescripcion());
			ps.setInt(2, entidad.getIdMetodo());
			ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return entidad;
	}

	@Override
	public void delete(MetodoPago entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement("DELETE FROM `Sucursal`.`Metodo_Pago` WHERE `idMetodo`=?;");
			ps.setInt(1, entidad.getIdMetodo());
			ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
	}

	@Override
	public MetodoPago load(Integer id) throws PersistenciaException {
		MetodoPago r = null;
		try {
			PreparedStatement ps = Util.prepareStatement("SELECT * FROM Metodo_Pago WHERE idMetodo=?;");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				MetodoPago mp = new MetodoPago(rs.getInt("idMetodo"), rs.getString("Descripcion"));
				r = mp;
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}

}
