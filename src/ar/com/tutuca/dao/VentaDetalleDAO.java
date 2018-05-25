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
import ar.com.tutuca.model.Venta;
import ar.com.tutuca.model.VentaDetalle;

public class VentaDetalleDAO implements GenericDAO<VentaDetalle, Integer> {

	private ProductoDAO proDAO;
	private VentaDAO ventaDAO;

	public VentaDetalleDAO() {
	}

	public VentaDetalleDAO(ProductoDAO proDAO) {
		this.proDAO = proDAO;
	}

	public VentaDetalleDAO(VentaDAO ventaDAO) {
		this.ventaDAO = ventaDAO;
	}

	public VentaDetalleDAO(VentaDAO ventaDAO, ProductoDAO proDAO) {
		this.ventaDAO = ventaDAO;
		this.proDAO = proDAO;
	}

	public List<VentaDetalle> listPorVenta(int idVenta) throws PersistenciaException {
		List<VentaDetalle> r = new ArrayList<VentaDetalle>();
		try {
			PreparedStatement ps = Util.prepareStatement("SELECT * FROM Ventas_Detalle WHERE idVenta=?;");
			ps.setInt(1, idVenta);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Venta venta = ventaDAO.load(rs.getInt("idVenta"));
				Producto prod = proDAO.load(rs.getInt("idProductos"));
				int cant = rs.getInt("Cantidad");
				double precioUnitario = rs.getDouble("PrecioUnitario");
				int descuento = rs.getInt("Descuento");
				double neto = rs.getDouble("Neto");
				BigDecimal porcentajeIva = rs.getBigDecimal("PorcentajeIVA");

				VentaDetalle ventDet = new VentaDetalle(venta, prod, cant, precioUnitario, descuento, neto,
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

				Venta venta = ventaDAO.load(rs.getInt("idVenta"));
				Producto prod = proDAO.load(rs.getInt("idProductos"));
				int cant = rs.getInt("Cantidad");
				double precioUnitario = rs.getDouble("PrecioUnitario");
				int descuento = rs.getInt("Descuento");
				double neto = rs.getDouble("Neto");
				BigDecimal porcentajeIva = rs.getBigDecimal("PorcentajeIVA");

				VentaDetalle ventDet = new VentaDetalle(venta, prod, cant, precioUnitario, descuento, neto,
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VentaDetalle update(VentaDetalle entidad) throws PersistenciaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(VentaDetalle entidad) throws PersistenciaException {
		// TODO Auto-generated method stub

	}

	@Override
	public VentaDetalle load(Integer id) throws PersistenciaException {
		// TODO Auto-generated method stub
		return null;
	}

}
