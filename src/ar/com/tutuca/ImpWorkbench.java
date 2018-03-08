package ar.com.tutuca;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelListener;

import ar.com.tutuca.dao.CategoriaDAO;
import ar.com.tutuca.extras.GenericDAO;
import ar.com.tutuca.extras.GenericModel;
import ar.com.tutuca.extras.PersistenciaException;
import ar.com.tutuca.model.Categoria;
import ar.com.tutuca.model.CategoriaIva;
import ar.com.tutuca.model.Mayorista;

public class ImpWorkbench {

	public static void main(String[] args) {


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
			List<String> fieldsNames = new ArrayList<String>();
			Object obj = daoList.get(0);
			Field[] fields = (obj.getClass()).getDeclaredFields();
			for (Field field : fields) {
				fieldsNames.add(field.getName());
			}
			return fieldsNames.get(columnIndex);
		}

		@Override
		public int getColumnCount() {
			Object obj = daoList.get(0);
			Field[] fields = (obj.getClass()).getDeclaredFields();
			return fields.length;
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
	}
}
