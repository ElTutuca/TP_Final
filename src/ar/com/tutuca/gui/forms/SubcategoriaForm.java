package ar.com.tutuca.gui.forms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import ar.com.tutuca.dao.CategoriaDAO;
import ar.com.tutuca.dao.SubcategoriaDAO;
import ar.com.tutuca.extras.GenericDAO;
import ar.com.tutuca.extras.PersistenciaException;
import ar.com.tutuca.extras.Util;
import ar.com.tutuca.gui.tables.ModeloTabla;
import ar.com.tutuca.model.Categoria;
import ar.com.tutuca.model.Subcategoria;

public class SubcategoriaForm extends JDialog {

	private JPanel contentPane;
	private JTextField txtSub;
	private static JFrame superFrame;
	private static boolean alta;
	private static GenericDAO dao;
	private static JTable table;
	private SubcategoriaDAO subcatDAO = new SubcategoriaDAO();
	private CategoriaDAO catDAO = new CategoriaDAO();
	private Categoria selectCat;
	private Subcategoria selectSubcat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SubcategoriaForm dialog = new SubcategoriaForm(superFrame, dao, table, alta);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public SubcategoriaForm(JFrame superFrame, GenericDAO dao, JTable table, boolean alta) {
		SubcategoriaForm.alta = alta;
		SubcategoriaForm.dao = dao;
		SubcategoriaForm.superFrame = superFrame;
		SubcategoriaForm.table = table;

		String accion = alta ? "Crear" : "Modificar";
		JDialog dialog = this;
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Subcategoria");
		setBounds(100, 100, 341, 145);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					table.setModel(new ModeloTabla(dao.list()));
					superFrame.setEnabled(true);
				} catch (PersistenciaException e1) {
					e1.printStackTrace();
				}
			}
		});

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnCrear = new JButton(accion);
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (alta) {
						try {
							altaModifica(alta, 0);
							table.setModel(new ModeloTabla(dao.list()));
							superFrame.setEnabled(true);
							dialog.dispose();
							return;
						} catch (PersistenciaException e1) {
							e1.printStackTrace();
						}
					} else {
						altaModifica(false, selectSubcat.getIdSubcategoria());
						table.setModel(new ModeloTabla(dao.list()));
						superFrame.setEnabled(true);
						dialog.dispose();
						return;
					}
				} catch (PersistenciaException e2) {
					e2.printStackTrace();
				}
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					table.setModel(new ModeloTabla(dao.list()));
					superFrame.setEnabled(true);
					dialog.dispose();
				} catch (PersistenciaException e1) {
					e1.printStackTrace();
				}
			}
		});

		JLabel lblMain = new JLabel("Subcategoria:");

		txtSub = new JTextField();
		txtSub.setColumns(10);

		List<String> categorias = new ArrayList<String>();
		try {
			List<Categoria> catList = catDAO.list();
			for (Categoria categ : catList) {
				categorias.add(categ.getCategoria());
			}
		} catch (PersistenciaException e) {
			JOptionPane.showMessageDialog(this, "Error al recibir las categorias de iva.", "Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		String[] cats = new String[categorias.size()];
		cats = categorias.toArray(cats);
		// Poner "cats" en ..JComboBox<String>(*aca*);
		JComboBox comboBox = new JComboBox(cats);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Categoria> catList;
				try {
					catList = catDAO.list();
					selectCat = (catList.get(comboBox.getSelectedIndex()));
				} catch (PersistenciaException e1) {
					e1.printStackTrace();
				}
			}
		});
		JLabel lblNewLabel = new JLabel("Categoria:");

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane
								.createParallelGroup(Alignment.LEADING).addComponent(lblMain).addComponent(lblNewLabel))
						.addGap(4)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtSub, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
						.addContainerGap(162, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap(89, Short.MAX_VALUE)
						.addComponent(btnCrear, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		gl_contentPane
				.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
						gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblMain)
										.addComponent(txtSub, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblNewLabel).addComponent(comboBox, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(btnCrear)
										.addComponent(btnCancelar))));
		contentPane.setLayout(gl_contentPane);

		if (!alta) {
			if (table.getSelectedRow() != -1) {
				try {
					List<Subcategoria> subcategoriaList = dao.list();
					selectSubcat = subcategoriaList.get(table.getSelectedRow());
					txtSub.setText(selectSubcat.getSubcategoria());
					comboBox.setSelectedItem(selectSubcat.getCategoria().getCategoria());
				} catch (PersistenciaException e1) {
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(this, "Para modificar tiene que elegir una fila de la tabla.",
						"Precaucion", JOptionPane.WARNING_MESSAGE);
				superFrame.setEnabled(true);
				dialog.dispose();
			}
		}
	}

	private void altaModifica(boolean alta, int id) {
		int valid = Util.isValid(txtSub.getText(), 3, 30, 3);
		int nombre = Util.checkAll(txtSub, "\"Subcategoria\"", valid, this);
		if (nombre == 3) {
			JOptionPane.showMessageDialog(this, "El campo \"Nombre\" esta vacio.", "Precaucion",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else if (nombre == 2) {
			return;
		}

		if (selectCat == null) {
			JOptionPane.showMessageDialog(this, "Tiene que seleccionar una Categoria.", "Precaucion",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			if (alta) {
				Subcategoria entidad = new Subcategoria(selectCat, txtSub.getText());
				dao.insert(entidad);
			} else {
				Subcategoria entidad = new Subcategoria(id, selectCat, txtSub.getText());
				dao.update(entidad);
			}
		} catch (PersistenciaException e) {
			JOptionPane.showMessageDialog(this, "Error de persistensia.", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return;
		}
	}
}
