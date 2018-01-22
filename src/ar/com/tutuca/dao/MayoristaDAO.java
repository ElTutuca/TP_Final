package ar.com.tutuca.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.com.tutuca.model.Mayorista;

public class MayoristaDAO implements GenericDAO<Mayorista, Integer> {

	@Override
	public List<Mayorista> list() throws PersistenciaException {
		List<Mayorista> r=new ArrayList<Mayorista>();
		try {
			ResultSet rs=Util.createStatement().executeQuery("SELECT * FROM Mayorista ORDER BY Nombre");
			while(rs.next()) {
				r.add(new Mayorista(rs.getInt("idMayorista"),rs.getString("Nombre")));
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e.getMessage(),e);
		}
		return r;
	}

	@Override
	public Mayorista insert(Mayorista entidad) throws PersistenciaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mayorista update(Mayorista entidad) throws PersistenciaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Mayorista entidad) throws PersistenciaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Mayorista load(Integer id) throws PersistenciaException {
		// TODO Auto-generated method stub
		return null;
	}

}
