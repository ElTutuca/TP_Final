package ar.com.tutuca.dao;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.com.tutuca.dao.extras.GenericDAO;
import ar.com.tutuca.dao.extras.PersistenciaException;
import ar.com.tutuca.dao.extras.Util;
import ar.com.tutuca.model.Mayorista;

public class MayoristaDAO implements GenericDAO<Mayorista, Integer> {

	public void deleteEnMayProd(int idProducto, List<Mayorista> mayoristas) {
		try {
			for (Mayorista may : mayoristas) {
				PreparedStatement ps1 = Util.prepareStatement(
						"DELETE FROM `Sucursal`.`Mayorista_Productos` WHERE `idMayorista`=? and`idProductos`=?;");
				ps1.setInt(1, may.getIdMayorista());
				ps1.setInt(2, idProducto);
				ps1.execute();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertEnMayProd(int idProducto, List<Mayorista> mayoristas) {
		for (Mayorista may : mayoristas) {
			try {
				PreparedStatement ps = Util.prepareStatement("INSERT INTO `Sucursal`.`Mayorista_Productos` (`idMayorista`, `idProductos`) VALUES (?, ?);");
				ps.setInt(1, may.getIdMayorista());
				ps.setInt(2, idProducto);
				ps.execute();	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public List<Mayorista> listPorProducto(int id) throws PersistenciaException {
		List<Mayorista> mayoristas = new ArrayList<Mayorista>();
		try {
			PreparedStatement ps = Util.prepareStatement(
					"SELECT mp.idMayorista, may.Nombre FROM Mayorista may INNER JOIN Mayorista_Productos mp ON may.idMayorista=mp.idMayorista WHERE mp.idProductos=?;");
			ps.setInt(1, id);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				Mayorista mayorista = new Mayorista(rs1.getInt("idMayorista"), rs1.getString("Nombre"));
				mayoristas.add(mayorista);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return mayoristas;
	}

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
