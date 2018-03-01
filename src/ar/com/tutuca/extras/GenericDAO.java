package ar.com.tutuca.extras;

import java.util.List;

public interface GenericDAO<T, ID> {

	public List<T> list() throws PersistenciaException;

	public T insert(T entidad) throws PersistenciaException;

	public T update(T entidad) throws PersistenciaException;

	public void delete(T entidad) throws PersistenciaException;

	public T load(ID id) throws PersistenciaException;

}
