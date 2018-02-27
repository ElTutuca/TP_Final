package ar.com.tutuca.dao;

import java.sql.PreparedStatement;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.com.tutuca.dao.extras.GenericDAO;
import ar.com.tutuca.dao.extras.Util;
import ar.com.tutuca.dao.extras.Exceptions.PersistenciaException;
import ar.com.tutuca.model.Mayorista;

public class MayoristaDAO implements GenericDAO<Mayorista, Integer> {
	
	private CategoriaIvaDAO catDAO;
	
	public MayoristaDAO(CategoriaIvaDAO c) {
		this.catDAO = c;
	}

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
				PreparedStatement ps = Util.prepareStatement(
						"INSERT INTO `Sucursal`.`Mayorista_Productos` (`idMayorista`, `idProductos`) VALUES (?, ?);");
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
					"SELECT may.* FROM Mayorista may INNER JOIN Mayorista_Productos mp ON may.idMayorista=mp.idMayorista WHERE mp.idProductos=?;");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Mayorista mayorista = new Mayorista(rs.getInt("idMayorista"), rs.getString("Nombre"),
						rs.getString("NombreDeFantasia"), rs.getString("Direccion"), rs.getString("Telefono"),
						rs.getString("NmroIngresosBrutos"), rs.getString("CUIT"));
				mayorista.setCatIva(catDAO.load(rs.getInt("idCategoriasIVA")));
				
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
			ResultSet rs = Util.createStatement().executeQuery("SELECT * FROM Mayorista;");
			while (rs.next()) {
				Mayorista mayorista = new Mayorista(rs.getInt("idMayorista"), rs.getString("Nombre"),
						rs.getString("NombreDeFantasia"), rs.getString("Direccion"), rs.getString("Telefono"),
						rs.getString("NmroIngresosBrutos"), rs.getString("CUIT"));
				mayorista.setCatIva(catDAO.load(rs.getInt("idCategoriasIVA")));
				
				r.add(mayorista);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}

	@Override
	public Mayorista insert(Mayorista entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement("INSERT INTO `Sucursal`.`Mayorista` (`Nombre`, `NombreDeFantasia`, `Direccion`, `Telefono`, `NmroIngresosBrutos`, `CUIT`, `idCategoriasIVA`) VALUES (?, ?, ?, ?, ?, ?, ?);");
			ps.setString(1, entidad.getNombre());
			ps.setString(2, entidad.getNombreDeFantasia());
			ps.setString(3, entidad.getDireccion());
			ps.setString(4, entidad.getTelefono());
			ps.setString(5, entidad.getNmroIngresosBrutos());
			ps.setString(6, entidad.getCuit());
			ps.setInt(7, entidad.getCatIva().getIdCategoriasIVA());
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
					.prepareStatement("UPDATE `Sucursal`.`Mayorista` SET `Nombre`=?, `NombreDeFantasia`=?, `Direccion`=?, `Telefono`=?, `NmroIngresosBrutos`=?, `CUIT`=?, `idCategoriasIVA`=? WHERE `idMayorista`=?;");
			ps.setString(1, entidad.getNombre());
			ps.setString(2, entidad.getNombreDeFantasia());
			ps.setString(3, entidad.getDireccion());
			ps.setString(4, entidad.getTelefono());
			ps.setString(5, entidad.getNmroIngresosBrutos());
			ps.setString(6, entidad.getCuit());
			ps.setInt(7, entidad.getCatIva().getIdCategoriasIVA());
			ps.setInt(8, entidad.getIdMayorista());
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
			PreparedStatement ps = Util.prepareStatement("SELECT * FROM Mayorista WHERE idMayorista=?;");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Mayorista mayorista = new Mayorista(rs.getInt("idMayorista"), rs.getString("Nombre"),
						rs.getString("NombreDeFantasia"), rs.getString("Direccion"), rs.getString("Telefono"),
						rs.getString("NmroIngresosBrutos"), rs.getString("CUIT"));
				mayorista.setCatIva(catDAO.load(rs.getInt("idCategoriasIVA")));
				
				r = mayorista;
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}
}
