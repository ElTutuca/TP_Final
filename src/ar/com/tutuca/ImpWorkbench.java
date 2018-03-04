package ar.com.tutuca;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ar.com.tutuca.dao.CategoriaDAO;
import ar.com.tutuca.extras.GenericDAO;
import ar.com.tutuca.extras.GenericModel;
import ar.com.tutuca.extras.PersistenciaException;
import ar.com.tutuca.model.Categoria;
import ar.com.tutuca.model.CategoriaIva;
import ar.com.tutuca.model.Mayorista;

public class ImpWorkbench {

	public static void main(String[] args) {

		Categoria c = new Categoria(1, "Hola");
		CategoriaDAO dao = new CategoriaDAO();

		String str1 = "4468486-486";
		
		System.out.println(str1+": "+str1.matches("[\\d-?(?)?:?=?.?,?-?/?]+"));
		
		String str = "Hola: Manola-";
		System.out.println(str+": "+str.matches("[a-zA-Z\\s-?(?)?:?=?.?,?-?/?]+"));
		
		
		
		
		
		
		
		boolean estaSeguro = false;
		int valid = isValid(txtNombre.getText(), 3, 30, 3);
		int nombre = checkAll(txtNombre, "\"Nombre\"", valid);
		if (nombre == 3) {
			JOptionPane.showMessageDialog(this, "El campo \"Nombre\" esta vacio.", "Precaucion",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else if (nombre == 2) {
			return;
		}

		int catIva = selectCatIva == null ? 2 : 1;
		if (catIva != 1) {
			JOptionPane.showMessageDialog(this, "Tiene que seleccionar una Categoria Iva.", "Precaucion",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		int nmbreDeFantasia = checkAll(txtNombreDeFantasia, "\"Nombre de Fantasia\"",
				isValid(txtNombreDeFantasia.getText(), 3, 45, 3));
		if (nmbreDeFantasia == 2) {
			return;
		} else if (nmbreDeFantasia == 3) {
			estaSeguro = true;
		}

		int direccion = checkAll(txtDireccion, "\"Direccion\"", isValid(txtDireccion.getText(), 6, 60, 3));
		if (direccion == 2) {
			return;
		} else if (direccion == 3) {
			estaSeguro = true;
		}

		int telefono = checkAll(txtTelefono, "\"Telefono\"", isValid(txtTelefono.getText(), 9, 40, 3));
		if (telefono == 2) {
			return;
		} else if (telefono == 3) {
			estaSeguro = true;
		}

		int nmroDeIngresos = checkAll(txtNmroDeIngresos, "\"Nmro de Ingresos Brutos\"",
				isValid(txtNmroDeIngresos.getText(), 9, 11, 3));
		if (nmroDeIngresos == 2) {
			return;
		} else if (nmroDeIngresos == 3) {
			estaSeguro = true;
		}

		int cuit = checkAll(txtCuit, "\"Cuit\"", isValid(txtCuit.getText(), 9, 11, 2));
		if (cuit == 2) {
			return;
		} else if (cuit == 3) {
			estaSeguro = true;
		}

		if (estaSeguro == true) {
			int response = JOptionPane.showConfirmDialog(null, "Faltan algunos campos. Esta seguro?", "Confirme",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.YES_OPTION) {
				try {
					Mayorista entidad = new Mayorista(txtNombre.getText(), isNull(txtNombreDeFantasia),
							isNull(txtDireccion), isNull(txtTelefono), isNull(txtNmroDeIngresos), isNull(txtCuit),
							selectCatIva);
					mayDAO.insert(entidad);
					return;
				} catch (PersistenciaException e) {
					JOptionPane.showMessageDialog(this, "Error de persistensia.", "Error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
					return;
				}
			} else {
				return;
			}
		} else {
			try {
				Mayorista entidad = new Mayorista(txtNombre.getText(), isNull(txtNombreDeFantasia),
						isNull(txtDireccion), isNull(txtTelefono), isNull(txtNmroDeIngresos), isNull(txtCuit),
						selectCatIva);
				mayDAO.insert(entidad);
				return;
			} catch (PersistenciaException e) {
				JOptionPane.showMessageDialog(this, "Error de persistensia.", "Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				return;
			}
		}
		
		
		
		
		
		
		
		
		

		boolean estaSeguro = false;
		int valid = isValid(txtNombre.getText(), 3, 30, 3);
		int nombre = checkAll(txtNombre, "\"Nombre\"", valid);
		if (nombre == 3) {
			JOptionPane.showMessageDialog(this, "El campo \"Nombre\" esta vacio.", "Precaucion",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else if (nombre == 2) {
			return;
		}

		int catIva = selectCatIva == null ? 2 : 1;
		if (catIva != 1) {
			JOptionPane.showMessageDialog(this, "Tiene que seleccionar una Categoria Iva.", "Precaucion",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		int nmbreDeFantasia = checkAll(txtNombreDeFantasia, "\"Nombre de Fantasia\"",
				isValid(txtNombreDeFantasia.getText(), 3, 45, 3));
		if (nmbreDeFantasia == 2) {
			return;
		} else if (nmbreDeFantasia == 3) {
			estaSeguro = true;
		}

		int direccion = checkAll(txtDireccion, "\"Direccion\"", isValid(txtDireccion.getText(), 6, 60, 3));
		if (direccion == 2) {
			return;
		} else if (direccion == 3) {
			estaSeguro = true;
		}

		int telefono = checkAll(txtTelefono, "\"Telefono\"", isValid(txtTelefono.getText(), 9, 40, 3));
		if (telefono == 2) {
			return;
		} else if (telefono == 3) {
			estaSeguro = true;
		}

		int nmroDeIngresos = checkAll(txtNmroDeIngresos, "\"Nmro de Ingresos Brutos\"",
				isValid(txtNmroDeIngresos.getText(), 9, 11, 3));
		if (nmroDeIngresos == 2) {
			return;
		} else if (nmroDeIngresos == 3) {
			estaSeguro = true;
		}

		int cuit = checkAll(txtCuit, "\"Cuit\"", isValid(txtCuit.getText(), 9, 11, 2));
		if (cuit == 2) {
			return;
		} else if (cuit == 3) {
			estaSeguro = true;
		}

		if (estaSeguro == true) {
			int response = JOptionPane.showConfirmDialog(null, "Faltan algunos campos. Esta seguro?", "Confirme",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.YES_OPTION) {
				try {
					Mayorista entidad = new Mayorista(txtNombre.getText(), isNull(txtNombreDeFantasia),
							isNull(txtDireccion), isNull(txtTelefono), isNull(txtNmroDeIngresos), isNull(txtCuit),
							selectCatIva);
					mayDAO.update(entidad);
					return;
				} catch (PersistenciaException e) {
					JOptionPane.showMessageDialog(this, "Error de persistensia.", "Error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
					return;
				}
			} else {
				return;
			}
		} else {
			try {
				Mayorista entidad = new Mayorista(txtNombre.getText(), isNull(txtNombreDeFantasia),
						isNull(txtDireccion), isNull(txtTelefono), isNull(txtNmroDeIngresos), isNull(txtCuit),
						selectCatIva);
				mayDAO.update(entidad);
				return;
			} catch (PersistenciaException e) {
				JOptionPane.showMessageDialog(this, "Error de persistensia.", "Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				return;
			}
		}
		
		
	}
}
