package ar.com.tutuca.gui.forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import ar.com.tutuca.dao.CategoriaIvaDAO;
import ar.com.tutuca.extras.GenericDAO;
import ar.com.tutuca.extras.PersistenciaException;
import ar.com.tutuca.extras.Util;
import ar.com.tutuca.gui.tables.ModeloTabla;
import ar.com.tutuca.model.CategoriaIva;

public class CategoriaIvaForm extends JDialog {
	private static boolean isAlta;
	private static JTable superTable;
	private JTextField txtNombre;
	private JTextField txtTasa;
	private JCheckBox checkBox;
	private CategoriaIva selectCat;
	private CategoriaIvaDAO catDAO = new CategoriaIvaDAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CategoriaIvaForm dialog = new CategoriaIvaForm(isAlta, superTable);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CategoriaIvaForm(boolean isAlta, JTable superTable) {
		JDialog dialog = this;
		CategoriaIvaForm.superTable = superTable;
		CategoriaIvaForm.isAlta = isAlta;
		String accion = isAlta ? "Crear" : "Modificar";

		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Categoria Iva");

		setBounds(100, 100, 246, 166);

		JLabel lblNombre = new JLabel("Nombre:");
		JLabel lblTasa = new JLabel("Tasa:");
		JLabel lblDiscrimina = new JLabel("Discrimina:");

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialog.dispose();
			}
		});
		JButton btnAccion = new JButton(accion);
		btnAccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = isAlta ? 0 : selectCat.getIdCategoriasIVA();
				altaModifica(isAlta, id);
				
				GenericDAO dao = (GenericDAO) catDAO;
				try {
					superTable.setModel(new ModeloTabla(dao.list()));
				} catch (PersistenciaException e1) {
					e1.printStackTrace();
				}
			}
		});

		txtNombre = new JTextField();
		txtNombre.setColumns(10);

		txtTasa = new JTextField();
		txtTasa.setColumns(10);

		checkBox = new JCheckBox("");

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup().addComponent(lblNombre).addGap(3)
										.addComponent(txtNombre, 0, 0, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup().addComponent(lblTasa).addGap(4)
										.addComponent(txtTasa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup().addComponent(lblDiscrimina)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(checkBox)))
						.addGroup(Alignment.TRAILING,
								groupLayout.createSequentialGroup().addComponent(btnAccion)
										.addPreferredGap(ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
										.addComponent(btnCancelar)))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNombre).addComponent(
						txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblTasa).addComponent(
						txtTasa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(8)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(checkBox)
						.addComponent(lblDiscrimina))
				.addPreferredGap(ComponentPlacement.RELATED, 145, Short.MAX_VALUE).addGroup(groupLayout
						.createParallelGroup(Alignment.BASELINE).addComponent(btnCancelar).addComponent(btnAccion))
				.addContainerGap()));
		getContentPane().setLayout(groupLayout);

		if (!isAlta) {
			if (superTable.getSelectedRow() != -1) {
				try {
					List<CategoriaIva> listCatIva = catDAO.list();
					selectCat = listCatIva.get(superTable.getSelectedRow());
					txtNombre.setText(selectCat.getNombre());
					txtTasa.setText(selectCat.getTasa().toString());
					checkBox.setSelected(selectCat.isDiscrimina());
				} catch (PersistenciaException e1) {
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(this, "Para modificar tiene que elegir una fila de la tabla.",
						"Precaucion", JOptionPane.WARNING_MESSAGE);
				this.dispose();
			}
		}
	}

	private void altaModifica(boolean isAlta, int id) {
		// Control Nombre
		int nombre = Util.checkAll(txtNombre, "\"Nombre\"", Util.isValid(txtNombre.getText(), 2, 30, 3), this);
		if (nombre == 3) {
			JOptionPane.showMessageDialog(this, "El campo \"Nombre\" esta vacio.", "Precaucion",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else if (nombre == 2) {
			return;
		}

		// Contol de Tasa
		int tasa = Util.checkAll(txtTasa, "\"Tasa\"", Util.isValid(txtTasa.getText(), 1, 5, 2), this);
		if (tasa == 3) {
			JOptionPane.showMessageDialog(this, "El campo \"Tasa\" esta vacio.", "Precaucion",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else if (tasa == 2) {
			return;
		}

		// Insert / Update
		if (isAlta) {
			try {
				CategoriaIva entidad = new CategoriaIva(txtNombre.getText(), new BigDecimal(txtTasa.getText()),
						checkBox.isSelected());
				catDAO.insert(entidad);
			} catch (PersistenciaException e) {
				e.printStackTrace();
			}
		} else {
			try {
				CategoriaIva entidad = new CategoriaIva(selectCat.getIdCategoriasIVA(), txtNombre.getText(),
						new BigDecimal(txtTasa.getText()), checkBox.isSelected());
				catDAO.update(entidad);
			} catch (PersistenciaException e) {
				e.printStackTrace();
			}
		}
		this.dispose();
	}
}
