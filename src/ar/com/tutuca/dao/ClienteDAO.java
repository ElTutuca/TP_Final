package ar.com.tutuca.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.com.tutuca.extras.GenericDAO;
import ar.com.tutuca.extras.PersistenciaException;
import ar.com.tutuca.extras.Util;
import ar.com.tutuca.model.CategoriaIva;
import ar.com.tutuca.model.Cliente;

public class ClienteDAO implements GenericDAO<Cliente, Integer> {

	private CategoriaIvaDAO catIvaDAO;

	public ClienteDAO(CategoriaIvaDAO catIvaDAO) {
		this.catIvaDAO = catIvaDAO;
	}

	@Override
	public List<Cliente> list() throws PersistenciaException {
		List<Cliente> r = new ArrayList<Cliente>();
		try {
			ResultSet rs = Util.createStatement().executeQuery("SELECT * FROM Clientes;");
			while (rs.next()) {
				;
				Cliente c = new Cliente(rs.getInt("idCliente"), rs.getString("Nombre"),
						rs.getString("NombreDeFantasia"), rs.getString("Direccion"), rs.getString("Telefono"),
						rs.getString("NmroIngresosBrutos"), rs.getString("CUIT"),
						catIvaDAO.load(rs.getInt("idCategoriasIVA")));
				r.add(c);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}

	@Override
	public Cliente insert(Cliente entidad) throws PersistenciaException {
		Cliente cl = entidad;
		try {
			PreparedStatement ps = Util.prepareStatement(
					"INSERT INTO `Sucursal`.`Clientes` (`Nombre`, `NombreDeFantasia`, `Direccion`, `Telefono`, `NmroIngresosBrutos`, `CUIT`, `idCategoriasIVA`) VALUES (?, ?, ?, ?, ?, ?, ?);");
			ps.setString(1, cl.getNombre());
			ps.setString(2, cl.getNombreDeFantasia());
			ps.setString(3, cl.getDireccion());
			ps.setString(4, cl.getTelefono());
			ps.setString(5, cl.getNmroIngresosBrutos());
			ps.setString(6, cl.getCuit());
			ps.setInt(7, cl.getCatIva().getIdCategoriasIVA());
			ps.execute();
			cl.setIdCliente(Util.lastId());
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return cl;
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
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return entidad;
	}

	@Override
	public void delete(Cliente entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement("DELETE FROM `Sucursal`.`Clientes` WHERE `idCliente`=?;");
			ps.setInt(1, entidad.getIdCliente());
			ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		/**
		 * No va a funcionar
		 */
	}

	@Override
	public Cliente load(Integer id) throws PersistenciaException {
		/**
		 * Carga un cliente
		 * 
		 * @return Cliente
		 */
		Cliente r = null;
		try {
			PreparedStatement ps = Util.prepareStatement("SELECT * FROM Clientes WHERE idCliente=?;");
			ps.setInt(1, id);
			System.out.println("IdCliente: " + id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				int idCliente = rs.getInt("idCliente");
				String nombre = rs.getString("Nombre");
				String nombreDeFantasia = rs.getString("NombreDeFantasia");
				String direccion = rs.getString("Direccion");
				String telefono = rs.getString("Telefono");
				String nmroIngresosBrutos = rs.getString("NmroIngresosBrutos");
				String cuit = rs.getString("CUIT");
				CategoriaIva catIva = catIvaDAO.load(rs.getInt("idCategoriasIVA"));

				r = new Cliente(idCliente, nombre, nombreDeFantasia, direccion, telefono, nmroIngresosBrutos, cuit,
						catIva);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}
}
