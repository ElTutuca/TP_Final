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

	private List<GenericDAO> daoList;

	// Constructor
	public ModeloTabla( List<GenericDAO> daoList) {
		this.daoList = daoList;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		List<Class> classes = new ArrayList<Class>();
		Object obj = daoList.get(0);
		Field[] fields = (obj.getClass()).getDeclaredFields();
		for (Field field : fields) {
			classes.add(field.getType());
		}
		return classes.get(columnIndex);
	}

	@Override
	public String getColumnName(int columnIndex) {
		GenericModel gm = (GenericModel) daoList.get(0);
		String[] names = gm.getFieldNames();
		return names[columnIndex];
	}

	@Override
	public int getColumnCount() {
		GenericModel gm = (GenericModel) daoList.get(0);
		return gm.getFieldsValues().length;
	}

	@Override
	public int getRowCount() {
		return daoList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object[] r = null;
			GenericModel gm = (GenericModel) daoList.get(rowIndex);
			r = gm.getFieldsValues();
		return r[columnIndex];
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// No se va a usar NUNCA
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
