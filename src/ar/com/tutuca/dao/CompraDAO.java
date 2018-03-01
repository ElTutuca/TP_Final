package ar.com.tutuca.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.com.tutuca.extras.GenericDAO;
import ar.com.tutuca.extras.PersistenciaException;
import ar.com.tutuca.extras.Util;
import ar.com.tutuca.model.Compra;
import ar.com.tutuca.model.CompraDetalle;

public class CompraDAO implements GenericDAO<Compra, Integer> {

	private MayoristaDAO mDAO;
	private TipoDeComprobanteDAO tCompDAO;
	private CompraDetalleDAO cdDAO;
	private MetodoPagoDAO mpDAO;

	public CompraDAO(MayoristaDAO mDAO, TipoDeComprobanteDAO tcomp, CompraDetalleDAO cdDAO, MetodoPagoDAO mpDAO) {
		this.mDAO = mDAO;
		this.tCompDAO = tcomp;
		this.cdDAO = cdDAO;
		this.mpDAO = mpDAO;
	}

	@Override
	public List<Compra> list() throws PersistenciaException {
		List<Compra> r = new ArrayList<Compra>();
		try {
			ResultSet rs = Util.createStatement().executeQuery("SELECT * FROM Compras;");
			while (rs.next()) {
				Compra comp = new Compra(rs.getInt("idCompra"), mDAO.load(rs.getInt("idMayorista")),
						tCompDAO.load(rs.getInt("idTiposDeComprob")), rs.getTimestamp("Fecha"),
						rs.getString("NmroDeComprobante"), rs.getDouble("Neto1050"), rs.getDouble("Neto2100"),
						rs.getDouble("Neto2700"), rs.getDouble("Iva1050"), rs.getDouble("Iva2100"),
						rs.getDouble("Iva2700"), rs.getDouble("Total"));
				comp.setComprasDetalle(cdDAO.listPorCompra(comp));
				comp.setMetodosPago(mpDAO.listPorCompra(comp));
				r.add(comp);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}

	@Override
	public Compra insert(Compra entidad) throws PersistenciaException {
		Compra c = entidad;
		try {
			PreparedStatement ps = Util.prepareStatement(
					"INSERT INTO `Sucursal`.`Compras` (`idMayorista`, `idTiposDeComprob`, `Fecha`, `NmroDeComprobante`, `Neto1050`, `Neto2100`, `Neto2700`, `Iva1050`, `Iva2100`, `Iva2700`, `Total`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
			ps.setInt(1, entidad.getMayorista().getIdMayorista());
			ps.setInt(2, entidad.getTipoDeComprob().getIdTipoDeComprob());
			ps.setTimestamp(3, entidad.getFecha());
			ps.setString(4, entidad.getNmroDeComprobante());
			ps.setDouble(5, entidad.getNeto1050());
			ps.setDouble(6, entidad.getNeto2100());
			ps.setDouble(7, entidad.getNeto2700());
			ps.setDouble(8, entidad.getIva1050());
			ps.setDouble(9, entidad.getIva2100());
			ps.setDouble(10, entidad.getIva2700());
			ps.setDouble(11, entidad.getTotal());
			ps.execute();
			c.setIdCompra(Util.lastId());
			
			mpDAO.inserEnMetodoCompra(c);
			for (CompraDetalle cd : c.getComprasDetalle()) {
				cd.setIdCompra(c.getIdCompra());
				cdDAO.insert(cd);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return c;
	}

	@Override
	public Compra update(Compra entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement(
					"UPDATE `Sucursal`.`Compras` SET `idMayorista`=?, `idTiposDeComprob`=?, `Fecha`=?, `NmroDeComprobante`=?, `Neto1050`=?, `Neto2100`=?, `Neto2700`=?, `Iva1050`=?, `Iva2100`=?, `Iva2700`=?, `Total`=? WHERE `idCompra`=?;");
			ps.setInt(1, entidad.getMayorista().getIdMayorista());
			ps.setInt(2, entidad.getTipoDeComprob().getIdTipoDeComprob());
			ps.setTimestamp(3, entidad.getFecha());
			ps.setString(4, entidad.getNmroDeComprobante());
			ps.setDouble(5, entidad.getNeto1050());
			ps.setDouble(6, entidad.getNeto2100());
			ps.setDouble(7, entidad.getNeto2700());
			ps.setDouble(8, entidad.getIva1050());
			ps.setDouble(9, entidad.getIva2100());
			ps.setDouble(10, entidad.getIva2700());
			ps.setDouble(11, entidad.getTotal());
			ps.setInt(12, entidad.getIdCompra());
			ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return entidad;
	}

	@Override
	public void delete(Compra entidad) throws PersistenciaException {
		/*
		 * try { PreparedStatement ps =
		 * Util.prepareStatement("DELETE FROM `Sucursal`.`Compras` WHERE `idCompra`=?;"
		 * ); ps.setInt(1, entidad.getIdCompra()); ps.execute(); } catch
		 * (ClassNotFoundException | SQLException e) { throw new
		 * PersistenciaException(e.getMessage(), e); }
		 */
		
		/**
		 * Useless method due to the protection of the db integrity.
		 */
	}

	@Override
	public Compra load(Integer id) throws PersistenciaException {
		Compra r = null;
		try {
			PreparedStatement ps = Util.prepareStatement("SELECT * FROM Compras WHERE idCompra=?;");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Compra comp = new Compra(rs.getInt("idCompra"), mDAO.load(rs.getInt("idMayorista")),
						tCompDAO.load(rs.getInt("idTiposDeComprob")), rs.getTimestamp("Fecha"),
						rs.getString("NmroDeComprobante"), rs.getDouble("Neto1050"), rs.getDouble("Neto2100"),
						rs.getDouble("Neto2700"), rs.getDouble("Iva1050"), rs.getDouble("Iva2100"),
						rs.getDouble("Iva2700"), rs.getDouble("Total"));
				comp.setComprasDetalle(cdDAO.listPorCompra(comp));
				comp.setMetodosPago(mpDAO.listPorCompra(comp));
				r = comp;
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}

}
