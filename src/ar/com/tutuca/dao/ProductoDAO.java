package ar.com.tutuca.dao;

import java.util.List;
import ar.com.tutuca.model.Producto;;

public class ProductoDAO implements GenericDAO<Producto, Integer> {

	@Override
	public List<Producto> list() throws PersistenciaException {

		return null;
	}

	@Override
	public Producto insert(Producto entidad) throws PersistenciaException {
		return null;
	}

	@Override
	public Producto update(Producto entidad) throws PersistenciaException {
		return null;
	}

	@Override
	public void delete(Producto entidad) throws PersistenciaException {
		
	}

	@Override
	public Producto load(Integer id) throws PersistenciaException {
		return null;
	}

}
