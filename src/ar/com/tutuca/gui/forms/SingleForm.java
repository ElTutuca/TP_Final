package ar.com.tutuca.gui.forms;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
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
import ar.com.tutuca.dao.MarcaDAO;
import ar.com.tutuca.dao.MetodoPagoDAO;
import ar.com.tutuca.dao.TipoDeComprobanteDAO;
import ar.com.tutuca.extras.GenericDAO;
import ar.com.tutuca.extras.PersistenciaException;
import ar.com.tutuca.extras.Util;
import ar.com.tutuca.gui.tables.ModeloTabla;
import ar.com.tutuca.model.Categoria;
import ar.com.tutuca.model.Marca;
import ar.com.tutuca.model.MetodoPago;
import ar.com.tutuca.model.TipoDeComprobante;

public class SingleForm extends JDialog {
	private JPanel contentPane;
	private JTextField txtMain;
	private static String nombre;
	private static String title;
	private static JFrame superFrame;
	private static boolean alta;
	private static GenericDAO dao;
	private static JTable table;
	private static int model;
	private Categoria selectCat;
	private Marca selectMarca;
	private MetodoPago selectMetodo;
	private static int max;
	public static final int CATEGORIA_MODEL = 1;
	public static final int MARCA_MODEL = 2;
	public static final int METODO_PAGO_MODEL = 3;
	// TODO Implementar METODO_PAGO_MODEL

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SingleForm dialog = new SingleForm(nombre, title, superFrame, alta, dao, table, model, max);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public SingleForm(String nombre, String titulo, JFrame superFrame, boolean alta, GenericDAO dao, JTable table,
			int model, int max) {
		SingleForm.nombre = nombre;
		SingleForm.title = titulo;
		SingleForm.superFrame = superFrame;
		SingleForm.alta = alta;
		SingleForm.dao = dao;
		SingleForm.table = table;
		SingleForm.model = model;
		SingleForm.max = max;

		String accion = alta ? "Crear" : "Modificar";
		JDialog dialog = this;
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle(titulo);
		setBounds(100, 100, 315, 115);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

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

		JLabel lblMain = new JLabel(nombre + ":");

		txtMain = new JTextField();
		txtMain.setColumns(10);

		JButton btnCrear = new JButton(accion);
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = "";
				if (model == CATEGORIA_MODEL) {
					name = "Categoria";
				} else if (model == MARCA_MODEL) {
					name = "Nombre";
				} else if (model == METODO_PAGO_MODEL) {
					name = "Metodo";
				}
				if (alta) {
					try {
						altaModifica(true, 0, name, max);
						table.setModel(new ModeloTabla(dao.list()));
						superFrame.setEnabled(true);
						dialog.dispose();
						return;
					} catch (PersistenciaException e1) {
						e1.printStackTrace();
					}
				} else {
					int id = 0;
					if (model == CATEGORIA_MODEL) {
						id = selectCat.getIdCategoria();
					} else if (model == MARCA_MODEL) {
						id = selectMarca.getIdMarca();
					} else if (model == METODO_PAGO_MODEL) {
						id = selectMetodo.getIdMetodo();
					}
					try {
						altaModifica(false, id, name, max);
						table.setModel(new ModeloTabla(dao.list()));
						superFrame.setEnabled(true);
						dialog.dispose();
						return;
					} catch (PersistenciaException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					table.setModel(new ModeloTabla(dao.list()));
					superFrame.setEnabled(true);
					dialog.dispose();
				} catch (PersistenciaException e1) {
					e1.printStackTrace();
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(
										Alignment.LEADING,
										gl_contentPane.createSequentialGroup().addComponent(lblMain)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(txtMain, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
										.addComponent(btnCrear, GroupLayout.PREFERRED_SIZE, 108,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(btnCancelar)))
						.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblMain).addComponent(
						txtMain, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE).addGroup(gl_contentPane
						.createParallelGroup(Alignment.BASELINE).addComponent(btnCancelar).addComponent(btnCrear))
				.addContainerGap()));
		contentPane.setLayout(gl_contentPane);

		if (!alta) {
			if (table.getSelectedRow() != -1) {
				if (model == CATEGORIA_MODEL) {
					try {
						CategoriaDAO catDAO = new CategoriaDAO();
						List<Categoria> catList = catDAO.list();
						selectCat = catList.get(table.getSelectedRow());
						txtMain.setText(selectCat.getCategoria());
					} catch (PersistenciaException e1) {
						e1.printStackTrace();
					}
				} else if (model == MARCA_MODEL) {
					try {
						MarcaDAO marcaDAO = new MarcaDAO();
						List<Marca> marcaList = marcaDAO.list();
						selectMarca = marcaList.get(table.getSelectedRow());
						txtMain.setText(selectMarca.getNombre());
					} catch (PersistenciaException e1) {
						e1.printStackTrace();
					}
				} else if (model == METODO_PAGO_MODEL) {
					try {
						MetodoPagoDAO pagoDAO = new MetodoPagoDAO();
						List<MetodoPago> pagoList = pagoDAO.list();
						selectMetodo = pagoList.get(table.getSelectedRow());
						txtMain.setText(selectMetodo.getDescripcion());
					} catch (PersistenciaException e1) {
						e1.printStackTrace();
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "Para modificar tiene que elegir una fila de la tabla.",
						"Precaucion", JOptionPane.WARNING_MESSAGE);
				superFrame.setEnabled(true);
				dialog.dispose();
			}
		}
	}

	private void altaModifica(boolean alta, int id, String name, int max) {
		int valid = Util.isValid(txtMain.getText(), 2, max, 3);
		int nombre = Util.checkAll(txtMain, "\"" + name + "\"", valid, this);
		if (nombre == 3) {
			JOptionPane.showMessageDialog(this, "El campo \"" + name + "\" esta vacio.", "Precaucion",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else if (nombre == 2) {
			return;
		}

		try {
			if (alta) {
				if (model == CATEGORIA_MODEL) {
					Categoria entidad = new Categoria(txtMain.getText());
					dao.insert(entidad);
				} else if (model == MARCA_MODEL) {
					Marca entidad = new Marca(txtMain.getText());
					dao.insert(entidad);
				} else if (model == METODO_PAGO_MODEL) {
					MetodoPago entidad = new MetodoPago(txtMain.getText());
					dao.insert(entidad);
				}
			} else {
				if (model == CATEGORIA_MODEL) {
					Categoria entidad = new Categoria(id, txtMain.getText());
					dao.update(entidad);
				} else if (model == MARCA_MODEL) {
					Marca entidad = new Marca(id, txtMain.getText());
					dao.update(entidad);
				} else if (model == METODO_PAGO_MODEL) {
					MetodoPago entidad = new MetodoPago(id, txtMain.getText());
					dao.update(entidad);
				}
			}
		} catch (PersistenciaException e) {
			JOptionPane.showMessageDialog(this, "Error de persistensia.", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return;
		}
	}
}
