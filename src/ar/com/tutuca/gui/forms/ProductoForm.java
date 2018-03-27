package ar.com.tutuca.gui.forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ar.com.tutuca.dao.ArchivoDAO;
import ar.com.tutuca.dao.CategoriaIvaDAO;
import ar.com.tutuca.dao.MarcaDAO;
import ar.com.tutuca.dao.MayoristaDAO;
import ar.com.tutuca.dao.ProdArchivosDAO;
import ar.com.tutuca.dao.ProductoDAO;
import ar.com.tutuca.dao.SubcategoriaDAO;
import ar.com.tutuca.extras.PersistenciaException;
import ar.com.tutuca.model.Marca;
import ar.com.tutuca.model.Mayorista;
import ar.com.tutuca.model.Producto;
import ar.com.tutuca.model.Subcategoria;

public class ProductoForm extends JDialog {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtPrecio;
	private JTextField txtNombre;
	private JTextField txtUbicacion;
	private JTextField txtStockMax;
	private JTextField txtStockMin;
	private JTextField txtStockIdeal;
	private JTextField txtStock;
	private JTextField txtDescuento;
	private JTextField txtPorcentajeIva;
	private JTable subTable;
	private JTable mayTable;
	private JComboBox<String> comboMarca;
	private String toggleButton = "Eliminado";
	private SubcategoriaDAO subDAO = new SubcategoriaDAO();
	private MayoristaDAO mayDAO = new MayoristaDAO(new CategoriaIvaDAO());
	private ArchivoDAO archDAO = new ArchivoDAO();
	private ProdArchivosDAO paDAO = new ProdArchivosDAO(archDAO);
	private ProductoDAO prodDAO = new ProductoDAO(mayDAO, subDAO, paDAO);
	private MarcaDAO marcaDAO = new MarcaDAO();
	private Producto selectProducto;
	private static boolean isAlta;
	private static JTable superTable;

