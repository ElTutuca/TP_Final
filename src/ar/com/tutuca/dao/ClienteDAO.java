package ar.com.tutuca.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ar.com.tutuca.dao.extras.GenericDAO;
import ar.com.tutuca.dao.extras.Util;
import ar.com.tutuca.dao.extras.Exceptions.PersistenciaException;
import ar.com.tutuca.model.Cliente;
import ar.com.tutuca.model.Cuenta;

public class ClienteDAO implements GenericDAO<Cliente, Integer> {

	private CuentaDAO cuentaDAO;

	public ClienteDAO(CuentaDAO cuentaDAO) {
		this.cuentaDAO = cuentaDAO;
	}

	@Override
	public List<Cliente> list() throws PersistenciaException {
		List<Cliente> r = new ArrayList<Cliente>();
		try {
			ResultSet rs = Util.createStatement().executeQuery("SELECT * FROM Clientes;");
			while (rs.next()) {
				Cuenta c = cuentaDAO.load(rs.getInt("idCuenta"));
				r.add(new Cliente(rs.getInt("idCliente"), rs.getString("Nombre"), rs.getString("Apellido"),
						rs.getInt("Edad"), rs.getString("Genero"), rs.getString("Direccion"), rs.getString("Telefono"),
						rs.getString("DNI"), c));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return r;
	}

	@Override
	public Cliente insert(Cliente entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement("INSERT INTO `Sucursal`.`Clientes` (`Nombre`, `Apellido`, `Edad`, `Genero`, `Direccion`, `Telefono`, `DNI`, `idCuenta`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
			ps.setString(1, entidad.getNombre());
			ps.setString(2, entidad.getApellido());
			ps.setInt(3, entidad.getEdad());
			ps.setString(4, entidad.getGenero());
			ps.setString(5, entidad.getDireccion());
			ps.setString(6, entidad.getTelefono());
			ps.setString(7, entidad.getDni());
			ps.setInt(8, entidad.getCuenta().getIdCuenta());
			ps.execute();
		} catch (Exception e) {
			e.getStackTrace();
		}
		return entidad;
	}

	@Override
	public Cliente update(Cliente entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement("UPDATE `Sucursal`.`Clientes` SET `Nombre`=?, `Apellido`=?, `Edad`=?, `Genero`=?, `Direccion`=?, `Telefono`=?, `DNI`=?, `idCuenta`=? WHERE `idCliente`=?;");
			ps.setString(1, entidad.getNombre());
			ps.setString(2, entidad.getApellido());
			ps.setInt(3, entidad.getEdad());
			ps.setString(4, entidad.getGenero());
			ps.setString(5, entidad.getDireccion());
			ps.setString(6, entidad.getTelefono());
			ps.setString(7, entidad.getDni());
			ps.setInt(8, entidad.getCuenta().getIdCuenta());
			ps.setInt(9, entidad.getIdCliente());
			ps.execute();
		} catch (Exception e) {
			e.getStackTrace();
		}
		return entidad;
	}

	@Override
	public void delete(Cliente entidad) throws PersistenciaException {
		try {
			//TODO Preguntar a Mariano si tiene sentido borrar clientes (por las ordenes)
			PreparedStatement ps = Util.prepareStatement("DELETE FROM `Sucursal`.`Clientes` WHERE `idCliente`=?;");
			ps.setInt(1, entidad.getIdCliente());
			ps.execute();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	@Override
	public Cliente load(Integer id) throws PersistenciaException {
		Cliente r = null;
		try {
			PreparedStatement ps = Util.prepareStatement("SELECT * FROM Clientes WHERE idCliente=?;");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Cuenta c = cuentaDAO.load(rs.getInt("idCuenta"));
				r = (new Cliente(rs.getInt("idCliente"), rs.getString("Nombre"), rs.getString("Apellido"),
						rs.getInt("Edad"), rs.getString("Genero"), rs.getString("Direccion"), rs.getString("Telefono"),
						rs.getString("DNI"), c));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return r;
	}

}
