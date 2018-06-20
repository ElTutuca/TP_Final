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
import ar.com.tutuca.model.Compra;
import ar.com.tutuca.model.CompraDetalle;
import ar.com.tutuca.model.Mayorista;
import ar.com.tutuca.model.TipoDeComprobante;

public class CompraDAO implements GenericDAO<Compra, Integer> {

	private CategoriaIvaDAO catIvaDAO = new CategoriaIvaDAO();
	private TipoDeComprobanteDAO tCompDAO = new TipoDeComprobanteDAO();
	private MetodoPagoDAO mpDAO = new MetodoPagoDAO();

	private MayoristaDAO mDAO = new MayoristaDAO(catIvaDAO);
	private SubcategoriaDAO scDAO = new SubcategoriaDAO();
	private ArchivoDAO archDAO = new ArchivoDAO();
	private ProdArchivosDAO paDAO = new ProdArchivosDAO(archDAO);
	private ProductoDAO proDAO = new ProductoDAO(mDAO, scDAO, paDAO);

	private CompraDetalleDAO cdDAO = new CompraDetalleDAO(proDAO);

	@Override
	public List<Compra> list() throws PersistenciaException {
		List<Compra> r = new ArrayList<Compra>();
		try {
			ResultSet rs = Util.createStatement().executeQuery("SELECT * FROM Compras;");
			while (rs.next()) {

				int idCompra = rs.getInt("idCompra");
				Mayorista mayorista = mDAO.load(rs.getInt("idMayorista"));
				TipoDeComprobante tComprob = tCompDAO.load(rs.getInt("idTiposDeComprob"));
				Timestamp fecha = rs.getTimestamp("Fecha");
				String nmroComprob = rs.getString("NmroDeComprobante");
				double neto1050 = rs.getDouble("Neto1050");
				double neto2100 = rs.getDouble("Neto2100");
				double neto2700 = rs.getDouble("Neto2700");
				double iva1050 = rs.getDouble("Iva1050");
				double iva2100 = rs.getDouble("Iva2100");
				double iva2700 = rs.getDouble("Iva2700");
				double total = rs.getDouble("Total");

				Compra comp = new Compra(idCompra, mayorista, tComprob, fecha, nmroComprob, neto1050, neto2100,
						neto2700, iva1050, iva2100, iva2700, total);

				comp.setMetodosPago(mpDAO.listPorCompra(idCompra));
				comp.setComprasDetalle(cdDAO.listPorCompra(idCompra));
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

			int idCompra = Util.lastId();
			c.setIdCompra(idCompra);
			
			
			mpDAO.inserEnMetodoCompra(c);
			for (CompraDetalle cd : c.getComprasDetalle()) {
				cd.setIdCompra(idCompra);
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

				int idCompra = rs.getInt("idCompra");
				Mayorista mayorista = mDAO.load(rs.getInt("idMayorista"));
				TipoDeComprobante tComprob = tCompDAO.load(rs.getInt("idTiposDeComprob"));
				Timestamp fecha = rs.getTimestamp("Fecha");
				String nmroComprob = rs.getString("NmroDeComprobante");
				double neto1050 = rs.getDouble("Neto1050");
				double neto2100 = rs.getDouble("Neto2100");
				double neto2700 = rs.getDouble("Neto2700");
				double iva1050 = rs.getDouble("Iva1050");
				double iva2100 = rs.getDouble("Iva2100");
				double iva2700 = rs.getDouble("Iva2700");
				double total = rs.getDouble("Total");

				r = new Compra(idCompra, mayorista, tComprob, fecha, nmroComprob, neto1050, neto2100, neto2700, iva1050,
						iva2100, iva2700, total);

				r.setMetodosPago(mpDAO.listPorCompra(idCompra));
				r.setComprasDetalle(cdDAO.listPorCompra(idCompra));
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}

}
