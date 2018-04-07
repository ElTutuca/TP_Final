package ar.com.tutuca.gui.forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
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
import ar.com.tutuca.extras.GenericDAO;
import ar.com.tutuca.extras.PersistenciaException;
import ar.com.tutuca.extras.Util;
import ar.com.tutuca.gui.tables.ModeloTabla;
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
	private String toggleButton = "No Eliminado";
	private SubcategoriaDAO subDAO = new SubcategoriaDAO();
	private MayoristaDAO mayDAO = new MayoristaDAO(new CategoriaIvaDAO());
	private ArchivoDAO archDAO = new ArchivoDAO();
	private ProdArchivosDAO paDAO = new ProdArchivosDAO(archDAO);
	private ProductoDAO prodDAO = new ProductoDAO(mayDAO, subDAO, paDAO);
	private MarcaDAO marcaDAO = new MarcaDAO();
	private Producto selectProducto;
	private ProductoImagenes prodI;
	private JToggleButton tglbtnEliminado;
	private Marca selectMarca;
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
		JDialog dialog = this;
		ProductoForm.isAlta = isAlta;
		ProductoForm.superTable = superTable;
		String accion = isAlta ? "Crear" : "Modificar";

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
			JOptionPane.showMessageDialog(this, "Error al recibir las Marcas.", "Error", JOptionPane.ERROR_MESSAGE);
			e2.printStackTrace();
		}
		String[] marcaStrings = new String[marcas.size()];
		marcaStrings = marcas.toArray(marcaStrings);

		// Poner "marcaStrings" en ...JComboBox<String>(*ACA*)
		comboMarca = new JComboBox<String>(marcaStrings);
		comboMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					selectMarca = marcaDAO.list().get(comboMarca.getSelectedIndex());
				} catch (PersistenciaException e) {
					e.printStackTrace();
				}
			}
		});

		tglbtnEliminado = new JToggleButton(toggleButton);
		tglbtnEliminado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				toggleButton = tglbtnEliminado.isSelected() ? "Eliminado" : "No Eliminado";
				tglbtnEliminado.setText(toggleButton);
			}
		});

		JButton btnCrear = new JButton(accion);
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accion(dialog);
			}
		});

		JScrollPane scrollPane = new JScrollPane();

		JScrollPane scrollPane_1 = new JScrollPane();

		JButton btnImagenes = new JButton("Imagenes");
		btnImagenes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Hacer Imagenes
				int response = JOptionPane.showConfirmDialog(null,
						"Las imagenes deben ser lo ultimo en ser agregado. Esta seguro?", "Confirme",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.NO_OPTION) {
					return;
				}

				/**
				 * Las imagens tienen que ir al final de todo! Cuando se quiera poner imagenes,
				 * se va a tener que insertar el producto para tener el id y pasarlo como
				 * parametro.
				 */
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
					comboMarca.setSelectedItem(selectProducto.getMarca().getNombre());
					prodI = new ProductoImagenes(selectProducto.getIdProductos(), isAlta);

					boolean elim = selectProducto.isEliminado();
					tglbtnEliminado.setSelected(elim);
					toggleButton = elim ? "Eliminado" : "No Eliminado";
					tglbtnEliminado.setText(toggleButton);
				} else {
					JOptionPane.showMessageDialog(this, "Para modificar tiene que elegir una fila de la tabla.",
							"Precaucion", JOptionPane.WARNING_MESSAGE);
					this.dispose();
				}
			} else {
				tglbtnEliminado.setEnabled(false);
				prodI = new ProductoImagenes(-1, isAlta);
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
			if (isAlta) {
				for (Subcategoria sub : subDAO.list()) {
					model.addRow(new Object[] { false, sub.getSubcategoria() });
				}
			} else {
				List<Subcategoria> subList = selectProducto.getSubcategoria();
				for (Subcategoria sub : subDAO.list()) {
					boolean boolState = false;
					for (Subcategoria subcategoria : subList) {
						if (sub.equals(subcategoria)) {
							boolState = true;
						}
					}
					model.addRow(new Object[] { boolState, sub.getSubcategoria() });
				}
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
			if (isAlta) {
				for (Mayorista may : mayDAO.list()) {
					model.addRow(new Object[] { false, may.getNombre(), may.getNombreDeFantasia() });
				}
			} else {
				List<Mayorista> mayList = selectProducto.getMayoristas();
				for (Mayorista mayorista1 : mayDAO.list()) {
					boolean boolState = false;
					for (Mayorista mayorista2 : mayList) {
						if (mayorista1.equals(mayorista2)) {
							boolState = true;
						}
					}
					model.addRow(new Object[] { boolState, mayorista1.getNombre(), mayorista1.getNombreDeFantasia() });
				}
			}
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		mayTable.setModel(model);
	}

	private void altaModifica(boolean alta, int id) {
		// TODO Hacer boton crear

		/*
		 * Falta hacer los checks
		 */

		// Control de Codigo
		boolean estaSeguro = false;
		int codigo = Util.checkAll(txtCodigo, "\"Codigo\"", Util.isValid(txtCodigo.getText(), 2, 8, 3), this);
		if (codigo == 3) {
			JOptionPane.showMessageDialog(this, "El campo \"Codigo\" esta vacio.", "Precaucion",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else if (codigo == 2) {
			return;
		}

		// Control de Precio
		int precio = Util.checkAll(txtPrecio, "\"Precio\"", Util.isValid(txtPrecio.getText(), 1, 9, 2), this);
		if (precio == 3) {
			JOptionPane.showMessageDialog(this, "El campo \"Precio\" esta vacio.", "Precaucion",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else if (precio == 2) {
			return;
		}

		// Control de Nombre
		int nombre = Util.checkAll(txtNombre, "\"Nombre\"", Util.isValid(txtNombre.getText(), 1, 45, 3), this);
		if (nombre == 3) {
			JOptionPane.showMessageDialog(this, "El campo \"Nombre\" esta vacio.", "Precaucion",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else if (nombre == 2) {
			return;
		}

		// Control de Stock
		int stock = Util.checkAll(txtStock, "\"Stock\"", Util.isValid(txtStock.getText(), 1, 7, 2), this);
		if (stock == 3) {
			JOptionPane.showMessageDialog(this, "El campo \"Stock\" esta vacio.", "Precaucion",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else if (stock == 2) {
			return;
		}

		// Control de Porcentaje Iva
		int porcentIva = Util.checkAll(txtPorcentajeIva, "\"Porcentaje Iva\"",
				Util.isValid(txtPorcentajeIva.getText(), 1, 4, 2), this);
		if (porcentIva == 3) {
			JOptionPane.showMessageDialog(this, "El campo \"Porcentaje Iva\" esta vacio.", "Precaucion",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else if (porcentIva == 2) {
			return;
		}

		// Control de SelectMarca
		if (selectMarca == null) {
			JOptionPane.showMessageDialog(this, "Tiene que seleccionar una Categoria Iva.", "Precaucion",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		// Control de Ubicacion
		int ubicacion = Util.checkAll(txtUbicacion, "\"Ubicacion\"", Util.isValid(txtUbicacion.getText(), 1, 20, 3),
				this);
		if (ubicacion == 2) {
			return;
		} else if (ubicacion == 3) {
			estaSeguro = true;
		}
		
		// Control de StockMax
		int stockMax = Util.checkAll(txtStockMax, "\"Stock Max\"", Util.isValid(txtStockMax.getText(), 1, 7, 2), this);
		if (stockMax == 2) {
			return;
		} else if (stockMax == 3) {
			estaSeguro = true;
		}

		// Control de StockMin
		int stockMin = Util.checkAll(txtStockMin, "\"Stock Min\"", Util.isValid(txtStockMin.getText(), 1, 7, 2), this);
		if (stockMin == 2) {
			return;
		} else if (stockMin == 3) {
			estaSeguro = true;
		}
		
		// Control de StockIdeal
		int stockIdeal = Util.checkAll(txtStockIdeal, "\"Stock Ideal\"", Util.isValid(txtStockIdeal.getText(), 1, 7, 2),
				this);
		if (stockIdeal == 2) {
			return;
		} else if (stockIdeal == 3) {
			estaSeguro = true;
		}

		// Control de Descuento
		int descuento = Util.checkAll(txtDescuento, "\"Descuento\"", Util.isValid(txtDescuento.getText(), 1, 4, 2),
				this);
		if (descuento == 2) {
			return;
		}
		
		Producto prod = new Producto(txtCodigo.getText(), Double.parseDouble(txtPrecio.getText()), txtNombre.getText(),
				txtUbicacion.getText(), Integer.parseInt(txtStockMax.getText()),
				Integer.parseInt(txtStockMin.getText()), Integer.parseInt(txtStockIdeal.getText()),
				Integer.parseInt(txtStock.getText()), new BigDecimal(txtDescuento.getText()),
				tglbtnEliminado.isSelected(), new BigDecimal(txtPorcentajeIva.getText()), selectMarca);
		if (!alta) {
			prod.setIdProductos(selectProducto.getIdProductos());
		}

		// Subcategorias
		List<Subcategoria> subCatList = null;
		try {
			subCatList = subDAO.list();
		} catch (PersistenciaException e1) {
			e1.printStackTrace();
			return;
		}
		List<Subcategoria> returnSubCatList = new ArrayList<Subcategoria>();
		for (int i = 0; i < subTable.getRowCount(); i++) {
			boolean checked = Boolean.valueOf(subTable.getValueAt(i, 0).toString());
			if (checked) {
				returnSubCatList.add(subCatList.get(i));
			}
		}

		if(returnSubCatList.size() == 0) {
			JOptionPane.showMessageDialog(this, "Tiene que elegir al menos una subcategoria.", "Precaucion",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		prod.setSubcategoria(returnSubCatList);

		// Mayoristas
		List<Mayorista> mayList = null;
		try {
			mayList = mayDAO.list();
		} catch (PersistenciaException e1) {
			e1.printStackTrace();
			return;
		}
		List<Mayorista> returnMayList = new ArrayList<Mayorista>();
		for (int i = 0; i < mayTable.getRowCount(); i++) {
			boolean checked = Boolean.valueOf(mayTable.getValueAt(i, 0).toString());
			if (checked) {
				returnMayList.add(mayList.get(i));
			}
		}
		
		if(returnMayList.size() == 0) {
			JOptionPane.showMessageDialog(this, "Tiene que elegir al menos un Mayorista.", "Precaucion",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		prod.setMayoristas(returnMayList);

		// Insert / Update
		if (alta) {
			try {
				prodDAO.insert(prod);
			} catch (PersistenciaException e) {
				JOptionPane.showMessageDialog(this, "Error insertando el producto.", "Precaucion",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		} else {
			try {
				prodDAO.update(prod);
			} catch (PersistenciaException e) {
				JOptionPane.showMessageDialog(this, "Error modificando el producto.", "Precaucion",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}

	private void accion(JDialog dialog) {
		if (isAlta) {
			altaModifica(true, 0);
		} else {
			altaModifica(false, selectProducto.getIdProductos());
		}

		GenericDAO dao = (GenericDAO) prodDAO;
		try {
			superTable.setModel(new ModeloTabla(dao.list()));
		} catch (PersistenciaException e1) {
			e1.printStackTrace();
		}
		dialog.dispose();
	}

}