	public static void main(String[] args) {
		try {
			ProductoForm dialog = new ProductoForm(isAlta, superTable);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public ProductoForm(boolean isAlta, JTable superTable) {
		ProductoForm.isAlta = isAlta;
		ProductoForm.superTable = superTable;

		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Producto");
		setBounds(100, 100, 601, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblCodigo = new JLabel("Codigo:");

		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);

		JLabel lblPrecio = new JLabel("Precio:");

		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre:");

		txtNombre = new JTextField();
		txtNombre.setColumns(10);

		JLabel lblUbicacion = new JLabel("Ubicacion:");

		txtUbicacion = new JTextField();
		txtUbicacion.setColumns(10);

		JLabel lblStockMax = new JLabel("Stock Max:");

		txtStockMax = new JTextField();
		txtStockMax.setColumns(10);

		JLabel lblStockMin = new JLabel("Stock Min:");

		txtStockMin = new JTextField();
		txtStockMin.setColumns(10);

		JLabel lblStockIdeal = new JLabel("Stock Ideal:");

		txtStockIdeal = new JTextField();
		txtStockIdeal.setColumns(10);

		JLabel lblStock = new JLabel("Stock:");

		txtStock = new JTextField();
		txtStock.setColumns(10);

		JLabel lblDescuento = new JLabel("Descuento:");

		txtDescuento = new JTextField();
		txtDescuento.setColumns(10);

		JLabel lblPorcentajeIva = new JLabel("Porcentaje Iva:");

		txtPorcentajeIva = new JTextField();
		txtPorcentajeIva.setColumns(10);

		JLabel lblMarca = new JLabel("Marca");

		List<String> marcas = new ArrayList<String>();
		try {
			for (Marca mar : marcaDAO.list()) {
				marcas.add(mar.getNombre());
			}
		} catch (PersistenciaException e2) {
			JOptionPane.showMessageDialog(this, "Error al recibir las Marcas.", "Error",
					JOptionPane.ERROR_MESSAGE);
			e2.printStackTrace();
		}
		String[] marcaStrings = (String[]) marcas.toArray();
		comboMarca = new JComboBox<String>(marcaStrings);

		JToggleButton tglbtnEliminado = new JToggleButton(toggleButton);
		tglbtnEliminado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tglbtnEliminado.isSelected()) {
					toggleButton = "Eliminado";
				} else {
					toggleButton = "No Eliminado";
				}
				tglbtnEliminado.setText(toggleButton);
			}
		});

		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < subTable.getRowCount(); i++) {
					boolean checked = Boolean.valueOf(subTable.getValueAt(i, 0).toString());
					String col = subTable.getValueAt(i, 1).toString();
					System.out.println(checked);
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane();

		JScrollPane scrollPane_1 = new JScrollPane();

		JButton btnImagenes = new JButton("Imagenes");
		btnImagenes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProductoImagenes prodI = null;
				if (isAlta) {
					prodI = new ProductoImagenes(selectProducto.getIdProductos());
				} else {
					prodI = new ProductoImagenes(-1);
				}
				prodI.setVisible(true);
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
						.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false).addGroup(gl_contentPane
								.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(lblCodigo)
										.addComponent(lblPrecio).addComponent(lblNombre))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(txtNombre).addComponent(txtCodigo)
										.addComponent(txtPrecio, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)))
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblUbicacion).addGap(4)
										.addComponent(txtUbicacion)))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblMarca)
										.addGroup(gl_contentPane
												.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup().addGap(133)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																.addComponent(lblStock, GroupLayout.PREFERRED_SIZE, 73,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(lblPorcentajeIva)
																.addComponent(lblStockMin, GroupLayout.PREFERRED_SIZE,
																		73, GroupLayout.PREFERRED_SIZE))
														.addGap(3)
														.addGroup(gl_contentPane
																.createParallelGroup(Alignment.LEADING, false)
																.addComponent(txtPorcentajeIva, 0, 0, Short.MAX_VALUE)
																.addComponent(txtStock, GroupLayout.DEFAULT_SIZE, 70,
																		Short.MAX_VALUE)
																.addComponent(txtStockMin, GroupLayout.PREFERRED_SIZE,
																		70, GroupLayout.PREFERRED_SIZE)))
												.addGroup(gl_contentPane.createSequentialGroup()
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(comboMarca, GroupLayout.PREFERRED_SIZE, 217,
																GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblStockMax)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(txtStockMax, GroupLayout.PREFERRED_SIZE, 62,
														GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(lblDescuento).addComponent(lblStockIdeal))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(txtDescuento, 0, 0, Short.MAX_VALUE)
														.addComponent(txtStockIdeal, GroupLayout.PREFERRED_SIZE, 62,
																GroupLayout.PREFERRED_SIZE))))))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(btnCrear)
										.addPreferredGap(ComponentPlacement.RELATED, 257, Short.MAX_VALUE)
										.addComponent(btnImagenes).addGap(18).addComponent(tglbtnEliminado))
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 194,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(scrollPane_1,
												GroupLayout.PREFERRED_SIZE, 347, GroupLayout.PREFERRED_SIZE))))
				.addGap(186)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap().addGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblCodigo)
										.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblPrecio)
										.addComponent(txtPrecio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addComponent(lblNombre)
										.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblUbicacion).addComponent(txtUbicacion,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGap(12))
						.addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
								.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(txtStockMax, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblStockMax))
										.addGap(14)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
														.addComponent(txtStockIdeal, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblStockIdeal))
												.addGroup(gl_contentPane.createSequentialGroup().addGap(31)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
																.addComponent(txtDescuento, GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(lblDescuento)))))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblStockMin).addComponent(txtStockMin,
														GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblStock)
												.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(txtStock, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
																.addComponent(txtPorcentajeIva,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(lblPorcentajeIva))))))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblMarca)
										.addComponent(comboMarca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(
								gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 161,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 161,
												GroupLayout.PREFERRED_SIZE))
						.addGap(73).addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnCrear).addComponent(btnImagenes).addComponent(tglbtnEliminado))));

		try {
			if (!isAlta) {
				if (superTable.getSelectedRow() != -1) {
					List<Producto> prodList = prodDAO.list();
					selectProducto = prodList.get(superTable.getSelectedRow());
					txtCodigo.setText(selectProducto.getCodigo());
					txtPrecio.setText(Double.toString(selectProducto.getPrecio()));
					txtNombre.setText(selectProducto.getNombre());
					txtUbicacion.setText(selectProducto.getUbicacion());
					txtStockMax.setText(Integer.toString(selectProducto.getStockMaximo()));
					txtStockMin.setText(Integer.toString(selectProducto.getStockMinimo()));
					txtStockIdeal.setText(Integer.toString(selectProducto.getStockIdeal()));
					txtStock.setText(Integer.toString(selectProducto.getStock()));
					txtDescuento.setText(selectProducto.getDescuento().toString());
					txtPorcentajeIva.setText(selectProducto.getPorcentajeIva().toString());

				} else {
					JOptionPane.showMessageDialog(this, "Para modificar tiene que elegir una fila de la tabla.",
							"Precaucion", JOptionPane.WARNING_MESSAGE);
					this.dispose();
				}
			}
		} catch (PersistenciaException e1) {
			e1.printStackTrace();
		}
		// Tabla de Mayoritas
		mayTable = new JTable();
		scrollPane_1.setViewportView(mayTable);
		mayTable.setFillsViewportHeight(true);
		createTableMayorista();
		mayTable.getColumnModel().getColumn(0).setPreferredWidth(10);
		mayTable.getColumnModel().getColumn(1).setPreferredWidth(110);
		mayTable.getColumnModel().getColumn(2).setPreferredWidth(140);

		// Tabla de Subcategoria
		subTable = new JTable();
		subTable.setFillsViewportHeight(true);
		scrollPane.setViewportView(subTable);
		createTableSub();
		subTable.getColumnModel().getColumn(0).setPreferredWidth(20);
		subTable.getColumnModel().getColumn(1).setPreferredWidth(130);

		contentPane.setLayout(gl_contentPane);

	}

	private void createTableSub() {

		// Modelo de tabla
		DefaultTableModel model = new DefaultTableModel() {
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return Boolean.class;
				case 1:
					return String.class;
				default:
					return String.class;
				}
			}

			public boolean isCellEditable(int row, int column) {
				return column == 0;
			}
		};
		model.addColumn("");
		model.addColumn("Subcategoria");

		try {
			for (Subcategoria sub : subDAO.list()) {
				model.addRow(new Object[] { false, sub.getSubcategoria() });
			}
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		subTable.setModel(model);
	}

	private void createTableMayorista() {

		// Modelo de tabla
		DefaultTableModel model = new DefaultTableModel() {
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return Boolean.class;
				case 1:
					return String.class;
				default:
					return String.class;
				}
			}

			public boolean isCellEditable(int row, int column) {
				return column == 0;
			}
		};
		model.addColumn("");
		model.addColumn("Nombre");
		model.addColumn("Mayorista");

		try {
			for (Mayorista may : mayDAO.list()) {
				model.addRow(new Object[] { false, may.getNombre(), may.getNombreDeFantasia() });
			}
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		mayTable.setModel(model);
	}
}
