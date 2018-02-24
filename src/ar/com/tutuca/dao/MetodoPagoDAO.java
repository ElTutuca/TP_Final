package ar.com.tutuca.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ar.com.tutuca.dao.extras.GenericDAO;
import ar.com.tutuca.dao.extras.Util;
import ar.com.tutuca.dao.extras.Exceptions.PersistenciaException;
import ar.com.tutuca.model.MetodoPago;

public class MetodoPagoDAO implements GenericDAO<MetodoPago, Integer> {

	@Override
	public List<MetodoPago> list() throws PersistenciaException {
		List<MetodoPago> r = new ArrayList<MetodoPago>();
		try {
			ResultSet rs = Util.createStatement().executeQuery("SELECT * FROM Metodo_Pago");
			while(rs.next()) {
				MetodoPago mp = new MetodoPago(rs.getInt("idMetodo"), rs.getString("Descripcion"));
				r.add(mp);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return r;
	}

	@Override
	public MetodoPago insert(MetodoPago entidad) throws PersistenciaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MetodoPago update(MetodoPago entidad) throws PersistenciaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(MetodoPago entidad) throws PersistenciaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MetodoPago load(Integer id) throws PersistenciaException {
		// TODO Auto-generated method stub
		return null;
	}

}
