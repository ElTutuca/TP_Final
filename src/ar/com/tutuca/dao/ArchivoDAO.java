package ar.com.tutuca.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ar.com.tutuca.dao.extras.GenericDAO;
import ar.com.tutuca.dao.extras.PersistenciaException;
import ar.com.tutuca.dao.extras.Util;
import ar.com.tutuca.model.Archivo;

public class ArchivoDAO implements GenericDAO<Archivo, Integer> {
	
	
	public void deleteEnProdArch(int id, List<Archivo> archivos) {
		for (Archivo archivo : archivos) {
			try {
				PreparedStatement ps = Util.prepareStatement(
						"DELETE FROM `Sucursal`.`Productos_Archivos` WHERE `idProductos`=? and`idArchivos`=?;");
				ps.setInt(1, id);
				ps.setInt(2, archivo.getIdArchivo());
				ps.execute();
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
	}
	
	public void insertEnProdArch(int id, List<Archivo> archivos) {
		for (Archivo archivo : archivos) {
			try {
				PreparedStatement ps = Util.prepareStatement(
						"INSERT INTO `Sucursal`.`Productos_Archivos` (`idProductos`, `idArchivos`) VALUES (?, ?);");
				ps.setInt(1, id);
				ps.setInt(2, archivo.getIdArchivo());
				ps.execute();
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
	}

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
		} catch (Exception e) {
			e.printStackTrace();
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public Archivo insert(Archivo entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement(
					"INSERT INTO `Sucursal`.`Archivos` (`PATH`, `Nombre`, `mimeType`, `Tamaño`) VALUES (?, ?, ?, ?);");
			ps.setString(1, entidad.getPath());
			ps.setString(2, entidad.getNombre());
			ps.setString(3, entidad.getMimeType());
			ps.setInt(4, entidad.getTamaño());
			ps.execute();
		} catch (Exception e) {
			e.getStackTrace();
		}
		return entidad;
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
		} catch (Exception e) {
			e.getStackTrace();
		}
		return entidad;
	}

	@Override
	public void delete(Archivo entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement("DELETE FROM `Sucursal`.`Archivos` WHERE `idArchivos`=?;");
			ps.setInt(1, entidad.getIdArchivo());
			ps.execute();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	@Override
	public Archivo load(Integer id) throws PersistenciaException {
		Archivo r = null;
		try {
			ResultSet rs = Util.createStatement().executeQuery("SELECT * FROM Archivos;");
			if (rs.next()) {
				r = new Archivo(rs.getInt("idArchivos"), rs.getString("PATH"), rs.getString("Nombre"),
						rs.getString("mimeType"), rs.getInt("Tamaño"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
}