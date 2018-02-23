package ar.com.tutuca.dao.extras;

import java.util.List;

import ar.com.tutuca.dao.extras.Exceptions.PersistenciaException;

public interface GenericDAO<T, ID> {

	public List<T> list() throws PersistenciaException;

	public T insert(T entidad) throws PersistenciaException;

	public T update(T entidad) throws PersistenciaException;

	public void delete(T entidad) throws PersistenciaException;

	public T load(ID id) throws PersistenciaException;

}
