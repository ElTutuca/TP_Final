package ar.com.tutuca;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import ar.com.tutuca.extras.GenericDAO;
import ar.com.tutuca.extras.PersistenciaException;

public class Workbench {

	public Object getValueAt(int rowIndex, int columnIndex, GenericDAO<?, ?> dao) {
		List<Field> rows = new ArrayList<Field>();
		try {
			List<?> list = dao.list();
			if (!list.isEmpty()) {
				Object obj = list.get(rowIndex);
				Method[] methods= obj.getClass().getDeclaredMethods();
				Field[] fields = (obj.getClass()).getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					rows.add(field);
				}
			}
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		return rows.get(columnIndex);
	}
}
