package ar.com.tutuca.dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.com.tutuca.extras.GenericDAO;
import ar.com.tutuca.extras.PersistenciaException;
import ar.com.tutuca.extras.Util;
import ar.com.tutuca.model.Producto;
import ar.com.tutuca.model.VentaDetalle;

public class VentaDetalleDAO implements GenericDAO<VentaDetalle, Integer> {

	private ProductoDAO proDAO;

	public VentaDetalleDAO() {
	}

	public VentaDetalleDAO(ProductoDAO proDAO) {
		this.proDAO = proDAO;
	}

	public List<VentaDetalle> listPorVenta(int idVenta) throws PersistenciaException {
		List<VentaDetalle> r = new ArrayList<VentaDetalle>();
		try {
			PreparedStatement ps = Util.prepareStatement("SELECT * FROM Ventas_Detalle WHERE idVenta=?;");
			ps.setInt(1, idVenta);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				int cant = rs.getInt("Cantidad");
				double precioUnitario = rs.getDouble("PrecioUnitario");
				int descuento = rs.getInt("Descuento");
				double neto = rs.getDouble("Neto");
				BigDecimal porcentajeIva = rs.getBigDecimal("PorcentajeIVA");

				int idProd= rs.getInt("idProductos");
				System.out.println("IdProductos: "+idProd);
				
				Producto prod = proDAO.load(idProd);

				VentaDetalle ventDet = new VentaDetalle(idVenta, prod, cant, precioUnitario, descuento, neto,
						porcentajeIva);
				r.add(ventDet);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}

	@Override
	public List<VentaDetalle> list() throws PersistenciaException {
		List<VentaDetalle> r = new ArrayList<VentaDetalle>();
		try {
			ResultSet rs = Util.createStatement().executeQuery("SELECT * FROM Ventas_Detalle;");
			while (rs.next()) {

				int idVenta = rs.getInt("idVenta");
				Producto prod = proDAO.load(rs.getInt("idProductos"));
				int cant = rs.getInt("Cantidad");
				double precioUnitario = rs.getDouble("PrecioUnitario");
				int descuento = rs.getInt("Descuento");
				double neto = rs.getDouble("Neto");
				BigDecimal porcentajeIva = rs.getBigDecimal("PorcentajeIVA");

				VentaDetalle ventDet = new VentaDetalle(idVenta, prod, cant, precioUnitario, descuento, neto,
						porcentajeIva);
				r.add(ventDet);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return r;
	}

	@Override
	public VentaDetalle insert(VentaDetalle entidad) throws PersistenciaException {
		/*
		 * Antes de llamar a este metodo se deberia insertar una 'Vemta', y a
		 * continuacion se debera crear un 'VentaDetalle' con el id de la venta
		 * insertada para ser insertado.
		 */

		try {
			PreparedStatement ps = Util.prepareStatement(
					"INSERT INTO `Sucursal`.`Ventas_Detalle` (`idVenta`, `idProductos`, `Cantidad`, `PrecioUnitario`, `Descuento`, `Neto`, `PorcentajeIVA`) VALUES (?,?,?,?,?,?,?);");
			ps.setInt(1, entidad.getIdVenta());
			ps.setInt(2, entidad.getProducto().getIdProductos());
			ps.setInt(3, entidad.getCantidad());
			ps.setDouble(4, entidad.getPrecioUnitario());
			ps.setInt(5, entidad.getDescuento());
			ps.setDouble(6, entidad.getNeto());
			ps.setBigDecimal(7, entidad.getPorcentajeIva());
			ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return entidad;
	}

	@Override
	public VentaDetalle update(VentaDetalle entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement(
					"UPDATE `Sucursal`.`Ventas_Detalle` SET `idVenta`=?, `idProductos`=?, `Cantidad`=?, `PrecioUnitario`=?, `Descuento`=?, `Neto`=?, `PorcentajeIVA`=? WHERE `idVenta`=? and`idProductos`=?;");
			ps.setInt(1, entidad.getIdVenta());
			ps.setInt(2, entidad.getProducto().getIdProductos());
			ps.setInt(3, entidad.getCantidad());
			ps.setDouble(4, entidad.getPrecioUnitario());
			ps.setInt(5, entidad.getDescuento());
			ps.setDouble(6, entidad.getNeto());
			ps.setBigDecimal(7, entidad.getPorcentajeIva());
			ps.setInt(8, entidad.getIdVenta());
			ps.setInt(9, entidad.getProducto().getIdProductos());

			ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
		return entidad;
	}

	@Override
	public void delete(VentaDetalle entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement(
					"DELETE FROM `Sucursal`.`Ventas_Detalle` WHERE `idVenta`=? and`idProductos`=?;");
			ps.setInt(1, entidad.getIdVenta());
			ps.setInt(2, entidad.getProducto().getIdProductos());
			ps.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(), e);
		}
	}

	@Override
	public VentaDetalle load(Integer id) throws PersistenciaException {
		/**
		 * Usless method, use listPorVenta instead
		 * 
		 */
		return null;
	}

}
