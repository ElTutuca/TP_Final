package ar.com.tutuca.gui.tables;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import ar.com.tutuca.extras.GenericDAO;
import ar.com.tutuca.extras.GenericModel;
import ar.com.tutuca.extras.PersistenciaException;

public class ModeloTabla implements TableModel {

	private GenericDAO dao;

	// Constructor
	public ModeloTabla(GenericDAO dao) {
		this.dao = dao;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		List<Class> classes = new ArrayList<Class>();
		try {
			List list = dao.list();
			if (!list.isEmpty()) {
				Object obj = list.get(0);
				Field[] fields = (obj.getClass()).getDeclaredFields();
				for (Field field : fields) {
					classes.add(field.getType());
				}
			}
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		return classes.get(columnIndex);
	}

	@Override
	public String getColumnName(int columnIndex) {
		List<String> fieldsNames = new ArrayList<String>();
		try {
			List list = dao.list();
			if (!list.isEmpty()) {
				Object obj = list.get(0);
				Field[] fields = (obj.getClass()).getDeclaredFields();
				for (Field field : fields) {
					fieldsNames.add(field.getName());
				}
			}
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		return fieldsNames.get(columnIndex);
	}

	@Override
	public int getColumnCount() {
		int count = 0;
		try {
			List list = dao.list();
			if (!list.isEmpty()) {
				Object obj = list.get(0);
				Field[] fields = (obj.getClass()).getDeclaredFields();
				count = fields.length;
			}
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int getRowCount() {
		int count = 0;
		try {
			count = dao.list().size();
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		List list;
		Object[] r = null;
		try {
			list = dao.list();
			GenericModel gm = (GenericModel) list.get(rowIndex);
			r = gm.getFieldsValues();
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		return r[columnIndex];
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Hacer esta cagada :(
		try {
			List list = dao.list();
			if (!list.isEmpty()) {
				Object obj = list.get(rowIndex);
				Field[] fields = (obj.getClass()).getDeclaredFields();
			}
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
	}
	
	@Override
	public void addTableModelListener(TableModelListener l) {
	}

}
