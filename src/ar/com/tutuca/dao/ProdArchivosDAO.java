package ar.com.tutuca.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ar.com.tutuca.dao.extras.GenericDAO;
import ar.com.tutuca.dao.extras.Util;
import ar.com.tutuca.dao.extras.Exceptions.PersistenciaException;
import ar.com.tutuca.model.ProdArchivos;

public class ProdArchivosDAO implements GenericDAO<ProdArchivos, Integer> {
	
	private ArchivoDAO archDAO;
	
	public ProdArchivosDAO(ArchivoDAO archDAO) {
		this.archDAO = archDAO;
	}
	
	// TODO Terminar
	
	public List<ProdArchivos> listPorProducto(int id) throws PersistenciaException {
		List<ProdArchivos> r = new ArrayList<ProdArchivos>();
		try {
			PreparedStatement ps = Util.prepareStatement("SELECT a.* FROM Productos_Archivos a INNER JOIN Productos p ON p.idProductos=a.idProductos WHERE a.idProductos=?;");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				r.add(new ProdArchivos(rs.getInt("idProductos"), archDAO.load(rs.getInt("idArchivos")), rs.getInt("Orden")));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return r;
	}
	
	@Override
	public List<ProdArchivos> list() throws PersistenciaException {
		List<ProdArchivos> r = new ArrayList<ProdArchivos>();
		try {
			ResultSet rs = Util.createStatement().executeQuery("SELECT * FROM Productos_Archivos;");
			while (rs.next()) {
				r.add(new ProdArchivos(rs.getInt("idProductos"),archDAO.load(rs.getInt("idArchivos")), rs.getInt("Orden")));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return r;
	}

	@Override
	public ProdArchivos insert(ProdArchivos entidad) throws PersistenciaException {
		return null;
	}

	@Override
	public ProdArchivos update(ProdArchivos entidad) throws PersistenciaException {
		return null;
	}

	@Override
	public void delete(ProdArchivos entidad) throws PersistenciaException {

	}

	@Override
	public ProdArchivos load(Integer id) throws PersistenciaException {
		ProdArchivos r = null;
		try {
			// TODO REPENSAR
			PreparedStatement ps = Util.prepareStatement("SELECT * FROM Productos_Archivos WHERE ;");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				r = new ProdArchivos(rs.getInt("idProductos"),archDAO.load(rs.getInt("idArchivos")), rs.getInt("Orden"));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return r;
	}

}
