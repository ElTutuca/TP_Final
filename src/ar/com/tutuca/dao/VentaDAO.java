package ar.com.tutuca.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import ar.com.tutuca.extras.GenericDAO;
import ar.com.tutuca.extras.PersistenciaException;
import ar.com.tutuca.extras.Util;
import ar.com.tutuca.model.Cliente;
import ar.com.tutuca.model.TipoDeComprobante;
import ar.com.tutuca.model.Venta;
import ar.com.tutuca.model.VentaDetalle;

public class VentaDAO implements GenericDAO<Venta, Integer> {

	private CategoriaIvaDAO catIvaDAO = new CategoriaIvaDAO();
	private ClienteDAO clDAO = new ClienteDAO(catIvaDAO);
	private TipoDeComprobanteDAO tCompDAO = new TipoDeComprobanteDAO();
	private MetodoPagoDAO mpDAO = new MetodoPagoDAO();
	private ProductoDAO proDAO;
	private VentaDetalleDAO ventDetDAO = new VentaDetalleDAO(this, proDAO);

	public VentaDAO(ProductoDAO proDAO) {
		this.proDAO = proDAO;
	}

	@Override
	public List<Venta> list() throws PersistenciaException {
		List<Venta> r = new ArrayList<Venta>();
		try {
			ResultSet rs = Util.createStatement().executeQuery("SELECT * FROM Ventas;");
			while (rs.next()) {

				int idVenta = rs.getInt("idVenta");
				Cliente cl = clDAO.load(rs.getInt("idCliente"));
				TipoDeComprobante tComp = tCompDAO.load(rs.getInt("idTiposDeComprob"));
				Timestamp fecha = rs.getTimestamp("Fecha");
				String nmroComp = rs.getString("NmroDeComprobante");
				String puntoVenta = rs.getString("PuntoDeVenta");
				double neto1050 = rs.getDouble("Neto1050");
				double neto2100 = rs.getDouble("Neto2100");
				double neto2700 = rs.getDouble("Neto2700");
				double iva1050 = rs.getDouble("Iva1050");
				double iva2100 = rs.getDouble("Iva2100");
				double iva2700 = rs.getDouble("Iva2700");
				double total = rs.getDouble("Total");

				Venta vent = new Venta(idVenta, cl, tComp, fecha, nmroComp, puntoVenta, neto1050, neto2100, neto2700,
						iva1050, iva2100, iva2700, total);

				vent.setMetodosPago(mpDAO.listPorVenta(idVenta));
				vent.setVentasDetalle(ventDetDAO.listPorVenta(idVenta));

				r.add(vent);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}

	@Override
	public Venta insert(Venta entidad) throws PersistenciaException {
		Venta v = entidad;
		try {
			PreparedStatement ps = Util.prepareStatement(
					"INSERT INTO `Sucursal`.`Ventas` (`idCliente`, `idTiposDeComprob`, `Fecha`, `NmroDeComprobante`, `PuntoDeVenta`, `Neto1050`, `Neto2100`, `Neto2700`, `Iva1050`, `Iva2100`, `Iva2700`, `Total`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
			ps.setInt(1, entidad.getCliente().getIdCliente());
			ps.setInt(2, entidad.getTipoDeComprob().getIdTipoDeComprob());
			ps.setTimestamp(3, entidad.getFecha());
			ps.setString(4, entidad.getNmroDeComprobante());
			ps.setString(5, entidad.getPuntoDeVenta());
			ps.setDouble(6, entidad.getNeto1050());
			ps.setDouble(7, entidad.getNeto2100());
			ps.setDouble(8, entidad.getNeto2700());
			ps.setDouble(9, entidad.getIva1050());
			ps.setDouble(10, entidad.getIva2100());
			ps.setDouble(11, entidad.getIva2700());
			ps.setDouble(12, entidad.getTotal());
			ps.execute();
			v.setIdVenta(Util.lastId());

			mpDAO.inserEnMetodoVenta(v);
			for (VentaDetalle vd : v.getVentasDetalle()) {
				vd.setVenta(v);
				ventDetDAO.insert(vd);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return v;
	}

	@Override
	public Venta update(Venta entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement(
					"UPDATE `Sucursal`.`Ventas` SET `idCliente`=?, `idTiposDeComprob`=?, `Fecha`=?, `NmroDeComprobante`=?, `PuntoDeVenta`=?, `Neto1050`=?, `Neto2100`=?, `Neto2700`=?, `Iva1050`=?, `Iva2100`=?, `Iva2700`=?, `Total`=? WHERE `idVenta`=?;");
			ps.setInt(1, entidad.getCliente().getIdCliente());
			ps.setInt(2, entidad.getTipoDeComprob().getIdTipoDeComprob());
			ps.setTimestamp(3, entidad.getFecha());
			ps.setString(4, entidad.getNmroDeComprobante());
			ps.setString(5, entidad.getPuntoDeVenta());
			ps.setDouble(6, entidad.getNeto1050());
			ps.setDouble(7, entidad.getNeto2100());
			ps.setDouble(8, entidad.getNeto2700());
			ps.setDouble(9, entidad.getIva1050());
			ps.setDouble(10, entidad.getIva2100());
			ps.setDouble(11, entidad.getIva2700());
			ps.setDouble(12, entidad.getTotal());
			ps.setInt(13, entidad.getIdVenta());
			ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return entidad;
	}

	@Override
	public void delete(Venta entidad) throws PersistenciaException {
		/**
		 * Useless method due to the protection of the db integrity.
		 */
	}

	@Override
	public Venta load(Integer id) throws PersistenciaException {
		Venta r = null;
		try {
			PreparedStatement ps = Util.prepareStatement("SELECT * FROM Ventas WHERE idVenta=?;");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				
				int idVenta = rs.getInt("idVenta");
				Cliente cl = clDAO.load(rs.getInt("idCliente"));
				TipoDeComprobante tComp = tCompDAO.load(rs.getInt("idTiposDeComprob"));
				Timestamp fecha = rs.getTimestamp("Fecha");
				String nmroComp = rs.getString("NmroDeComprobante");
				String puntoVenta = rs.getString("PuntoDeVenta");
				double neto1050 = rs.getDouble("Neto1050");
				double neto2100 = rs.getDouble("Neto2100");
				double neto2700 = rs.getDouble("Neto2700");
				double iva1050 = rs.getDouble("Iva1050");
				double iva2100 = rs.getDouble("Iva2100");
				double iva2700 = rs.getDouble("Iva2700");
				double total = rs.getDouble("Total");

				Venta vent = new Venta(idVenta, cl, tComp, fecha, nmroComp, puntoVenta, neto1050, neto2100, neto2700,
						iva1050, iva2100, iva2700, total);

				vent.setMetodosPago(mpDAO.listPorVenta(idVenta));
				vent.setVentasDetalle(ventDetDAO.listPorVenta(idVenta));
				
				r = vent;
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}

}
