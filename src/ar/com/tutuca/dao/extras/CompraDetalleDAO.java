package ar.com.tutuca.dao.extras;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ar.com.tutuca.dao.ProductoDAO;
import ar.com.tutuca.dao.extras.Exceptions.PersistenciaException;
import ar.com.tutuca.model.CompraDetalle;

public class CompraDetalleDAO implements GenericDAO<CompraDetalle, Integer> {

	private ProductoDAO proDAO;

	public CompraDetalleDAO(ProductoDAO productoDAO) {
		this.proDAO = productoDAO;
	}

	@Override
	public List<CompraDetalle> list() throws PersistenciaException {
		List<CompraDetalle> r = new ArrayList<CompraDetalle>();
		try {
			ResultSet rs = Util.createStatement().executeQuery("SELECT * FROM Compras_Detalle;");
			while (rs.next()) {
				r.add(new CompraDetalle(rs.getInt("idCompra"), proDAO.load(rs.getInt("idProductos")),
						rs.getInt("Cantidad")));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return r;
	}

	@Override
	public CompraDetalle insert(CompraDetalle entidad) throws PersistenciaException {
		try {
			PreparedStatement ps = Util.prepareStatement(
					"INSERT INTO `Sucursal`.`Compras_Detalle` (`idCompra`, `idProductos`, `Cantidad`) VALUES (?, ?, ?);");
			ps.setInt(1, entidad.get);
			ps.execute();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return entidad;
	}

	@Override
	public CompraDetalle update(CompraDetalle entidad) throws PersistenciaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(CompraDetalle entidad) throws PersistenciaException {
		// TODO Auto-generated method stub

	}

	@Override
	public CompraDetalle load(Integer id) throws PersistenciaException {
		// TODO Auto-generated method stub
		return null;
	}

}
