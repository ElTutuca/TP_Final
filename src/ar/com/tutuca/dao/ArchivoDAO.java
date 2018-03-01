package ar.com.tutuca.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.com.tutuca.extras.GenericDAO;
import ar.com.tutuca.extras.PersistenciaException;
import ar.com.tutuca.extras.Util;
import ar.com.tutuca.model.Archivo;

public class ArchivoDAO implements GenericDAO<Archivo, Integer> {

	public List<Archivo> listPorProducto(int id) throws PersistenciaException {
		List<Archivo> r = new ArrayList<Archivo>();
		try {
			PreparedStatement ps = Util.prepareStatement(
					"SELECT a.* FROM Archivos a INNER JOIN Productos_Archivos pa ON a.idArchivos=pa.idArchivos WHERE pa.idProductos=?;");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				r.add(new Archivo(rs.getInt("idArchivos"), rs.getString("PATH"), rs.getString("Nombre"),
						rs.getString("mimeType"), rs.getInt("Tamaño")));
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}

	@Override
	public List<Archivo> list() throws PersistenciaException {
		List<Archivo> r = new ArrayList<Archivo>();
		try {
			ResultSet rs = Util.createStatement().executeQuery("SELECT * FROM Archivos;");
			while (rs.next()) {
				r.add(new Archivo(rs.getInt("idArchivos"), rs.getString("PATH"), rs.getString("Nombre"),
						rs.getString("mimeType"), rs.getInt("Tamaño")));
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}

	@Override
	public Archivo insert(Archivo entidad) throws PersistenciaException {
		Archivo arch = entidad;
		try {
			PreparedStatement ps = Util.prepareStatement(
					"INSERT INTO `Sucursal`.`Archivos` (`PATH`, `Nombre`, `mimeType`, `Tamaño`) VALUES (?, ?, ?, ?);");
			ps.setString(1, arch.getPath());
			ps.setString(2, arch.getNombre());
			ps.setString(3, arch.getMimeType());
			ps.setInt(4, arch.getTamaño());
			ps.execute();
			arch.setIdArchivo(Util.lastId());
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return arch;
	}

	@Override
	public Archivo update(Archivo entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement(
					"UPDATE `Sucursal`.`Archivos` SET `PATH`=?, `Nombre`=?, `mimeType`=?, `Tamaño`=? WHERE `idArchivos`=?;");
			ps.setString(1, entidad.getPath());
			ps.setString(2, entidad.getNombre());
			ps.setString(3, entidad.getMimeType());
			ps.setInt(4, entidad.getTamaño());
			ps.setInt(5, entidad.getIdArchivo());
			ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return entidad;
	}

	@Override
	public void delete(Archivo entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement("DELETE FROM `Sucursal`.`Archivos` WHERE `idArchivos`=?;");
			ps.setInt(1, entidad.getIdArchivo());
			ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
	}

	@Override
	public Archivo load(Integer id) throws PersistenciaException {
		Archivo r = null;
		try {
			PreparedStatement ps = Util.prepareStatement("SELECT * FROM Archivos WHERE idArchivos=?;");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				r = new Archivo(rs.getInt("idArchivos"), rs.getString("PATH"), rs.getString("Nombre"),
						rs.getString("mimeType"), rs.getInt("Tamaño"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}
}