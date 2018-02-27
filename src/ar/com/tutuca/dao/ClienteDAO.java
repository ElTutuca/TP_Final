package ar.com.tutuca.dao;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import ar.com.tutuca.dao.extras.GenericDAO;
import ar.com.tutuca.dao.extras.Util;
import ar.com.tutuca.dao.extras.Exceptions.PersistenciaException;
import ar.com.tutuca.model.CategoriaIva;
import ar.com.tutuca.model.Cliente;

public class ClienteDAO implements GenericDAO<Cliente, Integer> {

	@Override
	public List<Cliente> list() throws PersistenciaException {
		List<Cliente> r = new ArrayList<Cliente>();
		try {
			ResultSet rs = Util.createStatement().executeQuery(
					"SELECT c.*, ci.* FROM Clientes c INNER JOIN CategoriasIVA ci ON c.idCategoriasIVA=ci.idCategoriasIVA;");
			while (rs.next()) {
				Cliente c = new Cliente(rs.getInt("idCliente"), rs.getString("Nombre"),
						rs.getString("NombreDeFantasia"), rs.getString("Direccion"), rs.getString("Telefono"),
						rs.getString("NmroIngresosBrutos"), rs.getString("CUIT"));

				c.setCatIva(new CategoriaIva(rs.getInt("idCategoriasIVA"), rs.getString("Nombre"),
						rs.getBigDecimal("Tasa"), rs.getBoolean("Discrimina")));
				r.add(c);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return r;
	}

	@Override
	public Cliente insert(Cliente entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement(
					"INSERT INTO `Sucursal`.`Clientes` (`Nombre`, `NombreDeFantasia`, `Direccion`, `Telefono`, `NmroIngresosBrutos`, `CUIT`, `idCategoriasIVA`) VALUES (?, ?, ?, ?, ?, ?, ?);");
			ps.setString(1, entidad.getNombre());
			ps.setString(2, entidad.getNombreDeFantasia());
			ps.setString(3, entidad.getDireccion());
			ps.setString(4, entidad.getTelefono());
			ps.setString(5, entidad.getNmroIngresosBrutos());
			ps.setString(6, entidad.getCuit());
			ps.setInt(7, entidad.getCatIva().getIdCategoriasIVA());
			ps.execute();
		} catch (Exception e) {
			e.getStackTrace();
		}
		return entidad;
	}

	@Override
	public Cliente update(Cliente entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement(
					"UPDATE `Sucursal`.`Clientes` SET `Nombre`=?, `NombreDeFantasia`=?, `Direccion`=?, `Telefono`=?, `NmroIngresosBrutos`=?, `CUIT`=?, `idCategoriasIVA`=? WHERE `idCliente`=?;");
			ps.setString(1, entidad.getNombre());
			ps.setString(2, entidad.getNombreDeFantasia());
			ps.setString(3, entidad.getDireccion());
			ps.setString(4, entidad.getTelefono());
			ps.setString(5, entidad.getNmroIngresosBrutos());
			ps.setString(6, entidad.getCuit());
			ps.setInt(7, entidad.getCatIva().getIdCategoriasIVA());
			ps.setInt(8, entidad.getIdCliente());
			ps.execute();
		} catch (Exception e) {
			e.getStackTrace();
		}
		return entidad;
	}

	@Override
	public void delete(Cliente entidad) throws PersistenciaException {
		/**
		 * No tiene codigo adentro.
		 */
	}

	@Override
	public Cliente load(Integer id) throws PersistenciaException {
		/**
		 * Carga un cliente;
		 * @return Cliente
		 */
		Cliente r = null;
		try {
			PreparedStatement ps = Util.prepareStatement(
					"SELECT c.*, ci.* FROM Clientes c INNER JOIN CategoriasIVA ci ON c.idCategoriasIVA=ci.idCategoriasIVA WHERE c.idCliente=?;");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Cliente c = new Cliente(rs.getInt("idCliente"), rs.getString("Nombre"),
						rs.getString("NombreDeFantasia"), rs.getString("Direccion"), rs.getString("Telefono"),
						rs.getString("NmroIngresosBrutos"), rs.getString("CUIT"));

				c.setCatIva(new CategoriaIva(rs.getInt("idCategoriasIVA"), rs.getString("Nombre"),
						rs.getBigDecimal("Tasa"), rs.getBoolean("Discrimina")));

				r = c;
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return r;
	}

}
